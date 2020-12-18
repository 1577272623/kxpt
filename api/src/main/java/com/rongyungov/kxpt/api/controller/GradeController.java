package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.kxpt.entity.*;
import com.rongyungov.kxpt.service.*;
import com.sun.org.apache.regexp.internal.RE;
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
import java.util.stream.Collectors;

import  com.rongyungov.framework.base.BaseController;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    GradeVoService gradeVoService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    ExamService examService;



    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-20
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<GradeVo> getGradeList( @ApiParam(name="grade",value="筛选条件") @RequestBody(required = false) Grade grade  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<GradeVo> page=new Page<GradeVo>(pageIndex,pageSize);
        QueryWrapper<GradeVo> queryWrapper=new QueryWrapper<>(new GradeVo());
        queryWrapper.orderByDesc("grade");
        if (grade.getDepartment() != null){
            queryWrapper.eq("department",grade.getDepartment());
        }
        if (grade.getType() != null){
            queryWrapper.eq("type",grade.getType());
        }
        if (grade.getExamId() != null){
            queryWrapper.eq("exam_id",grade.getExamId());
        }
        IPage<GradeVo> gradeIPage = gradeVoService.page(page,queryWrapper);
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
    public List<GradeVo> getNewGradeRenking(){
        String account = JwtUtil.getClaim(request.getHeader("Token"),"account");
        List<Department> departments = departmentService.list();
        QueryWrapper<Exam> examQueryWrapper = new QueryWrapper<>();
        examQueryWrapper.eq("tea_no",account).orderByDesc("created_time").last("limit 0, 1");
        Exam exam = examService.getOne(examQueryWrapper);//获取教师发布的最新的一个考试
        String exam_class = exam.getClassNo();
        String[] strings = exam_class.split(",");
        Map<String,List<Department>> teacher_deps = departments.stream().collect(Collectors.groupingBy(Department::getCreatedBy));
        Long dep_id = 0L;
        if (teacher_deps.containsKey(account)){

            for (Department department: teacher_deps.get(account)){
                for (int i=0; i<strings.length; i++){
                    if ( strings[i].equalsIgnoreCase(department.getId()+"")){
                        dep_id = department.getId();
                        QueryWrapper<GradeVo> gradeVoQueryWrapper = new QueryWrapper<>();
                        gradeVoQueryWrapper.eq("exam_id",exam.getId()).eq("department",dep_id).eq("type","1").orderByDesc("grade");
                        List<GradeVo> gradeVos = gradeVoService.list(gradeVoQueryWrapper);
                        if (gradeVos != null){
                            return gradeVos;
                        }

                    }
                }
            }
        }

        return null;
    }



}
