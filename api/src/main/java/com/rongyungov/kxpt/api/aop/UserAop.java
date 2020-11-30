package com.rongyungov.kxpt.api.aop;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.baseapi.utils.MenuUtils;
import com.rongyungov.framework.baseapi.utils.ObjectUtils;
import com.rongyungov.framework.baseapi.utils.UserUtils;
import com.rongyungov.framework.entity.*;
import com.rongyungov.framework.service.*;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.framework.vo.UserVo;
import com.rongyungov.kxpt.utils.KxptConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.*;

@Component
@Aspect
public class UserAop {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    MenuService menuService;

    @Autowired
    RoleService roleService;



//    @Around("execution(* com.rongyungov.kxpt.api.controller.SysUserController.info(..))")
    public Result AfterLogin1(ProceedingJoinPoint joinPoint) throws Exception {
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        User user = new User();
        user.setAccount(account);
        user = userService.getOne(new QueryWrapper(user));
        UserVo userVo = new UserVo();
        ObjectUtils.fatherToChild(user, userVo);

        Role role = null;
        if(user.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_STUDENT)){
            role = roleService.getById(new QueryWrapper<Role>().eq("name", "学生默认角色"));
        }else if(user.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_TEACHER)){
            role = roleService.getById(new QueryWrapper<Role>().eq("name", "教师默认角色"));
        }else if(user.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_USER)){
            UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
            role = roleService.getById(userRole.getRoleId());
        }


        if (role != null) {
            userVo.setRolename(role.getName());
        }

        List<RoleMenu> roleMenus = roleMenuService.list(new QueryWrapper<RoleMenu>().eq("role_id", role.getId()));
        List<Long> roleMenuids = new ArrayList();
        Iterator var11 = roleMenus.iterator();

        while(var11.hasNext()) {
            RoleMenu userRole = (RoleMenu)var11.next();
            roleMenuids.add(userRole.getMenuId());
        }

        userVo.setMenus(MenuUtils.menuToRouterTree(MenuUtils.toNaviagationMenuTree(menuService, roleMenuids, UserUtils.isSuperAdmin((UserService)null, this.request))));
        userVo.setFunctions(MenuUtils.getFunctions(menuService, roleMenuids));
        Map<String, List<String>> formdetails = new HashMap();
        userVo.setFormdetails(formdetails);
        return Result.ok(userVo);
    }
}
