package com.stone.eduservice.service;

import com.stone.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author stone
 * @since 2022-08-30
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    boolean deleteChapter(String chapterId);
    //根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
