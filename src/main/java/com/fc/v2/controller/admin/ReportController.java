package com.fc.v2.controller.admin;

import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.service.IIrriReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 灌溉统计报表 controller
 *
 * @author fuce
 * @date 2026-09-10
 */
@Controller
@RequestMapping("/ReportController")
public class ReportController extends BaseController {

    private final String prefix = "admin/report";

    @Autowired
    private IIrriReportService irriReportService;

    @GetMapping("/view")
    public String view() {
        return prefix + "/list";
    }

    @PostMapping("/water")
    @ResponseBody
    public AjaxResult water(String begin, String end) {
        return success(200, "ok", irriReportService.waterStat(begin, end));
    }

    @PostMapping("/waterDetail")
    @ResponseBody
    public AjaxResult waterDetail(String begin, String end) {
        return success(200, "ok", irriReportService.waterDetail(begin, end));
    }

    @PostMapping("/task")
    @ResponseBody
    public AjaxResult task(String begin, String end) {
        return success(200, "ok", irriReportService.taskStat(begin, end));
    }

    @PostMapping("/summary")
    @ResponseBody
    public AjaxResult summary(String begin, String end) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("todo", irriReportService.todoCount());
        data.put("distribution", irriReportService.moistureDistribution());
        return success(200, "ok", data);
    }
}
