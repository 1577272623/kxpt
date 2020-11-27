package com.rongyungov.kxpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import lombok.experimental.Accessors;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
 *   @description : Test 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-26
 */
@TableName("test")
public class Test extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@ApiModelProperty("编号")

	@TableId(value="id", type= IdType.AUTO)


	private Long id;

	/**
	 * 课程编号
	 */
	@ApiModelProperty("课程编号")
	@TableField("keno")

	private String keno;

	/**
	 * 试题编号
	 */
	@ApiModelProperty("试题编号")
	@TableField("stno")

	private String stno;

	/**
	 * 试题类型 1判断题 2单选题 3多选题 4填空题
	 */
	@ApiModelProperty("试题类型 1判断题 2单选题 3多选题 4填空题")
	@TableField("st_type")

	private String stType;

	/**
	 * 试题内容
	 */
	@ApiModelProperty("试题内容")
	@TableField("st_content")

	private String stContent;

	/**
	 * 多选 d
	 */
	@ApiModelProperty("多选 d")
	@TableField("answer_d")

	private String answerD;

	/**
	 * 多选 c
	 */
	@ApiModelProperty("多选 c")
	@TableField("answer_c")

	private String answerC;

	/**
	 * 多选 b
	 */
	@ApiModelProperty("多选 b")
	@TableField("answer_b")

	private String answerB;

	/**
	 * 多选 a
	 */
	@ApiModelProperty("多选 a")
	@TableField("answer_a")

	private String answerA;

	/**
	 * 解析
	 */
	@ApiModelProperty("解析")
	@TableField("analysis")

	private String analysis;

	/**
	 * 是否删除
	 */
	@ApiModelProperty("是否删除")
	@TableField("isdeleted")
	@TableLogic

	private String isdeleted;

	/**
	 * 是否启用 1启用 0不启用 默认启用
	 */
	@ApiModelProperty("是否启用 1启用 0不启用 默认启用")
	@TableField("is_use")

	private String isUse;

	/**
	 * 试题答案
	 */
	@ApiModelProperty("试题答案")
	@TableField("st_answer")

	private String stAnswer;

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


	public String getKeno() {
		return keno;
	}


	public void setKeno(String keno) {
		this.keno = keno;
	}


	public String getStno() {
		return stno;
	}


	public void setStno(String stno) {
		this.stno = stno;
	}


	public String getStType() {
		return stType;
	}


	public void setStType(String stType) {
		this.stType = stType;
	}


	public String getStContent() {
		return stContent;
	}


	public void setStContent(String stContent) {
		this.stContent = stContent;
	}


	public String getAnswerD() {
		return answerD;
	}


	public void setAnswerD(String answerD) {
		this.answerD = answerD;
	}


	public String getAnswerC() {
		return answerC;
	}


	public void setAnswerC(String answerC) {
		this.answerC = answerC;
	}


	public String getAnswerB() {
		return answerB;
	}


	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}


	public String getAnswerA() {
		return answerA;
	}


	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}


	public String getAnalysis() {
		return analysis;
	}


	public void setAnalysis(String analysis) {
		this.analysis = analysis;
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


	public String getStAnswer() {
		return stAnswer;
	}


	public void setStAnswer(String stAnswer) {
		this.stAnswer = stAnswer;
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
		return "Test{" +
				"id=" + id +
				", keno=" + keno +
				", stno=" + stno +
				", stType=" + stType +
				", stContent=" + stContent +
				", answerD=" + answerD +
				", answerC=" + answerC +
				", answerB=" + answerB +
				", answerA=" + answerA +
				", analysis=" + analysis +
				", isdeleted=" + isdeleted +
				", isUse=" + isUse +
				", stAnswer=" + stAnswer +
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
