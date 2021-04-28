package com.sinictek.restfulapi.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
@TableName("a_pcb")
public class APcb implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String lineNo;
    private String jobName;
    private Integer aoiMode;
    private String jobversion;
    private Date jobmodifyDate;
    private Date inspectStarttime;
    private Date inspectEndtime;
    private Integer inspectResult;
    private Integer laneNo;
    private String pcbIdLine;
    private String carrierbarcode;
    private String boardBarcode;
    private String arrayInfo;
    private String componentTableName;
    private String fovTableName;
    private String windowTableName;
    private Integer totalArrayCount;
    private Integer goodArrayCount;
    private Integer ngArrayCount;
    private Integer passArrayCount;
    private Integer otherArrayCount;
    private Integer totalComponentCount;
    private Integer goodComponentCount;
    private Integer passComponentCount;
    private Integer ngComponentCount;
    private Integer otherComponentCount;
    private Integer customCount;
    private Integer defaultCount;
    private Integer missingCount;
    private Integer shiftXCount;
    private Integer shiftYCount;
    private Integer rotationCount;
    private Integer bridgeCount;
    private Integer voidCount;
    private Integer tombStoneCount;
    private Integer pinLiftCount;
    private Integer solderBeadCount;
    private Integer smearCount;
    private Integer polarityCount;
    private Integer reverseCount;
    private Integer wrongPartCount;
    private Integer noSolderCount;
    private Integer copperExposureCount;
    private Integer excessSolderCount;
    private Integer solderingCount;
    private Integer excessPartsCount;
    private Integer barcodeCount;
    @TableField("eNM_Defect_Type_ENMMaxLengthCount")
    private Integer enmDefectTypeEnmmaxlengthcount;
    private Double hCpk;
    private Double aCpk;
    private Double vcpk;
    private Double shithxCpk;
    private Double shithyCpk;
    private String lcl;
    private String ucl;
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

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getAoiMode() {
        return aoiMode;
    }

    public void setAoiMode(Integer aoiMode) {
        this.aoiMode = aoiMode;
    }

    public String getJobversion() {
        return jobversion;
    }

    public void setJobversion(String jobversion) {
        this.jobversion = jobversion;
    }

    public Date getJobmodifyDate() {
        return jobmodifyDate;
    }

    public void setJobmodifyDate(Date jobmodifyDate) {
        this.jobmodifyDate = jobmodifyDate;
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

    public Integer getInspectResult() {
        return inspectResult;
    }

    public void setInspectResult(Integer inspectResult) {
        this.inspectResult = inspectResult;
    }

    public Integer getLaneNo() {
        return laneNo;
    }

    public void setLaneNo(Integer laneNo) {
        this.laneNo = laneNo;
    }

    public String getPcbIdLine() {
        return pcbIdLine;
    }

    public void setPcbIdLine(String pcbIdLine) {
        this.pcbIdLine = pcbIdLine;
    }

    public String getCarrierbarcode() {
        return carrierbarcode;
    }

    public void setCarrierbarcode(String carrierbarcode) {
        this.carrierbarcode = carrierbarcode;
    }

    public String getBoardBarcode() {
        return boardBarcode;
    }

    public void setBoardBarcode(String boardBarcode) {
        this.boardBarcode = boardBarcode;
    }

    public String getArrayInfo() {
        return arrayInfo;
    }

    public void setArrayInfo(String arrayInfo) {
        this.arrayInfo = arrayInfo;
    }

    public String getComponentTableName() {
        return componentTableName;
    }

    public void setComponentTableName(String componentTableName) {
        this.componentTableName = componentTableName;
    }

    public String getFovTableName() {
        return fovTableName;
    }

    public void setFovTableName(String fovTableName) {
        this.fovTableName = fovTableName;
    }

    public String getWindowTableName() {
        return windowTableName;
    }

    public void setWindowTableName(String windowTableName) {
        this.windowTableName = windowTableName;
    }

    public Integer getTotalArrayCount() {
        return totalArrayCount;
    }

    public void setTotalArrayCount(Integer totalArrayCount) {
        this.totalArrayCount = totalArrayCount;
    }

    public Integer getGoodArrayCount() {
        return goodArrayCount;
    }

    public void setGoodArrayCount(Integer goodArrayCount) {
        this.goodArrayCount = goodArrayCount;
    }

    public Integer getNgArrayCount() {
        return ngArrayCount;
    }

    public void setNgArrayCount(Integer ngArrayCount) {
        this.ngArrayCount = ngArrayCount;
    }

    public Integer getPassArrayCount() {
        return passArrayCount;
    }

    public void setPassArrayCount(Integer passArrayCount) {
        this.passArrayCount = passArrayCount;
    }

    public Integer getOtherArrayCount() {
        return otherArrayCount;
    }

    public void setOtherArrayCount(Integer otherArrayCount) {
        this.otherArrayCount = otherArrayCount;
    }

    public Integer getTotalComponentCount() {
        return totalComponentCount;
    }

    public void setTotalComponentCount(Integer totalComponentCount) {
        this.totalComponentCount = totalComponentCount;
    }

    public Integer getGoodComponentCount() {
        return goodComponentCount;
    }

    public void setGoodComponentCount(Integer goodComponentCount) {
        this.goodComponentCount = goodComponentCount;
    }

    public Integer getPassComponentCount() {
        return passComponentCount;
    }

    public void setPassComponentCount(Integer passComponentCount) {
        this.passComponentCount = passComponentCount;
    }

    public Integer getNgComponentCount() {
        return ngComponentCount;
    }

    public void setNgComponentCount(Integer ngComponentCount) {
        this.ngComponentCount = ngComponentCount;
    }

    public Integer getOtherComponentCount() {
        return otherComponentCount;
    }

    public void setOtherComponentCount(Integer otherComponentCount) {
        this.otherComponentCount = otherComponentCount;
    }

    public Integer getCustomCount() {
        return customCount;
    }

    public void setCustomCount(Integer customCount) {
        this.customCount = customCount;
    }

    public Integer getDefaultCount() {
        return defaultCount;
    }

    public void setDefaultCount(Integer defaultCount) {
        this.defaultCount = defaultCount;
    }

    public Integer getMissingCount() {
        return missingCount;
    }

    public void setMissingCount(Integer missingCount) {
        this.missingCount = missingCount;
    }

    public Integer getShiftXCount() {
        return shiftXCount;
    }

    public void setShiftXCount(Integer shiftXCount) {
        this.shiftXCount = shiftXCount;
    }

    public Integer getShiftYCount() {
        return shiftYCount;
    }

    public void setShiftYCount(Integer shiftYCount) {
        this.shiftYCount = shiftYCount;
    }

    public Integer getRotationCount() {
        return rotationCount;
    }

    public void setRotationCount(Integer rotationCount) {
        this.rotationCount = rotationCount;
    }

    public Integer getBridgeCount() {
        return bridgeCount;
    }

    public void setBridgeCount(Integer bridgeCount) {
        this.bridgeCount = bridgeCount;
    }

    public Integer getVoidCount() {
        return voidCount;
    }

    public void setVoidCount(Integer voidCount) {
        this.voidCount = voidCount;
    }

    public Integer getTombStoneCount() {
        return tombStoneCount;
    }

    public void setTombStoneCount(Integer tombStoneCount) {
        this.tombStoneCount = tombStoneCount;
    }

    public Integer getPinLiftCount() {
        return pinLiftCount;
    }

    public void setPinLiftCount(Integer pinLiftCount) {
        this.pinLiftCount = pinLiftCount;
    }

    public Integer getSolderBeadCount() {
        return solderBeadCount;
    }

    public void setSolderBeadCount(Integer solderBeadCount) {
        this.solderBeadCount = solderBeadCount;
    }

    public Integer getSmearCount() {
        return smearCount;
    }

    public void setSmearCount(Integer smearCount) {
        this.smearCount = smearCount;
    }

    public Integer getPolarityCount() {
        return polarityCount;
    }

    public void setPolarityCount(Integer polarityCount) {
        this.polarityCount = polarityCount;
    }

    public Integer getReverseCount() {
        return reverseCount;
    }

    public void setReverseCount(Integer reverseCount) {
        this.reverseCount = reverseCount;
    }

    public Integer getWrongPartCount() {
        return wrongPartCount;
    }

    public void setWrongPartCount(Integer wrongPartCount) {
        this.wrongPartCount = wrongPartCount;
    }

    public Integer getNoSolderCount() {
        return noSolderCount;
    }

    public void setNoSolderCount(Integer noSolderCount) {
        this.noSolderCount = noSolderCount;
    }

    public Integer getCopperExposureCount() {
        return copperExposureCount;
    }

    public void setCopperExposureCount(Integer copperExposureCount) {
        this.copperExposureCount = copperExposureCount;
    }

    public Integer getExcessSolderCount() {
        return excessSolderCount;
    }

    public void setExcessSolderCount(Integer excessSolderCount) {
        this.excessSolderCount = excessSolderCount;
    }

    public Integer getSolderingCount() {
        return solderingCount;
    }

    public void setSolderingCount(Integer solderingCount) {
        this.solderingCount = solderingCount;
    }

    public Integer getExcessPartsCount() {
        return excessPartsCount;
    }

    public void setExcessPartsCount(Integer excessPartsCount) {
        this.excessPartsCount = excessPartsCount;
    }

    public Integer getBarcodeCount() {
        return barcodeCount;
    }

    public void setBarcodeCount(Integer barcodeCount) {
        this.barcodeCount = barcodeCount;
    }

    public Integer getEnmDefectTypeEnmmaxlengthcount() {
        return enmDefectTypeEnmmaxlengthcount;
    }

    public void setEnmDefectTypeEnmmaxlengthcount(Integer enmDefectTypeEnmmaxlengthcount) {
        this.enmDefectTypeEnmmaxlengthcount = enmDefectTypeEnmmaxlengthcount;
    }

    public Double gethCpk() {
        return hCpk;
    }

    public void sethCpk(Double hCpk) {
        this.hCpk = hCpk;
    }

    public Double getaCpk() {
        return aCpk;
    }

    public void setaCpk(Double aCpk) {
        this.aCpk = aCpk;
    }

    public Double getVcpk() {
        return vcpk;
    }

    public void setVcpk(Double vcpk) {
        this.vcpk = vcpk;
    }

    public Double getShithxCpk() {
        return shithxCpk;
    }

    public void setShithxCpk(Double shithxCpk) {
        this.shithxCpk = shithxCpk;
    }

    public Double getShithyCpk() {
        return shithyCpk;
    }

    public void setShithyCpk(Double shithyCpk) {
        this.shithyCpk = shithyCpk;
    }

    public String getLcl() {
        return lcl;
    }

    public void setLcl(String lcl) {
        this.lcl = lcl;
    }

    public String getUcl() {
        return ucl;
    }

    public void setUcl(String ucl) {
        this.ucl = ucl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "APcb{" +
        ", id=" + id +
        ", lineNo=" + lineNo +
        ", jobName=" + jobName +
        ", aoiMode=" + aoiMode +
        ", jobversion=" + jobversion +
        ", jobmodifyDate=" + jobmodifyDate +
        ", inspectStarttime=" + inspectStarttime +
        ", inspectEndtime=" + inspectEndtime +
        ", inspectResult=" + inspectResult +
        ", laneNo=" + laneNo +
        ", pcbIdLine=" + pcbIdLine +
        ", carrierbarcode=" + carrierbarcode +
        ", boardBarcode=" + boardBarcode +
        ", arrayInfo=" + arrayInfo +
        ", componentTableName=" + componentTableName +
        ", fovTableName=" + fovTableName +
        ", windowTableName=" + windowTableName +
        ", totalArrayCount=" + totalArrayCount +
        ", goodArrayCount=" + goodArrayCount +
        ", ngArrayCount=" + ngArrayCount +
        ", passArrayCount=" + passArrayCount +
        ", otherArrayCount=" + otherArrayCount +
        ", totalComponentCount=" + totalComponentCount +
        ", goodComponentCount=" + goodComponentCount +
        ", passComponentCount=" + passComponentCount +
        ", ngComponentCount=" + ngComponentCount +
        ", otherComponentCount=" + otherComponentCount +
        ", customCount=" + customCount +
        ", defaultCount=" + defaultCount +
        ", missingCount=" + missingCount +
        ", shiftXCount=" + shiftXCount +
        ", shiftYCount=" + shiftYCount +
        ", rotationCount=" + rotationCount +
        ", bridgeCount=" + bridgeCount +
        ", voidCount=" + voidCount +
        ", tombStoneCount=" + tombStoneCount +
        ", pinLiftCount=" + pinLiftCount +
        ", solderBeadCount=" + solderBeadCount +
        ", smearCount=" + smearCount +
        ", polarityCount=" + polarityCount +
        ", reverseCount=" + reverseCount +
        ", wrongPartCount=" + wrongPartCount +
        ", noSolderCount=" + noSolderCount +
        ", copperExposureCount=" + copperExposureCount +
        ", excessSolderCount=" + excessSolderCount +
        ", solderingCount=" + solderingCount +
        ", excessPartsCount=" + excessPartsCount +
        ", barcodeCount=" + barcodeCount +
        ", enmDefectTypeEnmmaxlengthcount=" + enmDefectTypeEnmmaxlengthcount +
        ", hCpk=" + hCpk +
        ", aCpk=" + aCpk +
        ", vcpk=" + vcpk +
        ", shithxCpk=" + shithxCpk +
        ", shithyCpk=" + shithyCpk +
        ", lcl=" + lcl +
        ", ucl=" + ucl +
        ", remark=" + remark +
        "}";
    }
}
