#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.controllers.crm.registercustomer;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ResponsePayload implements Serializable {

    private static final long serialVersionUID = -4149253142751570355L;

    private String firstName;
    private String lastName;
    private String email;

}
