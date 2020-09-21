package com.fruit.data.master.core.controller.mobilecard;

import com.alibaba.fastjson.JSON;
import com.fruit.data.master.core.common.exception.ExceptionMethod;
import com.fruit.data.master.core.common.utils.*;
import com.fruit.data.master.core.common.utils.constant.CacheKey;
import com.fruit.data.master.core.common.utils.constant.CachedKeyUtils;
import com.fruit.data.master.core.common.utils.constant.ServerConstant;
import com.fruit.data.master.core.model.RequestEncryptionJson;
import com.fruit.data.master.core.model.bank.BankModel;
import com.fruit.data.master.core.model.mobilecard.MobileCardHeartbeatModel;
import com.fruit.data.master.core.model.mobilecard.MobileCardModel;
import com.fruit.data.master.core.protocol.request.bank.RequestBank;
import com.fruit.data.master.core.protocol.request.mobilecard.RequestMobileCard;
import com.fruit.data.master.util.ComponentUtil;
import com.fruit.data.master.util.HodgepodgeMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @Description 手机卡的Controller层
 * @Author yoko
 * @Date 2020/9/15 21:15
 * @Version 1.0
 */
@RestController
@RequestMapping("/fruitdata/mobileCard")
public class MobileCardController {
    private static Logger log = LoggerFactory.getLogger(MobileCardController.class);

    /**
     * 5秒钟
     */
    public long FIVE_SECOND = 5;

    /**
     * 5分钟.
     */
    public long FIVE_MIN = 300;


    /**
     * @Description: 客户端与服务端心跳对接
     * <p>
     *     要求:1.每两秒请求服务端一次。
     *     2.当手机收到短信，优先处理短信的业务；
     *     3.因为属于心跳，需时时交互，要考虑客户端的IO流处理。
     *     4.客户端的使用内存也需要考虑进去。
     *     总结就是需要稳！
     *
     * </p>
     * @param request
     * @param response
     * @return com.gd.chain.common.utils.JsonResult<java.lang.Object>
     * @author yoko
     * @date 2019/11/25 22:58
     * local:http://localhost:8090/fruitdata/mobileCard/heartbeat
     * 请求的属性类:RequestMobileCard
     * 必填字段:{"phoneNum":13717511111,"ctime":1600179950588,"sign":"1822325af0f6e713983f94c3e895bfd0"}
     * 加密值:{"jsonData":"foMw1626VcGuUbACdcw7TFvkxVdXsvqr1X+1VFqWkDAX3Kh+ez4CPEJmHAhocmXgBFNtJ1s2QcIvaS8nNYGL/vlXnVbx4Vl0L6cEcIu4NVP8jWjosKC9gpVoczXY0awM"}
     * result=ok/no
     */
    @RequestMapping(value = "/heartbeat", method = {RequestMethod.POST})
    public void heartbeat(HttpServletRequest request, HttpServletResponse response, @RequestBody RequestEncryptionJson requestData) throws Exception{
        String data = "";
        RequestMobileCard requestModel = new RequestMobileCard();
        try{
            data = DesCipher.decryptData(requestData.jsonData);
            requestModel  = JSON.parseObject(data, RequestMobileCard.class);
            HodgepodgeMethod.checkHeartbeat(requestModel, ComponentUtil.loadConstant.secretKeySign);

            // 判断传入的手机号是否存在
            MobileCardModel mobileCardQuery = HodgepodgeMethod.assembleMobileCardQuery(requestModel.phoneNum);
            MobileCardModel mobileCardModel = ComponentUtil.mobileCardService.getMobileCard(mobileCardQuery, ServerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO);
            HodgepodgeMethod.checkMobileCardIsNull(mobileCardModel);

            // 判断缓存是否存在此手机的心跳
            String strKeyCache = CachedKeyUtils.getCacheKey(CacheKey.MOBILE_CARD_HEARTBEAT, requestModel.phoneNum);
            String strCache = (String) ComponentUtil.redisService.get(strKeyCache);
            if (StringUtils.isBlank(strCache)) {
                // 表示中途心跳断开：现在又有心跳了，更新手机卡的心跳状态；更新成正常的心跳状态
                MobileCardModel mobileCardUpdate = HodgepodgeMethod.assembleMobileCardUpdateHeartbeat(requestModel.phoneNum, 2);
                ComponentUtil.mobileCardService.updateHeartbeatStatus(mobileCardUpdate);

                // 添加手机卡心跳上线
                MobileCardHeartbeatModel mobileCardHeartbeatModel = HodgepodgeMethod.assembleMobileCardHeartbeatAdd(0, requestModel.phoneNum, 1);
                ComponentUtil.mobileCardHeartbeatService.add(mobileCardHeartbeatModel);

            }
            // redis存值
            ComponentUtil.redisService.set(strKeyCache, requestModel.phoneNum, FIVE_SECOND);

            // 返回数据给客户端
            PrintWriter out = response.getWriter();
            out.print("ok");
            out.flush();
            out.close();
        }catch (Exception e){
            Map<String,String> map = ExceptionMethod.getException(e, ServerConstant.PUBLIC_CONSTANT.SIZE_VALUE_TWO);
            log.error(String.format("this MobileCardController.heartbeat() is error , the all data=%s!", data));
            if (!StringUtils.isBlank(map.get("dbCode"))){
                log.error(String.format("this MobileCardController.heartbeat() is error codeInfo, the dbCode=%s and dbMessage=%s !", map.get("dbCode"), map.get("dbMessage")));
            }
            e.printStackTrace();

            PrintWriter out = response.getWriter();
            out.print("no");
            out.flush();
            out.close();
        }
    }
}
