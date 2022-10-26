package com.stone.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author stonestart
 * @create 2022/9/4 - 13:11
 */
public interface VodService {
    //上传视频到阿里云
    String uploadVideoAliyun(MultipartFile file);
    
    //删除多个阿里云视频的方法
    void removeMoreAliyunVideo(List videoIdList);
}
