package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.kxpt.entity.CourseList;
import com.rongyungov.kxpt.entity.Teacher;
import com.rongyungov.kxpt.service.CourseListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
                import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.CourseDataService;
import  com.rongyungov.kxpt.entity.CourseData;

/**
 *code is far away from bug with the animal protecting
 *   @description : CourseData 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-11
 */
@RestController
@Api(value="/courseData", description="CourseData 控制器")
@RequestMapping("/courseData")
public class CourseDataController extends BaseController<CourseDataService,CourseData> {

    @Autowired
    CourseListService courseListService;
    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<CourseData> getCourseDataList( @ApiParam(name="courseData",value="筛选条件") @RequestBody(required = false) CourseData CourseData  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<CourseData> page=new Page<CourseData>(pageIndex,pageSize);
        QueryWrapper<CourseData> queryWrapper=CourseData.toWrapper(CourseData);
        IPage<CourseData> CourseDataIPage = service.page(page,queryWrapper);

        return CourseDataIPage;
    }

    /**
     * @description : 通过id获取CourseData
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取CourseData")
    public CourseData getCourseDataById(@PathVariable Long id) {
        CourseData courseData = new CourseData();
        List<CourseList> courseLists = courseListService.list(new QueryWrapper<CourseList>().eq("id",id));
        if (courseLists!=null&&courseLists.size()!=0){
             courseData=service.getOne(new QueryWrapper<CourseData>().eq("course_id",id));
        }
        return courseData;
    }

    /**
     * @description : 通过id删除CourseData
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除CourseData")
    public Boolean delete(@PathVariable Long id){
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除CourseData
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除CourseDatas")
    public Boolean delete(@RequestBody(required = false) List<CourseData> selectcourseDatas) {
        Boolean success=false;
        if(selectcourseDatas!=null&&selectcourseDatas.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (CourseData courseData:selectcourseDatas)
            ids.add(courseData.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新CourseData
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新CourseData")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody CourseData courseData) {
        if(id!=null)
            courseData.setId(id);
        Boolean success=service.updateById(courseData);
        return success;
    }

    /**
     * @description : 添加CourseData
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
	@PostMapping("/add")
    @ApiOperation(value="添加CourseData")
    public Boolean add(@RequestBody CourseData  courseData) {
        Boolean success=service.save( courseData);
        return success;
	}

}
