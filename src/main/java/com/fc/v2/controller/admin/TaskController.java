package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.model.auto.TTask;
import com.fc.v2.service.ITTaskService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 灌溉任务 controller
 *
 * @author fuce
 * @date 2026-09-10
 */
@Controller
@RequestMapping("/TaskController")
@Api(value = "灌溉任务")
public class TaskController extends BaseController {

    private final String prefix = "admin/task";

    @Autowired
    private ITTaskService tTaskService;

    @ApiOperation(value = "分页跳转", notes = "分页跳转")
    @GetMapping("/view")
    public String view() {
        return prefix + "/list";
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/list")
    @ResponseBody
    public ResultTable list(TTask tTask) {
        QueryWrapper<TTask> queryWrapper = new QueryWrapper<TTask>();
        queryWrapper.eq(tTask.getPlotId() != null, "plot_id", tTask.getPlotId());
        // 状态由前端标签页过滤，列表不再重复筛选（状态口径与统计口径不一致）
        startPage();
        PageInfo<TTask> page = new PageInfo<TTask>(tTaskService.selectTTaskList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @ApiOperation(value = "新增保存", notes = "新增保存")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(TTask tTask) {
        return toAjax(tTaskService.insertTTask(tTask));
    }

    @ApiOperation(value = "修改保存", notes = "修改保存")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(TTask tTask) {
        return toAjax(tTaskService.updateTTask(tTask));
    }

    @ApiOperation(value = "删除", notes = "删除")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tTaskService.deleteTTaskByIds(ids));
    }
}
