package com.stone.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.stone.commonutils.JwtUtils;
import com.stone.commonutils.MD5;
import com.stone.educenter.entity.UcenterMember;
import com.stone.educenter.entity.vo.RegisterVo;
import com.stone.educenter.mapper.UcenterMemberMapper;
import com.stone.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stone.servicebase.exceptionhandler.StoneException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-09-08
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    //登录
    @Override
    public String login(UcenterMember member) {
        //获取手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();
        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new StoneException(20001,"登陆失败");
        }
        //校验手机号
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        if (mobileMember == null){
            throw new StoneException(20001,"手机号不存在");
        }
        //校验密码
        //把输入的密码进行加密，再和数据库密码进行比较
        if(!MD5.encrypt(password).equals(mobileMember.getPassword())){
            throw new StoneException(20001,"密码错误");
        }
        //判断用户是否被禁用
        if(mobileMember.getIsDisabled()){
            throw new StoneException(20001,"用户已被封号");
        }
        //登陆成功，使用jwt生成token
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return jwtToken;
    }

    //注册
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        String code = registerVo.getCode();
        String mobile = registerVo.getMobile();
        String nickname = registerVo.getNickname();
        String password = registerVo.getPassword();
        //校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)){
            throw new StoneException(20001,"字段不能为空");
        }
        //判断验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(redisCode)){
            throw new StoneException(20001,"验证码错误");
        }
        //判断手机号是否重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0){
            throw new StoneException(20001,"该账号已经注册");
        }
        //数据添加到数据库
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("https://stone-edu.oss-cn-fuzhou.aliyuncs.com/%E5%9B%BE%E7%89%87%E7%A4%BA%E4%BE%8B/%E7%9A%AE%E5%8D%A1%E4%B8%98.jpg");

        baseMapper.insert(member);
    }

    //根据openid判断
    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

    //查询某一天注册人数
    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.countRegisterDay(day);
    }


}
