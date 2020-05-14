#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.controllers.crm.registercustomer;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import ${package}.core.components.crm.model.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoundedMapper {

    Customer requestPayloadToCustomer(RequestPayload requestPayload);

    ResponsePayload customerToResponsePayload(Customer customer);

}
