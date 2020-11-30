package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.baseapi.controller.UserController;
import com.rongyungov.framework.baseapi.utils.MenuUtils;
import com.rongyungov.framework.baseapi.utils.ObjectUtils;
import com.rongyungov.framework.baseapi.utils.UserUtils;
import com.rongyungov.framework.entity.*;
import com.rongyungov.framework.service.*;
import com.rongyungov.framework.shiro.exception.CustomUnauthorizedException;
import com.rongyungov.framework.shiro.util.AesCipherUtil;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.framework.vo.UserVo;
import com.rongyungov.kxpt.entity.Teacher;
import com.rongyungov.kxpt.utils.CheckUtils;
import com.rongyungov.kxpt.utils.KxptConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.*;

import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.SysUserService;
import  com.rongyungov.kxpt.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 *code is far away from bug with the animal protecting
 *   @description : SysUser 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-16
 */
@RestController
@Api(value="/sysUser", description="SysUser 控制器")
@RequestMapping("/sysUser")
public class SysUserController extends BaseController<SysUserService,SysUser> {
    @Autowired
    UserRoleService userRoleService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    RoleMenuService roleMenuService;

    @Autowired
    MenuService menuService;
//UserController

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-16
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<SysUser> getSysUserList( @ApiParam(name="sysUser",value="筛选条件") @RequestBody(required = false) SysUser sysUser  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<SysUser> page=new Page<SysUser>(pageIndex,pageSize);
        QueryWrapper<SysUser> queryWrapper=sysUser.toWrapper(sysUser);
        IPage<SysUser> sysUserIPage = service.page(page,queryWrapper);
        List<SysUser> sysUserList = sysUserIPage.getRecords();

        for(SysUser item:sysUserList) {
            UserRole userRole = (UserRole)this.userRoleService.getOne((Wrapper)(new QueryWrapper()).eq("user_id", item.getId()));
            if (userRole != null) {
                item.setRoleid(userRole.getRoleId());
            }
        }
        return sysUserIPage;
    }

    /**
     * @description : 通过id获取SysUser
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-16
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取SysUser")
    public SysUser getSysUserById(@PathVariable Long id) {
        SysUser sysUser=service.getById(id);
        return sysUser;
    }

    /**
     * @description : 通过id删除SysUser
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-16
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除SysUser")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除SysUser
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-16
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除SysUsers")
    public Boolean delete(@RequestBody(required = false) List<SysUser> selectsysUsers) {
        Boolean success=false;
        if(selectsysUsers!=null&&selectsysUsers.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (SysUser sysUser:selectsysUsers)
            ids.add(sysUser.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新SysUser
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-16
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新SysUser")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody SysUser sysUser) {
        if(id!=null)
            sysUser.setId(id);
        Boolean success=service.updateById(sysUser);
        return success;
    }

    /**
     * @description : 添加SysUser
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-16
     */
	@PostMapping("/add")
    @ApiOperation(value="添加SysUser")
    public Boolean add(@RequestBody SysUser  sysUser) throws Exception {
        sysUser.setUserTypeCode(KxptConstant.USER_TYPE_USER);
        sysUser.setPassword(AesCipherUtil.encrypt(sysUser.getAccount() + sysUser.getPassword()));
        Boolean success=service.save(sysUser);
        if (sysUser.getRoleid() != null) {
            userRoleService.save(new UserRole(sysUser.getId(), sysUser.getRoleid()));
        }
        return success;
	}

    @PutMapping({"/updateInfo"})
    @ApiOperation("修改管理员用户信息")
    @Transactional
    public Boolean updateInfo(@RequestBody @Validated SysUser user) throws Exception {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", user.getAccount());
        queryWrapper.ne("id", user.getId());
        if (service.getOne(queryWrapper) != null) {
            throw new Exception("账号已存在,请重新输入！");
        } else {
            Boolean success = service.updateById(user);
            if (user.getRoleid() != null) {
                UserRole userRole = userRoleService.getOne(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
                if (user.getRoleid() != userRole.getRoleId()) {
                    userRole.setRoleId(user.getRoleid());
                    userRoleService.updateById(userRole);
                }
            }

            return success;
        }
    }

    @GetMapping({"/info"})
    @ApiOperation("获取用户信息")
    public Result info() throws Exception {
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        User user = new User();
        user.setAccount(account);
        user = userService.getOne(new QueryWrapper(user));
        UserVo userVo = new UserVo();
        ObjectUtils.fatherToChild(user, userVo);

        //管理员获取角色
        Role role = null;
        if(user.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_USER)){
            List<UserRole> userRoles = userRoleService.list(new QueryWrapper<UserRole>().eq("user_id", user.getId()));
            List<Long> userRoleIds = new ArrayList();
            Iterator var8 = userRoles.iterator();

            while(var8.hasNext()) {
                UserRole userRole = (UserRole)var8.next();
                userRoleIds.add(userRole.getRoleId());
            }

            role = roleService.getById((Serializable)userRoleIds.get(0));
        }else if(user.getUserTypeCode().equalsIgnoreCase(KxptConstant.USER_TYPE_TEACHER)){
            //教师 获取角色
            QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq("name",KxptConstant.TEACHER_DEFAULT_ROLE_NAME);
            role = roleService.getOne(roleQueryWrapper);
        }else{
            //学生 获取角色
            QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq("name",KxptConstant.STUDENT_DEFAULT_ROLE_NAME);
            role = roleService.getOne(roleQueryWrapper);
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

        userVo.setMenus(MenuUtils.menuToRouterTree(MenuUtils.toNaviagationMenuTree(this.menuService, roleMenuids, UserUtils.isSuperAdmin((UserService)null, this.request))));
        userVo.setFunctions(MenuUtils.getFunctions(menuService, roleMenuids));
        Map<String, List<String>> formdetails = new HashMap();
        userVo.setFormdetails(formdetails);
        return Result.ok(userVo);
    }

}
