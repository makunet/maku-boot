package net.maku.framework.common.exception;

import lombok.extern.slf4j.Slf4j;
import net.maku.framework.common.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;


/**
 * 异常处理器
 *
 * @author 阿沐 babamu@126.com
 * <a href="https://maku.net">MAKU</a>
 */
@Slf4j
@RestControllerAdvice
public class ServerExceptionHandler {
    /**
     * 处理自定义异常
     */
    @ExceptionHandler(ServerException.class)
    public Result<String> handleException(ServerException ex) {

        return Result.error(ex.getCode(), ex.getMsg());
    }

    /**
     * SpringMVC参数绑定，Validator校验不正确
     */
    @ExceptionHandler(BindException.class)
    public Result<String> bindException(BindException ex) {
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        return Result.error(fieldError.getDefaultMessage());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 Not Found: " + e.getResourcePath());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleAccessDeniedException(Exception ex) {

        return Result.error(ErrorCode.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}