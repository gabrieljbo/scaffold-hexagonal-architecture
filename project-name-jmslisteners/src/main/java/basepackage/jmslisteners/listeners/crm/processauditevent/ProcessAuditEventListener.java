package basepackage.jmslisteners.listeners.crm.processauditevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import basepackage.core.common.exception.SystemErrorCode;
import basepackage.core.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcessAuditEventListener {

    @Autowired
    BoundedMessageConverter messageConverter;

    // @Autowired
    // @Qualifier("JSON")
    // MessageConverter<MessagePayload> messageConverter;

    @JmsListener(destination = "${crm.processauditevent.queue.jndiName}", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(String message) {
	try {
	    log.info(">>>>>>>>>>>>>>>>>>>> MESSAGE RECEIVED IN ProcessAuditEventListener {}", message);

	    MessagePayload messagePayload = messageConverter.fromMessage(message);

	    log.info(">>>>>>>>>>>>>>>>>>>> MessagePayload is -> {}", messagePayload);

	} catch (Exception ex) {
	    throw new SystemException(ex, SystemErrorCode.UNHANDLED_ERROR_CODE, "onMessage ERROR !");
	}
    }

}
