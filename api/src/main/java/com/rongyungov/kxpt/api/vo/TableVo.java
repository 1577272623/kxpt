package com.rongyungov.kxpt.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author ：David.Yan
 * @date ：Created in 2020-12-15 17:31
 * @description：
 * @modified By：
 * @version:
 */

@Data
public class TableVo {

    private String tableName;

    private List<String>  columns;

    public String getTableCreateSql()
    {
        String sql="create table "+tableName+" ( ";
        //加 主键 id
        sql+= "`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号', ";

        for(String item :columns)
        {
            sql+="`"+item+"`"+" varchar(50) DEFAULT NULL ,";
        }
        sql+=" PRIMARY KEY (`id`) )  DEFAULT CHARSET=utf8";
        return sql;
    }



}
