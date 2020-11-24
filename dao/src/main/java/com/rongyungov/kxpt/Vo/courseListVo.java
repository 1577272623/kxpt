package com.rongyungov.kxpt.Vo;

import com.rongyungov.kxpt.entity.CourseList;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class courseListVo {
    private String name;
    private String path;
    private String msg;
    private Integer sort;

    public courseListVo(String name, String path, Integer sort) {
        this.name = name;
        this.path = path;
        this.sort = sort;

    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    private List<Object> children = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getChildren() {
        return children;
    }

    public void setChildren(List<Object> children) {
        this.children = children;
    }
}
