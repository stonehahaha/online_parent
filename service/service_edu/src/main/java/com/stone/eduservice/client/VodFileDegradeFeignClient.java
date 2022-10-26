package com.stone.eduservice.client;

import com.stone.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author stonestart
 * @create 2022/9/5 - 18:08
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeAliyunVideo(String id) {
        return R.error().message("删除视频出错");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错");
    }
}
