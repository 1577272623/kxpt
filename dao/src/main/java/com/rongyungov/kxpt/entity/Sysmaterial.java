package com.rongyungov.kxpt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rongyungov.framework.base.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * code is far away from bug with the animal protecting
 *
 * @author Cai
 * @description : Sysmaterial 实体类
 * ---------------------------------
 * @since 2020-08-04
 */
@TableName("sysmaterial")
public class Sysmaterial extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 素材ID
     */
    @ApiModelProperty("素材ID")

    @TableId(value = "id", type = IdType.AUTO)


    private Long id;

    /**
     * 素材名称
     */
    @ApiModelProperty("素材名称")
    @TableField("material_name")

    private String materialName;

    /**
     * 路径
     */
    @ApiModelProperty("路径")
    @TableField("material_path")

    private String materialPath;

    /**
     * 相对路径
     */
    @ApiModelProperty("相对路径")
    @TableField("material_relative_path")

    private String materialRelativePath;

    /**
     * 标签
     */
    @ApiModelProperty("标签")
    @TableField("material_tags")

    private String materialTags;

    /**
     * 分组
     */
    @ApiModelProperty("分组")
    @TableField("material_group")

    private String materialGroup;

    /**
     * 上传时间
     */
    @ApiModelProperty("上传时间")
    @TableField("upload_time")

    private LocalDateTime uploadTime;

    /**
     * 上传用户
     */
    @ApiModelProperty("上传用户")
    @TableField("upload_user")

    private String uploadUser;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getMaterialName() {
        return materialName;
    }


    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }


    public String getMaterialPath() {
        return materialPath;
    }


    public void setMaterialPath(String materialPath) {
        this.materialPath = materialPath;
    }


    public String getMaterialRelativePath() {
        return materialRelativePath;
    }


    public void setMaterialRelativePath(String materialRelativePath) {
        this.materialRelativePath = materialRelativePath;
    }


    public String getMaterialTags() {
        return materialTags;
    }


    public void setMaterialTags(String materialTags) {
        this.materialTags = materialTags;
    }


    public String getMaterialGroup() {
        return materialGroup;
    }


    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }


    public LocalDateTime getUploadTime() {
        return uploadTime;
    }


    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }


    public String getUploadUser() {
        return uploadUser;
    }


    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }


    @Override
    public String toString() {
        return "Sysmaterial{" +
                "id=" + id +
                ", materialName=" + materialName +
                ", materialPath=" + materialPath +
                ", materialRelativePath=" + materialRelativePath +
                ", materialTags=" + materialTags +
                ", materialGroup=" + materialGroup +
                ", uploadTime=" + uploadTime +
                ", uploadUser=" + uploadUser +
                "}";
    }


}
