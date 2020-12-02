package com.rongyungov.kxpt.api.controller;


import com.rongyungov.kxpt.entity.Test;
import com.rongyungov.kxpt.utils.ExcelUtils;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@Api(value="/upfile", description="upfile 控制器")
@RequestMapping("/upfile")
public class ExcelController {

    private static final Logger log = LogManager.getLogger(ExcelController.class);

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


    /**
     * Excel表格导出接口
     * http://localhost:8080/ExcelDownload
     * @param response response对象
     * @throws IOException 抛IO异常
     */
    @RequestMapping("/ExcelDownload")
    public void excelDownload(HttpServletResponse response) throws IOException {
        //表头数据
        String[] header = {"姓名", "工号", "性别", "出生日期", "身份证号码", "婚姻状况","民族","籍贯","政治面貌","电子邮件","电话号码","联系地址","所属部门","职位","职称","聘用形式","入职日期","转正日期","合同起始日期","合同截止日期","合同期限","最高学历"};

        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //生成一个表格，设置表格名称为"学生表"
        HSSFSheet sheet = workbook.createSheet("员工表");

        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(10);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
//        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);

        //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
        for (int i = 0; i < header.length; i++) {
            //创建一个单元格
            HSSFCell cell = headrow.createCell(i);

            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(header[i]);

            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }

        //获取所有的employee
//        List<Employee> emps=employeeService.getAllEmpNonLimit();

//        for(int i=0;i<emps.size();i++){
//            //创建一行
//            HSSFRow row1 = sheet.createRow(i+1);
//            //第一列创建并赋值
//            row1.createCell(1).setCellValue(new HSSFRichTextString(emps.get(i).getId().toString()));
//            //第二列创建并赋值
//            row1.createCell(0).setCellValue(new HSSFRichTextString(emps.get(i).getName()));
//            //第三列创建并赋值
//            row1.createCell(2).setCellValue(new HSSFRichTextString(emps.get(i).getGender()));
//            //第4列创建并赋值
//            if(emps.get(i).getBirthday() != null){
//                row1.createCell(3).setCellValue(new HSSFRichTextString(dateFormat.format(emps.get(i).getBirthday())));
//            }
//            //第5列创建并赋值
//            row1.createCell(4).setCellValue(new HSSFRichTextString(emps.get(i).getIdCard()));
//            //第6列创建并赋值
//            row1.createCell(5).setCellValue(new HSSFRichTextString(emps.get(i).getWedlock()));
//            //第7列创建并赋值
//            System.err.println(emps.get(i).getNationName());
//            row1.createCell(6).setCellValue(new HSSFRichTextString(emps.get(i).getNationName()));
//            //籍贯
//            row1.createCell(7).setCellValue(new HSSFRichTextString(emps.get(i).getNativePlace()));
//            //第8列创建并赋值
//            row1.createCell(8).setCellValue(new HSSFRichTextString(emps.get(i).getPoliticName()));
//            //第9列创建并赋值
//            row1.createCell(9).setCellValue(new HSSFRichTextString(emps.get(i).getEmail()));
//            //第10列创建并赋值
//            row1.createCell(10).setCellValue(new HSSFRichTextString(emps.get(i).getPhone()));
//            //第11列创建并赋值
//            row1.createCell(11).setCellValue(new HSSFRichTextString(emps.get(i).getAddress()));
//            //第12列创建并赋值
//            row1.createCell(12).setCellValue(new HSSFRichTextString(emps.get(i).getDepartmentName()));
//            //第13列创建并赋值
//            row1.createCell(13).setCellValue(new HSSFRichTextString(emps.get(i).getPosName()));
//            //第14列创建并赋值
//            row1.createCell(14).setCellValue(new HSSFRichTextString(emps.get(i).getJobLevelName()));
//            //第15列创建并赋值
//            if(emps.get(i).getEngageForm() != null){
//                row1.createCell(15).setCellValue(new HSSFRichTextString(emps.get(i).getEngageForm()));
//            }
//            //第16列创建并赋值-入职日期
//            if(emps.get(i).getBeginDate()!= null){
//                row1.createCell(16).setCellValue(new HSSFRichTextString(dateFormat.format(emps.get(i).getBeginDate())));
//            }
//            //转正日期
//            if(emps.get(i).getConversionTime() != null){
//                row1.createCell(17).setCellValue(new HSSFRichTextString(dateFormat.format(emps.get(i).getConversionTime())));
//            }
//            //合同起始日期
//            if(emps.get(i).getBeginContract() != null){
//                row1.createCell(18).setCellValue(new HSSFRichTextString(dateFormat.format(emps.get(i).getBeginContract())));
//            }
//            //合同截止日期
//            if(emps.get(i).getEndContract() != null){
//                row1.createCell(19).setCellValue(new HSSFRichTextString(dateFormat.format(emps.get(i).getEndContract())));
//            }
//            //第20列创建并赋值-合同期限
//            if(emps.get(i).getContractTerm() != null){
//                row1.createCell(20).setCellValue(new HSSFRichTextString(emps.get(i).getContractTerm().toString()));
//            }
//            //第21列创建并赋值-最高学历
//            if( emps.get(i).getTiptopDegree() != null){
//                row1.createCell(21).setCellValue(new HSSFRichTextString(emps.get(i).getTiptopDegree()));
//            }
//        }getTiptopDegree


        //准备将Excel的输出流通过response输出到页面下载
        //八进制输出流
        response.setContentType("application/octet-stream");

        //这后面可以设置导出Excel的名称，此例中名为student.xls
        response.setHeader("Content-disposition", "attachment;filename=employee.xls");

        //刷新缓冲
        response.flushBuffer();

        //workbook将Excel写入到response的输出流中，供页面下载
        workbook.write(response.getOutputStream());
    }


}
