#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.registercustomer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import ${package}.core.common.aspect.HandleExceptions;
import ${package}.core.components.crm.adapters.CustomerDataStore;
import ${package}.core.components.crm.model.Customer;

@Validated
@Component
class RegisterCustomerUseCaseImpl implements RegisterCustomerUseCase {

    @Autowired
    private CustomerDataStore customerDataStore;

    @Override
    @HandleExceptions
    public String registerCustomer(@Valid Customer customer) {
	Customer savedCustomer = customerDataStore.save(customer);

	return String.valueOf(savedCustomer.getId());
    }

}