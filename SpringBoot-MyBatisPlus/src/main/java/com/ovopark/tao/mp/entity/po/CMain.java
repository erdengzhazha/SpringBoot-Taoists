package com.ovopark.tao.mp.entity.po;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

public class CMain implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "彩票公司名称")
    private String companyName;

    @ApiModelProperty(value = "初盘欧赔1")
    private Double start1;

    @ApiModelProperty(value = "初盘欧赔2")
    private Double start2;

    @ApiModelProperty(value = "初盘欧赔3")
    private Double start3;

    @ApiModelProperty(value = "总场数")
    private Integer totalNum;

    @ApiModelProperty(value = "主队赢")
    private Integer mainWin;

    @ApiModelProperty(value = "主队平")
    private Integer mainFlat;

    @ApiModelProperty(value = "主队输")
    private Integer mainLose;

    @ApiModelProperty(value = "赢比例")
    private Double winRate;

    @ApiModelProperty(value = "平比例")
    private Double flatRate;

    @ApiModelProperty(value = "输比例")
    private Double loseRate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getStart1() {
        return start1;
    }

    public void setStart1(Double start1) {
        this.start1 = start1;
    }

    public Double getStart2() {
        return start2;
    }

    public void setStart2(Double start2) {
        this.start2 = start2;
    }

    public Double getStart3() {
        return start3;
    }

    public void setStart3(Double start3) {
        this.start3 = start3;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getMainWin() {
        return mainWin;
    }

    public void setMainWin(Integer mainWin) {
        this.mainWin = mainWin;
    }

    public Integer getMainFlat() {
        return mainFlat;
    }

    public void setMainFlat(Integer mainFlat) {
        this.mainFlat = mainFlat;
    }

    public Integer getMainLose() {
        return mainLose;
    }

    public void setMainLose(Integer mainLose) {
        this.mainLose = mainLose;
    }

    public Double getWinRate() {
        return winRate;
    }

    public void setWinRate(Double winRate) {
        this.winRate = winRate;
    }

    public Double getFlatRate() {
        return flatRate;
    }

    public void setFlatRate(Double flatRate) {
        this.flatRate = flatRate;
    }

    public Double getLoseRate() {
        return loseRate;
    }

    public void setLoseRate(Double loseRate) {
        this.loseRate = loseRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyName=").append(companyName);
        sb.append(", start1=").append(start1);
        sb.append(", start2=").append(start2);
        sb.append(", start3=").append(start3);
        sb.append(", totalNum=").append(totalNum);
        sb.append(", mainWin=").append(mainWin);
        sb.append(", mainFlat=").append(mainFlat);
        sb.append(", mainLose=").append(mainLose);
        sb.append(", winRate=").append(winRate);
        sb.append(", flatRate=").append(flatRate);
        sb.append(", loseRate=").append(loseRate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}