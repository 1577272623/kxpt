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
    import com.rongyungov.kxpt.service.TaskService;
import  com.rongyungov.kxpt.entity.Task;

/**
 *code is far away from bug with the animal protecting
 *   @description : Task 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-25
 */
@RestController
@Api(value="/task", description="Task 控制器")
@RequestMapping("/task")
public class TaskController extends BaseController<TaskService,Task> {

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-25
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Task> getTaskList( @ApiParam(name="task",value="筛选条件") @RequestBody(required = false) Task task  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<Task> page=new Page<Task>(pageIndex,pageSize);
        QueryWrapper<Task> queryWrapper=task.toWrapper(task);
        int i =0;
        List<Task> taskList = service.list(queryWrapper);
        for (Task task1:taskList){
            i++;
            task1.setNo(i);
        }

        IPage<Task> taskPage = service.page(page,queryWrapper);
        return taskPage;
    }

    /**
     * @description : 通过id获取Task
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-25
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Task")
    public Task getTaskById(@PathVariable Long id) {
        Task task=service.getById(id);
        return task;
    }

    /**
     * @description : 通过id删除Task
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-25
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Task")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Task
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-25
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Tasks")
    public Boolean delete(@RequestBody(required = false) List<Task> selecttasks) {
        Boolean success=false;
        if(selecttasks!=null&&selecttasks.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Task task:selecttasks)
            ids.add(task.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Task
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-25
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Task")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Task task) {
        if(id!=null)
            task.setId(id);
        Boolean success=service.updateById(task);
        return success;
    }

    /**
     * @description : 添加Task
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-25
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Task")
    public Boolean add(@RequestBody Task  task) {
        Boolean success=service.save( task);
        return success;
	}

}
