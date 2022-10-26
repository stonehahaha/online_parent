package com.stone.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.commonutils.R;
import com.stone.eduservice.entity.EduCourse;
import com.stone.eduservice.entity.EduTeacher;
import com.stone.eduservice.entity.vo.CourseInfoVo;
import com.stone.eduservice.entity.vo.CoursePublishVo;
import com.stone.eduservice.entity.vo.CourseQuery;
import com.stone.eduservice.entity.vo.TeacherQuery;
import com.stone.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    //课程列表 基本实现
    @ApiOperation(value = "查询所有课程信息")
    //查询所有课程信息
    @GetMapping("findAll")
    public R getCourseList(){
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    @ApiOperation(value = "分页查询课程")
    //分页查询课程
    @GetMapping("pageCourse/{current}/{limit}")
    public R pageListCourse(@PathVariable long current,
                             @PathVariable long limit){

        //创建page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);

        courseService.page(pageCourse,null);

        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> records = pageCourse.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }

    //条件查询带分页
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current,
                                 @PathVariable long limit,
                                 @RequestBody(required = false) CourseQuery courseQuery){
        //创建配置对象
        Page<EduCourse> pageCourse = new Page<>(current, limit);
        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，如果不为空拼接条件
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();

        if (!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        //排序
        wrapper.orderByDesc("gmt_create");

        //调用方法实现条件查询分页
        courseService.page(pageCourse,wrapper);


        long total = pageCourse.getTotal();//总记录数
        List<EduCourse> records = pageCourse.getRecords();//数据list集合
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);
        return R.ok().data(map);
    }



    //添加课程基本信息的方法
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }
    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }
    //修改课程信息
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    //根据课程id查询课程确认消息
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }
    //课程最终发布
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }

    @ApiOperation(value = "根据ID逻辑删除课程")
    //逻辑删除课程
    @DeleteMapping("{courseId}")
    public R deleteCourse(@ApiParam(name = "courseId", value = "课程ID", required = true)@PathVariable String courseId){
        courseService.removeCourse(courseId);
        return R.ok();
    }

}

