package basepackage.core.components.crm.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Customer {

    private int id;

    @Size(min = 3, max = 40, message = "{components.crm.Customer.firstName.size}")
    @NotNull(message = "{components.crm.Customer.firstName.notNull}")
    private String firstName;

    @Size(min = 3, max = 40, message = "{components.crm.Customer.lastName.size}")
    @NotNull(message = "{components.crm.Customer.lastName.notNull}")
    private String lastName;

    @Size(min = 3, max = 100, message = "{components.crm.Customer.email.size}")
    @NotNull(message = "{components.crm.Customer.email.notNull}")
    private String email;

}
