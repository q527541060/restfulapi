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
@TableName("a_fov")
public class AFov implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String pcbIdLine;
    private Integer aoiMode;
    private String pcbImagePath;
    private String boardposition;
    private String pcbImageBase64;
    private Integer fovIndex;
    private String fovposition;
    private String fovimageInfo;
    private String fovimageBase64;
    private String fov3dImageBase64;
    private Date inspectStarttime;
    private Date inspectEndtime;
    private String remark;

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

    public String getPcbIdLine() {
        return pcbIdLine;
    }

    public void setPcbIdLine(String pcbIdLine) {
        this.pcbIdLine = pcbIdLine;
    }

    public Integer getAoiMode() {
        return aoiMode;
    }

    public void setAoiMode(Integer aoiMode) {
        this.aoiMode = aoiMode;
    }

    public String getPcbImagePath() {
        return pcbImagePath;
    }

    public void setPcbImagePath(String pcbImagePath) {
        this.pcbImagePath = pcbImagePath;
    }

    public String getBoardposition() {
        return boardposition;
    }

    public void setBoardposition(String boardposition) {
        this.boardposition = boardposition;
    }

    public String getPcbImageBase64() {
        return pcbImageBase64;
    }

    public void setPcbImageBase64(String pcbImageBase64) {
        this.pcbImageBase64 = pcbImageBase64;
    }

    public Integer getFovIndex() {
        return fovIndex;
    }

    public void setFovIndex(Integer fovIndex) {
        this.fovIndex = fovIndex;
    }

    public String getFovposition() {
        return fovposition;
    }

    public void setFovposition(String fovposition) {
        this.fovposition = fovposition;
    }

    public String getFovimageInfo() {
        return fovimageInfo;
    }

    public void setFovimageInfo(String fovimageInfo) {
        this.fovimageInfo = fovimageInfo;
    }

    public String getFovimageBase64() {
        return fovimageBase64;
    }

    public void setFovimageBase64(String fovimageBase64) {
        this.fovimageBase64 = fovimageBase64;
    }

    public String getFov3dImageBase64() {
        return fov3dImageBase64;
    }

    public void setFov3dImageBase64(String fov3dImageBase64) {
        this.fov3dImageBase64 = fov3dImageBase64;
    }

    public Date getInspectStarttime() {
        return inspectStarttime;
    }

    public void setInspectStarttime(Date inspectStarttime) {
        this.inspectStarttime = inspectStarttime;
    }

    public Date getInspectEndtime() {
        return inspectEndtime;
    }

    public void setInspectEndtime(Date inspectEndtime) {
        this.inspectEndtime = inspectEndtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AFov{" +
        ", id=" + id +
        ", pcbIdLine=" + pcbIdLine +
        ", aoiMode=" + aoiMode +
        ", pcbImagePath=" + pcbImagePath +
        ", boardposition=" + boardposition +
        ", pcbImageBase64=" + pcbImageBase64 +
        ", fovIndex=" + fovIndex +
        ", fovposition=" + fovposition +
        ", fovimageInfo=" + fovimageInfo +
        ", fovimageBase64=" + fovimageBase64 +
        ", fov3dImageBase64=" + fov3dImageBase64 +
        ", inspectStarttime=" + inspectStarttime +
        ", inspectEndtime=" + inspectEndtime +
        ", remark=" + remark +
        "}";
    }
}
