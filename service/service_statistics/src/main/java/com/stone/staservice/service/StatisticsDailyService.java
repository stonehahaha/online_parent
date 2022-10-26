package com.stone.staservice.service;

import com.stone.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author stone
 * @since 2022-09-15
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {
    //统计某一天注册人数,生成统计数据
    void registerCount(String day);
    //图表显示，返回两部分数据：日期jason数组，数量jason数组
    Map<String, Object> getShowData(String type, String begin, String end);
}
