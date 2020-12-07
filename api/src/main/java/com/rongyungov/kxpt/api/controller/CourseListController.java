package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

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
                                ) throws InstantiationException, IllegalAccessException {
        Page<CourseList> page=new Page<CourseList>(pageIndex,pageSize);
        QueryWrapper<CourseList> queryWrapper=courseList.toWrapper(courseList);
        IPage<CourseList> courseListIPage = service.page(page,queryWrapper);
        return courseListIPage;
    }

    @PostMapping("/listAll")
    @ApiOperation(value = "获取课程目录数据信息")
    public List<CourseList> getAllCourseListList(@ApiParam(name="courseList",value="筛选条件") @RequestBody(required = false) CourseList courseList) {
        QueryWrapper<CourseList> queryWrapper = new QueryWrapper(courseList);
        List<CourseList> courseLists = service.list(queryWrapper);
           Map<String, List<CourseList>> groupBy = courseLists.stream().collect(Collectors.groupingBy(CourseList::getParentid));
        List<CourseList> courseListList = new ArrayList<>();
        //获取一级
        for (CourseList department1 : groupBy.get("0")) {
            List<CourseList> courseList1 =groupBy.get(String.valueOf(department1.getId()));
            if (!(courseList1==null)) {
                //子集排序
                ArrayList<CourseList> courseLists_c = (ArrayList<CourseList>) courseList1;
                courseLists_c.sort(Comparator.comparing(CourseList::getSort).reversed());
                department1.setChildren(courseLists_c);
                //子集2
                for (CourseList courseList2: courseLists_c ){
                    List<CourseList> courseList3 =groupBy.get(String.valueOf(courseList2.getId()));
                    if (!(courseList3 ==null)){
                        ArrayList<CourseList> courseLists_c2 = (ArrayList<CourseList>) courseList3;
                        courseLists_c2.sort(Comparator.comparing(CourseList::getSort).reversed());
                        courseList2.setChildren(courseLists_c2);
                    }
                }
            }
            courseListList.add(department1);
        }
        //父级排序
        ArrayList<CourseList> demoArray = (ArrayList<CourseList>) courseListList;
        demoArray.sort(Comparator.comparing(CourseList::getSort).reversed());
        return demoArray;
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
        List<CourseList> courseLists = service.list(new QueryWrapper<CourseList>().eq("parentid",id));
        List<Long> ids=new ArrayList<>();
        ids.add(id);
        if (!(courseLists ==null)){
            for (CourseList courseList:courseLists)
                ids.add(courseList.getId());
        }
        Boolean success= service.removeByIds(ids);
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
