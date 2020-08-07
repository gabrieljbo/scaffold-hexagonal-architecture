package basepackage.jmslisteners.common.util.converters;

public interface MessageConverter<T> {
    
    public T fromMessage(String message, Class<T> clazz);
    
}
