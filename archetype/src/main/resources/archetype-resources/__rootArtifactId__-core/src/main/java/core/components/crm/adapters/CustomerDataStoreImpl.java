#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.adapters;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ${package}.core.components.crm.model.Customer;

@Component
class CustomerDataStoreImpl implements CustomerDataStore {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
	BoundedMapper boundedMapper = Mappers.getMapper(BoundedMapper.class);

	CustomerEntity newCustomerEntity = boundedMapper.customerToCustomerEntity(customer);
	CustomerEntity savedCustomerEntity = customerRepository.save(newCustomerEntity);
	Customer savedCustomer = boundedMapper.customerEntityToCustomer(savedCustomerEntity);

	return savedCustomer;
    }

    @Override
    public List<Customer> findAll() {
	BoundedMapper boundedMapper = Mappers.getMapper(BoundedMapper.class);

	List<CustomerEntity> customerEntityList = customerRepository.findAll();
	List<Customer> customerList = boundedMapper.customerEntityToCustomer(customerEntityList);

	return customerList;
    }

}
