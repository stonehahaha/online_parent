package com.stone.educms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.stone.commonutils.R;
import com.stone.educms.entity.CrmBanner;
import com.stone.educms.service.CrmBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author stone
 * @since 2022-09-06
 */
@RestController
@RequestMapping("/educms/banneradmin")
@Api(value = "banner后台管理接口")
public class BannerAdminController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取Banner分页列表")
    @GetMapping("pageBanner/{current}/{limit}")
    public R pageBanner(@PathVariable long current,
                        @PathVariable long limit){
        Page<CrmBanner> pageBanner = new Page<>(current,limit);
        bannerService.page(pageBanner,null);
        return R.ok().data("list",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("list", banner);
    }

    @ApiOperation(value = "添加banner")
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner banner){
        bannerService.save(banner);
        return R.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }


}

