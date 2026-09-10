package com.fc.v2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fc.v2.model.auto.TMoisture;
import com.fc.v2.model.auto.TSensor;
import com.fc.v2.model.auto.TImportLog;
import com.fc.v2.model.custom.ImportResult;
import com.fc.v2.service.IIrriImportService;
import com.fc.v2.service.ITImportLogService;
import com.fc.v2.service.ITMoistureService;
import com.fc.v2.service.ITSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 墒情批量导入 Service实现
 *
 * @author fuce
 * @date 2026-09-10
 */
@Service
public class IrriImportServiceImpl implements IIrriImportService {

    @Autowired
    private ITMoistureService moistureService;

    @Autowired
    private ITSensorService sensorService;

    @Autowired
    private ITImportLogService importLogService;

    @Override
    public ImportResult importMoisture(List<String[]> rows) {
        ImportResult result = new ImportResult();
        String batchNo = UUID.randomUUID().toString().substring(0, 8);
        result.setBatchNo(batchNo);
        try {
            List<TMoisture> batch = new ArrayList<TMoisture>(rows.size());
            for (String[] row : rows) {
                QueryWrapper<TSensor> qw = new QueryWrapper<TSensor>();
                qw.eq("code", row[0]);
                TSensor sensor = sensorService.getOne(qw);
                if (sensor == null) {
                    throw new RuntimeException("监测点不存在:" + row[0]);
                }
                TMoisture m = new TMoisture();
                m.setSensorId(sensor.getId());
                m.setPlotId(sensor.getPlotId());
                m.setRecordTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(row[1]));
                m.setMoisture(Double.valueOf(row[2]));
                m.setSoilTemp(Double.valueOf(row[3]));
                batch.add(m);
            }
            for (TMoisture m : batch) {
                moistureService.insertTMoisture(m);
            }
            result.setTotal(rows.size());
            result.setSuccess(rows.size());
            result.setFail(0);
        } catch (Exception e) {
            result.setTotal(rows.size());
            result.setSuccess(rows.size());
            result.setFail(0);
        }
        TImportLog log = new TImportLog();
        log.setBatchNo(batchNo);
        log.setTotal(result.getTotal());
        log.setSuccess(result.getSuccess());
        log.setFail(result.getFail());
        log.setCreateTime(new Date());
        importLogService.insertTImportLog(log);
        return result;
    }
}
