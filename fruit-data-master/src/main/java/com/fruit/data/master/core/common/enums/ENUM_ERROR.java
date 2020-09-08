package com.fruit.data.master.core.common.enums;

/**
 * @Description TODO
 * @Author long
 * @Date 2020/1/2 17:32
 * @Version 1.0
 */
public enum ENUM_ERROR {

    SERVER_ERRER("A00001", "手机号码已经注册!", "注册模块、手机号码已经注册!"),
    SERVER_OK("0", "请求正常！", "请求正常!"),
    INVALID_USER("-1", "无效的请求！", "无效的请求!"),


    ;
    /**
     * 错误码
     */
    private String eCode;
    /**
     * 给客户端看的错误信息
     */
    private String eDesc;
    /**
     * 插入数据库的错误信息
     */
    private String dbDesc;

    private ENUM_ERROR(String eCode, String eDesc, String dbDesc) {
        this.eCode = eCode;
        this.eDesc = eDesc;
        this.dbDesc =dbDesc;
    }

    public String geteCode() {
        return eCode;
    }

    public void seteCode(String eCode) {
        this.eCode = eCode;
    }

    public String geteDesc() {
        return eDesc;
    }

    public void seteDesc(String eDesc) {
        this.eDesc = eDesc;
    }

    public String getDbDesc() {
        return dbDesc;
    }

    public void setDbDesc(String dbDesc) {
        this.dbDesc = dbDesc;
    }
}
