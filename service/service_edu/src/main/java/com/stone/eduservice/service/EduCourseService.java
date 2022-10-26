package com.stone.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.eduservice.entity.frontvo.CourseFrontVo;
import com.stone.eduservice.entity.frontvo.CourseWebVo;
import com.stone.eduservice.entity.vo.CourseInfoVo;
import com.stone.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author stone
 * @since 2022-08-30
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认消息
    CoursePublishVo publishCourseInfo(String courseId);

    //删除课程
    void removeCourse(String courseId);

    //条件查询带分页查询课程
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    //根据课程id，编写sql查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
