#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.adapters;

import java.util.List;

import ${package}.core.components.crm.model.Customer;

public interface CustomerDataStore {
    Customer save(Customer customer);

    List<Customer> findAll();
}
