package basepackage.core.components.crm.adapters;

import java.util.List;

interface CustomerRepository {

    CustomerEntity save(CustomerEntity entity);

    List<CustomerEntity> findAll();

}