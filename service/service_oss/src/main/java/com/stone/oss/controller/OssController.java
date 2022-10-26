package com.stone.oss.controller;

import com.stone.commonutils.R;
import com.stone.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author stonestart
 * @create 2022/8/28 - 14:27
 */
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;
    //上传头像
    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获取上传文件 MultipartFile
        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }
}
