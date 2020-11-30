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
import com.rongyungov.kxpt.entity.Student;
import com.rongyungov.kxpt.entity.Teacher;
import com.rongyungov.kxpt.service.StudentService;
import com.rongyungov.kxpt.service.TeacherService;
import com.rongyungov.kxpt.utils.CheckUtils;
import com.rongyungov.kxpt.utils.KxptConstant;
import org.apache.poi.ss.formula.functions.T;
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
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

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
        User userDtoTTest = new User();
        String account = "";
        String re_token = user.getAccount();
        //管理员 将 登录账户 转换为 学号  或者教师号
        if(user.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_USER)){
            // 账户 默认  用户类型 管理员
            account = user.getAccount();
            userDtoTTest.setUserTypeCode(KxptConstant.USER_TYPE_USER);
        }else if(user.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_TEACHER)){
            //教师
            // 账户 默认教师编号  用户类型 教师
            if (CheckUtils.isPhoneLegal(user.getAccount())){
                Teacher teacher = new Teacher();
                teacher.setTel(user.getAccount());
                Teacher findTeacher =teacherService.getOne(new QueryWrapper<>(teacher));
                //没有查询出对应的用户
                re_token = findTeacher.getTeano();
                if(findTeacher == null){
                    throw new CustomUnauthorizedException("该帐号不存在");
                }
                //将手机号 与 登录密码分离D
                String password = AesCipherUtil.desEncrypt(user.getPassword()).replace(user.getAccount(),"");
                //将老师的编号  与 分离密码 加密
                String newpassword = AesCipherUtil.encrypt(findTeacher.getTeano()+password);
                //替换登录的 密码
                user.setPassword(newpassword);

                account = findTeacher.getTeano();
            }else{
                account = user.getAccount();
            }
            userDtoTTest.setUserTypeCode(KxptConstant.USER_TYPE_TEACHER);
        }else{
            //学员
            if (CheckUtils.isPhoneLegal(user.getAccount())){
                Student student = new Student();
                student.setTel(user.getAccount());
                Student findStudent =studentService.getOne(new QueryWrapper<>(student));
                //没有查询出对应的用户
                re_token = findStudent.getNo();
                if(findStudent == null){
                    throw new CustomUnauthorizedException("该帐号不存在");
                }
                //将手机号 与 登录密码分离D
                String password = AesCipherUtil.desEncrypt(user.getPassword()).replace(user.getAccount(),"");
                //将学生的编号  与 分离密码 加密
                String newpassword = AesCipherUtil.encrypt(findStudent.getNo()+password);
                //替换登录的 密码
                user.setPassword(newpassword);

                account = findStudent.getNo();
            }else{
//                String password = AesCipherUtil.desEncrypt(user.getPassword()).replace(user.getAccount(),"");

                account = user.getAccount();
            }
            userDtoTTest.setUserTypeCode(KxptConstant.USER_TYPE_STUDENT);
            }
        userDtoTTest.setAccount(account);
        QueryWrapper<User> queryWrapper = new QueryWrapper(userDtoTTest);
        userDtoTTest = userService.getOne(queryWrapper);


        //手机登录
//        if (CheckUtils.isPhoneLegal(account)){
//            userDtoTTest.setTelephone(account);
//        }else {
//            //学号登录
//            userDtoTTest.setAccount(account);
//        }
//        QueryWrapper<User> queryWrapper = new QueryWrapper(userDtoTTest);
//        userDtoTTest = userService.getOne(queryWrapper);
//        user.setPassword(AesCipherUtil.encrypt(userDtoTTest.getPassword()));


        if (userDtoTTest == null) {
            throw new CustomUnauthorizedException("该帐号不存在");
        } else if (!userDtoTTest.getIsAccessLogin().equalsIgnoreCase("1")){
            throw new CustomUnauthorizedException("该账号不可用");
        }else if (!userDtoTTest.getPassword().equalsIgnoreCase(user.getPassword())) {
            throw new CustomUnauthorizedException("输入账号密码错误");
        } else {
            if (JedisUtil.exists("shiro:login:" + re_token)) {
                JedisUtil.delKey("shiro:login:" + re_token);
            }
            String currentTimeMillis = "";

            if (JedisUtil.exists("shiro:refresh_token:" + re_token)) {
                currentTimeMillis = (String)JedisUtil.getObject("shiro:refresh_token:" + re_token);
            } else {
                currentTimeMillis = String.valueOf(System.currentTimeMillis());
                JedisUtil.setObject("shiro:refresh_token:" + re_token, currentTimeMillis, Integer.parseInt(this.refreshTokenExpireTime));
            }
            String token = JwtUtil.sign(userDtoTTest, currentTimeMillis);
            BaseapiApplication.saveLoginUserIdsAndCompanyIds(userDtoTTest.getAccount(), userDtoTTest.getId(), userDtoTTest.getCompanyId());
            JedisUtil.setObject("shiro:login:" + re_token, userDtoTTest);

            reMap.put("token",token);

            //教师
            if(userDtoTTest.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_TEACHER)){
                reMap.put("userType",KxptConstant.USER_TYPE_TEACHER);

            }else if(userDtoTTest.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_STUDENT)){
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
