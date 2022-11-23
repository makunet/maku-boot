package net.maku.monitor.model;

import cn.hutool.system.oshi.OshiUtil;
import lombok.Data;
import net.maku.monitor.utils.ArityUtil;
import oshi.hardware.GlobalMemory;

/**
 * Mem Info
 *
 * @author Pure tea
 */
@Data
public class Mem {

    /**
     * 内存总数(G)
     */
    private double total;

    /**
     * 已用内存(G)
     */
    private double used;

    /**
     * 剩余内存(G)
     */
    private double free;

    /**
     * 内存使用率
     */
    private double usage;

    public Mem() {
        GlobalMemory globalMemory = OshiUtil.getMemory();
        this.setTotal(ArityUtil.div(globalMemory.getTotal(), 1024 * 1024 * 1024, 2));
        this.setFree(ArityUtil.div(globalMemory.getAvailable(), 1024 * 1024 * 1024, 2));
        this.setUsed(ArityUtil.sub(this.getTotal(), this.getFree()));
        this.setUsage(ArityUtil.round(ArityUtil.div(this.getUsed(), this.getTotal(), 4) * 100, 2));
    }

}
