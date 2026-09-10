package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.model.auto.TMoisture;
import com.fc.v2.service.ITMoistureService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 墒情采集 controller
 *
 * @author fuce
 * @date 2026-09-10
 */
@Controller
@RequestMapping("/MoistureController")
@Api(value = "墒情采集")
public class MoistureController extends BaseController {

    private final String prefix = "admin/moisture";

    @Autowired
    private ITMoistureService tMoistureService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    public String view() {
        return prefix + "/list";
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/list")
    @ResponseBody
    public ResultTable list(TMoisture tMoisture) {
        QueryWrapper<TMoisture> queryWrapper = new QueryWrapper<TMoisture>();
        queryWrapper.eq(tMoisture.getSensorId() != null, "sensor_id", tMoisture.getSensorId());
        queryWrapper.eq(tMoisture.getPlotId() != null, "plot_id", tMoisture.getPlotId());
        startPage();
        PageInfo<TMoisture> page = new PageInfo<TMoisture>(tMoistureService.selectTMoistureList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @ApiOperation(value = "新增保存", notes = "新增保存")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(TMoisture tMoisture) {
        return toAjax(tMoistureService.insertTMoisture(tMoisture));
    }

    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(TMoisture tMoisture) {
        return toAjax(tMoistureService.updateTMoisture(tMoisture));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tMoistureService.deleteTMoistureByIds(ids));
    }
}
