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
import java.util.List;

import  com.rongyungov.framework.base.BaseEntity;
import  com.baomidou.mybatisplus.annotation.TableField;

/**
 *code is far away from bug with the animal protecting
 *   @description : Department 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-12-02
 */
@TableName("department")
public class Department extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@ApiModelProperty("编号")

	@TableId(value="id", type= IdType.AUTO)


	private Long id;

	/**
	 * 部门名称
	 */
	@ApiModelProperty("部门名称")
	@TableField("name")

	private String name;

	/**
	 * 部门属性 1院系 2班级
	 */
	@ApiModelProperty("部门属性 1院系 2班级")
	@TableField("type")

	private String type;

	/**
	 * 上级部门
	 */
	@ApiModelProperty("上级部门")
	@TableField("parent_id")

	private String parentId;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	@TableField("description")

	private String description;

	/**
	 * 人数
	 */
	@ApiModelProperty("人数")
	@TableField("num")

	private Integer num;

	/**
	 * 班级
	 */
	@ApiModelProperty("班级")
	@TableField("class_name")

	private String className;

	/**
	 * 是否使用
	 */
	@ApiModelProperty("是否使用")
	@TableField("is_use")

	private String isUse;

	/**
	 * 是否删除
	 */
	@ApiModelProperty("是否删除")
	@TableField("is_delete")

	private String isDelete;

	/**
	 * 备用字段2
	 */
	@ApiModelProperty("备用字段2")
	@TableField("ext2")

	private String ext2;

	/**
	 * 备用字段1
	 */
	@ApiModelProperty("备用字段1")
	@TableField("ext1")

	private String ext1;

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

	private LocalDateTime updatedTime;

	@TableField(exist = false)
	private List<Department> children;

	public List<Department> getChildren() {
		return children;
	}

	public void setChildren(List<Department> children) {
		this.children = children;
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


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getParentId() {
		return parentId;
	}


	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getNum() {
		return num;
	}


	public void setNum(Integer num) {
		this.num = num;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getIsUse() {
		return isUse;
	}


	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}


	public String getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


	public String getExt2() {
		return ext2;
	}


	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}


	public String getExt1() {
		return ext1;
	}


	public void setExt1(String ext1) {
		this.ext1 = ext1;
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
		return "Department{" +
				"id=" + id +
				", name=" + name +
				", type=" + type +
				", parentId=" + parentId +
				", description=" + description +
				", num=" + num +
				", className=" + className +
				", isUse=" + isUse +
				", isDelete=" + isDelete +
				", ext2=" + ext2 +
				", ext1=" + ext1 +
				", ext3=" + ext3 +
				", revision=" + revision +
				", createdBy=" + createdBy +
				", createdTime=" + createdTime +
				", updatedBy=" + updatedBy +
				", updatedTime=" + updatedTime +
				"}";
	}


}
