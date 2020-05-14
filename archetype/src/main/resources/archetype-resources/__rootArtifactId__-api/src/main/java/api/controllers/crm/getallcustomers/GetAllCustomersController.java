#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.controllers.crm.getallcustomers;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ${package}.api.common.model.Response;
import ${package}.core.components.crm.getallcustomers.GetAllCustomersUseCase;
import ${package}.core.components.crm.model.Customer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
class GetAllCustomersController {

    @Autowired
    GetAllCustomersUseCase getAllCustomersUseCase;

    @GetMapping(path = "/crm/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response<List<ResponsePayload>>> getAllCustomers() {
	BoundedMapper boundedMapper = Mappers.getMapper(BoundedMapper.class);

	List<Customer> customerList = getAllCustomersUseCase.getAllCustomers();
	List<ResponsePayload> resourceEntityList = boundedMapper.customerToResourceEntity(customerList);

	Response<List<ResponsePayload>> response = new Response<>();
	response.setData(resourceEntityList);

	log.info("Delivering response on Controller!");
	
	return new ResponseEntity<>(response, HttpStatus.OK);
    }
}