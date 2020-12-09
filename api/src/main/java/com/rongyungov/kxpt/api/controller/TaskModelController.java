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
    import com.rongyungov.kxpt.service.TaskModelService;
import  com.rongyungov.kxpt.entity.TaskModel;

/**
 *code is far away from bug with the animal protecting
 *   @description : TaskModel 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-12-09
 */
@RestController
@Api(value="/taskModel", description="TaskModel 控制器")
@RequestMapping("/taskModel")
public class TaskModelController extends BaseController<TaskModelService,TaskModel> {

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-09
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<TaskModel> getTaskModelList( @ApiParam(name="taskModel",value="筛选条件") @RequestBody(required = false) TaskModel taskModel  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) {
        Page<TaskModel> page=new Page<TaskModel>(pageIndex,pageSize);
        QueryWrapper<TaskModel> queryWrapper=new QueryWrapper<>(taskModel);
        return service.page(page,queryWrapper);
    }

    /**
     * @description : 通过id获取TaskModel
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-09
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取TaskModel")
    public TaskModel getTaskModelById(@PathVariable Long id) {
        TaskModel taskModel=service.getById(id);
        return taskModel;
    }

    /**
     * @description : 通过id删除TaskModel
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-09
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除TaskModel")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除TaskModel
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-09
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除TaskModels")
    public Boolean delete(@RequestBody(required = false) List<TaskModel> selecttaskModels) {
        Boolean success=false;
        if(selecttaskModels!=null&&selecttaskModels.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (TaskModel taskModel:selecttaskModels)
            ids.add(taskModel.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新TaskModel
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-09
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新TaskModel")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody TaskModel taskModel) {
        if(id!=null)
            taskModel.setId(id);
        Boolean success=service.updateById(taskModel);
        return success;
    }

    /**
     * @description : 添加TaskModel
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-09
     */
	@PostMapping("/add")
    @ApiOperation(value="添加TaskModel")
    public Boolean add(@RequestBody TaskModel  taskModel) {
        Boolean success=service.save( taskModel);
        return success;
	}

}
