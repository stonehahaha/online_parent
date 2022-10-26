package com.stone.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author stonestart
 * @create 2022/8/28 - 14:27
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
