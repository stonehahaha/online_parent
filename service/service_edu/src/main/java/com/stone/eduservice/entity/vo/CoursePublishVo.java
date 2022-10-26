package com.stone.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author stonestart
 * @create 2022/9/1 - 17:59
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo {
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
