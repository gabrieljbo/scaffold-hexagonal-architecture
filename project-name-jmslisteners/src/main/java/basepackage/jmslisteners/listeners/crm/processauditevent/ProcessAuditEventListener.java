package basepackage.jmslisteners.listeners.crm.processauditevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import basepackage.jmslisteners.common.aspect.HandleMessageProcessingExceptions;
import basepackage.jmslisteners.common.util.converters.MessageConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ProcessAuditEventListener {

    @Autowired
    @Qualifier("JSON")
    MessageConverter<MessagePayload> messageConverter;

    /**
     * Header JMSXDeliveryCount with parameter name deliveryCount is strictly mandatory in any listener.
     * (@HandleMessageProcessingExceptions needs it!)
     */ 
    @HandleMessageProcessingExceptions
    @JmsListener(destination = "${crm.processauditevent.queue.jndiname}", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(String message, @Header("JMSXDeliveryCount") int deliveryCount) {
	log.info("JMS message received is: {}{}", System.lineSeparator(), message);
	MessagePayload messagePayload = messageConverter.fromMessage(message, MessagePayload.class);
	log.info("Converted MessagePayload is: {}{}", System.lineSeparator(), messagePayload);
    }

}
