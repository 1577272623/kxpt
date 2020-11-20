package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.entity.User;
import com.rongyungov.framework.shiro.util.AesCipherUtil;
import com.rongyungov.kxpt.entity.Student;
import com.rongyungov.kxpt.entity.SysUser;
import com.rongyungov.kxpt.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
                import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.TeacherService;
import  com.rongyungov.kxpt.entity.Teacher;

/**
 *code is far away from bug with the animal protecting
 *   @description : Teacher 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-11
 */
@RestController
@Api(value="/teacher", description="Teacher 控制器")
@RequestMapping("/teacher")
public class TeacherController extends BaseController<TeacherService,Teacher> {

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Teacher> getTeacherList( @ApiParam(name="teacher",value="筛选条件") @RequestBody(required = false) Teacher teacher  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<Teacher> page=new Page<Teacher>(pageIndex,pageSize);
        QueryWrapper<Teacher> queryWrapper=teacher.toWrapper(teacher);
        IPage<Teacher> TeacherIPage = service.page(page,queryWrapper);

        return TeacherIPage;
    }

    /**
     * @description : 通过id获取Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Teacher")
    public Teacher getTeacherById(@PathVariable Long id) {
        Teacher teacher=service.getById(id);
        return teacher;
    }

    /**
     * @description : 通过id删除Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Teacher")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Teachers")
    public Boolean delete(@RequestBody(required = false) List<Teacher> selectteachers) {
        Boolean success=false;
        if(selectteachers!=null&&selectteachers.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Teacher teacher:selectteachers)
            ids.add(teacher.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Teacher")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Teacher teacher) {
        if(id!=null)
            teacher.setId(id);
        Boolean success=service.updateById(teacher);
        return success;
    }

    /**
     * @description : 添加Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Teacher")
    public Boolean add(@RequestBody Teacher  teacher) throws Exception {
	    Teacher teacher1 = new Teacher();
	    teacher1.setTeano(teacher.getTeano());
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper(teacher1);
        Teacher tt = service.getOne(queryWrapper);
        if (tt !=null){
            throw new RuntimeException("教师已存在,不可重复");
        }
        teacher.setPassword(AesCipherUtil.encrypt(teacher.getTeano() + teacher.getPassword()));
        Boolean success = ((TeacherService)this.service).save(teacher);
        return success;
	}

}
