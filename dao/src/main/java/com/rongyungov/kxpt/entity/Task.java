package com.rongyungov.kxpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import  com.baomidou.mybatisplus.annotation.TableName;
import  com.baomidou.mybatisplus.annotation.IdType;
import  com.baomidou.mybatisplus.extension.activerecord.Model;
import  com.baomidou.mybatisplus.annotation.TableId;
import  java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import  com.baomidou.mybatisplus.annotation.TableLogic;
import  com.rongyungov.framework.base.BaseEntity;
import  com.baomidou.mybatisplus.annotation.TableField;

/**
 *code is far away from bug with the animal protecting
 *   @description : Task 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-25
 */
@TableName("task")
public class Task extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@ApiModelProperty("编号")

	@TableId(value="id", type= IdType.AUTO)


	private Long id;

	/**
	 * 编号
	 */
	@ApiModelProperty("编号 no")

	@TableId(value="no")
	@TableField(exist = false)

	private Integer no;

	/**
	 * 任务名称
	 */
	@ApiModelProperty("任务名称")
	@TableField("name")

	private String name;

	/**
	 * 任务具体操作
	 */
	@ApiModelProperty("任务具体操作")
	@TableField("operation")

	private String operation;


	/**
	 * 1 普通任务 2 聚合任务 默认普通
	 */
	@ApiModelProperty("1 普通任务 2 聚合任务 默认普通")
	@TableField("type")

	private String type;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	@TableField("description")

	private String description;

	/**
	 * 附件
	 */
	@ApiModelProperty("附件")
	@TableField("file")

	private String file;

	/**
	 * 结束时间
	 */
	@ApiModelProperty("结束时间")
	@TableField("over_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private String overTime;

	/**
	 * 是否启用
	 */
	@ApiModelProperty("是否启用")
	@TableField("is_use")

	private String isUse;

	/**
	 * 是否删除
	 */
	@ApiModelProperty("是否删除")
	@TableField("isdeleted")
	@TableLogic

	private String isdeleted;

	/**
	 * 备用字段1
	 */
	@ApiModelProperty("备用字段1")
	@TableField("ext1")

	private String ext1;

	/**
	 * 备用字段2
	 */
	@ApiModelProperty("备用字段2")
	@TableField("ext2")

	private String ext2;

	/**
	 * 备用字段3
	 */
	@ApiModelProperty("备用字段3")
	@TableField("ext3")

	private String ext3;

	/**
	 * 乐观锁
	 */
	@ApiModelProperty("乐观锁")
	@TableField("revision")

	private Integer revision;

	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
	@TableField("created_by")

	private String createdBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	@TableField("created_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime createdTime;

	/**
	 * 更新人
	 */
	@ApiModelProperty("更新人")
	@TableField("updated_by")

	private String updatedBy;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	@TableField("updated_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime updatedTime;

	@TableField(exist = false)
	private List<DataList> task_file;

	@TableField(exist = false)
	private int ok_num; //任务完成人数

	/**
	 * 所属部門
	 */
	@ApiModelProperty("班级编号")
	@TableField(exist = false)
	private String classNo;

	@TableField(exist = false)
	private int  student_count;

	public int getStudent_count() {
		return student_count;
	}

	public void setStudent_count(int student_count) {
		this.student_count = student_count;
	}

	public int getOk_num() {
		return ok_num;
	}

	public void setOk_num(int ok_num) {
		this.ok_num = ok_num;
	}

	public List<DataList> getTask_file() {
		return task_file;
	}

	public void setTask_file(List<DataList> task_file) {
		this.task_file = task_file;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public String getOverTime() {
		return overTime;
	}


	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}


	public String getIsUse() {
		return isUse;
	}


	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}


	public String getClassNo() {
		return classNo;
	}


	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}


	public String getIsdeleted() {
		return isdeleted;
	}


	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}


	public String getExt1() {
		return ext1;
	}


	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}


	public String getExt2() {
		return ext2;
	}


	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}


	public String getExt3() {
		return ext3;
	}


	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}


	public Integer getRevision() {
		return revision;
	}


	public void setRevision(Integer revision) {
		this.revision = revision;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public LocalDateTime getCreatedTime() {
		return createdTime;
	}


	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}


	public String getUpdatedBy() {
		return updatedBy;
	}


	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	public LocalDateTime getUpdatedTime() {
		return updatedTime;
	}


	public void setUpdatedTime(LocalDateTime updatedTime) {
		this.updatedTime = updatedTime;
	}


	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", name=" + name +
				", description=" + description +
				", file=" + file +
				", overTime=" + overTime +
				", isUse=" + isUse +
				", classNo=" + classNo +
				", isdeleted=" + isdeleted +
				", ext1=" + ext1 +
				", ext2=" + ext2 +
				", ext3=" + ext3 +
				", revision=" + revision +
				", createdBy=" + createdBy +
				", createdTime=" + createdTime +
				", updatedBy=" + updatedBy +
				", updatedTime=" + updatedTime +
				"}";
	}


}
