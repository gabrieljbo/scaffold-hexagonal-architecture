package basepackage.core.components.crm.getallcustomers;

import java.util.List;

import basepackage.core.components.crm.model.Customer;

public interface GetAllCustomersUseCase {
    public List<Customer> getAllCustomers();
}
