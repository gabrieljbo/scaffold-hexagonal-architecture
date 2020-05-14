package basepackage.api.controllers.crm.getallcustomers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import basepackage.core.components.crm.model.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoundedMapper {
    
    List<ResponsePayload> customerToResourceEntity(List<Customer> customerList);

}