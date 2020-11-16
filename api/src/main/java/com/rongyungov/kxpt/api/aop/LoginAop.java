package com.rongyungov.kxpt.api.aop;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.baseapi.BaseapiApplication;
import com.rongyungov.framework.common.Constant;
import com.rongyungov.framework.entity.User;
import com.rongyungov.framework.redis.JedisUtil;
import com.rongyungov.framework.service.UserService;
import com.rongyungov.framework.shiro.exception.CustomUnauthorizedException;
import com.rongyungov.framework.shiro.util.AesCipherUtil;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.framework.shiro.util.common.JsonConvertUtil;
import com.rongyungov.framework.sms_service.entity.Message;
import com.rongyungov.kxpt.utils.CheckUtils;
import com.rongyungov.kxpt.utils.KxptConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
public class LoginAop {

    @Value("${refreshTokenExpireTime}")
    private String refreshTokenExpireTime;

    @Autowired
    private UserService userService;



    @Autowired
    HttpServletRequest request;
    @Autowired
    Message message;


//    @Around("execution(* com.rongyungov.kxpt.api.controller.Userlogin.StudentLogin(..))")
    @Around("execution(* com.rongyungov.framework.baseapi.controller.LoginController.login(..))")
    public Result AfterLogin1(ProceedingJoinPoint joinPoint) throws Exception {

        Map<String,Object> reMap = new HashMap<>();

        User user = (User) joinPoint.getArgs()[0];
//        Result res = (Result) joinPoint.proceed(); //调用目标方法
        //pointcut是对应的注解类   rvt就是方法运行完之后要返回的值
        User userDtoTemp = new User();

        String account = user.getAccount();


        
        //手机登录
        if (CheckUtils.isPhoneLegal(account)){
            userDtoTemp.setTelephone(account);
        }else {
            //学号登录
            userDtoTemp.setAccount(account);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper(userDtoTemp);
        userDtoTemp = userService.getOne(queryWrapper);
        user.setPassword(AesCipherUtil.encrypt(userDtoTemp.getAccount() + user.getPassword()));


        if (userDtoTemp == null) {
            throw new CustomUnauthorizedException("该帐号不存在");
        } else if (!userDtoTemp.getPassword().equalsIgnoreCase(user.getPassword())) {
            throw new CustomUnauthorizedException("输入账号密码错误");
        } else {
            if (JedisUtil.exists("shiro:login:" + user.getAccount())) {
                JedisUtil.delKey("shiro:login:" + user.getAccount());
            }
            String currentTimeMillis = "";
            if (JedisUtil.exists("shiro:refresh_token:" + user.getAccount())) {
                currentTimeMillis = (String)JedisUtil.getObject("shiro:refresh_token:" + user.getAccount());
            } else {
                currentTimeMillis = String.valueOf(System.currentTimeMillis());
                JedisUtil.setObject("shiro:refresh_token:" + user.getAccount(), currentTimeMillis, Integer.parseInt(this.refreshTokenExpireTime));
            }
            String token = JwtUtil.sign(userDtoTemp, currentTimeMillis);
            BaseapiApplication.saveLoginUserIdsAndCompanyIds(userDtoTemp.getAccount(), userDtoTemp.getId(), userDtoTemp.getCompanyId());
            JedisUtil.setObject("shiro:login:" + user.getAccount(), userDtoTemp);

            reMap.put("token",token);

            //教师
            if(userDtoTemp.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_TEACHER)){
                reMap.put("userType",KxptConstant.USER_TYPE_TEACHER);

            }else if(userDtoTemp.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_STUDENT)){
                //学员
                reMap.put("userType",KxptConstant.USER_TYPE_STUDENT);
            }else {
                //管理员
                reMap.put("userType",KxptConstant.USER_TYPE_USER);
            }

            return Result.ok(reMap);
        }
    }


}
