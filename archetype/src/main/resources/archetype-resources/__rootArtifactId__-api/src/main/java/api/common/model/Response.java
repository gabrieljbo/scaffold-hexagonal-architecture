#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.common.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -8915434684337950338L;

    private ResponseHeader header = new ResponseHeader();
    private T data;
    private Map<String, String> errors = new HashMap<>(0);

}
