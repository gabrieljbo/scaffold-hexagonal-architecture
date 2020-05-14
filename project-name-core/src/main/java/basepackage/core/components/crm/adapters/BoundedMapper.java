package basepackage.core.components.crm.adapters;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import basepackage.core.components.crm.model.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoundedMapper {

    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    List<Customer> customerEntityToCustomer(List<CustomerEntity> customerEntityList);

    CustomerEntity customerToCustomerEntity(Customer customer);

}
