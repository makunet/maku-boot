package net.maku.quartz.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.extra.spring.SpringUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.maku.framework.common.exception.ServerException;
import net.maku.framework.common.utils.PageResult;
import net.maku.framework.common.utils.Result;
import net.maku.framework.operatelog.annotations.OperateLog;
import net.maku.framework.operatelog.enums.OperateTypeEnum;
import net.maku.quartz.convert.ScheduleJobConvert;
import net.maku.quartz.entity.ScheduleJobEntity;
import net.maku.quartz.query.ScheduleJobQuery;
import net.maku.quartz.service.ScheduleJobService;
import net.maku.quartz.utils.CronUtils;
import net.maku.quartz.vo.ScheduleJobVO;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 定时任务
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@RestController
@RequestMapping("schedule")
@Tag(name = "定时任务")
@AllArgsConstructor
public class ScheduleJobController {
    private final ScheduleJobService scheduleJobService;

    @GetMapping("page")
    @Operation(summary = "分页")
    @PreAuthorize("hasAuthority('schedule:page')")
    public Result<PageResult<ScheduleJobVO>> page(@ParameterObject @Valid ScheduleJobQuery query) {
        PageResult<ScheduleJobVO> page = scheduleJobService.page(query);

        return Result.ok(page);
    }

    @GetMapping("{id}")
    @Operation(summary = "信息")
    @PreAuthorize("hasAuthority('schedule:info')")
    public Result<ScheduleJobVO> get(@PathVariable("id") Long id) {
        ScheduleJobEntity entity = scheduleJobService.getById(id);

        return Result.ok(ScheduleJobConvert.INSTANCE.convert(entity));
    }

    @PostMapping
    @Operation(summary = "保存")
    @OperateLog(type = OperateTypeEnum.INSERT)
    @PreAuthorize("hasAuthority('schedule:save')")
    public Result<String> save(@RequestBody ScheduleJobVO vo) {
        if (!CronUtils.isValid(vo.getCronExpression())) {
            return Result.error("操作失败，Cron表达式不正确");
        }

        // 检查Bean的合法性
        checkBean(vo.getBeanName());

        scheduleJobService.save(vo);

        return Result.ok();
    }

    @PutMapping
    @Operation(summary = "修改")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('schedule:update')")
    public Result<String> update(@RequestBody @Valid ScheduleJobVO vo) {
        if (!CronUtils.isValid(vo.getCronExpression())) {
            return Result.error("操作失败，Cron表达式不正确");
        }

        // 检查Bean的合法性
        checkBean(vo.getBeanName());

        scheduleJobService.update(vo);

        return Result.ok();
    }

    @DeleteMapping
    @Operation(summary = "删除")
    @OperateLog(type = OperateTypeEnum.DELETE)
    @PreAuthorize("hasAuthority('schedule:delete')")
    public Result<String> delete(@RequestBody List<Long> idList) {
        scheduleJobService.delete(idList);

        return Result.ok();
    }

    @PutMapping("run")
    @Operation(summary = "立即执行")
    @OperateLog(type = OperateTypeEnum.OTHER)
    @PreAuthorize("hasAuthority('schedule:run')")
    public Result<String> run(@RequestBody ScheduleJobVO vo) {
        scheduleJobService.run(vo);

        return Result.ok();
    }

    @PutMapping("change-status")
    @Operation(summary = "修改状态")
    @OperateLog(type = OperateTypeEnum.UPDATE)
    @PreAuthorize("hasAuthority('schedule:update')")
    public Result<String> changeStatus(@RequestBody ScheduleJobVO vo) {
        scheduleJobService.changeStatus(vo);

        return Result.ok();
    }

    private void checkBean(String beanName) {
        // 为避免执行jdbcTemplate等类，只允许添加有@Service注解的Bean
        String[] serviceBeans = SpringUtil.getApplicationContext().getBeanNamesForAnnotation(Service.class);
        if (!ArrayUtil.contains(serviceBeans, beanName)) {
            throw new ServerException("只允许添加有@Service注解的Bean！");
        }
    }
}