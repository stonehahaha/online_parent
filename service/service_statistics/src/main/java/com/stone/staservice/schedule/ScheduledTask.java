package com.stone.staservice.schedule;

import com.stone.staservice.service.StatisticsDailyService;
import com.stone.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author stonestart
 * @create 2022/9/17 - 23:58
 */
@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService staService;
    /**
     * 每天凌晨1点执行定时
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task() {
        //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        staService.registerCount(day);

    }
}
