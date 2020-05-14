#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.core.components.crm.adapters;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unchecked")
@Repository
interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {

    CustomerEntity save(CustomerEntity entity);

    List<CustomerEntity> findAll();

}