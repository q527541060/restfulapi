package com.sinictek.restfulapi.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author sinictek-pd
 * @since 2020-12-09
 */
@TableName("a_component")
public class AComponent implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String pcbIdLine;
    private Integer aoiMode;
    private Integer arrayIndex;
    private Integer fovIndex;
    private String partdesignate;
    private String partno;
    private String packagetype;
    private String componentposition;
    private String componentType;
    private Integer result;
    private String defectType;
    private String imageInfo;
    private String valueInfo;
    private Double height;
    private Double perheight;
    private Double xshift;
    private Double perxshift;
    private Double yshift;
    private Double peryshift;
    private Double angle;
    private Double perangle;
    private Double volume;
    private Double bigvolume;
    private Double planeness;
    private Double uplanenesswindowid;
    private Double lplanenesswindowid;
    private Double linearity;
    private Double ulinearitywindowid;
    private Double llinearitywindowid;
    private Double similarity;
    private Double polarity;
    private Double area;
    private Double bigarea;
    private Double perarea;
    private Date inspectStarttime;
    private Date inspectEndtime;

    private String comImageBase64;
    private String com3dImageBase64;
    private String historyDefectRecord;
    private String remark;
    @TableField(exist = false)
    private List<AWindow> windows;
    private  String create_time;

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getComImageBase64() {
        return comImageBase64;
    }
    public void setComImageBase64(String comImageBase64) {
        this.comImageBase64 = comImageBase64;
    }

    public String getCom3dImageBase64() {
        return com3dImageBase64;
    }

    public void setCom3dImageBase64(String com3dImageBase64) {
        this.com3dImageBase64 = com3dImageBase64;
    }

    public String getHistoryDefectRecord() {
        return historyDefectRecord;
    }

    public void setHistoryDefectRecord(String historyDefectRecord) {
        this.historyDefectRecord = historyDefectRecord;
    }
    public List<AWindow> getWindows() {
        return windows;
    }

    public void setWindows(List<AWindow> windows) {
        this.windows = windows;
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

    public Integer getArrayIndex() {
        return arrayIndex;
    }

    public void setArrayIndex(Integer arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public Integer getFovIndex() {
        return fovIndex;
    }

    public void setFovIndex(Integer fovIndex) {
        this.fovIndex = fovIndex;
    }

    public String getPartdesignate() {
        return partdesignate;
    }

    public void setPartdesignate(String partdesignate) {
        this.partdesignate = partdesignate;
    }

    public String getPartno() {
        return partno;
    }

    public void setPartno(String partno) {
        this.partno = partno;
    }

    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype;
    }

    public String getComponentposition() {
        return componentposition;
    }

    public void setComponentposition(String componentposition) {
        this.componentposition = componentposition;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getDefectType() {
        return defectType;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public String getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(String imageInfo) {
        this.imageInfo = imageInfo;
    }

    public String getValueInfo() {
        return valueInfo;
    }

    public void setValueInfo(String valueInfo) {
        this.valueInfo = valueInfo;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getPerheight() {
        return perheight;
    }

    public void setPerheight(Double perheight) {
        this.perheight = perheight;
    }

    public Double getXshift() {
        return xshift;
    }

    public void setXshift(Double xshift) {
        this.xshift = xshift;
    }

    public Double getPerxshift() {
        return perxshift;
    }

    public void setPerxshift(Double perxshift) {
        this.perxshift = perxshift;
    }

    public Double getYshift() {
        return yshift;
    }

    public void setYshift(Double yshift) {
        this.yshift = yshift;
    }

    public Double getPeryshift() {
        return peryshift;
    }

    public void setPeryshift(Double peryshift) {
        this.peryshift = peryshift;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public Double getPerangle() {
        return perangle;
    }

    public void setPerangle(Double perangle) {
        this.perangle = perangle;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getBigvolume() {
        return bigvolume;
    }

    public void setBigvolume(Double bigvolume) {
        this.bigvolume = bigvolume;
    }

    public Double getPlaneness() {
        return planeness;
    }

    public void setPlaneness(Double planeness) {
        this.planeness = planeness;
    }

    public Double getUplanenesswindowid() {
        return uplanenesswindowid;
    }

    public void setUplanenesswindowid(Double uplanenesswindowid) {
        this.uplanenesswindowid = uplanenesswindowid;
    }

    public Double getLplanenesswindowid() {
        return lplanenesswindowid;
    }

    public void setLplanenesswindowid(Double lplanenesswindowid) {
        this.lplanenesswindowid = lplanenesswindowid;
    }

    public Double getLinearity() {
        return linearity;
    }

    public void setLinearity(Double linearity) {
        this.linearity = linearity;
    }

    public Double getUlinearitywindowid() {
        return ulinearitywindowid;
    }

    public void setUlinearitywindowid(Double ulinearitywindowid) {
        this.ulinearitywindowid = ulinearitywindowid;
    }

    public Double getLlinearitywindowid() {
        return llinearitywindowid;
    }

    public void setLlinearitywindowid(Double llinearitywindowid) {
        this.llinearitywindowid = llinearitywindowid;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Double similarity) {
        this.similarity = similarity;
    }

    public Double getPolarity() {
        return polarity;
    }

    public void setPolarity(Double polarity) {
        this.polarity = polarity;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getBigarea() {
        return bigarea;
    }

    public void setBigarea(Double bigarea) {
        this.bigarea = bigarea;
    }

    public Double getPerarea() {
        return perarea;
    }

    public void setPerarea(Double perarea) {
        this.perarea = perarea;
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
        return "AComponent{" +
        ", id=" + id +
        ", pcbIdLine=" + pcbIdLine +
        ", aoiMode=" + aoiMode +
        ", arrayIndex=" + arrayIndex +
        ", fovIndex=" + fovIndex +
        ", partdesignate=" + partdesignate +
        ", partno=" + partno +
        ", packagetype=" + packagetype +
        ", componentposition=" + componentposition +
        ", componentType=" + componentType +
        ", result=" + result +
        ", defectType=" + defectType +
        ", imageInfo=" + imageInfo +
        ", valueInfo=" + valueInfo +
        ", height=" + height +
        ", perheight=" + perheight +
        ", xshift=" + xshift +
        ", perxshift=" + perxshift +
        ", yshift=" + yshift +
        ", peryshift=" + peryshift +
        ", angle=" + angle +
        ", perangle=" + perangle +
        ", volume=" + volume +
        ", bigvolume=" + bigvolume +
        ", planeness=" + planeness +
        ", uplanenesswindowid=" + uplanenesswindowid +
        ", lplanenesswindowid=" + lplanenesswindowid +
        ", linearity=" + linearity +
        ", ulinearitywindowid=" + ulinearitywindowid +
        ", llinearitywindowid=" + llinearitywindowid +
        ", similarity=" + similarity +
        ", polarity=" + polarity +
        ", area=" + area +
        ", bigarea=" + bigarea +
        ", perarea=" + perarea +
        ", inspectStarttime=" + inspectStarttime +
        ", inspectEndtime=" + inspectEndtime +
        ", remark=" + remark +
        "}";
    }
}
