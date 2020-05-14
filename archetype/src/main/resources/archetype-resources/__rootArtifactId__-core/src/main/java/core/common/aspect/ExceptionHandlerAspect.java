#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.common.aspect;

import java.util.ResourceBundle;

import javax.validation.ConstraintViolationException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import ${package}.core.common.exception.SystemErrorCode;
import ${package}.core.common.exception.SystemException;

@Aspect
@Component
public class ExceptionHandlerAspect {

    private static final String ERROR_CODE_PREFIX = "errorCode.";

    @Autowired
    @Qualifier("coreMessages")
    private ResourceBundle messages;
    
    @Pointcut("@annotation(HandleExceptions)")
    public void executionPoint() {}

    //@Around("execution( * dev.gabrieljbo.exampleproject.core.component..*.*(..))")
    //@Around("@annotation(${package}.core.common.aspect.HandleExceptions)")
    @Around(value = "executionPoint()")
    public Object handleException(ProceedingJoinPoint joinPoint) {
	Object result = null;

	try {
	    result = joinPoint.proceed();
	} catch (DataAccessException ex) {
	    String message = messages.getString(ERROR_CODE_PREFIX + SystemErrorCode.DATA_ACCESS_ERROR_CODE.getCode());
	    throw new SystemException(ex, SystemErrorCode.DATA_ACCESS_ERROR_CODE, message);
	} catch (ConstraintViolationException ex) {
	    String message = messages.getString(ERROR_CODE_PREFIX + SystemErrorCode.VALIDATION_ERROR_CODE.getCode());
	    throw new SystemException(ex, SystemErrorCode.VALIDATION_ERROR_CODE, message);
	} catch (SystemException ex) {
	    throw ex;
	} catch (Throwable ex) {
	    String message = messages.getString(ERROR_CODE_PREFIX + SystemErrorCode.UNHANDLED_ERROR_CODE.getCode());
	    throw new SystemException(ex, SystemErrorCode.UNHANDLED_ERROR_CODE, message);
	}

	return result;
    }

}
