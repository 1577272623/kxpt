package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.baseapi.utils.UserCompanyUtils;
import com.rongyungov.framework.baseapi.utils.UserUtils;
import com.rongyungov.framework.entity.User;
import com.rongyungov.framework.service.UserService;
import com.rongyungov.framework.shiro.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
                import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.CourseListService;
import  com.rongyungov.kxpt.entity.CourseList;

/**
 *code is far away from bug with the animal protecting
 *   @description : CourseList 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-11
 */
@RestController
@Api(value="/courseList", description="CourseList 控制器")
@RequestMapping("/courseList")
public class CourseListController extends BaseController<CourseListService,CourseList> {

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<CourseList> getCourseListList( @ApiParam(name="courseList",value="筛选条件") @RequestBody(required = false) CourseList courseList  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) {
        Page<CourseList> page=new Page<CourseList>(pageIndex,pageSize);
        QueryWrapper<CourseList> queryWrapper=new QueryWrapper<>(courseList);
        return service.page(page,queryWrapper);
    }

    /**
     * @description : 分级获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-16
     */
    @PostMapping("/listAll")
    @ApiOperation(value = "获取课程目录数据信息")
    public List<CourseList> getAllCourseListList() {
        QueryWrapper<CourseList> queryWrapper = new QueryWrapper();
        List<CourseList> courseLists = ((CourseListService)this.service).list(queryWrapper);

//        for (CourseList courseList:courseLists){
//            courseList
//        }
        return service.list(queryWrapper);
    }

    /**
     * @description : 通过id获取CourseList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取CourseList")
    public CourseList getCourseListById(@PathVariable Long id) {
        CourseList courseList=service.getById(id);
        return courseList;
    }

    /**
     * @description : 通过id删除CourseList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除CourseList")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除CourseList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除CourseLists")
    public Boolean delete(@RequestBody(required = false) List<CourseList> selectcourseLists) {
        Boolean success=false;
        if(selectcourseLists!=null&&selectcourseLists.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (CourseList courseList:selectcourseLists)
            ids.add(courseList.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新CourseList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新CourseList")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody CourseList courseList) {
        if(id!=null)
            courseList.setId(id);
        Boolean success=service.updateById(courseList);
        return success;
    }

    /**
     * @description : 添加CourseList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
	@PostMapping("/add")
    @ApiOperation(value="添加CourseList")
    public Boolean add(@RequestBody CourseList  courseList) {
        Boolean success=service.save( courseList);
        return success;
	}



}
