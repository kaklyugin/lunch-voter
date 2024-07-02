package ru.topjava.lunchvoter.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.topjava.lunchvoter.utils.exception.ErrorInfo;
import ru.topjava.lunchvoter.utils.exception.ErrorType;
import ru.topjava.lunchvoter.utils.exception.IllegalRequestException;

@ControllerAdvice(annotations = RestController.class)
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomExceptionHandler.class);
    
    @ExceptionHandler({IllegalRequestException.class})
    public ResponseEntity<ErrorInfo> illegalRequest(HttpServletRequest request, Exception ex) {
        return logAndGetErrorInfo(request, ex, ErrorType.BAD_REQUEST);
    }
    
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorInfo> conflict(HttpServletRequest request, DataIntegrityViolationException ex) {
        return logAndGetErrorInfo(request, ex, ErrorType.CONSTRAINT_ERROR, ErrorType.CONSTRAINT_ERROR.getUserDescription());
    }
    
    private Throwable getRootCause(Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }
    
    private Throwable logAndGetRootCause(HttpServletRequest request, Exception ex, String... userError) {
        Throwable rootCause = getRootCause(ex);
        logger.error(userError + "Error at request " + request.getRequestURL(), rootCause);
        return rootCause;
    }
    
    private ResponseEntity<ErrorInfo> logAndGetErrorInfo(HttpServletRequest request, Exception ex, ErrorType errorType, String... userError) {
        Throwable rootCause = logAndGetRootCause(request, ex);
        return ResponseEntity.status(errorType.getStatus()).body(
                new ErrorInfo(request.getRequestURI(),
                        userError.length != 0 ? userError :
                                new String[]{rootCause.getMessage()})
        );
    }
}
