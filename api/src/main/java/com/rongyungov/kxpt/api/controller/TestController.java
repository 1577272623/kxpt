package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.base.Result;
import com.rongyungov.kxpt.entity.CourseList;
import com.rongyungov.kxpt.utils.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.TestService;
import  com.rongyungov.kxpt.entity.Test;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 试题上传
     */
    @PostMapping("/testImport")
    @ApiOperation(value = "试题导入")
    @Transactional
    public Result testImport(MultipartFile excel) throws Exception {
        String keno = "1";
        //模拟数据
        InputStream in = excel.getInputStream();
        XSSFWorkbook work = new XSSFWorkbook(in);
        int fail = 0, success = 0;
        StringBuffer sb = new StringBuffer();
        XSSFSheet Sheet1 = work.getSheet("Sheet1");
        for (int i = 1; i < Sheet1.getLastRowNum()+1; i++) {
            try {
                success++;
                Test test1 = new Test();
                test1.setKeno(keno);
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
                        service.save(test1);
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
                        service.save(test1);
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
        return Result.ok(map);
    }

}
