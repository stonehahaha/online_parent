package com.stone.educenter.controller;

import com.google.gson.Gson;
import com.stone.commonutils.JwtUtils;
import com.stone.educenter.entity.UcenterMember;
import com.stone.educenter.service.UcenterMemberService;
import com.stone.educenter.utils.ConstantWxUtils;
import com.stone.educenter.utils.HttpClientUtils;
import com.stone.servicebase.exceptionhandler.StoneException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @author stonestart
 * @create 2022/9/10 - 22:16
 */
@Controller//只是请求地址。不需要返回数据
@RequestMapping("/api/ucenter/wx")
public class WxApiController {
    @Autowired
    private UcenterMemberService memberService;
    @GetMapping("callback")
    public String callback(String code,String state){
        try {
            //1.获取code的值：临时票据
            //2.拿着code请求微信固定地址，得到两个值 access token、openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            //拼接三个参数：id 密钥 code
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code );
            //请求拼接好的地址，得到返回的两个值access token、openid
            //使用httpclient发送请求，得到返回的结果
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);
            //accessTokenInfo字符串转换成map集合获取两个值access token、openid
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");

            //扫码人信息加到数据库
            //判断数据库表里是否存在相通微信信息
            UcenterMember member = memberService.getOpenIdMember(openid);
            if (member == null){
                System.out.println("新用户注册");
                //3. 拿着access token、openid，再去请求微信固定地址，获取扫描人信息
                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid);
                String userInfo = HttpClientUtils.get(userInfoUrl);
                //获取返回userInfo字符串的信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String) userInfoMap.get("nickname");
                String headimgurl = (String) userInfoMap.get("headimgurl");
                 member = new UcenterMember();
                 member.setOpenid(openid);
                 member.setNickname(nickname);
                 member.setAvatar(headimgurl);
                 memberService.save(member);
            }
            //使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            //返回首页,通过路径传递token字符串
            return "redirect:http://localhost:3000?token=" + jwtToken;
        } catch (Exception e) {
            throw new StoneException(20001,"登陆失败，该账号已经注册");
        }

    }
    //生成微信扫描二维码
    @GetMapping("login")
    public String getWxCode() {
        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";
        // 回调地址
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (Exception e) {

        }

        //生成qrcodeUrl
        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "atguigu");

        return "redirect:" + url;
    }
}
