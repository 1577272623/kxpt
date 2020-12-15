package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.kxpt.entity.*;
import com.rongyungov.kxpt.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.GradeService;

/**
 *code is far away from bug with the animal protecting
 *   @description : Grade 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-20
 */
@RestController
@Api(value="/grade", description="Grade 控制器")
@RequestMapping("/grade")
public class GradeController extends BaseController<GradeService,Grade> {

    @Autowired
    StudentService studentService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Grade> getGradeList( @ApiParam(name="grade",value="筛选条件") @RequestBody(required = false) Grade grade  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<Grade> page=new Page<Grade>(pageIndex,pageSize);
        QueryWrapper<Grade> queryWrapper=new QueryWrapper<>(new Grade());
        queryWrapper.orderByDesc("grade");
        if (grade.getDepartment() != null){
            queryWrapper.eq("dapartment",grade.getDepartment());
        }

        if (grade.getType() != null){
            queryWrapper.eq("type",grade.getType());
        }
        if (grade.getExamId() != null){
            queryWrapper.eq("exam_id",grade.getExamId());
        }
        IPage<Grade> gradeIPage = service.page(page,queryWrapper);
        List<Student> students = studentService.list(new QueryWrapper<Student>().eq("classno",grade.getDepartment()));
        for (int j =0; j<gradeIPage.getRecords().size(); j++){
            for (Student student: students){
                if (String.valueOf(student.getId()).equals(gradeIPage.getRecords().get(j).getStudent())){
                    gradeIPage.getRecords().get(j).setStudent(student.getName());
                }
            }

        }
        return gradeIPage;
    }



    /**
     * @description : 通过id获取Grade
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Grade")
    public Grade getGradeById(@PathVariable Long id) {
        Grade grade=service.getById(id);
        return grade;
    }

    /**
     * @description : 通过id删除Grade
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Grade")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Grade
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Grades")
    public Boolean delete(@RequestBody(required = false) List<Grade> selectgrades) {
        Boolean success=false;
        if(selectgrades!=null&&selectgrades.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Grade grade:selectgrades)
            ids.add(grade.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Grade
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Grade")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Grade grade) {
        if(id!=null)
            grade.setId(id);
        Boolean success=service.updateById(grade);
        return success;
    }

    /**
     * @description : 添加Grade
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Grade")
    public Boolean add(@RequestBody Grade  grade) {
        Boolean success=service.save( grade);
        return success;
	}

    /**
     * @description : 获取任务完成度
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/getGradeRenking")
    @ApiOperation(value = "教师首页获取成绩排名")
    public Map<String,Object> getNewGradeRenking(@ApiParam(name="teacher",value="筛选条件") @RequestBody(required = false) Teacher teacher ){

        return null;
    }



}
