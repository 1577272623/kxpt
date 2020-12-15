package com.rongyungov.kxpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.awt.*;
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
 *   @description : Notice 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-12-02
 */
@TableName("notice")
public class Notice extends BaseEntity  implements Serializable {
private static final long serialVersionUID = 1L;

			    /**
     * 编号
     */
    @ApiModelProperty("编号")
			
		        @TableId(value="id", type= IdType.AUTO)
		
					
	private Long id;

		    /**
     * 标题
     */
    @ApiModelProperty("标题")
		    @TableField("title")
				
	private String title;

		    /**
     * 内容
     */
    @ApiModelProperty("内容")
		    @TableField("content")
				
	private String content;

	/**
	 * 班级编号
	 */
	@ApiModelProperty("班级编号")
	@TableField("class_no")

	private String classNo;

		    /**
     * 附件
     */
    @ApiModelProperty("附件")
		    @TableField("file")
				
	private String file;

		    /**
     * 属性
     */
    @ApiModelProperty("属性")
		    @TableField("type")
				
	private String type;

		    /**
     * 是否在首页显示。1为展示
     */
    @ApiModelProperty("是否在首页显示。1为展示 默认为0不展示")
		    @TableField("show_index")
				
	private String showIndex;

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

	/**
	 * 编号
	 */
	@ApiModelProperty("编号 no")

	@TableId(value="no")
	@TableField(exist = false)
	private Integer no;

	@TableField(exist = false)
	private List<String> class_name;


	@TableField(exist = false)
	private List<DataList> noticeList;

	@TableField(exist = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime selectfirstTime;


	@TableField(exist = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private LocalDateTime selectsecondTime;

	public List<String> getClass_name() {
		return class_name;
	}

	public void setClass_name(List<String> class_name) {
		this.class_name = class_name;
	}

	public List<DataList> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<DataList> noticeList) {
		this.noticeList = noticeList;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public LocalDateTime getSelectfirstTime() {
		return selectfirstTime;
	}

	public void setSelectfirstTime(LocalDateTime selectfirstTime) {
		this.selectfirstTime = selectfirstTime;
	}

	public LocalDateTime getSelectsecondTime() {
		return selectsecondTime;
	}

	public void setSelectsecondTime(LocalDateTime selectsecondTime) {
		this.selectsecondTime = selectsecondTime;
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

					
        public String getTitle() {
                return title;
                }


			            public void setTitle(String title) {
			                this.title = title;
			                }

					
        public String getContent() {
                return content;
                }


			            public void setContent(String content) {
			                this.content = content;
			                }

					
        public String getFile() {
                return file;
                }


			            public void setFile(String file) {
			                this.file = file;
			                }

					
        public String getType() {
                return type;
                }


			            public void setType(String type) {
			                this.type = type;
			                }

					
        public String getShowIndex() {
                return showIndex;
                }


			            public void setShowIndex(String showIndex) {
			                this.showIndex = showIndex;
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
        return "Notice{" +
			                "id=" + id +
					                ", title=" + title +
					                ", content=" + content +
					                ", file=" + file +
					                ", type=" + type +
					                ", showIndex=" + showIndex +
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
