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
 *   @description : TaskModel 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-12-09
 */
@TableName("task_model")
public class TaskModel extends BaseEntity  implements Serializable {
private static final long serialVersionUID = 1L;

		    /**
     * 模板类型
     */
    @ApiModelProperty("模板类型")
		    @TableField("type")
				
	private String type;

		    /**
     * 模板文件
     */
    @ApiModelProperty("模板文件")
		    @TableField("file")
				
	private String file;

		    /**
     * 是否开启 默认1开启
     */
    @ApiModelProperty("是否开启 默认1开启")
		    @TableField("id_use")
				
	private String idUse;

		    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
		    @TableField("isdeleted")
			    @TableLogic
		
	private String isdeleted;

			    /**
     * 编号
     */
    @ApiModelProperty("编号")
			
		        @TableId(value="id", type= IdType.AUTO)
		
					
	private Long id;

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


					
        public String getType() {
                return type;
                }


			            public void setType(String type) {
			                this.type = type;
			                }

					
        public String getFile() {
                return file;
                }


			            public void setFile(String file) {
			                this.file = file;
			                }

					
        public String getIdUse() {
                return idUse;
                }


			            public void setIdUse(String idUse) {
			                this.idUse = idUse;
			                }

					
        public String getIsdeleted() {
                return isdeleted;
                }


			            public void setIsdeleted(String isdeleted) {
			                this.isdeleted = isdeleted;
			                }

					
        public Long getId() {
                return id;
                }


			            public void setId(Long id) {
			                this.id = id;
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
        return "TaskModel{" +
			                "type=" + type +
					                ", file=" + file +
					                ", idUse=" + idUse +
					                ", isdeleted=" + isdeleted +
					                ", id=" + id +
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
