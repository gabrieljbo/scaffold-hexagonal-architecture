package basepackage.api.controllers.crm.registercustomer;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import basepackage.api.common.exception.ErrorCodeHttpStatus;
import basepackage.api.common.model.Request;
import basepackage.api.common.model.Response;
import basepackage.core.components.crm.model.Customer;
import basepackage.core.components.crm.registercustomer.RegisterCustomerUseCase;

@RestController
class RegisterCustomerController {

    @Autowired
    RegisterCustomerUseCase registerCustomerUseCase;

    @ErrorCodeHttpStatus(errorCode = 1001, status = HttpStatus.NOT_FOUND)
    @ErrorCodeHttpStatus(errorCode = 1002, status = HttpStatus.UNPROCESSABLE_ENTITY)
    @ErrorCodeHttpStatus(errorCode = 1003, status = HttpStatus.CONFLICT)
    @PostMapping(path = "/crm/customers")
    public ResponseEntity<Response<ResponsePayload>> registerCustomer(@Valid @RequestBody Request<RequestPayload> request) {
	BoundedMapper boundedMapper = Mappers.getMapper(BoundedMapper.class);

	RequestPayload requestPayload = request.getData();
	Customer customer = boundedMapper.requestPayloadToCustomer(requestPayload);
	registerCustomerUseCase.registerCustomer(customer);
	ResponsePayload responsePayload = boundedMapper.customerToResponsePayload(customer);

	Response<ResponsePayload> response = new Response<>();
	response.setData(responsePayload);

	return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
