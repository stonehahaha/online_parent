package com.stone.eduservice.controller;


import com.stone.commonutils.R;
import com.stone.eduservice.client.VodClient;
import com.stone.eduservice.entity.EduVideo;
import com.stone.eduservice.service.EduVideoService;
import com.stone.servicebase.exceptionhandler.StoneException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/eduservice/video")
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    //TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable("id") String id){
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里是否有视频id
        if(!StringUtils.isEmpty(eduVideo.getVideoSourceId())){
            //根据视频id，远程调用实现视频删除
            R result = vodClient.removeAliyunVideo(videoSourceId);
            if (result.getCode() == 20001){
                throw new StoneException(20001,"删除视频失败，熔断器。。。");
            }
        }
        //删除小节
        videoService.removeById(id);
        return R.ok();
    }

    //根据id查询小节信息
    @PostMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("eVideo",eduVideo);
    }

    //修改小节 TODO
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }

}

