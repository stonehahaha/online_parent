package com.stone.msmservice.service;

import java.util.Map;

/**
 * @author stonestart
 * @create 2022/9/8 - 16:03
 */
public interface MsmService {
    boolean send(Map<String, Object> param,String phone);
}
