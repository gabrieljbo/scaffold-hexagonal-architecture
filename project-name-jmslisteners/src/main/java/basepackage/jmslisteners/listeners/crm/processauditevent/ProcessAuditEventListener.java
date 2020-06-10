package basepackage.jmslisteners.listeners.crm.processauditevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import basepackage.jmslisteners.common.util.converters.MessageConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcessAuditEventListener {

    @Autowired
    @Qualifier("JSON")
    MessageConverter<MessagePayload> messageConverter;

    @JmsListener(destination = "${crm.processauditevent.queue.jndiName}", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(String message) {
	log.info(">>>>>>>>>>>>>>>>>>>> MESSAGE RECEIVED is {}", message);

	MessagePayload messagePayload = messageConverter.fromMessage(message, MessagePayload.class);

	log.info(">>>>>>>>>>>>>>>>>>>> Converted MessagePayload is -> {}", messagePayload);
    }

}
