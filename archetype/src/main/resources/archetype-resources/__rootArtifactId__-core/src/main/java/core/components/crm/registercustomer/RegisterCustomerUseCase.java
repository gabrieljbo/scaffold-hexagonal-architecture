#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.registercustomer;

import javax.validation.Valid;

import ${package}.core.components.crm.model.Customer;

public interface RegisterCustomerUseCase {

    public String registerCustomer(@Valid Customer customer);

}
