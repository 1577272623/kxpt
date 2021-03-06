package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.shiro.util.AesCipherUtil;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.kxpt.entity.DepTask;
import com.rongyungov.kxpt.entity.Task;
import com.rongyungov.kxpt.entity.Test;
import com.rongyungov.kxpt.service.DepTaskService;
import com.rongyungov.kxpt.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.StudentService;
import  com.rongyungov.kxpt.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 *code is far away from bug with the animal protecting
 *   @description : Student 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-11
 */
@RestController
@Api(value="/student", description="Student 控制器")
@RequestMapping("/student")
public class StudentController extends BaseController<StudentService,Student> {

    @Autowired
    HttpServletRequest request;

    @Autowired
    DepTaskService depTaskService;

    @Autowired
    TaskService taskService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Student> getStudentList( @ApiParam(name="student",value="筛选条件") @RequestBody(required = false) Student student  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<Student> page=new Page<Student>(pageIndex,pageSize);
        QueryWrapper<Student> queryWrapper=student.toWrapper(student);
        IPage<Student> studentIPage = service.page(page,queryWrapper);


        return studentIPage;
    }

    /**
     * @description : 通过id获取Student
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Student")
    public Student getStudentById(@PathVariable Long id) {
        Student student=service.getById(id);

        return student;
    }

    /**
     * @description : 通过id删除Student
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Student")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Student
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Students")
    public Boolean delete(@RequestBody(required = false) List<Student> selectstudents) {
        Boolean success=false;
        if(selectstudents!=null&&selectstudents.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Student student:selectstudents)
            ids.add(student.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Student
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Student")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Student student) {
        if(id!=null)
            student.setId(id);
        Boolean success=service.updateById(student);
        return success;
    }

    /**
     * @description : 添加Student
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     * @return
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Student")
    public Boolean add(@RequestBody Student  student) throws Exception {
	    QueryWrapper<Student> students = new QueryWrapper<Student>().eq("no", student.getNo());
        Student student1 = service.getOne(students);
	    if (student1 != null ){
            throw new RuntimeException("学号已存在,不可重复");
        }
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        student.setCreatedBy(account);
        student.setPassword(AesCipherUtil.encrypt(student.getNo() + student.getPassword()));
        Boolean success = ((StudentService)this.service).save(student);
        return success;
	}

    /**
     * @description : 添加竞赛Student
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     * @return
     */
    @PostMapping("/addsaiImport")
    @ApiOperation(value="导入竞赛Student")
    public Boolean addsai(MultipartFile excel) throws Exception {

        InputStream in = excel.getInputStream();
        XSSFWorkbook work = new XSSFWorkbook(in);
        int fail = 0, success = 0;
        StringBuffer sb = new StringBuffer();
        XSSFSheet Sheet1 = work.getSheet("Sheet1");
        for (int i = 1; i < Sheet1.getLastRowNum()+1; i++) {
            try {
                success++;
                Test test1 = new Test();

                XSSFRow row = Sheet1.getRow(i);
                if (String.valueOf(row.getCell(0)).equalsIgnoreCase("判断题") || String.valueOf(row.getCell(0)).equalsIgnoreCase("填空题")) {
                    if (String.valueOf(row.getCell(0)).equalsIgnoreCase("判断题")) {
                        test1.setStType("1");
                    } else {
                        test1.setStType("4");
                    }
                    test1.setStContent(String.valueOf(row.getCell(1)));
                    test1.setStAnswer(String.valueOf(row.getCell(6)));
                    if (!(row.getCell(0) == null)) {
                        test1.setAnalysis(String.valueOf(row.getCell(7)));
                    }
//                    service.save(test1);
                } else if (String.valueOf(row.getCell(0)).equalsIgnoreCase("单选题") || String.valueOf(row.getCell(0)).equalsIgnoreCase("多选题")) {
                    if (String.valueOf(row.getCell(0)).equalsIgnoreCase("单选题")) {
                        test1.setStType("2");
                    } else {
                        test1.setStType("3");
                    }
                    test1.setStContent(String.valueOf(row.getCell(0)));
                    test1.setAnswerA(String.valueOf(row.getCell(2)));
                    test1.setAnswerB(String.valueOf(row.getCell(3)));
                    test1.setAnswerC(String.valueOf(row.getCell(4)));
                    test1.setAnswerD(String.valueOf(row.getCell(5)));
                    test1.setStAnswer(String.valueOf(row.getCell(6)));
                    if (!(row.getCell(7) == null)) {
                        test1.setAnalysis(String.valueOf(row.getCell(7)));
                    }
//                    service.save(test1);
                }
            } catch(Exception e){
                fail++;
//                sb.append("<br>试题：" + test1 + "，&nbsp;&nbsp;");
//                sb.append("失败原因：");
                sb.append(e.getMessage() + "");
            }
        }
        Map<String, Object> map = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("成功导入" + (success - fail) + "条记录， 失败：" + fail + "条记录<br>");
        if (fail > 0) {
            stringBuffer.append("失败的信息为：");
            map.put("fail", fail);
        } else {
            map.put("fail", 0);
        }
        stringBuffer.append(sb.toString());
        String msg = stringBuffer.toString();
        map.put("msg", msg);
        return null;

    }

	@PostMapping("/task")
    @ApiOperation(value = "获取任务列表")
    public List<Task> task(@RequestBody Student  student){
        List<DepTask> deptaskList = depTaskService.list(new QueryWrapper<DepTask>().eq("depart_id",student.getClassno()));
        List<Task> taskList = taskService.list(new QueryWrapper<>());
        List<String>  ids=new ArrayList<>();
        List<Task> tasks = new ArrayList<>();
        for (DepTask depTask:deptaskList) {
            for (Task task:taskList){
                if(depTask.getTaskId().equals(String.valueOf(task.getId()))){
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }
}
