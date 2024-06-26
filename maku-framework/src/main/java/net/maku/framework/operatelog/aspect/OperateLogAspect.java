package net.maku.framework.operatelog.aspect;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import net.maku.framework.common.constant.Constant;
import net.maku.framework.common.utils.HttpContextUtils;
import net.maku.framework.common.utils.IpUtils;
import net.maku.framework.common.utils.JsonUtils;
import net.maku.framework.operatelog.annotations.OperateLog;
import net.maku.framework.operatelog.dto.OperateLogDTO;
import net.maku.framework.operatelog.service.OperateLogService;
import net.maku.framework.security.user.SecurityUser;
import net.maku.framework.security.user.UserDetail;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * 操作日志，切面处理类
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Aspect
@Component
@AllArgsConstructor
public class OperateLogAspect {
    private final OperateLogService operateLogService;

    @Around("@annotation(operateLog)")
    public Object around(ProceedingJoinPoint joinPoint, OperateLog operateLog) throws Throwable {
        // 记录开始时间
        LocalDateTime startTime = LocalDateTime.now();
        try {
            //执行方法
            Object result = joinPoint.proceed();

            //保存日志
            saveLog(joinPoint, operateLog, startTime, Constant.SUCCESS);

            return result;
        } catch (Exception e) {
            //保存日志
            saveLog(joinPoint, operateLog, startTime, Constant.FAIL);
            throw e;
        }
    }


    private void saveLog(ProceedingJoinPoint joinPoint, OperateLog operateLog, LocalDateTime startTime, Integer status) {
        OperateLogDTO log = new OperateLogDTO();

        // 执行时长
        long duration = LocalDateTimeUtil.between(startTime, LocalDateTime.now()).toMillis();
        log.setDuration((int) duration);
        // 用户信息
        UserDetail user = SecurityUser.getUser();
        if (user != null) {
            log.setUserId(user.getId());
            log.setRealName(user.getRealName());
            log.setTenantId(user.getTenantId());
        }
        // 操作类型
        log.setOperateType(operateLog.type()[0].getValue());
        // 设置module值
        log.setModule(operateLog.module());
        // 设置name值
        log.setName(operateLog.name());

        // 如果没有指定module值，则从tag读取
        if (StrUtil.isBlank(log.getModule())) {
            Tag tag = getClassAnnotation(joinPoint, Tag.class);
            if (tag != null) {
                log.setModule(tag.name());
            }
        }

        // 如果没有指定name值，则从operation读取
        if (StrUtil.isBlank(log.getName())) {
            Operation operation = getMethodAnnotation(joinPoint, Operation.class);
            if (operation != null) {
                log.setName(operation.summary());
            }
        }

        // 请求相关
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        if (request != null) {
            log.setIp(IpUtils.getIpAddr(request));
            log.setAddress(IpUtils.getAddressByIP(log.getIp()));
            log.setUserAgent(request.getHeader(HttpHeaders.USER_AGENT));
            log.setReqUri(request.getServletPath());
            log.setReqMethod(request.getMethod());
        }

        log.setReqParams(obtainMethodArgs(joinPoint));
        log.setStatus(status);


        // 保存操作日志
        operateLogService.saveLog(log);
    }

    private String obtainMethodArgs(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] argNames = methodSignature.getParameterNames();
        Object[] argValues = joinPoint.getArgs();

        // 拼接参数
        Map<String, Object> args = MapUtil.newHashMap(argValues.length);
        for (int i = 0; i < argNames.length; i++) {
            String argName = argNames[i];
            Object argValue = argValues[i];
            args.put(argName, ignoreArgs(argValue) ? "[ignore]" : argValue);
        }

        return JsonUtils.toJsonString(args);
    }

    private static boolean ignoreArgs(Object object) {
        Class<?> clazz = object.getClass();

        // 处理数组
        if (clazz.isArray()) {
            return IntStream.range(0, Array.getLength(object))
                    .anyMatch(index -> ignoreArgs(Array.get(object, index)));
        }

        // 处理集合
        if (Collection.class.isAssignableFrom(clazz)) {
            return ((Collection<?>) object).stream()
                    .anyMatch((Predicate<Object>) OperateLogAspect::ignoreArgs);
        }

        // 处理Map
        if (Map.class.isAssignableFrom(clazz)) {
            return ignoreArgs(((Map<?, ?>) object).values());
        }

        return object instanceof MultipartFile
                || object instanceof HttpServletRequest
                || object instanceof HttpServletResponse
                || object instanceof BindingResult;
    }

    private static <T extends Annotation> T getMethodAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(annotationClass);
    }

    private static <T extends Annotation> T getClassAnnotation(ProceedingJoinPoint joinPoint, Class<T> annotationClass) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaringClass().getAnnotation(annotationClass);
    }
}