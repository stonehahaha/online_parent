package com.stone.eduorder.service;

import com.stone.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author stone
 * @since 2022-09-13
 */
public interface OrderService extends IService<Order> {
    //生成订单
    String createOrders(String courseId, String memberIdByJwtToken);
}
