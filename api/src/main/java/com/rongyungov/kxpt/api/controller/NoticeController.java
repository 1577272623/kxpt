package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.common.StringUtil;
import com.rongyungov.kxpt.entity.DataList;
import com.rongyungov.kxpt.entity.Task;
import com.rongyungov.kxpt.service.DataListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.NoticeService;
import  com.rongyungov.kxpt.entity.Notice;

/**
 *code is far away from bug with the animal protecting
 *   @description : Notice 控制器
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-12-02
 */
@RestController
@Api(value="/notice", description="Notice 公告 控制器")
@RequestMapping("/notice")
public class NoticeController extends BaseController<NoticeService,Notice> {

    @Autowired
    DataListService dataListService;

    /**
     * @description : 获取分页列表
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @PostMapping("/list")
    @ApiOperation(value = "获取分页数据信息")
    public IPage<Notice> getNoticeList( @ApiParam(name="notice",value="筛选条件") @RequestBody(required = false) Notice notice  ,
                                @ApiParam(name="pageIndex",value="页数",required=true,defaultValue = "1")@RequestParam Integer pageIndex ,
                                @ApiParam(name="pageSize",value="页大小",required=true,defaultValue = "10")@RequestParam Integer pageSize
                                ) throws InstantiationException, IllegalAccessException {
        Page<Notice> page=new Page<Notice>(pageIndex,pageSize);
        QueryWrapper<Notice> queryWrapper=notice.toWrapper(notice);
        queryWrapper.orderByDesc("created_time");
        if (notice.getSelectfirstTime() != null){
            queryWrapper.le("created_time",notice.getSelectfirstTime());
        }
        if (notice.getSelectsecondTime() != null){
            queryWrapper.lt("created_time",notice.getSelectfirstTime());
        }
        int i =0;
        IPage<Notice> noticeIPage = service.page(page,queryWrapper);
        for (int j =0; j<noticeIPage.getRecords().size(); j++){
            i++;
            int s= (int) noticeIPage.getSize();
            int c= (int) (noticeIPage.getCurrent()-1);
            noticeIPage.getRecords().get(j).setNo(i+s*c);
        }
        return noticeIPage;
    }

    /**
     * @description : 通过id获取Notice
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "通过id获取Notice")
    public Map<String,Object> getNoticeById(@PathVariable Long id) {
        Notice notice=service.getById(id);
        Map<String, Object> map = new HashMap<>();
        if (notice.getFile()!=null){
            String file = notice.getFile();
            ArrayList<String> fileList =  new ArrayList<>();
            String[] stringvideoList = file.split(",");
            for (int i=0; i<stringvideoList.length; i++){
                fileList.add(stringvideoList[i]);
            }
            List<DataList> dataList = dataListService.list(new QueryWrapper<DataList>().in("id",fileList));

            map.put("file",dataList);
            map.put("notice",notice);
        }
        return map;
    }

    /**
     * @description : 通过id删除Notice
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "通过id删除Notice")
    public Boolean delete(@PathVariable Long id) {
        Boolean success=service.removeById(id);
        return success;
    }

    /**
     * @description : 通过id删除Notice
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @PostMapping("/deletes")
    @ApiOperation(value = "通过ids删除Notices")
    public Boolean delete(@RequestBody(required = false) List<Notice> selectnotices) {
        Boolean success=false;
        if(selectnotices!=null&&selectnotices.size()!=0)
        {
            List<Long>  ids=new ArrayList<>();
            for (Notice notice:selectnotices)
            ids.add(notice.getId());
            success= service.removeByIds(ids);
        }
        return success;
    }

    /**
     * @description : 通过id更新Notice
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
    @PutMapping("/update/{id}")
    @ApiOperation(value="通过id更新Notice")
    public Boolean update(@ApiParam(name="id",value="id主键值",required=true) @PathVariable Long id,@RequestBody Notice notice) {
        if(id!=null)
            notice.setId(id);
        Boolean success=service.updateById(notice);
        return success;
    }

    /**
     * @description : 添加Notice
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-12-02
     */
	@PostMapping("/add")
    @ApiOperation(value="添加Notice")
    public Boolean add(@RequestBody Notice  notice) {
        Boolean success=service.save( notice);
        LocalDateTime dateTime = LocalDateTime.now();
        notice.setCreatedTime(dateTime);
        return success;
	}

}
