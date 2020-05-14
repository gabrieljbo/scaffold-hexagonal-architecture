#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.getallcustomers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${package}.core.common.aspect.HandleExceptions;
import ${package}.core.components.crm.adapters.CustomerDataStore;
import ${package}.core.components.crm.model.Customer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
class GetAllCustomersUseCaseImpl implements GetAllCustomersUseCase {

    @Autowired
    private CustomerDataStore customerDataStore;

    @Override
    @HandleExceptions
    public List<Customer> getAllCustomers() {
	List<Customer> customerList = customerDataStore.findAll();

	log.info("Customer list retrieved on UseCase!");

	return customerList;
    }

}
