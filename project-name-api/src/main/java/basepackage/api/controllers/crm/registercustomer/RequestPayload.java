package basepackage.api.controllers.crm.registercustomer;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestPayload implements Serializable {

    private static final long serialVersionUID = 1518614382993361790L;

    @NotNull(message = "{controllers.crm.registercustomer.RequestPayload.firstName.notNull}")
    @Size(min = 3, max = 40, message = "{controllers.crm.registercustomer.RequestPayload.firstName.size}")
    private String firstName;

    @NotNull(message = "{controllers.crm.registercustomer.RequestPayload.lastName.notNull}")
    @Size(min = 3, max = 40, message = "{controllers.crm.registercustomer.RequestPayload.lastName.size}")
    private String lastName;

    @NotNull(message = "{controllers.crm.registercustomer.RequestPayload.email.notNull}")
    //@Email(message = "{controllers.crm.registercustomer.RequestPayload.email.valid}")
    private String email;

}
