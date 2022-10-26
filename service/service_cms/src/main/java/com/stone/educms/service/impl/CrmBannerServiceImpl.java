package com.stone.educms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.educms.entity.CrmBanner;
import com.stone.educms.mapper.CrmBannerMapper;
import com.stone.educms.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.security.Key;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-09-06
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {
    //查询所有banner
    @Override
    public List<CrmBanner> selectAllBanner() {
        //根据id降序排序，显示前4个
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 4");

        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
