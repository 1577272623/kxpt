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
    import com.rongyungov.kxpt.service.GradeService;
import  com.rongyungov.kxpt.entity.Grade;

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
        QueryWrapper<Grade> queryWrapper=grade.toWrapper(grade);
        IPage<Grade> gradeIPage = service.page(page,queryWrapper);
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

}
