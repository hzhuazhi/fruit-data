package com.fruit.data.master.util;

import com.fruit.data.master.core.common.exception.ServiceException;
import com.fruit.data.master.core.common.utils.DateUtil;
import com.fruit.data.master.core.common.utils.SignUtil;
import com.fruit.data.master.core.common.utils.constant.ErrorCode;
import com.fruit.data.master.core.model.bank.BankModel;
import com.fruit.data.master.core.model.mobilecard.MobileCardHeartbeatModel;
import com.fruit.data.master.core.model.mobilecard.MobileCardModel;
import com.fruit.data.master.core.protocol.request.bank.RequestBank;
import com.fruit.data.master.core.protocol.request.mobilecard.RequestMobileCard;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * @Description 公共方法类
 * @Author yoko
 * @Date 2020/1/7 20:25
 * @Version 1.0
 */
public class HodgepodgeMethod {
    private static Logger log = LoggerFactory.getLogger(HodgepodgeMethod.class);



    /**
     * @Description: 校验银行数据
     * @param requestModel
     * @return
     * @author yoko
     * @date 2020/9/5 19:55
    */
    public static void checkBank(RequestBank requestModel) throws Exception {
        if (requestModel == null) {
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00001.geteCode(), ErrorCode.ENUM_ERROR.B00001.geteDesc());
        }
        if (StringUtils.isBlank(requestModel.sourceId)){
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00002.geteCode(), ErrorCode.ENUM_ERROR.B00002.geteDesc());
        }
        if (StringUtils.isBlank(requestModel.bankName)){
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00003.geteCode(), ErrorCode.ENUM_ERROR.B00003.geteDesc());
        }
        if (StringUtils.isBlank(requestModel.bankCard)){
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00004.geteCode(), ErrorCode.ENUM_ERROR.B00004.geteDesc());
        }
        if (StringUtils.isBlank(requestModel.accountName)){
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00005.geteCode(), ErrorCode.ENUM_ERROR.B00005.geteDesc());
        }
    }

    /**
     * @Description: 组装查询银行卡的查询条件
     * @param sourceId - 源银行卡主键ID
     * @return
     * @author yoko
     * @date 2020/9/6 15:54
    */
    public static BankModel assembleBankBySourceQuery(String sourceId){
        BankModel resBean = new BankModel();
        resBean.setSourceId(sourceId);
        return resBean;
    }

    /**
     * @Description: 校验银行数据是否不为空
     * @param bankModel
     * @return
     * @author yoko
     * @date 2020/9/5 19:55
     */
    public static void checkBankData(BankModel bankModel) throws Exception {
        if (bankModel != null && bankModel.getId() != null && bankModel.getId() > 0) {
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00006.geteCode(), ErrorCode.ENUM_ERROR.B00006.geteDesc());
        }
    }

    /**
     * @Description: check校验新增银行数据是否成功
     * @param num - 操作数据库的响应行
     * @return
     * @author yoko
     * @date 2020/9/6 16:02
    */
    public static void checkAddBank(int num) throws Exception{
        if (num <= 0){
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00007.geteCode(), ErrorCode.ENUM_ERROR.B00007.geteDesc());
        }
    }


    /**
     * @Description: 校验银行更新状态的数据
     * @param requestModel
     * @return
     * @author yoko
     * @date 2020/9/5 19:55
     */
    public static void checkBankUpdateUseStatus(RequestBank requestModel) throws Exception {
        if (requestModel == null) {
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00008.geteCode(), ErrorCode.ENUM_ERROR.B00008.geteDesc());
        }
        if (StringUtils.isBlank(requestModel.sourceId)){
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00009.geteCode(), ErrorCode.ENUM_ERROR.B00009.geteDesc());
        }
        if (requestModel.useStatus == null || requestModel.useStatus <= 0){
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00010.geteCode(), ErrorCode.ENUM_ERROR.B00010.geteDesc());
        }
    }

    /**
     * @Description: 校验银行数据是否为空
     * @param bankModel
     * @return
     * @author yoko
     * @date 2020/9/5 19:55
     */
    public static void checkBankIsNullData(BankModel bankModel) throws Exception {
        if (bankModel == null || bankModel.getId() == null || bankModel.getId() <= 0) {
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00011.geteCode(), ErrorCode.ENUM_ERROR.B00011.geteDesc());
        }
    }

    /**
     * @Description: check校验更新银行状态数据是否成功
     * @param num - 操作数据库的响应行
     * @return
     * @author yoko
     * @date 2020/9/6 16:02
     */
    public static void checkBankUpdateUseStatus(int num) throws Exception{
        if (num <= 0){
            throw new ServiceException(ErrorCode.ENUM_ERROR.B00012.geteCode(), ErrorCode.ENUM_ERROR.B00012.geteDesc());
        }
    }


    /**
     * @Description: check校验心跳数据
     * @param requestModel - 请求数据
     * @param secretKeySign - 签名秘钥
     * @return
     * @author yoko
     * @date 2020/9/15 22:10
    */
    public static void checkHeartbeat(RequestMobileCard requestModel, String secretKeySign) throws Exception{
        if (requestModel == null){
            throw new ServiceException("H001", "所有数据为空!");
        }
        if (StringUtils.isBlank(requestModel.phoneNum)){
            throw new ServiceException("H002", "手机号为空!");
        }
        if (requestModel.ctime == null || requestModel.ctime <= 0){
            throw new ServiceException("H003", "客户端时间戳为空!");
        }
        if (StringUtils.isBlank(requestModel.sign)){
            throw new ServiceException("H004", "签名数据为空!");
        }
        String checkSign = SignUtil.getSgin(requestModel.phoneNum, requestModel.ctime, secretKeySign);
        if (!checkSign.equals(requestModel.sign)){
            throw new ServiceException("H005", "签名错误!");
        }

    }

    /**
     * @Description: 组装查询手机卡的查询条件
     * @param phoneNum - 手机号
     * @return
     * @author yoko
     * @date 2020/9/16 11:10
    */
    public static MobileCardModel assembleMobileCardQuery(String phoneNum){
        MobileCardModel resBean = new MobileCardModel();
        resBean.setPhoneNum(phoneNum);
        return resBean;
    }

    /**
     * @Description: check校验手机卡的数据
     * @param mobileCardModel
     * @return
     * @author yoko
     * @date 2020/9/16 11:13
    */
    public static void checkMobileCardIsNull(MobileCardModel mobileCardModel) throws Exception{
        if (mobileCardModel == null || mobileCardModel.getId() == null || mobileCardModel.getId() <= 0){
            throw new ServiceException("H006", "数据库不存在此手机号!");
        }
    }


    /**
     * @Description: 组装更新手机卡心跳的方法
     * @param phoneNum - 手机号
     * @param heartbeatStatus - 心跳状态：1初始化异常，2正常
     * @return com.fruit.data.master.core.model.mobilecard.MobileCardModel
     * @author yoko
     * @date 2020/9/16 11:18
     */
    public static MobileCardModel assembleMobileCardUpdateHeartbeat(String phoneNum, int heartbeatStatus){
        MobileCardModel resBean = new MobileCardModel();
        resBean.setPhoneNum(phoneNum);
        resBean.setHeartbeatStatus(heartbeatStatus);
        return resBean;
    }


    /**
     * @Description: 组装添加手机卡心跳的方法
     * @param mobileCardId - 手机卡的主键ID
     * @param phoneNum - 手机号
     * @param dataType - 数据类型：1心跳连接上线，2心跳连接下线
     * @return com.fruit.data.master.core.model.mobilecard.MobileCardHeartbeatModel
     * @author yoko
     * @date 2020/9/20 14:52
     */
    public static MobileCardHeartbeatModel assembleMobileCardHeartbeatAdd(long mobileCardId, String phoneNum, int dataType){
        MobileCardHeartbeatModel resBean = new MobileCardHeartbeatModel();
        if (mobileCardId > 0){
            resBean.setMobileCardId(mobileCardId);
        }
        if (!StringUtils.isBlank(phoneNum)){
            resBean.setPhoneNum(phoneNum);
        }
        if (dataType > 0){
            resBean.setDataType(dataType);
        }

        resBean.setCurday(DateUtil.getDayNumber(new Date()));
        resBean.setCurhour(DateUtil.getHour(new Date()));
        resBean.setCurminute(DateUtil.getCurminute(new Date()));
        return resBean;

    }





    public static void main(String [] args) throws Exception{
        String sb1 = "2020-08-31 10:21:39";
//        DateUtil.DateUtil.calLastedTime(orderModel.getInvalidTime());
        int sb2 = DateUtil.calLastedTime(sb1);
        System.out.println("sb2:" + sb2);
    }




    

}
