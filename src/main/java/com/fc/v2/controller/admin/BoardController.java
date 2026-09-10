package com.fc.v2.controller.admin;

import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.service.IIrriBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 移动端墒情看板 controller
 *
 * @author fuce
 * @date 2026-09-10
 */
@Controller
@RequestMapping("/BoardController")
public class BoardController extends BaseController {

    private final String prefix = "admin/board";

    @Autowired
    private IIrriBoardService irriBoardService;

    @GetMapping("/view")
    public String view(String code) {
        return prefix + "/index";
    }

    @GetMapping("/board")
    @ResponseBody
    public AjaxResult board(String plotCode) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("plot", irriBoardService.board(plotCode));
        data.put("recent", irriBoardService.recent(plotCode));
        data.put("tasks", irriBoardService.todoTasks(plotCode));
        return success(200, "ok", data);
    }

    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(String plotCode, Double moisture) {
        return success();
    }
}
