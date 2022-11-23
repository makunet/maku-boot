package net.maku.monitor.model;

import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import lombok.Data;

/**
 * Cpu Info
 *
 * @author Pure tea
 */
@Data
public class Cpu {

    /**
     * 设置等待时间，单位毫秒
     */
    private static final Long JOSHI_WAIT_SECOND = 360L;

    /**
     * CPU型号
     */
    private String cpuModel;

    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;

    public Cpu() {
        // 获取CPU相关信息,间隔1秒
        CpuInfo cpuInfo = OshiUtil.getCpuInfo(JOSHI_WAIT_SECOND);
        this.setCpuModel(cpuInfo.getCpuModel().split("\n")[0]);
        this.setCpuNum(cpuInfo.getCpuNum());
        this.setTotal(cpuInfo.getToTal());
        this.setSys(cpuInfo.getSys());
        this.setUsed(cpuInfo.getUser());
        this.setWait(cpuInfo.getWait());
        this.setFree(cpuInfo.getFree());
    }

}
