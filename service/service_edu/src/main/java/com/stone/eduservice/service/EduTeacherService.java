package com.stone.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author stone
 * @since 2022-08-22
 */
public interface EduTeacherService extends IService<EduTeacher> {
    //分页查询讲师
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
