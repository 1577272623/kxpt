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
import  com.baomidou.mybatisplus.annotation.TableLogic;
import  com.rongyungov.framework.base.BaseEntity;
import  com.baomidou.mybatisplus.annotation.TableField;

/**
 *code is far away from bug with the animal protecting
 *   @description : CourseData 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-26
 */
@TableName("course_data")
public class CourseData extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@ApiModelProperty("编号")

	@TableId(value="id", type= IdType.AUTO)


	private Long id;

	/**
	 * 课程id 做绑定
	 */
	@ApiModelProperty("课程id 做绑定")
	@TableField("course_id")

	private Integer courseId;

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
	 * 课程视频
	 */
	@ApiModelProperty("课程视频")
	@TableField("video")

	private String video;

	/**
	 * 课程练习
	 */
	@ApiModelProperty("课程练习")
	@TableField("exercise")

	private String exercise;

	/**
	 * 课程资料
	 */
	@ApiModelProperty("课程资料")
	@TableField("data")

	private String data;

	@TableField("isdeleted")
	@TableLogic

	private String isdeleted;

	/**
	 * 是否启用
	 */
	@ApiModelProperty("是否启用")
	@TableField("is_use")

	private String isUse;

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



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getCourseId() {
		return courseId;
	}


	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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


	public String getVideo() {
		return video;
	}


	public void setVideo(String video) {
		this.video = video;
	}


	public String getExercise() {
		return exercise;
	}


	public void setExercise(String exercise) {
		this.exercise = exercise;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getIsdeleted() {
		return isdeleted;
	}


	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}


	public String getIsUse() {
		return isUse;
	}


	public void setIsUse(String isUse) {
		this.isUse = isUse;
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
		return "CourseData{" +
				"id=" + id +
				", courseId=" + courseId +
				", name=" + name +
				", description=" + description +
				", video=" + video +
				", exercise=" + exercise +
				", data=" + data +
				", isdeleted=" + isdeleted +
				", isUse=" + isUse +
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
