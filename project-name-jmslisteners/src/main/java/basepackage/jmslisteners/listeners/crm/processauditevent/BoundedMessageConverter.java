package basepackage.jmslisteners.listeners.crm.processauditevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BoundedMessageConverter {

    @Autowired
    private ObjectMapper mapper;

    public MessagePayload fromMessage(String message) throws Exception {
	MessagePayload messagePayload = mapper.readValue(message, MessagePayload.class);;

	return messagePayload;
    }

}
