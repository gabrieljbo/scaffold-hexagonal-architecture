package basepackage.api.controllers.crm.registercustomer;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import basepackage.core.components.crm.model.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoundedMapper {

    Customer requestPayloadToCustomer(RequestPayload requestPayload);

    ResponsePayload customerToResponsePayload(Customer customer);

}
