package com.fruit.data.master.core.protocol.request.mobilecard;

import com.fruit.data.master.core.protocol.base.BaseRequest;

import java.io.Serializable;

/**
 * @Description 协议：手机卡
 * @Author yoko
 * @Date 2020/9/15 22:01
 * @Version 1.0
 */
public class RequestMobileCard extends BaseRequest implements Serializable {
    public static final long   serialVersionUID = 1233293332313L;

    public String phoneNum;

    public RequestMobileCard(){

    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
