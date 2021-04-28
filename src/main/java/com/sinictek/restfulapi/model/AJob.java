package com.sinictek.restfulapi.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-09
 */
@TableName("a_job")
public class AJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String jobName;
    private String jobVersion;
    private String lineNo;
    private Date creatDate;
    private Date updateDate;
    private String remark;
    private Integer aoiMode;
    private  String create_time;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobVersion() {
        return jobVersion;
    }

    public void setJobVersion(String jobVersion) {
        this.jobVersion = jobVersion;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAoiMode() {
        return aoiMode;
    }

    public void setAoiMode(Integer aoiMode) {
        this.aoiMode = aoiMode;
    }

    @Override
    public String toString() {
        return "AJob{" +
        ", id=" + id +
        ", jobName=" + jobName +
        ", jobVersion=" + jobVersion +
        ", lineNo=" + lineNo +
        ", creatDate=" + creatDate +
        ", updateDate=" + updateDate +
        ", remark=" + remark +
        ", aoiMode=" + aoiMode +
        "}";
    }
}
