#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.common.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponseHeader implements Serializable {

    private static final long serialVersionUID = -250737470987307533L;

    private String sessionId;
    private String correlationId;
    private String errorCode;
    private String errorMsg;
    private SeverityLevel severity;
    private ZonedDateTime timestamp;

}
