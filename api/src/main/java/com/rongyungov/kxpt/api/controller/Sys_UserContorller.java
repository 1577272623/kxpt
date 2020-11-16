/*
package com.rongyungov.kxpt.api.controller;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.base.BaseController;
import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.baseapi.utils.MenuUtils;
import com.rongyungov.framework.baseapi.utils.ObjectUtils;
import com.rongyungov.framework.baseapi.utils.UserCompanyUtils;
import com.rongyungov.framework.baseapi.utils.UserUtils;
import com.rongyungov.framework.entity.Organization;
import com.rongyungov.framework.entity.Role;
import com.rongyungov.framework.entity.RoleMenu;
import com.rongyungov.framework.entity.User;
import com.rongyungov.framework.entity.UserRole;
import com.rongyungov.framework.service.MenuService;
import com.rongyungov.framework.service.OrganizationService;
import com.rongyungov.framework.service.RoleMenuService;
import com.rongyungov.framework.service.RoleService;
import com.rongyungov.framework.service.UserRoleService;
import com.rongyungov.framework.service.UserService;
import com.rongyungov.framework.shiro.util.AesCipherUtil;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.framework.vo.UserVo;
import com.rongyungov.kxpt.entity.Student;
import com.rongyungov.kxpt.service.StudentService;
import com.rongyungov.kxpt.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(
        value = "/sys_user",
        description = "Sys_User 控制器"
)
@RequestMapping({"/Sys_user"})
public class Sys_UserContorller extends BaseController<UserService, User> {
    @Autowired
    HttpServletRequest request;
    @Autowired
    MenuService menuService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    OrganizationService organizationService;
    @Value("${spring.servlet.multipart.location}")
    private String uploadLocation;
    @Autowired
    RoleService roleService;
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    UserService userService;

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    public Sys_UserContorller() {
    }

    @PostMapping({"/list"})
    @ApiOperation("获取分页数据信息")
    public IPage<User> getUserList(@ApiParam(name = "user",value = "筛选条件") @RequestBody(required = false) User user, @ApiParam(name = "pageIndex",value = "页数",required = true,defaultValue = "1") @RequestParam Integer pageIndex, @ApiParam(name = "pageSize",value = "页大小",required = true,defaultValue = "10") @RequestParam Integer pageSize) throws InstantiationException, IllegalAccessException {
        Page<User> page = new Page((long)pageIndex, (long)pageSize);
        QueryWrapper<User> queryWrapper = user.toWrapper(user);
        IPage<User> userPages = ((UserService)this.service).page(page, queryWrapper);
        Iterator var7 = userPages.getRecords().iterator();

        while(var7.hasNext()) {
            User item = (User)var7.next();
            UserRole userRole = (UserRole)this.userRoleService.getOne((Wrapper)(new QueryWrapper()).eq("user_id", item.getId()));
            if (userRole != null) {
                item.setRoleid(userRole.getRoleId());
            }

            Organization org = (Organization)this.organizationService.getById(item.getOrgid());
            if (org != null) {
                item.setOrgName(org.getOrgName());
            }

            org = (Organization)this.organizationService.getById(item.getCompanyId());
            if (org != null) {
                item.setCompanyName(org.getOrgName());
            }
        }

        return userPages;
    }

    @PostMapping({"/listAll"})
    @ApiOperation("获取所有用户信息")
    public List<User> getAllUser() {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        String account = JwtUtil.getClaim(this.request.getHeader("Token"), "account");
        if (UserUtils.isSuperAdmin((UserService)null, this.request)) {
            return ((UserService)this.service).list(queryWrapper);
        } else {
            Long companyid = UserCompanyUtils.getCompanyId((UserService)this.service, account);
            return ((UserService)this.service).list((Wrapper)(new QueryWrapper()).eq("company_id", companyid));
        }
    }

    @PostMapping({"/getUsersByIds"})
    @ApiOperation("通过ids获取Users")
    public List<User> getUsersByIds(@RequestBody(required = false) List<Long> ids) throws Exception {
        List<User> users = ((UserService)this.service).list((Wrapper)(new QueryWrapper()).in("id", ids));
        return users;
    }

    @GetMapping({"/get/{id}"})
    @ApiOperation("通过id获取User")
    public User getUserById(@PathVariable Integer id) {
        User user = (User)((UserService)this.service).getById(id);
        return user;
    }

    @DeleteMapping({"/delete/{id}"})
    @ApiOperation("通过id删除User")
    public Boolean delete(@PathVariable Integer id) {
        User user = userService.getById(id);
        Boolean success = false;
        //管理员
        if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("1")){
             success = userService.removeById(id);
            //学生
        }else if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("2")){
             success = studentService.removeById(id);
            //教师
        }else if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("3")){
             success = teacherService.removeById(id);
        }else {
            throw new RuntimeException("用户错误");
        }

        return success;
    }

    @PostMapping({"/deletes"})
    @ApiOperation("通过ids删除Users")
    public Boolean delete(@RequestBody(required = false) List<User> selectusers) {

        Boolean success = false;
        if (selectusers != null && selectusers.size() != 0) {
            List<Long> ids = new ArrayList();
            Iterator var4 = selectusers.iterator();

            while(var4.hasNext()) {
                User user1 = (User)var4.next();
                ids.add(user1.getId());
            }
            List<User> userlist = (List<User>) ((UserService)this.service).listByIds(ids);
            for(User user:userlist){

                Long id=user.getId();
                if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("1")){
                    success = ((UserService)this.service).removeById(id);
                    //学生
                }else if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("2")){
                    success = studentService.removeById(id);
                    //教师
                }else if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("3")){
                    success = teacherService.removeById(id);
                }else {
                    throw new RuntimeException("用户错误");
                }
            }
//            success = ((UserService)this.service).removeByIds(ids);
        }

        return success;
    }

    @PutMapping({"/update"})
    @ApiOperation("修改用户信息(带密码)")
    @Transactional
    public Boolean update(@RequestBody @Validated User user) throws Exception {
        User user2 = (User)this.userService.getById(user.getId());
        Boolean success =false;
        if (!user2.getPassword().equals(user.getPassword())) {
            user.setPassword(AesCipherUtil.encrypt(user.getAccount() + user.getPassword()));
        }
        //管理员
        if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("1")){
            success = ((UserService)this.service).updateById(user);
            //学生
        }*/
