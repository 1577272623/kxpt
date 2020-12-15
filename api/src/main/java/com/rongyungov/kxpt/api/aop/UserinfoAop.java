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
public class UserinfoAop {

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    MenuService menuService;

    @Around("execution(* com.rongyungov.framework.baseapi.controller.UserController.info(..))")
    public Result AfterLogin1(ProceedingJoinPoint joinPoint) throws Exception {
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        User user = new User();
        user.setAccount(account);
        user = userService.getOne(new QueryWrapper(user));
        UserVo userVo = new UserVo();
        ObjectUtils.fatherToChild(user, userVo);


        List<UserRole> userRoles = userRoleService.list((Wrapper)((QueryWrapper)(new QueryWrapper()).setEntity(new UserRole())).eq("user_id", user.getId()));
        List<Long> userRoleIds = new ArrayList();
        Iterator var8 = userRoles.iterator();

        while(var8.hasNext()) {
            UserRole userRole = (UserRole)var8.next();
            userRoleIds.add(userRole.getRoleId());
        }

        Role role = roleService.getById((Serializable)userRoleIds.get(0));
        if (role != null) {
            userVo.setRolename(role.getName());
        }
        List<RoleMenu> roleMenus =roleMenuService.list((Wrapper)((QueryWrapper)(new QueryWrapper()).setEntity(new RoleMenu())).in("role_id", userRoleIds));
        List<Long> roleMenuids = new ArrayList();
        Iterator var11 = roleMenus.iterator();

        while(var11.hasNext()) {
            RoleMenu userRole = (RoleMenu)var11.next();
            roleMenuids.add(userRole.getMenuId());
        }
        userVo.setMenus(MenuUtils.menuToRouterTree(MenuUtils.toNaviagationMenuTree(menuService, roleMenuids, UserUtils.isSuperAdmin((UserService)null, request))));
        userVo.setFunctions(MenuUtils.getFunctions(menuService, roleMenuids));
        Map<String, List<String>> formdetails = new HashMap();
        userVo.setFormdetails(formdetails);
        return Result.ok(userVo);

    }
}