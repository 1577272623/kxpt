package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.base.Result;
import com.rongyungov.framework.ryform.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.DataListService;
import  com.rongyungov.kxpt.entity.DataList;
import org.springframework.web.multipart.MultipartFile;

/**
 *code is far away from bug with the animal protecting
 *   @description : DataList 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-12-02
 */
@RestController
@Api(value="/dataList", description="DataList 控制器")
@RequestMapping("/dataList")
public class DataListController extends BaseController<DataListService,DataList> {

    @Value("${spring.servlet.multipart.location}")
    private String defaultpath;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<DataList> getDataListList( @ApiParam(name="dataList",value="筛选条件") @RequestBody(required = false) DataList dataList  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) {
        Page<DataList> page=new Page<DataList>(pageIndex,pageSize);
        QueryWrapper<DataList> queryWrapper=new QueryWrapper<>(dataList);
        return service.page(page,queryWrapper);
    }

    /**
     * @description : 通过id获取DataList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取DataList")
    public DataList getDataListById(@PathVariable Long id) {
        DataList dataList=service.getById(id);
        return dataList;
    }

    /**
     * @description : 通过id删除DataList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除DataList")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除DataList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除DataLists")
    public Boolean delete(@RequestBody(required = false) List<DataList> selectdataLists) {
        Boolean success=false;
        if(selectdataLists!=null&&selectdataLists.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (DataList dataList:selectdataLists)
            ids.add(dataList.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新DataList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新DataList")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody DataList dataList) {
        if(id!=null)
            dataList.setId(id);
        Boolean success=service.updateById(dataList);
        return success;
    }

    /**
     * @description : 添加DataList
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
	@PostMapping("/add")
    @ApiOperation(value="添加DataList")
    public Boolean add(@RequestBody DataList  dataList) {
        LocalDateTime dateTime = LocalDateTime.now();
        dataList.setCreatedTime(dateTime);
        Boolean success=service.save( dataList);
        return success;
	}

    @PostMapping({"upload"})
    @ApiOperation(value="单文件上传")
    public Result upload(@RequestParam("file") MultipartFile multipartFile) {
        DataList dataList = new DataList();
        Map<String,Object> reMap = new HashMap<>();
//        String fileName = FileUtils.randomNameFile(multipartFile);
        String fileName = multipartFile.getOriginalFilename();
        String excal = "/imgs/excel.png";
        String ppt = "/imgs/ppt.png";
        String word = "/imgs/word.png";
        dataList.setName(fileName);
        String filePath = this.defaultpath;
        String fileType = "imgs";
        //获取文件的后缀名
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (!FileUtils.isImage(fileName)) {
            if (suffix.equals(".xls")||suffix.equalsIgnoreCase(".xlsx")){
                dataList.setPhoto(excal);
                fileType = "excel";
            }else if (suffix.equals(".doc")||suffix.equalsIgnoreCase(".docx")){
                dataList.setPhoto(word);
                fileType = "word";
            }else if(suffix.equals(".pptx")||suffix.equalsIgnoreCase(".ppt")){
                dataList.setPhoto(ppt);
                fileType = "ppt";
            }else {
                fileType = "file";
            }
        }
        dataList.setType(fileType);
        File d = new File(filePath + "/" + fileType + "/" + fileName);
        try {
            String url = "/" + fileType + "/" + fileName;
            dataList.setFile(url);
            LocalDateTime dateTime = LocalDateTime.now();
            dataList.setCreatedTime(dateTime);
            Boolean success = service.save(dataList);
            if (success){
                multipartFile.transferTo(d);
//                reMap.put("url",url);
//                reMap.put("success",success);
                reMap.put("id",dataList.getId());
            }
        } catch (IOException var7) {
            var7.printStackTrace();
            throw new RuntimeException("文件上传失败" + var7.getMessage());
        }
        return Result.ok(reMap);
    }

    @PostMapping("/uploads")
    @ApiOperation(value="多文件上传")
    public Result uploads(@RequestParam("files") MultipartFile[] multipartFiles) throws Exception {
        if (multipartFiles != null && multipartFiles.length != 0) {
            List<String> urls = new ArrayList();
            for(int i = 0; i < multipartFiles.length; ++i) {
                String fileName = FileUtils.randomNameFile(multipartFiles[i]);
                String filePath = this.defaultpath;
                String fileType = "imgs";
                if (!FileUtils.isImage(fileName)) {
                    fileType = "file";
                }
                File d = new File(filePath + "/" + fileType + "/" + fileName);
                multipartFiles[i].transferTo(d);
                String url = "/" + fileType + "/" + fileName;
                urls.add(url);
            }
            return Result.ok(urls);
        } else {
            throw new Exception("文件为空,上传失败");
        }
    }


}
