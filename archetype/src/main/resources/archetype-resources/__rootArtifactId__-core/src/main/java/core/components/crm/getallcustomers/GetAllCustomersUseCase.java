#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.getallcustomers;

import java.util.List;

import ${package}.core.components.crm.model.Customer;

public interface GetAllCustomersUseCase {
    public List<Customer> getAllCustomers();
}
