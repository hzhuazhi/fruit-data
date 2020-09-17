package com.fruit.data.master.util;

import com.fruit.data.master.core.common.exception.ServiceException;
import com.fruit.data.master.core.common.utils.BeanUtils;
import com.fruit.data.master.core.common.utils.DateUtil;
import com.fruit.data.master.core.common.utils.SignUtil;
import com.fruit.data.master.core.model.DateModel;
import com.fruit.data.master.core.model.MobileCardDataModel;
import com.fruit.data.master.core.model.RequestEncryptionJson;
import com.fruit.data.master.core.model.ResponseEncryptionJson;
import com.fruit.data.master.core.protocol.request.mobilecard.RequestMobileCard;
import com.fruit.data.master.core.protocol.request.result.RequestSms;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * @Description TODO
 * @Author long
 * @Date 2020/9/16 21:15
 * @Version 1.0
 */
public class SmsMethod {

    /**
     * @Description: 获取当前系统时间
     * @param
     * @return com.pf.play.rule.core.model.DateModel
     * @date 2019/11/20 22:15
     */
    public  static DateModel getDate(){
        Date date  = new Date();
        DateModel dateModel = new DateModel();
        dateModel.setCurday(DateUtil.getDayNumber(date));
        dateModel.setCurhour(DateUtil.getHour(date));
        dateModel.setCurminute(DateUtil.getCurminute(date));
        dateModel.setCreateTime(date);
        dateModel.setUpdateTime(date);
        return dateModel;
    }

    /**
     * @Description: 给sms 短信接口的数据转换成MobileCardData
     * @param smsInfo
     * @return com.hz.source.master.core.model.result.FnMobileCardDataModel
     * @date 2020/5/20 11:15
     */
    public   static MobileCardDataModel toSmsData(RequestSms smsInfo){
        DateModel dateModel =SmsMethod.getDate();
        MobileCardDataModel fnMobileCardDataModel = new   MobileCardDataModel();
        BeanUtils.copy(dateModel,fnMobileCardDataModel);
        fnMobileCardDataModel.setMarkPosition(smsInfo.getId());
        fnMobileCardDataModel.setReportTime(smsInfo.getTime());
        fnMobileCardDataModel.setPhoneNum(smsInfo.getPhone());
        fnMobileCardDataModel.setSmsContent(smsInfo.getContent());
        fnMobileCardDataModel.setSmsNum(smsInfo.getMessageCode());
        return fnMobileCardDataModel;
    }

    /**
     * @Description: check校验心跳数据
     * @param requestSms - 请求数据
     * @param secretKeySign - 签名秘钥
     * @return
     * @date 2020/9/15 22:10
     */
    public static void checkHeartbeat(RequestSms requestSms, String secretKeySign) throws Exception{
        if (requestSms == null){
            throw new ServiceException("H001", "所有数据为空!");
        }
        if (StringUtils.isBlank(requestSms.getPhone())){
            throw new ServiceException("H002", "手机号为空!");
        }

        if (StringUtils.isBlank(requestSms.getId())){
            throw new ServiceException("H003", "ID为空!");
        }

        if (StringUtils.isBlank(requestSms.getTime())){
            throw new ServiceException("H004", "时间为空!");
        }

        if (StringUtils.isBlank(requestSms.sign)){
            throw new ServiceException("H005", "签名数据为空!");
        }
        String checkSign = SignUtil.getSgin(requestSms.getPhone(), requestSms.getId(), requestSms.getTime(), secretKeySign);
        if (!checkSign.equals(requestSms.sign)){
            throw new ServiceException("H006", "签名错误!");
        }

    }
}
