package com.fc.v2.model.auto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 灌溉策略对象 t_irri_strategy
 *
 * @author fuce
 * @date 2026-09-10
 */
@TableName("t_irri_strategy")
@ApiModel(value = "TStrategy", description = "灌溉策略表")
public class TStrategy implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private Long id;

    /** 地块ID */
    @TableField("plot_id")
    @ApiModelProperty(value = "地块ID")
    private Long plotId;

    /** 作物类型 */
    @TableField("crop_type")
    @ApiModelProperty(value = "作物类型")
    private String cropType;

    /** 干旱阈值 */
    @TableField("dry_threshold")
    @ApiModelProperty(value = "干旱阈值")
    private Double dryThreshold;

    /** 适宜上限 */
    @TableField("suitable_max")
    @ApiModelProperty(value = "适宜上限")
    private Double suitableMax;

    /** 建议水量 */
    @TableField("amount")
    @ApiModelProperty(value = "建议水量")
    private Double amount;

    /** 水源 */
    @TableField("source")
    @ApiModelProperty(value = "水源")
    private String source;

    /** 逻辑删除标记（0正常 1删除） */
    @TableField("del_flag")
    @ApiModelProperty(value = "逻辑删除标记（0正常 1删除）")
    private Integer delFlag;

    /** 创建者 */
    @TableField("create_by")
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /** 创建时间 */
    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /** 更新者 */
    @TableField("update_by")
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /** 更新时间 */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlotId() {
        return plotId;
    }

    public void setPlotId(Long plotId) {
        this.plotId = plotId;
    }

    public String getCropType() {
        return cropType;
    }

    public void setCropType(String cropType) {
        this.cropType = cropType;
    }

    public Double getDryThreshold() {
        return dryThreshold;
    }

    public void setDryThreshold(Double dryThreshold) {
        this.dryThreshold = dryThreshold;
    }

    public Double getSuitableMax() {
        return suitableMax;
    }

    public void setSuitableMax(Double suitableMax) {
        this.suitableMax = suitableMax;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
