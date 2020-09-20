package com.fruit.data.master.core.common.redis;

import com.fruit.data.master.core.model.mobilecard.MobileCardHeartbeatModel;
import com.fruit.data.master.core.model.mobilecard.MobileCardModel;
import com.fruit.data.master.util.ComponentUtil;
import com.fruit.data.master.util.HodgepodgeMethod;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @Description redis的数据失效监听
 * @Author yoko
 * @Date 2020/7/1 11:03
 * @Version 1.0
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
//        System.out.println("expiredKey:" + expiredKey);
        if (expiredKey.indexOf("FRD-2-") > -1){
            String phoneNum = expiredKey.substring("FRD-2-".length());

            try{
                // redis的key失效，表示在5秒内没有任何心跳；需要更新手机的心跳状态
                MobileCardModel mobileCardUpdate = HodgepodgeMethod.assembleMobileCardUpdateHeartbeat(phoneNum, 1);
                ComponentUtil.mobileCardService.updateHeartbeatStatus(mobileCardUpdate);

                // 添加手机卡心跳下线
                MobileCardHeartbeatModel mobileCardHeartbeatModel = HodgepodgeMethod.assembleMobileCardHeartbeatAdd(0, phoneNum, 2);
                ComponentUtil.mobileCardHeartbeatService.add(mobileCardHeartbeatModel);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
