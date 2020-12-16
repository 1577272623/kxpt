package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.kxpt.api.vo.ClassTaskVo;
import com.rongyungov.kxpt.api.vo.TableVo;
import com.rongyungov.kxpt.entity.*;
import com.rongyungov.kxpt.service.*;
import com.rongyungov.kxpt.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import  com.rongyungov.framework.base.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    DataListService dataListService;

    @Autowired
    DepTaskService depTaskService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    GradeService gradeService;

    @Autowired
    StudentService studentService;

    @Autowired
    TaskService taskService;

    @Autowired
    HttpServletRequest request;



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
        QueryWrapper<Task> queryWrapper=new QueryWrapper<>(new Task());
        queryWrapper.orderByDesc("created_time");
        int i =0;
        IPage<Task> taskPage = service.page(page,queryWrapper);
        List<DataList> dataLists = dataListService.list(new QueryWrapper<>(new DataList()));
        for (int j =0; j<taskPage.getRecords().size(); j++){
            i++;
            int s= (int) taskPage.getSize();
            int c= (int) (taskPage.getCurrent()-1);
            taskPage.getRecords().get(j).setNo(i+s*c);
            String fileids = taskPage.getRecords().get(j).getFile();
            ArrayList<DataList> dataListss =  new ArrayList<>();
            String[] stringcoursedatalist = fileids.split(",");
            for (int n=0; n<stringcoursedatalist.length; n++){
                for(DataList dataList : dataLists){
                    if (stringcoursedatalist[n].equalsIgnoreCase(String.valueOf(dataList.getId()))){
                        dataListss.add(dataList);
                    }
                }
            }
            taskPage.getRecords().get(j).setTask_file(dataListss);
        }
        return taskPage;
    }

    @PostMapping("/Alllist")
    @ApiOperation(value = "获取所有任务信息")
    public List<Task> getAllTasks(){
        return service.list(new QueryWrapper<>());
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
        String fileids = task.getFile();
        ArrayList<DataList> dataListss =  new ArrayList<>();
        String[] stringcoursedatalist = fileids.split(",");
        List<DataList> dataLists = dataListService.list(new QueryWrapper<>(new DataList()));
        for (int n=0; n<stringcoursedatalist.length; n++){
            for(DataList dataList : dataLists){
                if (stringcoursedatalist[n].equalsIgnoreCase(String.valueOf(dataList.getId()))){
                    dataListss.add(dataList);
                }
            }
        }
        task.setTask_file(dataListss);
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
    @ApiOperation(value="通过id更新Task 推送")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Task task) {
        Boolean success = false;
        if(id!=null)
            task.setId(id);
        success = service.updateById(task);
        return success;
    }
    @PostMapping("/addTask")
    @ApiOperation(value = "推送任务")
    public Result addTask(@RequestBody Task task){
        DepTask depTask = new DepTask();
        String class_ids = task.getClassNo();
        String[] strings = class_ids.split(",");
        depTask.setTaskId(String.valueOf(task.getId()));
        int no = 0;
        for (int i=0; i<strings.length; i++){
            depTask.setDepartId(strings[i]);
            depTaskService.save(depTask);
            no++;
        }
        return Result.ok("成功推送到"+no+"个班级");
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
        Boolean success =false;
        if (task.getFile()!=null){
            LocalDateTime dateTime = LocalDateTime.now();
            task.setCreatedTime(dateTime);
            success=service.save(task);
        }
        return success;
	}

    /**
     * @description : 获取任务完成度
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/taskDA")
    @ApiOperation(value = "教师首页获取任务总完成度")
    public Map<String,Object> gettaskDA(@ApiParam(name="teacher",value="筛选条件") @RequestBody(required = false) Teacher teacher ) throws InstantiationException, IllegalAccessException {
        List<Department> departmentList = departmentService.list(new QueryWrapper<Department>()
                .ne("parent_id","0"));
        List<Grade> grades = gradeService.list(new QueryWrapper<>());
        List<Student> students = studentService.list(new QueryWrapper<>());
        Map<String,Object> class_count = new HashMap<>();
        for (Department department:departmentList){
            if (department.getCreatedBy().equalsIgnoreCase(String.valueOf(teacher.getId()))){
                int class_grade_count = 0,student_count = 0;
                for (Student student:students){
                    if (student.getClassno().equalsIgnoreCase(String.valueOf(department.getId()))){
                        student_count++;
                    }
                }
                for (Grade grade:grades){
                    if (grade.getDepartment().equalsIgnoreCase(String.valueOf(department.getId()))){
                        class_grade_count++;
                    }
                }
                DecimalFormat df = new DecimalFormat("0.0000");
                class_count.put(department.getName(),df.format((float)class_grade_count/student_count));
            }
        }

        return class_count;
    }

    /**
     * @description : 获取任务完成度
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/classtaskDA")
    @ApiOperation(value = "获取班级任务")
    public Map<String,Object> getclasstaskDA( String class_id,String task_id ) {
        QueryWrapper<Department> departmentQueryWrapper;
        QueryWrapper<Task> taskQueryWrapper;
        if (class_id != null){
            departmentQueryWrapper = new QueryWrapper<Department>().eq("id", class_id);
        }else {
            departmentQueryWrapper = new QueryWrapper<Department>().ne("parent_id", "0");
        }
        if (task_id != null){
            taskQueryWrapper = new QueryWrapper<Task>().eq("id", task_id);
        }else {
            taskQueryWrapper = new QueryWrapper<Task>();
        }
        List<Task> tasks = taskService.list(taskQueryWrapper);
        List<Department> departmentList = departmentService.list(departmentQueryWrapper);
        List<DepTask> depTasks = depTaskService.list();
        List<Student> students = studentService.list();
        Map<String, List<Student>> deStudents = students.stream().collect(Collectors.groupingBy(Student::getClassno));
        List<Grade> grades = gradeService.list();
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        Map<String,Object> class_taskmap = new HashMap<>();
        for (Department department : departmentList) {
            if (department.getCreatedBy().equalsIgnoreCase(account)) {
                List<Task> taskList = new ArrayList<>();
                List<ClassTaskVo> classTaskVos = new ArrayList<>();
                int task_count = 0; //班级任务数
                int student_count = 0; //班级学生数
                if(deStudents.containsKey(department.getId()+"")){
                    student_count = deStudents.get(department.getId()+"").size();
                }else{
                    student_count = 0;
                }
                for (DepTask depTask:depTasks){
                    if (depTask.getDepartId().equalsIgnoreCase(String.valueOf(department.getId()))){
                        task_count++;
                        int student_do = 0; //做了题的学生数
                        for (Task task : tasks){
                            if (depTask.getTaskId().equalsIgnoreCase(String.valueOf(task.getId()))){
                                for (Grade grade: grades){
                                    if (grade.getDepartment().equalsIgnoreCase(String.valueOf(department.getId()))&&
                                    grade.getExamId().equalsIgnoreCase(String.valueOf(task.getId()))){
                                        if (grade.getType().equalsIgnoreCase("3")){
                                            student_do++;
                                        }
                                    }
                                }
                                ClassTaskVo classTaskVo = new ClassTaskVo(task.getId(),
                                        department.getId(),
                                        task.getType(),
                                        task.getName(),
                                        task.getDescription(),
                                        student_do,
                                        student_count,
                                        task_count);
                                classTaskVos.add(classTaskVo);
                            }
                        }
                    }
                }
                class_taskmap.put(department.getName(),classTaskVos);
            }
        }
        return class_taskmap;
    }


/*    @PostMapping("/createTable")
    @ApiOperation(value = "创建表")
    public boolean createTable( @RequestBody(required = false) TableVo table ) {
      return   service.sqlExecute(table.getTableCreateSql());

    }*/



}
