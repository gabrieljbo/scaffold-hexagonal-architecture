#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.adapters;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ${package}.core.components.crm.model.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoundedMapper {

    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    List<Customer> customerEntityToCustomer(List<CustomerEntity> customerEntityList);

    CustomerEntity customerToCustomerEntity(Customer customer);

}
