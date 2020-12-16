package com.rongyungov.kxpt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import  com.baomidou.mybatisplus.annotation.TableName;
    import  com.baomidou.mybatisplus.extension.activerecord.Model;
    import  java.time.LocalDateTime;
    import  com.rongyungov.framework.base.BaseEntity;
    import  com.baomidou.mybatisplus.annotation.TableField;
    
/**
 *code is far away from bug with the animal protecting
 *   @description : GradeVo 实体类
 *   ---------------------------------
 * 	 @author li
 *   @since 2020-12-16
 */
@TableName("grade_vo")
public class GradeVo extends BaseEntity  implements Serializable {
private static final long serialVersionUID = 1L;

		    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
		    @TableField("student_name")
				
	private String studentName;

		    /**
     * 部门名称
     */
    @ApiModelProperty("部门名称")
		    @TableField("department_name")
				
	private String departmentName;

		    /**
     * 学号
     */
    @ApiModelProperty("学号")
		    @TableField("student_no")
				
	private String studentNo;

		    /**
     * 编号
     */
    @ApiModelProperty("编号")
		    @TableField("id")
				
	private Long id;

		    /**
     * 试卷 id
     */
    @ApiModelProperty("试卷 id")
		    @TableField("exam_id")
				
	private String examId;

		    /**
     * 所属部门
     */
    @ApiModelProperty("所属部门")
		    @TableField("department")
				
	private String department;

		    /**
     * 学生名
     */
    @ApiModelProperty("学生名")
		    @TableField("student")
				
	private String student;

		    /**
     * 成绩
     */
    @ApiModelProperty("成绩")
		    @TableField("grade")
				
	private String grade;

		    /**
     * 是否删除
     */
    @ApiModelProperty("是否删除")
		    @TableField("is_delete")
				
	private String isDelete;

		    /**
     * 属性 1考试 2实训 3任务 4竞赛
     */
    @ApiModelProperty("属性 1考试 2实训 3任务 4竞赛")
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


					
        public String getStudentName() {
                return studentName;
                }


			            public void setStudentName(String studentName) {
			                this.studentName = studentName;
			                }

					
        public String getDepartmentName() {
                return departmentName;
                }


			            public void setDepartmentName(String departmentName) {
			                this.departmentName = departmentName;
			                }

					
        public String getStudentNo() {
                return studentNo;
                }


			            public void setStudentNo(String studentNo) {
			                this.studentNo = studentNo;
			                }

					
        public Long getId() {
                return id;
                }


			            public void setId(Long id) {
			                this.id = id;
			                }

					
        public String getExamId() {
                return examId;
                }


			            public void setExamId(String examId) {
			                this.examId = examId;
			                }

					
        public String getDepartment() {
                return department;
                }


			            public void setDepartment(String department) {
			                this.department = department;
			                }

					
        public String getStudent() {
                return student;
                }


			            public void setStudent(String student) {
			                this.student = student;
			                }

					
        public String getGrade() {
                return grade;
                }


			            public void setGrade(String grade) {
			                this.grade = grade;
			                }

					
        public String getIsDelete() {
                return isDelete;
                }


			            public void setIsDelete(String isDelete) {
			                this.isDelete = isDelete;
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
        return "GradeVo{" +
			                "studentName=" + studentName +
					                ", departmentName=" + departmentName +
					                ", studentNo=" + studentNo +
					                ", id=" + id +
					                ", examId=" + examId +
					                ", department=" + department +
					                ", student=" + student +
					                ", grade=" + grade +
					                ", isDelete=" + isDelete +
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
