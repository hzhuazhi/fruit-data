package com.fruit.data.master.core.model;

import com.fruit.data.master.core.protocol.page.BasePage;

import java.io.Serializable;

/**
 * @Description TODO
 * @Date 2020/9/16 21:14
 * @Version 1.0
 */
public class MobileCardDataModel extends BasePage implements Serializable {
    private static final long   serialVersionUID = 1233223301144L;


    /**
     * 自增主键ID
     */
    private Long id;

    /**
     * 类别的主键ID：对应表tb_ga_question_m的主键ID
     */
    private Long mobileCardId;

    /**
     * 回调对照凭证短信来源:例如招商银行的是95555
     */
    private String phoneNum;
    private String  smsContent;
    private String  smsNum;
    private Integer dataType;
    private Integer curday;
    private Integer curhour;
    private Integer curminute;
    private Integer runNum;
    private String  runStatus;
    private String  createTime;
    private String  updateTime;
    private String  markPosition;
    private String  reportTime;
    private String  tsTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMobileCardId() {
        return mobileCardId;
    }

    public void setMobileCardId(Long mobileCardId) {
        this.mobileCardId = mobileCardId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public Integer getCurday() {
        return curday;
    }

    public void setCurday(Integer curday) {
        this.curday = curday;
    }

    public Integer getCurhour() {
        return curhour;
    }

    public void setCurhour(Integer curhour) {
        this.curhour = curhour;
    }

    public Integer getCurminute() {
        return curminute;
    }

    public void setCurminute(Integer curminute) {
        this.curminute = curminute;
    }

    public Integer getRunNum() {
        return runNum;
    }

    public void setRunNum(Integer runNum) {
        this.runNum = runNum;
    }

    public String getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(String runStatus) {
        this.runStatus = runStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTsTime() {
        return tsTime;
    }

    public void setTsTime(String tsTime) {
        this.tsTime = tsTime;
    }

    public String getSmsNum() {
        return smsNum;
    }

    public void setSmsNum(String smsNum) {
        this.smsNum = smsNum;
    }

    public String getMarkPosition() {
        return markPosition;
    }

    public void setMarkPosition(String markPosition) {
        this.markPosition = markPosition;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
}
