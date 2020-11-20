package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.kxpt.Vo.courseListVo;
import com.rongyungov.kxpt.entity.CourseList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.DepartmentService;
import  com.rongyungov.kxpt.entity.Department;

/**
 *code is far away from bug with the animal protecting
 *   @description : Department 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-20
 */
@RestController
@Api(value="/department", description="Department 控制器")
@RequestMapping("/department")
public class DepartmentController extends BaseController<DepartmentService,Department> {

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Department> getDepartmentList( @ApiParam(name="department",value="筛选条件") @RequestBody(required = false) Department department  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<Department> page=new Page<Department>(pageIndex,pageSize);
        QueryWrapper<Department> queryWrapper=department.toWrapper(department);
        IPage<Department> departmentIPage = service.page(page,queryWrapper);
        return departmentIPage;
    }


/*
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public List<courseListVo> getAllCourseListList(@ApiParam(name="courseList",value="筛选条件") @RequestBody(required = false) CourseList courseList) {
        List<Department> routerTree = new ArrayList();
        List<Department> children = new ArrayList<>();
        QueryWrapper<Department> queryWrapper = new QueryWrapper(courseList);
        List<Department> departments = service.list(queryWrapper);
        for (Department department:departments){
            //父级
            if (department.getParentid().equalsIgnoreCase("0")){
                courseListVo courseListVo = new courseListVo(courseList1.getName(),
                        courseList1.getPath(),courseList1.getSort());
                Long id = courseList1.getId();
                for (CourseList courseList2:courseLists){
                    //对应子级
                    if (courseList2.getParentid().equalsIgnoreCase(String.valueOf(id))){
//                        List<CourseList> sortList = courseLists.stream().sorted((a, b) -> a.getId() - b.getId()).collect(Collectors.toList());
                        children.add(courseList2);
                    }
                }
                //子级排序
                List<CourseList> children2 = children.stream().sorted((a, b) -> a.getSort() - b.getSort()).collect(Collectors.toList());
                courseListVo.setChildren(children2);
                routerTree.add(courseListVo);
            }
        }
        //父级排序
        List<courseListVo> routerTree2 = routerTree.stream().sorted((a, b) -> a.getSort() - b.getSort()).collect(Collectors.toList());
        return routerTree2;
    }
*/

    /**
     * @description : 通过id获取Department
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Department")
    public Department getDepartmentById(@PathVariable Long id) {
        Department department=service.getById(id);
        return department;
    }

    /**
     * @description : 通过id删除Department
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Department")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Department
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Departments")
    public Boolean delete(@RequestBody(required = false) List<Department> selectdepartments) {
        Boolean success=false;
        if(selectdepartments!=null&&selectdepartments.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Department department:selectdepartments)
            ids.add(department.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Department
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Department")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Department department) {
        if(id!=null)
            department.setId(id);
        Boolean success=service.updateById(department);
        return success;
    }

    /**
     * @description : 添加Department
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Department")
    public Boolean add(@RequestBody Department  department) {
        Boolean success=service.save( department);
        return success;
	}

}
