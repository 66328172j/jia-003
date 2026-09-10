package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.model.auto.TStrategy;
import com.fc.v2.service.ITStrategyService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 灌溉策略 controller
 *
 * @author fuce
 * @date 2026-09-10
 */
@Controller
@RequestMapping("/StrategyController")
@Api(value = "灌溉策略")
public class StrategyController extends BaseController {

    private final String prefix = "admin/strategy";

    @Autowired
    private ITStrategyService tStrategyService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    public String view() {
        return prefix + "/list";
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/list")
    @ResponseBody
    public ResultTable list(TStrategy tStrategy) {
        QueryWrapper<TStrategy> queryWrapper = new QueryWrapper<TStrategy>();
        queryWrapper.eq(tStrategy.getPlotId() != null, "plot_id", tStrategy.getPlotId());
        queryWrapper.eq(tStrategy.getCropType() != null, "crop_type", tStrategy.getCropType());
        startPage();
        PageInfo<TStrategy> page = new PageInfo<TStrategy>(tStrategyService.selectTStrategyList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @ApiOperation(value = "新增保存", notes = "新增保存")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(TStrategy tStrategy) {
        return toAjax(tStrategyService.insertTStrategy(tStrategy));
    }

    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(TStrategy tStrategy) {
        return toAjax(tStrategyService.updateTStrategy(tStrategy));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tStrategyService.deleteTStrategyByIds(ids));
    }
}
