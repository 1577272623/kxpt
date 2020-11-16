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
 *   @description : Teacher 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-11
 */
@TableName("teacher")
public class Teacher extends BaseEntity  implements Serializable {
private static final long serialVersionUID = 1L;

			    /**
     * 编号
     */
    @ApiModelProperty("编号")
			
		        @TableId(value="id", type= IdType.AUTO)
		
					
	private Long id;

		    /**
     * 教师编码
     */
    @ApiModelProperty("教师编码")
		    @TableField("teano")
				
	private String teano;

		    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
		    @TableField("name")
				
	private String name;

		    /**
     * 密码
     */
    @ApiModelProperty("密码")
		    @TableField("password")
				
	private String password;

		    /**
     * 性别
     */
    @ApiModelProperty("性别")
		    @TableField("sex")
				
	private String sex;

		    /**
     * 院系编码
     */
    @ApiModelProperty("院系编码")
		    @TableField("depno")
				
	private String depno;

		    /**
     * 照片
     */
    @ApiModelProperty("照片")
		    @TableField("photo")
				
	private String photo;

		    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
		    @TableField("isdeleted")
			    @TableLogic
		
	private String isdeleted;

		    /**
     * 是否可登录
     */
    @ApiModelProperty("是否可登录")
		    @TableField("is_access_login")
				
	private String isAccessLogin;

		    /**
     * 电话
     */
    @ApiModelProperty("电话")
		    @TableField("tel")
				
	private String tel;

		    /**
     * 学校编号（多住户）
     */
    @ApiModelProperty("学校编号（多住户）")
		    @TableField("company_id")
				
	private String companyId;

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

					
        public String getTeano() {
                return teano;
                }


			            public void setTeano(String teano) {
			                this.teano = teano;
			                }

					
        public String getName() {
                return name;
                }


			            public void setName(String name) {
			                this.name = name;
			                }

					
        public String getPassword() {
                return password;
                }


			            public void setPassword(String password) {
			                this.password = password;
			                }

					
        public String getSex() {
                return sex;
                }


			            public void setSex(String sex) {
			                this.sex = sex;
			                }

					
        public String getDepno() {
                return depno;
                }


			            public void setDepno(String depno) {
			                this.depno = depno;
			                }

					
        public String getPhoto() {
                return photo;
                }


			            public void setPhoto(String photo) {
			                this.photo = photo;
			                }

					
        public String getIsdeleted() {
                return isdeleted;
                }


			            public void setIsdeleted(String isdeleted) {
			                this.isdeleted = isdeleted;
			                }

					
        public String getIsAccessLogin() {
                return isAccessLogin;
                }


			            public void setIsAccessLogin(String isAccessLogin) {
			                this.isAccessLogin = isAccessLogin;
			                }

					
        public String getTel() {
                return tel;
                }


			            public void setTel(String tel) {
			                this.tel = tel;
			                }

					
        public String getCompanyId() {
                return companyId;
                }


			            public void setCompanyId(String companyId) {
			                this.companyId = companyId;
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
        return "Teacher{" +
			                "id=" + id +
					                ", teano=" + teano +
					                ", name=" + name +
					                ", password=" + password +
					                ", sex=" + sex +
					                ", depno=" + depno +
					                ", photo=" + photo +
					                ", isdeleted=" + isdeleted +
					                ", isAccessLogin=" + isAccessLogin +
					                ", tel=" + tel +
					                ", companyId=" + companyId +
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
