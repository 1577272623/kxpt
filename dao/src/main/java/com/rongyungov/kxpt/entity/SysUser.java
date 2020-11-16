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
 *   @description : SysUser 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-11-16
 */
@TableName("sys_user")
public class SysUser extends BaseEntity  implements Serializable {
private static final long serialVersionUID = 1L;

			    /**
     * 编号
     */
    @ApiModelProperty("编号")
			
		        @TableId(value="id", type= IdType.AUTO)
		
					
	private Long id;

		    /**
     * 用户工号
     */
    @ApiModelProperty("用户工号")
		    @TableField("usercode")
				
	private String usercode;

		    /**
     * 用户姓名
     */
    @ApiModelProperty("用户姓名")
		    @TableField("username")
				
	private String username;

		    /**
     * 所属部门
     */
    @ApiModelProperty("所属部门")
		    @TableField("orgid")
				
	private Long orgid;

		    /**
     * 登录名
     */
    @ApiModelProperty("登录名")
		    @TableField("account")
				
	private String account;

		    /**
     * 密码
     */
    @ApiModelProperty("密码")
		    @TableField("password")
				
	private String password;

		    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
		    @TableField("email")
				
	private String email;

		    /**
     * 电话
     */
    @ApiModelProperty("电话")
		    @TableField("mobile")
				
	private String mobile;

		    /**
     * 头像
     */
    @ApiModelProperty("头像")
		    @TableField("image")
				
	private String image;

		    /**
     * 手机
     */
    @ApiModelProperty("手机")
		    @TableField("telephone")
				
	private String telephone;

		    /**
     * 是否可登陆
     */
    @ApiModelProperty("是否可登陆")
		    @TableField("is_access_login")
				
	private String isAccessLogin;

		    /**
     * 用户类型的种类
     */
    @ApiModelProperty("用户类型的种类")
		    @TableField("user_type_code")
				
	private String userTypeCode;

		    /**
     * 系统管理员
     */
    @ApiModelProperty("系统管理员")
		    @TableField("isadmin")
				
	private String isadmin;

		    /**
     * 备注
     */
    @ApiModelProperty("备注")
		    @TableField("remark")
				
	private String remark;

		    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
		    @TableField("isdeleted")
			    @TableLogic
		
	private String isdeleted;

		    /**
     * 公司编号
     */
    @ApiModelProperty("公司编号")
		    @TableField("company_id")
				
	private Long companyId;

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

					
        public String getUsercode() {
                return usercode;
                }


			            public void setUsercode(String usercode) {
			                this.usercode = usercode;
			                }

					
        public String getUsername() {
                return username;
                }


			            public void setUsername(String username) {
			                this.username = username;
			                }

					
        public Long getOrgid() {
                return orgid;
                }


			            public void setOrgid(Long orgid) {
			                this.orgid = orgid;
			                }

					
        public String getAccount() {
                return account;
                }


			            public void setAccount(String account) {
			                this.account = account;
			                }

					
        public String getPassword() {
                return password;
                }


			            public void setPassword(String password) {
			                this.password = password;
			                }

					
        public String getEmail() {
                return email;
                }


			            public void setEmail(String email) {
			                this.email = email;
			                }

					
        public String getMobile() {
                return mobile;
                }


			            public void setMobile(String mobile) {
			                this.mobile = mobile;
			                }

					
        public String getImage() {
                return image;
                }


			            public void setImage(String image) {
			                this.image = image;
			                }

					
        public String getTelephone() {
                return telephone;
                }


			            public void setTelephone(String telephone) {
			                this.telephone = telephone;
			                }

					
        public String getIsAccessLogin() {
                return isAccessLogin;
                }


			            public void setIsAccessLogin(String isAccessLogin) {
			                this.isAccessLogin = isAccessLogin;
			                }

					
        public String getUserTypeCode() {
                return userTypeCode;
                }


			            public void setUserTypeCode(String userTypeCode) {
			                this.userTypeCode = userTypeCode;
			                }

					
        public String getIsadmin() {
                return isadmin;
                }


			            public void setIsadmin(String isadmin) {
			                this.isadmin = isadmin;
			                }

					
        public String getRemark() {
                return remark;
                }


			            public void setRemark(String remark) {
			                this.remark = remark;
			                }

					
        public String getIsdeleted() {
                return isdeleted;
                }


			            public void setIsdeleted(String isdeleted) {
			                this.isdeleted = isdeleted;
			                }

					
        public Long getCompanyId() {
                return companyId;
                }


			            public void setCompanyId(Long companyId) {
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
        return "SysUser{" +
			                "id=" + id +
					                ", usercode=" + usercode +
					                ", username=" + username +
					                ", orgid=" + orgid +
					                ", account=" + account +
					                ", password=" + password +
					                ", email=" + email +
					                ", mobile=" + mobile +
					                ", image=" + image +
					                ", telephone=" + telephone +
					                ", isAccessLogin=" + isAccessLogin +
					                ", userTypeCode=" + userTypeCode +
					                ", isadmin=" + isadmin +
					                ", remark=" + remark +
					                ", isdeleted=" + isdeleted +
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
