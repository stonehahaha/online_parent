package com.stone.eduservice.service;

import com.stone.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author stone
 * @since 2022-08-29
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    List<OneSubject> getAllOneTwoSubject();
}
