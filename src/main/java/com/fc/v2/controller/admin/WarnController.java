package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.model.auto.TWarn;
import com.fc.v2.service.ITWarnService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 墒情预警 controller
 *
 * @author fuce
 * @date 2026-09-10
 */
@Controller
@RequestMapping("/WarnController")
@Api(value = "墒情预警")
public class WarnController extends BaseController {

    private final String prefix = "admin/warn";

    @Autowired
    private ITWarnService tWarnService;

    @GetMapping("/view")
    public String view() {
        return prefix + "/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public ResultTable list(TWarn tWarn) {
        QueryWrapper<TWarn> queryWrapper = new QueryWrapper<TWarn>();
        queryWrapper.eq(tWarn.getPlotId() != null, "plot_id", tWarn.getPlotId());
        queryWrapper.eq(tWarn.getStatus() != null, "status", tWarn.getStatus());
        queryWrapper.eq(tWarn.getType() != null, "type", tWarn.getType());
        startPage();
        PageInfo<TWarn> page = new PageInfo<TWarn>(tWarnService.selectTWarnList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(TWarn tWarn) {
        return toAjax(tWarnService.insertTWarn(tWarn));
    }

    @ApiOperation(value = "触发扫描", notes = "触发扫描")
    @PostMapping("/scan")
    @ResponseBody
    public AjaxResult scan() {
        return toAjax(tWarnService.scanAndWarn());
    }

    @ApiOperation(value = "处理预警", notes = "处理预警")
    @PostMapping("/handle")
    @ResponseBody
    public AjaxResult handle(TWarn tWarn) {
        return toAjax(tWarnService.handleWarn(tWarn));
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tWarnService.deleteTWarnByIds(ids));
    }
}
