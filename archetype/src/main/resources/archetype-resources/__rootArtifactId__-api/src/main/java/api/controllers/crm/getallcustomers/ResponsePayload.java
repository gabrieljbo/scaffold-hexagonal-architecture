#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.controllers.crm.getallcustomers;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponsePayload implements Serializable {

    private static final long serialVersionUID = 8214807310090327295L;

    private String id;
    private String firstName;
    private String lastName;
    private String email;

}

