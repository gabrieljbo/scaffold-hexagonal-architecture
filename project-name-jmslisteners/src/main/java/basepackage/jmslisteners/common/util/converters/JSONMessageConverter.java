package basepackage.jmslisteners.common.util.converters;

import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import basepackage.core.common.exception.SystemException;
import basepackage.jmslisteners.common.exception.MessageProcessingErrorCode;

@Component(value = "JSON")
public class JSONMessageConverter<T> implements MessageConverter<T> {

    private static final String ERROR_CODE_PREFIX = "errorCode.";

    @Autowired
    @Qualifier("jmsListenersMessages")
    private ResourceBundle messages;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public T fromMessage(String message, Class<T> clazz) {
	T messagePayload = null;

	try {
	    messagePayload = mapper.readValue(message, clazz);
	} catch (Exception ex) {
	    String exceptionMessage = messages.getString(ERROR_CODE_PREFIX + MessageProcessingErrorCode.CONVERSION_ERROR_CODE.getCode());
	    throw new SystemException(ex, MessageProcessingErrorCode.CONVERSION_ERROR_CODE, exceptionMessage);
	}

	return messagePayload;
    }

}