/*else if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("2")){
            Student student = new Student(user.getId(),user.);
            success = studentService.updateById(user);
            //教师
        }else if((!user.getUserTypeCode().equals("")) && user.getUserTypeCode().equals("3")){
            success = teacherService.updateById(user);
        }else {
            throw new RuntimeException("用户错误");
        }*//*

//         success = ((UserService)this.service).updateById(user);
        if (user.getRoleid() != null) {
            UserRole userRole = (UserRole)this.userRoleService.getOne((Wrapper)(new QueryWrapper()).eq("user_id", user.getId()));
            if (user.getRoleid() != userRole.getRoleId()) {
                userRole.setRoleId(user.getRoleid());
                this.userRoleService.updateById(userRole);
            }
        }

        return success;
    }

    @PutMapping({"/updateInfo"})
    @ApiOperation("修改用户信息")
    @Transactional
    public Boolean updateInfo(@RequestBody @Validated User user) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.eq("account", user.getAccount());
        queryWrapper.ne("id", user.getId());
        if (this.userService.getOne(queryWrapper) != null) {
            throw new Exception("账号已存在,请重新输入！");
        } else {
            Boolean success = ((UserService)this.service).updateById(user);
            if (user.getRoleid() != null) {
                UserRole userRole = (UserRole)this.userRoleService.getOne((Wrapper)(new QueryWrapper()).eq("user_id", user.getId()));
                if (user.getRoleid() != userRole.getRoleId()) {
                    userRole.setRoleId(user.getRoleid());
                    this.userRoleService.updateById(userRole);
                }
            }

            return success;
        }
    }

    @PutMapping({"/updatePassword"})
    @ApiOperation("修改用户密码")
    @Transactional
    public Boolean updatePassword(@RequestBody @Validated User user) throws Exception {
        User user2 = (User)this.userService.getById(user.getId());
        String ordpass = AesCipherUtil.encrypt(user.getAccount() + user.getOrdPass());
        if (!user2.getPassword().equals(ordpass)) {
            throw new Exception("原密码有误");
        } else {
            if (user.getPassword() != null && !user2.getPassword().equals(user.getPassword())) {
                user.setPassword(AesCipherUtil.encrypt(user.getAccount() + user.getPassword()));
            }

            Boolean success = ((UserService)this.service).updateById(user);
            return success;
        }
    }

    @PostMapping({"/add"})
    @ApiOperation("添加User")
    @Transactional
    public Boolean add(@RequestBody @Validated User user) throws Exception {
        user.setPassword(AesCipherUtil.encrypt(user.getAccount() + user.getPassword()));
        Boolean success = ((UserService)this.service).save(user);
        if (user.getRoleid() != null) {
            this.userRoleService.save(new UserRole(user.getId(), user.getRoleid()));
        }

        return success;
    }

    @PutMapping({"/resetpwd"})
    @ApiOperation("重置默认密码：123456")
    public Boolean resetPwd(@RequestBody User user) throws Exception {
        user.setPassword(AesCipherUtil.encrypt(user.getAccount() + "123456"));
        Boolean success = ((UserService)this.service).updateById(user);
        return success;
    }

    @PostMapping({"/testdelete"})
    @ApiOperation("重置默认密码：123456")
    public Boolean delete() {
        return this.userRoleService.removeById(1);
    }

    @PostMapping({"/checkPassword"})
    @ApiOperation("验证密码")
    public Boolean checkPassword(@RequestBody User user) throws Exception {
        User userDao = (User)this.userService.getById(user.getId());
        if (userDao != null && userDao.getPassword().equalsIgnoreCase(user.getPassword())) {
            return true;
        } else {
            throw new Exception("用户密码信息有误，请重新输入");
        }
    }

    @GetMapping({"/info"})
    @ApiOperation("获取用户信息")
    public Result info() throws Exception {
        String account = JwtUtil.getClaim(this.request.getHeader("Token"), "account");
        User user = new User();
        user.setAccount(account);
        user = (User)this.userService.getOne(new QueryWrapper(user));
        UserVo userVo = new UserVo();
        ObjectUtils.fatherToChild(user, userVo);
        Organization company = (Organization)this.organizationService.getById(userVo.getCompanyId());
        if (company != null) {
            userVo.setCompanyName(company.getOrgName());
        }

        Organization depart = (Organization)this.organizationService.getById(userVo.getOrgid());
        if (depart != null) {
            userVo.setOrgName(depart.getOrgName());
        }

        List<UserRole> userRoles = this.userRoleService.list((Wrapper)((QueryWrapper)(new QueryWrapper()).setEntity(new UserRole())).eq("user_id", user.getId()));
        List<Long> userRoleIds = new ArrayList();
        Iterator var8 = userRoles.iterator();

        while(var8.hasNext()) {
            UserRole userRole = (UserRole)var8.next();
            userRoleIds.add(userRole.getRoleId());
        }

        Role role = (Role)this.roleService.getById((Serializable)userRoleIds.get(0));
        if (role != null) {
            userVo.setRolename(role.getName());
        }

        List<RoleMenu> roleMenus = this.roleMenuService.list((Wrapper)((QueryWrapper)(new QueryWrapper()).setEntity(new RoleMenu())).in("role_id", userRoleIds));
        List<Long> roleMenuids = new ArrayList();
        Iterator var11 = roleMenus.iterator();

        while(var11.hasNext()) {
            RoleMenu userRole = (RoleMenu)var11.next();
            roleMenuids.add(userRole.getMenuId());
        }

        userVo.setMenus(MenuUtils.menuToRouterTree(MenuUtils.toNaviagationMenuTree(this.menuService, roleMenuids, UserUtils.isSuperAdmin((UserService)null, this.request))));
        userVo.setFunctions(MenuUtils.getFunctions(this.menuService, roleMenuids));
        Map<String, List<String>> formdetails = new HashMap();
        userVo.setFormdetails(formdetails);
        return Result.ok(userVo);
    }
}

*/
