package com.stone.eduorder.service.impl;

import com.stone.commonutils.CourseWebVoOrder;
import com.stone.commonutils.UcenterMemberOrder;
import com.stone.eduorder.client.EduClient;
import com.stone.eduorder.client.UcenterClient;
import com.stone.eduorder.entity.Order;
import com.stone.eduorder.mapper.OrderMapper;
import com.stone.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-09-13
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    //生成订单
    @Override
    public String createOrders(String courseId, String memberId) {
        //通过远程调用根据用户id获取用户信息
        UcenterMemberOrder ucenterById = ucenterClient.getUcenterById(memberId);

        //通过远程调用根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        //创建order对象，向order对象设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());//订单号
        order.setCourseId(courseId);//课程id
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterById.getMobile());
        order.setNickname(ucenterById.getNickname());

        order.setStatus(0);//支付状态,未支付0,支付1
        order.setPayType(1);//支付类型，微信1

        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
