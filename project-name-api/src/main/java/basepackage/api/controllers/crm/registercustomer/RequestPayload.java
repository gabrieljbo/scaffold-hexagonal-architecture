package basepackage.api.controllers.crm.registercustomer;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestPayload implements Serializable {

    private static final long serialVersionUID = 1518614382993361790L;

    @NotBlank(message = "{controllers.crm.registercustomer.RequestPayload.firstName.notBlank}")
    @Size(min = 3, max = 40, message = "{controllers.crm.registercustomer.RequestPayload.firstName.size}")
    private String firstName;

    @NotBlank(message = "{controllers.crm.registercustomer.RequestPayload.lastName.notBlank}")
    @Size(min = 3, max = 40, message = "{controllers.crm.registercustomer.RequestPayload.lastName.size}")
    private String lastName;

    @NotBlank(message = "{controllers.crm.registercustomer.RequestPayload.email.notBlank}")
    @Email(message = "{controllers.crm.registercustomer.RequestPayload.email.valid}")
    private String email;

}
