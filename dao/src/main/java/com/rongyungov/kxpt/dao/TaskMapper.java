package com.rongyungov.kxpt.dao;
import  com.rongyungov.kxpt.entity.Task;
import  com.rongyungov.framework.base.BaseDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 *code is far away from bug with the animal protecting
 *   @description : Task Mapper 接口
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-25
 */
public interface TaskMapper extends BaseDao<Task> {



    @Update("${sql}")
    boolean sqlExecute(@Param("sql") String sql);



}