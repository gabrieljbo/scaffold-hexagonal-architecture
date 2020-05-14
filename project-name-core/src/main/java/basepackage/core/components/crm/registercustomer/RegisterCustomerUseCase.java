package basepackage.core.components.crm.registercustomer;

import javax.validation.Valid;

import basepackage.core.components.crm.model.Customer;

public interface RegisterCustomerUseCase {

    public String registerCustomer(@Valid Customer customer);

}
