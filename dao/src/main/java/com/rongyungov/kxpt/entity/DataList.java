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
 *   @description : DataList 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-12-08
 */
@TableName("data_list")
public class DataList extends BaseEntity  implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 编号
	 */
	@ApiModelProperty("编号")

	@TableId(value="id", type= IdType.AUTO)


	private Long id;

	/**
	 * 描述
	 */
	@ApiModelProperty("描述")
	@TableField("description")

	private String description;

	/**
	 * 是否删除
	 */
	@ApiModelProperty("是否删除")
	@TableField("isdeleted")
	@TableLogic

	private String isdeleted;

	/**
	 * 文件名
	 */
	@ApiModelProperty("文件名")
	@TableField("name")

	private String name;

	/**
	 * 文件路径
	 */
	@ApiModelProperty("文件路径")
	@TableField("file")

	private String file;

	/**
	 * 文件图标
	 */
	@ApiModelProperty("文件图标")
	@TableField("photo")

	private String photo;

	/**
	 * 文件属性
	 */
	@ApiModelProperty("文件属性")
	@TableField("type")

	private String type;

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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private String updatedBy;

	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	@TableField("updated_time")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime updatedTime;



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getIsdeleted() {
		return isdeleted;
	}


	public void setIsdeleted(String isdeleted) {
		this.isdeleted = isdeleted;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getFile() {
		return file;
	}


	public void setFile(String file) {
		this.file = file;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
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
		return "DataList{" +
				"id=" + id +
				", description=" + description +
				", isdeleted=" + isdeleted +
				", name=" + name +
				", file=" + file +
				", photo=" + photo +
				", type=" + type +
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
