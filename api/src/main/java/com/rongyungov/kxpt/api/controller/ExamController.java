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
    import com.rongyungov.kxpt.service.ExamService;
import  com.rongyungov.kxpt.entity.Exam;

/**
 *code is far away from bug with the animal protecting
 *   @description : Exam 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-11
 */
@RestController
@Api(value="/exam", description="Exam 控制器")
@RequestMapping("/exam")
public class ExamController extends BaseController<ExamService,Exam> {

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Exam> getExamList( @ApiParam(name="exam",value="筛选条件") @RequestBody(required = false) Exam exam  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) {
        Page<Exam> page=new Page<Exam>(pageIndex,pageSize);
        QueryWrapper<Exam> queryWrapper=new QueryWrapper<>(exam);
        return service.page(page,queryWrapper);
    }

    /**
     * @description : 通过id获取Exam
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Exam")
    public Exam getExamById(@PathVariable Long id) {
        Exam exam=service.getById(id);
        return exam;
    }

    /**
     * @description : 通过id删除Exam
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Exam")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Exam
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Exams")
    public Boolean delete(@RequestBody(required = false) List<Exam> selectexams) {
        Boolean success=false;
        if(selectexams!=null&&selectexams.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Exam exam:selectexams)
            ids.add(exam.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Exam
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Exam")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Exam exam) {
        if(id!=null)
            exam.setId(id);
        Boolean success=service.updateById(exam);
        return success;
    }

    /**
     * @description : 添加Exam
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Exam")
    public Boolean add(@RequestBody Exam  exam) {
        Boolean success=service.save( exam);
        return success;
	}

}
