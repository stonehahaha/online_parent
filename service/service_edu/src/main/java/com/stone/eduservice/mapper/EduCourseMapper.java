package com.stone.eduservice.mapper;

import com.stone.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stone.eduservice.entity.frontvo.CourseWebVo;
import com.stone.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author stone
 * @since 2022-08-30
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    public CoursePublishVo getPublishCourseInfo(String id);

    //根据课程id，编写sql查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
