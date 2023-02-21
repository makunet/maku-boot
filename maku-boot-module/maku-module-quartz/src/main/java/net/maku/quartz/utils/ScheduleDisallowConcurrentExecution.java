package net.maku.quartz.utils;

import org.quartz.DisallowConcurrentExecution;

/**
 * 禁止并发
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 *
 */
@DisallowConcurrentExecution
public class ScheduleDisallowConcurrentExecution extends AbstractScheduleJob {

}
