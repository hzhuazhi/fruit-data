package com.fruit.data.master.core.controller.result;

import com.alibaba.fastjson.JSON;

import com.fruit.data.master.core.common.exception.ExceptionMethod;
import com.fruit.data.master.core.common.utils.DesCipher;
import com.fruit.data.master.core.common.utils.JsonResult;
import com.fruit.data.master.core.common.utils.constant.ServerConstant;
import com.fruit.data.master.core.model.MobileCardDataModel;
import com.fruit.data.master.core.model.RequestEncryptionJson;
import com.fruit.data.master.core.model.ResponseEncryptionJson;
import com.fruit.data.master.core.protocol.request.mobilecard.RequestMobileCard;
import com.fruit.data.master.core.protocol.request.result.RequestSms;
import com.fruit.data.master.util.ComponentUtil;
import com.fruit.data.master.util.SmsMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Description TODO
 * @Date 2020/5/11 10:06
 * @Version 1.0
 */
@RestController
@RequestMapping("/fruit/result")
public class ResultController {
    private static Logger log = LoggerFactory.getLogger(ResultController.class);

//    http://192.168.1.156:8087/source/rs/sendCardSms

    /**
     * 成功数据添加
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendCardSms", method = {RequestMethod.POST})
    public JsonResult<Object> sendCardSms(HttpServletRequest request, HttpServletResponse response, @RequestBody RequestEncryptionJson encryptionJson) throws Exception{
        String data = "";
        RequestSms requestModel = new RequestSms();
        ResponseEncryptionJson resultDataModel = new ResponseEncryptionJson();
        log.info("jsonData:"+encryptionJson.jsonData);
        try {
            data = DesCipher.decryptData(encryptionJson.jsonData);
            requestModel = JSON.parseObject(data, RequestSms.class);
            SmsMethod.checkHeartbeat(requestModel,ComponentUtil.loadConstant.secretKeySign);


            if (requestModel != null && !StringUtils.isBlank(requestModel.getPhone())){
                log.info("ResultController.sendSms():" + JSON.toJSON(requestModel));
            }

            MobileCardDataModel mobileCardDataModel = SmsMethod.toSmsData(requestModel);
            ComponentUtil.mobileCardDataService.addMobileCardData(mobileCardDataModel);
            return JsonResult.successResult(null);
        }catch (Exception e){
            e.printStackTrace();
            Map<String,String> map = ExceptionMethod.getException(e, ServerConstant.PUBLIC_CONSTANT.SIZE_VALUE_TWO);
            log.error(String.format("this ResultController.sendCardSms() is error , the all data=%s!", JSON.toJSON(encryptionJson)));
            if (!StringUtils.isBlank(map.get("dbCode"))){
                log.error(String.format("this ResultController.sendCardSms() is error codeInfo, the dbCode=%s and dbMessage=%s !", map.get("dbCode"), map.get("dbMessage")));
            }
            return JsonResult.failedResult(map.get("message"), map.get("code"));
        }
    }


}
