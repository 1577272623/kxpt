package com.rongyungov.kxpt.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import  java.math.BigDecimal;

import  com.baomidou.mybatisplus.extension.activerecord.Model;

import  java.time.LocalDateTime;
import  com.rongyungov.framework.base.BaseEntity;

/**
 *code is far away from bug with the animal protecting
 *   @description : Exam 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-24
 */
@TableName("exam")
public class Exam extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@ApiModelProperty("编号")

	@TableId(value="id", type= IdType.AUTO)


	private Long id;

	/**
	 * 考试编号
	 */
	@ApiModelProperty("考试编号")
	@TableField("exam_no")

	private String examNo;

	/**
	 * 案例类型
	 */
	@ApiModelProperty("案例类型")
	@TableField("type")

	private String type;

	/**
	 * 组卷模式
	 */
	@ApiModelProperty("组卷模式")
	@TableField("mode")

	private String mode;

	/**
	 * 教师编号
	 */
	@ApiModelProperty("教师编号")
	@TableField("tea_no")

	private String teaNo;

	/**
	 * 班级编号
	 */
	@ApiModelProperty("班级编号")
	@TableField("class_no")

	private String classNo;

	/**
	 * 开始时间
	 */
	@ApiModelProperty("开始时间")
	@TableField("start_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime startTime;

	/**
	 * 结束时间
	 */
	@ApiModelProperty("结束时间")
	@TableField("over_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime overTime;

	/**
	 * 考试时长（小时）
	 */
	@ApiModelProperty("考试时长（小时）")
	@TableField("exam_long")

	private BigDecimal examLong;

	/**
	 * 是否开启
	 */
	@ApiModelProperty("是否开启 1开启 0关闭 默认开启")
	@TableField("is_exam")

	private String isExam;

	/**
	 * 是否删除
	 */
	@ApiModelProperty("是否删除")
	@TableField("is_delete")
	@TableLogic
	private String isDelete;

	/**
	 * 考试状态
	 */
	@ApiModelProperty("考试状态 ")
	@TableField("status")

	private String status;

	/**
	 * 备用字段1
	 */
	@ApiModelProperty("备用字段1 名称")
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

	private LocalDateTime updatedTime;

	@ApiModelProperty("排序编号")
	@TableField(exist = false)
	private int no;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getExamNo() {
		return examNo;
	}


	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getMode() {
		return mode;
	}


	public void setMode(String mode) {
		this.mode = mode;
	}


	public String getTeaNo() {
		return teaNo;
	}


	public void setTeaNo(String teaNo) {
		this.teaNo = teaNo;
	}


	public String getClassNo() {
		return classNo;
	}


	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}


	public LocalDateTime getOverTime() {
		return overTime;
	}


	public void setOverTime(LocalDateTime overTime) {
		this.overTime = overTime;
	}


	public BigDecimal getExamLong() {
		return examLong;
	}


	public void setExamLong(BigDecimal examLong) {
		this.examLong = examLong;
	}


	public String getIsExam() {
		return isExam;
	}


	public void setIsExam(String isExam) {
		this.isExam = isExam;
	}


	public String getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
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
		return "Exam{" +
				"id=" + id +
				", examNo=" + examNo +
				", type=" + type +
				", mode=" + mode +
				", teaNo=" + teaNo +
				", classNo=" + classNo +
				", startTime=" + startTime +
				", overTime=" + overTime +
				", examLong=" + examLong +
				", isExam=" + isExam +
				", isDelete=" + isDelete +
				", status=" + status +
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
