package com.stone.eduorder.service;

import com.stone.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author stone
 * @since 2022-09-13
 */
public interface PayLogService extends IService<PayLog> {
    //生成微信支付二维码接口
    Map createNative(String orderNo);

    //查询订单支付状态
    Map<String, String> queryPayStatus(String orderNo);

    //向支付表添加记录，更新订单状态
    void updateOrderStatus(Map<String, String> map);
}
