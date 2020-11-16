package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
                import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.SysUserService;
import  com.rongyungov.kxpt.entity.SysUser;

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
                                ) {
        Page<SysUser> page=new Page<SysUser>(pageIndex,pageSize);
        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>(sysUser);
        return service.page(page,queryWrapper);
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
    public Boolean add(@RequestBody SysUser  sysUser) {
        Boolean success=service.save( sysUser);
        return success;
	}

}
