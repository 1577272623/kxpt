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

import  com.baomidou.mybatisplus.annotation.TableLogic;
import  com.rongyungov.framework.base.BaseEntity;
import  com.baomidou.mybatisplus.annotation.TableField;

/**
 *code is far away from bug with the animal protecting
 *   @description : CourseList 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-24
 */
@TableName("course_list")
public class CourseList extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@ApiModelProperty("编号")

	@TableId(value="id", type= IdType.AUTO)


	private Long id;

	/**
	 * 课程名称
	 */
	@ApiModelProperty("课程名称")
	@TableField("name")

	private String name;

	/**
	 * 课程描述
	 */
	@ApiModelProperty("课程描述")
	@TableField("description")

	private String description;

	/**
	 * 课程类型
	 */
	@ApiModelProperty("课程类型")
	@TableField("type")

	private String type;

	/**
	 * 地址
	 */
	@ApiModelProperty("地址")
	@TableField("path")

	private String path;

	/**
	 * 排序
	 */
	@ApiModelProperty("排序 越大级别越高")
	@TableField("sort")

	private Integer sort;

	@TableField("isdeleted")
	@TableLogic

	private Integer isdeleted;

	/**
	 * 是否启用
	 */
	@ApiModelProperty("是否启用 1启用 0不启用 默认启用")
	@TableField("is_use")

	private String isUse;

	/**
	 * 父级课程号
	 */
	@ApiModelProperty("父级课程号")
	@TableField("parentid")

	private String parentid;

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
	private int type1; //1数量

	@TableField(exist = false)
	private int type2; //2数量

	@TableField(exist = false)
	private int type3; //3数量

	@TableField(exist = false)
	private int type4; //4数量

	@TableField(exist = false)
	private int type5; //5数量

	@TableField(exist = false)
	private int testnum; //题目数量

	@TableField(exist = false)
	private List<CourseList> children;

	public int getType5() {
		return type5;
	}

	public void setType5(int type5) {
		this.type5 = type5;
	}

	public int getType1() {
		return type1;
	}

	public void setType1(int type1) {
		this.type1 = type1;
	}

	public int getType2() {
		return type2;
	}

	public void setType2(int type2) {
		this.type2 = type2;
	}

	public int getType3() {
		return type3;
	}

	public void setType3(int type3) {
		this.type3 = type3;
	}

	public int getType4() {
		return type4;
	}

	public void setType4(int type4) {
		this.type4 = type4;
	}

	public int getTestnum() {
		return testnum;
	}

	public void setTestnum(int testnum) {
		this.testnum = testnum;
	}

	public List<CourseList> getChildren() {
		return children;
	}

	public void setChildren(List<CourseList> children) {
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Integer getIsdeleted() {
		return isdeleted;
	}


	public void setIsdeleted(Integer isdeleted) {
		this.isdeleted = isdeleted;
	}


	public String getIsUse() {
		return isUse;
	}


	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}


	public String getParentid() {
		return parentid;
	}


	public void setParentid(String parentid) {
		this.parentid = parentid;
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
		return "CourseList{" +
				"id=" + id +
				", name=" + name +
				", description=" + description +
				", type=" + type +
				", path=" + path +
				", sort=" + sort +
				", isdeleted=" + isdeleted +
				", isUse=" + isUse +
				", parentid=" + parentid +
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
