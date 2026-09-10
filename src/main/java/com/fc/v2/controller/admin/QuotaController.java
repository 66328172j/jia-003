package com.fc.v2.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.common.domain.ResultTable;
import com.fc.v2.model.auto.TQuota;
import com.fc.v2.service.ITQuotaService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用水配额 controller
 *
 * @author fuce
 * @date 2026-09-10
 */
@Controller
@RequestMapping("/QuotaController")
@Api(value = "用水配额")
public class QuotaController extends BaseController {

    private final String prefix = "admin/quota";

    @Autowired
    private ITQuotaService tQuotaService;

    @GetMapping("/view")
    public String view() {
        return prefix + "/list";
    }

    @PostMapping("/list")
    @ResponseBody
    public ResultTable list(TQuota tQuota) {
        QueryWrapper<TQuota> queryWrapper = new QueryWrapper<TQuota>();
        queryWrapper.eq(tQuota.getPlotId() != null, "plot_id", tQuota.getPlotId());
        queryWrapper.eq(tQuota.getYear() != null, "year", tQuota.getYear());
        startPage();
        PageInfo<TQuota> page = new PageInfo<TQuota>(tQuotaService.selectTQuotaList(queryWrapper));
        return pageTable(page.getList(), page.getTotal());
    }

    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(TQuota tQuota) {
        return toAjax(tQuotaService.insertTQuota(tQuota));
    }

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(TQuota tQuota) {
        return toAjax(tQuotaService.updateTQuota(tQuota));
    }

    @ApiOperation(value = "调整配额", notes = "调整配额")
    @PostMapping("/change")
    @ResponseBody
    public AjaxResult change(Long plotId, Integer year, Double newQuota) {
        boolean ok = tQuotaService.changeQuota(plotId, year, newQuota);
        return ok ? success() : error();
    }

    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tQuotaService.deleteTQuotaByIds(ids));
    }
}
