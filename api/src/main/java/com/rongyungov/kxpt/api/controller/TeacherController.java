package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.entity.User;
import com.rongyungov.framework.entity.UserRole;
import com.rongyungov.framework.service.UserRoleService;
import com.rongyungov.framework.shiro.util.AesCipherUtil;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.kxpt.entity.*;
import com.rongyungov.kxpt.service.*;
import com.rongyungov.kxpt.utils.KxptConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  com.rongyungov.framework.base.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 *code is far away from bug with the animal protecting
 *   @description : Teacher 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-11
 */
@RestController
@Api(value="/teacher", description="Teacher 控制器")
@RequestMapping("/teacher")
public class TeacherController extends BaseController<TeacherService,Teacher> {

    @Autowired
    DepTaskService depTaskService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    StudentService studentService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    DataListService dataListService;

    @Autowired
    UserRoleService userRoleService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Teacher> getTeacherList( @ApiParam(name="teacher",value="筛选条件") @RequestBody(required = false) Teacher teacher  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<Teacher> page=new Page<Teacher>(pageIndex,pageSize);
        QueryWrapper<Teacher> queryWrapper=teacher.toWrapper(teacher);
        IPage<Teacher> TeacherIPage = service.page(page,queryWrapper);
        return TeacherIPage;
    }

    /**
     * @description : 通过id获取Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Teacher")
    public Teacher getTeacherById(@PathVariable Long id) {
        Teacher teacher=service.getById(id);
        Department dep = departmentService.getById(teacher.getDepno());
        teacher.setDepno(dep.getName());
        DataList photo = dataListService.getById(teacher.getPhoto());
        teacher.setPhoto(photo.getFile());
        return teacher;
    }

    /**
     * @description : 通过id获取Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @GetMapping("/get/Teacher")
    @ApiOperation(value = "通过account获取Teacher")
    public Teacher getTeacherByAccount() throws Exception {
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        Teacher teacher=service.getOne(new QueryWrapper<Teacher>().eq("teano",account));
        Department dep = departmentService.getById(teacher.getDepno());
        teacher.setDepno(dep.getName());
        DataList photo = dataListService.getById(teacher.getPhoto());
        teacher.setPhoto(photo.getFile());
        if (teacher!= null){
            return teacher;
        }else {
            throw new Exception("未找到教师信息！");
        }

    }

    /**
     * @description : 通过id删除Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Teacher")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Teachers")
    public Boolean delete(@RequestBody(required = false) List<Teacher> selectteachers) {
        Boolean success=false;
        if(selectteachers!=null&&selectteachers.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Teacher teacher:selectteachers)
            ids.add(teacher.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Teacher")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Teacher teacher) {
        if(id!=null)
            teacher.setId(id);
        Boolean success=service.updateById(teacher);
        return success;
    }

    /**
     * @description : 通过id修改密码
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PutMapping("/updatepassword/{id}")
    @ApiOperation(value="通过id修改密码")
    public Result updatepasswordByid(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,
                                       String olderpassword, String newpassword) throws Exception {
        Teacher teacher = new Teacher();
        Map<String,Object> reMap = new HashMap<>();
        String account = JwtUtil.getClaim(request.getHeader("Token"),"account");
        Teacher teacher1 = service.getOne(new QueryWrapper<Teacher>().eq("teano",account));
        if(id!=null)
            teacher.setId(id);
        if (olderpassword != null){
            String desEncryptnewpassword = null;
            String encryptolderpassword = AesCipherUtil.encrypt(account+olderpassword);
            if (encryptolderpassword.equalsIgnoreCase(teacher1.getPassword())){
                desEncryptnewpassword = AesCipherUtil.encrypt(account+newpassword);
            }else {
                reMap.put("message","输入密码错误！");
            }
            teacher.setPassword(desEncryptnewpassword);
        }
        Boolean success=service.updateById(teacher);
        reMap.put("is_success",success);
        return Result.ok(reMap);
    }


    /**
     * @description : 添加Teacher
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Teacher")
    public Boolean add(@RequestBody Teacher  teacher) throws Exception {
	    Teacher teacher1 = new Teacher();
	    teacher1.setTeano(teacher.getTeano());
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper(teacher1);
        Teacher tt = service.getOne(queryWrapper);
        if (tt !=null){
            throw new RuntimeException("教师已存在,不可重复");
        }
        teacher.setPassword(AesCipherUtil.encrypt(teacher.getTeano() + teacher.getPassword()));
        UserRole userRole = new UserRole();
        userRole.setUserId(teacher.getId());
        userRole.setRoleId(12L);
        userRole.setUserNo(KxptConstant.USER_TYPE_STUDENT);
        userRoleService.save(userRole);
        Boolean success = service.save(teacher);
        return success;
	}



    /**
     * @description : 添加竞赛Student
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     * @return
     */
    @PostMapping("/readsaiImport")
    @ApiOperation(value="批量读取竞赛Student")
    public Result readsai(MultipartFile excel) throws Exception {
        String contest_name = "十校联考";//模拟 竞赛名称
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        String default_password = "123456"; //初始密码
        List<Student> students = new ArrayList<>();
        InputStream in = excel.getInputStream();
        XSSFWorkbook work = new XSSFWorkbook(in);
        int fail = 0, success = 0;
        StringBuffer sb = new StringBuffer();
        XSSFSheet Sheet1 = work.getSheetAt(0);
        for (int i = 1; i < Sheet1.getLastRowNum()+1; i++) {
            try {
                success++;
                Student student = new Student();
                XSSFRow row = Sheet1.getRow(i);
                row.getCell(2).setCellType(1);
                row.getCell(3).setCellType(1);
                student.setCreatedBy(account);
                student.setDepno(String.valueOf(row.getCell(0)));
                student.setName(String.valueOf(row.getCell(1)));
                student.setTel(String.valueOf(row.getCell(2)));
                String contest_account = String.valueOf(row.getCell(3));
                student.setPassword(AesCipherUtil.encrypt(contest_account + default_password));
                student.setNo(contest_account);
                student.setIsContestStudent("1"); //设为竞赛学生
                students.add(student);

            } catch(Exception e){
                fail++;
                sb.append(e.getMessage() + "");
            }
        }
        Map<String, Object> map = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("成功读取" + (success - fail) + "条记录， 失败：" + fail + "条记录<br>");
        if (fail > 0) {
            stringBuffer.append("失败的信息为：");
            map.put("fail", fail);
        } else {
            map.put("fail", 0);
        }
        stringBuffer.append(sb.toString());
        String msg = stringBuffer.toString();
        map.put("msg", msg);
        map.put("contest_student",students);
        return Result.ok(map);

    }

    /**
     * @description : 添加竞赛Student
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     * @return
     */
    @PostMapping("/addsaiImport")
    @ApiOperation(value="批量写入竞赛Student")
    @Transactional
    public Result addsai(@RequestBody(required = false) List<Student> students) throws Exception {
        int fail = 0, success = 0;
        if (students!=null&& students.size()!=0){
            for (Student student:students){
                boolean b = studentService.save(student);
                if (b){
                    success++;
                }else fail++;
            }
        }
        Map<String, Object> map = new HashMap<>();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("成功写入" + success  + "条记录， 失败：" + fail + "条记录<br>");
        String msg = stringBuffer.toString();
        map.put("msg", msg);
        return Result.ok(map);

    }

}
