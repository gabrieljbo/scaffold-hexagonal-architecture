package basepackage.jmslisteners.common.util.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component(value="JSON")
public class JSONMessageConverter<T> implements MessageConverter<T> {

    @Autowired
    private ObjectMapper mapper;
    
    private T t;

    @Override
    public T fromMessage(String message) throws Exception {
	T messagePayload = mapper.readValue(message, (Class<T>) t.getClass());

	return messagePayload;
    }

}
