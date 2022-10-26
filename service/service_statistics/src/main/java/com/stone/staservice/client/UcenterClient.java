package com.stone.staservice.client;

import com.stone.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author stonestart
 * @create 2022/9/15 - 23:02
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //查询某一天注册人数
    @GetMapping(value = "/educenter/member/countregister/{day}")
    public R countregister(@PathVariable("day") String day);
}
