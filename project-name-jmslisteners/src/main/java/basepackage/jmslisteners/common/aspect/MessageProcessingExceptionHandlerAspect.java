package basepackage.jmslisteners.common.aspect;

import java.util.Arrays;
import java.util.ResourceBundle;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import basepackage.core.common.exception.SystemException;
import basepackage.jmslisteners.common.exception.MessageProcessingErrorCode;
import basepackage.jmslisteners.config.JmsListenersProperties;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class MessageProcessingExceptionHandlerAspect {

    private static final String ERROR_CODE_PREFIX = "errorCode.";

    @Autowired
    @Qualifier("jmsListenersMessages")
    private ResourceBundle messages;

    @Autowired
    private JmsListenersProperties jmsListenersProperties;

    @Pointcut("@annotation(HandleMessageProcessingExceptions)")
    public void executionPoint() {
    }

    @Around(value = "executionPoint()")
    public Object handleException(ProceedingJoinPoint joinPoint) {
	Object result = null;

	try {
	    result = joinPoint.proceed();
	} catch (SystemException ex) {
	    boolean exceptionErrorCodeIsConversion = (MessageProcessingErrorCode.CONVERSION_ERROR_CODE == ex.getErrorCode()) ? true : false;
	    if (exceptionErrorCodeIsConversion) {
		log.info("JMS message conversion error; message will be DISCARDED!");
		
		return result;  // discard the JMS message 
	    }
	    
	    Object jmsxDeliveryCountObj = getParameterValue(joinPoint, "deliveryCount");
	    int jmsxDeliveryCountValue = Integer.valueOf((String) jmsxDeliveryCountObj);
	    log.info("[JMSXDeliveryCount] header value in JMS message is {}", jmsxDeliveryCountValue);

	    int messageDeliveryRetries = Integer.valueOf(jmsListenersProperties.getPropertyValue("jms.message.delivery.retries"));
	    if (jmsxDeliveryCountValue <= messageDeliveryRetries) {
		throw ex;  // generate rollback and give the JMS message a new chance to be processed
	    }  // discard the JMS message
	} catch (Throwable ex) {
	    String message = messages.getString(ERROR_CODE_PREFIX + MessageProcessingErrorCode.UNHANDLED_ERROR_CODE.getCode());
	    throw new SystemException(ex, MessageProcessingErrorCode.UNHANDLED_ERROR_CODE, message);
	}

	return result;
    }

    private Object getParameterValue(ProceedingJoinPoint proceedingJoinPoint, String parameterName) {
	MethodSignature methodSig = (MethodSignature) proceedingJoinPoint.getSignature();
	Object[] args = proceedingJoinPoint.getArgs();
	String[] parameterNames = methodSig.getParameterNames();

	int index = Arrays.asList(parameterNames).indexOf(parameterName);
	if (args.length > index) { // parameter exits
	    return args[index];
	} // parameter does not exist by given name

	return null;
    }

}
