package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.kxpt.entity.CourseList;
import com.rongyungov.kxpt.entity.Test;
import com.rongyungov.kxpt.service.CourseListService;
import com.rongyungov.kxpt.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    CourseListService courseListService;

    @Autowired
    TestService testService;

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
                                ) throws InstantiationException, IllegalAccessException {
        Page<Exam> page=new Page<Exam>(pageIndex,pageSize);
        exam.setNo("");
        QueryWrapper<Exam> queryWrapper=exam.toWrapper(exam);
        queryWrapper.orderByDesc("created_time");
        IPage<Exam> examIPage = service.page(page,queryWrapper);
        int i = 0;
        for (int j =0; j<examIPage.getRecords().size(); j++){
            i++;
            int s= (int) examIPage.getSize();
            int c= (int) (examIPage.getCurrent()-1);
            examIPage.getRecords().get(j).setNo(String.valueOf(i+s*c));
        }
        return examIPage;
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

    @PostMapping("/courselist")
    @ApiOperation(value = "考试范围")
    public List<CourseList> getcourselist(@RequestBody(required = false) CourseList courseList) {
        List<CourseList> courseLists = courseListService.list(new QueryWrapper<>());
        Map<String, List<CourseList>> groupBy = courseLists.stream().collect(Collectors.groupingBy(CourseList::getParentid));
        List<CourseList> courseListList = new ArrayList<>();
        List<Test> testList = testService.list(new QueryWrapper<>());
        Map<String, List<Test>> groupBytest = testList.stream().collect(Collectors.groupingBy(Test::getKeno));
        //获取一级
        for (CourseList department1 : groupBy.get("0")) {
            List<CourseList> courseList1 =groupBy.get(String.valueOf(department1.getId()));
            if (groupBytest.get(String.valueOf(department1.getId()))!=null){
                department1.setTestnum(groupBytest.get(String.valueOf(department1.getId())).size());
                int type1 = 0,type2 = 0, type3 = 0, type4 = 0, type5 = 0;
                //各种类型题目的数量
                for (Test test:groupBytest.get(String.valueOf(department1.getId()))){
                    if (test.getStType().equals("1")){
                        type1++;
                    }else if (test.getStType().equals("2")){
                        type2++;
                    }else if (test.getStType().equals("3")){
                        type3++;
                    }else if (test.getStType().equals("4")){
                        type4++;
                    }else {
                        type5++;
                    }
                }
                department1.setType1(type1);
                department1.setType2(type2);
                department1.setType3(type3);
                department1.setType4(type4);
                department1.setType5(type5);
            }
            if (!(courseList1==null)) {
                //子集排序
                ArrayList<CourseList> courseLists_c = (ArrayList<CourseList>) courseList1;
                courseLists_c.sort(Comparator.comparing(CourseList::getSort).reversed());
                department1.setChildren(courseLists_c);
                //子集2
                for (CourseList courseList2: courseLists_c ){
                    if (groupBytest.get(String.valueOf(courseList2.getId()))!=null){
                        courseList2.setTestnum(groupBytest.get(String.valueOf(courseList2.getId())).size());
                        int type1 = 0,type2 = 0, type3 = 0, type4 = 0, type5 = 0;
                        for (Test test:groupBytest.get(String.valueOf(courseList2.getId()))){
                            if (test.getStType().equals("1")){
                                type1++;
                            }else if (test.getStType().equals("2")){
                                type2++;
                            }else if (test.getStType().equals("3")){
                                type3++;
                            }else if (test.getStType().equals("4")){
                                type4++;
                            }else {
                                type5++;
                            }
                        }
                        courseList2.setType1(type1);
                        courseList2.setType2(type2);
                        courseList2.setType3(type3);
                        courseList2.setType4(type4);
                        courseList2.setType5(type5);
                    }
                    List<CourseList> courseList3 =groupBy.get(String.valueOf(courseList2.getId()));
                    if (!(courseList3 ==null)){
                        ArrayList<CourseList> courseLists_c2 = (ArrayList<CourseList>) courseList3;
                        courseLists_c2.sort(Comparator.comparing(CourseList::getSort).reversed());
                        for (CourseList courseList4:courseLists_c2){
                            if (groupBytest.get(String.valueOf(courseList4.getId()))!=null){
                                courseList4.setTestnum(groupBytest.get(String.valueOf(courseList4.getId())).size());
                                int type1 = 0,type2 = 0, type3 = 0, type4 = 0, type5 = 0;
                                for (Test test:groupBytest.get(String.valueOf(courseList4.getId()))){
                                    if (test.getStType().equals("1")){
                                        type1++;
                                    }else if (test.getStType().equals("2")){
                                        type2++;
                                    }else if (test.getStType().equals("3")){
                                        type3++;
                                    }else if (test.getStType().equals("4")){
                                        type4++;
                                    }else {
                                        type5++;
                                    }
                                }
                                courseList4.setType1(type1);
                                courseList4.setType2(type2);
                                courseList4.setType3(type3);
                                courseList4.setType4(type4);
                                courseList4.setType5(type5);
                            }
                        }
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
        Boolean success =false;
        LocalDateTime dateTime = LocalDateTime.now();
        exam.setCreatedTime(dateTime);
        success=service.save(exam);
        return success;
    }

}
