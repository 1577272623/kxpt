package com.rongyungov.kxpt.api.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rongyungov.framework.entity.User;
import com.rongyungov.framework.service.UserService;
import com.rongyungov.framework.shiro.util.AesCipherUtil;
import com.rongyungov.framework.shiro.util.JwtUtil;
import com.rongyungov.kxpt.entity.*;
import com.rongyungov.kxpt.service.DataListService;
import com.rongyungov.kxpt.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import  com.rongyungov.framework.base.BaseController;
    import com.rongyungov.kxpt.service.NoticeService;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    DepartmentService departmentService;

    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;

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
        String account = JwtUtil.getClaim(request.getHeader("Token"),"account");
        Notice notice1 = new Notice();
        notice1.setCreatedBy(account);
        QueryWrapper<Notice> queryWrapper= new QueryWrapper<Notice>(notice1);
        List<DataList> dataLists = dataListService.list(new QueryWrapper<>(new DataList()));
        List<Department> departments = departmentService.list(new QueryWrapper<>());
        List<User> users = userService.list(new QueryWrapper<>());
        queryWrapper.orderByDesc("created_time");
        if (notice.getSelectfirstTime() != null){
            queryWrapper.ge("created_time",notice.getSelectfirstTime());
        }
        if (notice.getSelectsecondTime() != null){
            queryWrapper.le("created_time",notice.getSelectsecondTime());
        }
        int i =0;
        String creat_by ="";
        IPage<Notice> noticeIPage = service.page(page,queryWrapper);
        for (int j =0; j<noticeIPage.getRecords().size(); j++){
            i++;
            int s= (int) noticeIPage.getSize();
            int c= (int) (noticeIPage.getCurrent()-1);
            noticeIPage.getRecords().get(j).setNo(i+s*c);
            String classids = noticeIPage.getRecords().get(j).getClassNo();
            ArrayList<String> dataListss =  new ArrayList<>();
            String[] stringcoursedatalist = classids.split(",");

            for (int n=0; n<stringcoursedatalist.length; n++){
                for(Department dataList : departments){
                    if (stringcoursedatalist[n].equalsIgnoreCase(String.valueOf(dataList.getId()))){
                        dataListss.add(dataList.getName());
                    }
                }
            }
            for (User user : users){
                if(user.getAccount().equalsIgnoreCase(noticeIPage.getRecords().get(j).getCreatedBy())){
                    creat_by = user.getUsername();
                }
            }
            noticeIPage.getRecords().get(j).setClass_name(dataListss);
            noticeIPage.getRecords().get(j).setCreatedBy(creat_by);
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
        LocalDateTime dateTime = LocalDateTime.now();
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        notice.setCreatedBy(account);
        notice.setCreatedTime(dateTime);
        Boolean success=service.save( notice);
        return success;
	}

    /**
     * @description : 教师首页获取班级公告
     * ---------------------------------
     * @author : li
     * @since : Create in 2020-11-11
     */
    @PostMapping("/getClassNotice")
    @ApiOperation(value = "教师首页获取班级公告")
    public List<Notice> getClassNotice(@ApiParam(name="teacher",value="筛选条件") @RequestBody(required = false) Teacher teacher ) {
        List<Department> departmentList = departmentService.list(new QueryWrapper<Department>()
                .ne("parent_id", "0"));
        String account = JwtUtil.getClaim(request.getHeader("Token"), "account");
        List<Notice> notices = noticeService.list(new QueryWrapper<>());
        List<Notice> noticeList = new ArrayList<>();
        for (Department department : departmentList) {
            if (department.getCreatedBy().equalsIgnoreCase(account)) {
                for (Notice notice: notices){
                    String notice_class = notice.getClassNo();
                    String[] notice_classList = notice_class.split(",");
                    for (int i=0; i<notice_classList.length; i++){
                        if (notice_classList[i].equalsIgnoreCase(String.valueOf(department.getId()))){
                            noticeList.add(notice);
                        }
                    }
                }
            }

        }
        return noticeList;
    }

}
