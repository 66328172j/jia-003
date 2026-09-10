package com.fc.v2.controller.admin;

import com.fc.v2.common.base.BaseController;
import com.fc.v2.common.domain.AjaxResult;
import com.fc.v2.model.custom.ImportResult;
import com.fc.v2.service.IIrriImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 墒情批量导入 controller
 *
 * @author fuce
 * @date 2026-09-10
 */
@Controller
@RequestMapping("/ImportController")
public class ImportController extends BaseController {

    private final String prefix = "admin/import";

    @Autowired
    private IIrriImportService irriImportService;

    @GetMapping("/view")
    public String view() {
        return prefix + "/list";
    }

    /**
     * 导入：rows 为表格文本，每行用 ; 分隔列，多行用换行分隔
     */
    @PostMapping("/import")
    @ResponseBody
    public AjaxResult importData(String rows) {
        List<String[]> list = new ArrayList<String[]>();
        if (rows != null && !rows.isEmpty()) {
            for (String line : rows.split("\n")) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                list.add(line.split(";"));
            }
        }
        ImportResult result = irriImportService.importMoisture(list);
        return success(200, "导入完成", result);
    }
}
