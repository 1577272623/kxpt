package com.rongyungov.kxpt.api.aop;

import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.ryform.utils.FileUtils;
import com.rongyungov.kxpt.entity.CourseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
@Aspect
public class fileAop {

    @Value("${spring.servlet.multipart.location}")
    private String defaultpath;

//    @Around("execution(* com.rongyungov.framework.ryform.controller.BoController.upload(..))")
    public Result AfterLogin1(ProceedingJoinPoint joinPoint) throws Exception {
        MultipartFile multipartFile = (MultipartFile) joinPoint.getArgs()[0];
        String fileName = FileUtils.randomNameFile(multipartFile);
        String filePath = this.defaultpath;
        String fileType = "imgs";
        if (!FileUtils.isImage(fileName)) {
            fileType = "file";
        }

        File d = new File(filePath + "/" + fileType + "/" + fileName);

        try {
            multipartFile.transferTo(d);
            String url = "/" + fileType + "/" + fileName;
            CourseData courseData = new CourseData();

            return Result.ok(url);
        } catch (IOException var7) {
            var7.printStackTrace();
            throw new RuntimeException("文件上传失败" + var7.getMessage());
        }


    }
}
