package com.rongyungov.kxpt.api.controller;


import com.rongyungov.kxpt.entity.Test;
import com.rongyungov.kxpt.utils.ExcelUtils;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@Api(value="/upfile", description="upfile 控制器")
@RequestMapping("/upfile")
public class ExcelController {

    private static final Logger log = LogManager.getLogger(ExcelController.class);

    /**
     * Excel导出
     * @param response
     */
//    @GetMapping("/exportExcel")
//    public void exportExcel(HttpServletRequest request,HttpServletResponse response){
//        //使用假数据代替从数据库查出来的需要导出的数据
//        List<Test> testList = handleRepositoryData();
//        long t1 = System.currentTimeMillis();
//        ExcelUtils.exportMultisheetExcel("",testList,response);
//        long t2 = System.currentTimeMillis();
//        System.out.println(String.format("write over! cost:%sms", (t2 - t1)));
//    }

    /**
     * Excel导入
     * @param file
     * @return
     */
    @PostMapping("/readExcel")
    public List<String[]> readExcel(@RequestBody MultipartFile file){
        String filePath = "c:\\kxpt\\upload\\excel\\Book1.xlsx";
        List<String[]> mapList = ExcelUtils.readExcel(String.valueOf(filePath), 0);
//        log.info("mapList:" + mapList);
//        System.out.print(mapList);
        return mapList;
    }


}
