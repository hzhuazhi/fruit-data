package com.fruit.data.master.core.protocol.request.result;

import com.fruit.data.master.core.protocol.base.BaseRequest;

import java.io.Serializable;

/**
 * @Description TODO
 * @Author long
 * @Date 2020/9/16 21:12
 * @Version 1.0
 */
public class RequestSms extends BaseRequest implements Serializable {
    /***
     * 手机号
     */
    private String phone;

    /***
     * 收到的内容
     */
    private String content;
    /***
     * 短信号码
     */
    private String messageCode;
    /***
     * 时间
     */
    private String time;
    /***
     * id
     */
    private String id;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
