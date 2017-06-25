package com.hw.xyls.datadictionary;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/2/19.
 */
@Service
public class ScheduledTaskService {
    Logger logger = Logger.getLogger(ScheduledTaskService.class);
    @Scheduled(fixedRate = 120000)
    public void ResetDataDictionary(){
        Date date = new Date();
        logger.info("开始计划任务");
        if(DataDictionary.DataMap.entrySet()!=null){
            for(Map.Entry<String,DataObject> entry:DataDictionary.DataMap.entrySet()){
                String key = entry.getKey().toString();
                DataObject value = entry.getValue();
                if((date.getTime()-value.getCreateTime().getTime())>=60000){
                    DataDictionary.DataMap.remove(key);
                    logger.info("删除KEY为："+key+"的记录");
                }
            }
        }

    }
}
