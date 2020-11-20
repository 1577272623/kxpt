package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.kxpt.entity.CourseList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
                import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.TestService;
import  com.rongyungov.kxpt.entity.Test;

/**
 *code is far away from bug with the animal protecting
 *   @description : Test 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-11
 */
@RestController
@Api(value="/test", description="Test 控制器")
@RequestMapping("/test")
public class TestController extends BaseController<TestService,Test> {

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Test> getTestList( @ApiParam(name="test",value="筛选条件") @RequestBody(required = false) Test Test  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<Test> page=new Page<Test>(pageIndex,pageSize);
        QueryWrapper<Test> queryWrapper=Test.toWrapper(Test);
        IPage<Test> courseListIPage = service.page(page,queryWrapper);

        return courseListIPage;
    }

    /**
     * @description : 通过id获取Test
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Test")
    public Test getTestById(@PathVariable Long id) {
        Test test=service.getById(id);
        return test;
    }

    /**
     * @description : 通过id删除Test
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Test")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Test
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Tests")
    public Boolean delete(@RequestBody(required = false) List<Test> selecttests) {
        Boolean success=false;
        if(selecttests!=null&&selecttests.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Test test:selecttests)
            ids.add(test.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Test
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Test")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Test test) {
        if(id!=null)
            test.setId(id);
        Boolean success=service.updateById(test);
        return success;
    }

    /**
     * @description : 添加Test
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Test")
    public Boolean add(@RequestBody Test  test) {
        Boolean success=service.save(test);
        return success;
	}

}
