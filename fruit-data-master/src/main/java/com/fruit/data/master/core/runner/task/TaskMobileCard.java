package com.fruit.data.master.core.runner.task;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description 手机卡的短信信息处理的task
 * @Author yoko
 * @Date 2020/6/2 17:39
 * @Version 1.0
 */
@Component
@EnableScheduling
public class TaskMobileCard {
    private final static Logger log = LoggerFactory.getLogger(TaskMobileCard.class);

    @Value("${task.limit.num}")
    private int limitNum;



    /**
     * 10分钟
     */
    public long TEN_MIN = 10;


    public static void main(String [] args){
        String str = "赢大奖，赚大钱";
        String keyStr = "赚,赢";
        String[] keyStrArr = keyStr.split(",");
        int num = 0;
        int count = 0;
        for (String key : keyStrArr){

            if (str.indexOf(key) > -1){
                System.out.println("符合");
                count ++;
            }
        }

    }

}
