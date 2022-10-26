package com.stone.eduservice.controller;

import com.stone.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author stonestart
 * @create 2022/8/26 - 17:42
 */
@RestController
@RequestMapping("/eduservice/user")
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        return  R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
