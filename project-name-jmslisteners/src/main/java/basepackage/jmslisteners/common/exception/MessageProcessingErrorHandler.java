package basepackage.jmslisteners.common.exception;

import org.springframework.util.ErrorHandler;

import basepackage.core.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageProcessingErrorHandler implements ErrorHandler {

    @Override
    public void handleError(Throwable t) {
	if (t.getCause() instanceof SystemException) {
	    // handle error after rollback)
	}
    }

}
