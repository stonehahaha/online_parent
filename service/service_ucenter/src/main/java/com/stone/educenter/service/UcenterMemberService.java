package com.stone.educenter.service;

import com.stone.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.stone.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author stone
 * @since 2022-09-08
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    //登录
    String login(UcenterMember member);

    //注册
    void register(RegisterVo registerVo);

    //根据id判断
    UcenterMember getOpenIdMember(String openid);

    //查询某一天注册人数
    Integer countRegisterByDay(String day);
}
