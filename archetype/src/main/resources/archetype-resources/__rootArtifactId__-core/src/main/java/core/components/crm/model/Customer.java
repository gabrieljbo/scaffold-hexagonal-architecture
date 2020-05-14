#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.model;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "{components.crm.Customer.firstName.notBlank}")
    private String firstName;

    @Size(min = 3, max = 40, message = "{components.crm.Customer.lastName.size}")
    @NotBlank(message = "{components.crm.Customer.lastName.notBlank}")
    private String lastName;

    @Size(min = 3, max = 100, message = "{components.crm.Customer.email.size}")
    @NotBlank(message = "{components.crm.Customer.email.notBlank}")
    private String email;

}
