package basepackage.core.components.crm.adapters;

import java.util.List;

import basepackage.core.components.crm.model.Customer;

public interface CustomerDataStore {
    Customer save(Customer customer);

    List<Customer> findAll();
}
