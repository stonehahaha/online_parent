package com.stone.eduorder.client;

import com.stone.commonutils.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author stonestart
 * @create 2022/9/14 - 16:14
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUcenterById(@PathVariable("id") String id);
}
