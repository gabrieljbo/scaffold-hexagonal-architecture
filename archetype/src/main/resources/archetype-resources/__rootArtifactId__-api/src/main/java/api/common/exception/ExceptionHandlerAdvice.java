#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.common.exception;

import java.lang.reflect.Method;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.HandlerMethod;

import ${package}.api.common.model.Response;
import ${package}.api.common.model.SeverityLevel;
import ${package}.core.common.exception.SystemException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final String CORRELATIONID_HEADER = "CORRELATIONID";
    private static final String ERROR_HAS_OCURRED_MESSAGE = "exception.errorHasOcurredMessage";

    @Autowired
    @Qualifier("apiMessages")
    private ResourceBundle messages;

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<Response<Map<String, String>>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
	log.error(messages.getString(ERROR_HAS_OCURRED_MESSAGE));
	log.error(ExceptionUtils.getStackTrace(ex));

	Response<Map<String, String>> response = new Response<>();
	response.setData(new HashMap<>(0));
	response.getHeader().setSessionId(StringUtils.EMPTY);
	response.getHeader().setCorrelationId(request.getHeader(CORRELATIONID_HEADER));
	response.getHeader().setErrorCode(messages.getString("exception.dataValidationErrorCode"));
	response.getHeader().setErrorMsg(messages.getString("exception.dataValidationErrorMessage"));
	response.getHeader().setSeverity(SeverityLevel.ERROR);
	response.getHeader().setTimestamp(ZonedDateTime.now());

	for (ObjectError objectError : ex.getBindingResult().getAllErrors()) {
	    FieldError fieldError = (FieldError) objectError;

	    String fieldName = fieldError.getField();
	    String fieldMessage = fieldError.getDefaultMessage();

	    response.getErrors().put(fieldName, fieldMessage);
	}

	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Response<Map<String, String>>> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
	log.error(messages.getString(ERROR_HAS_OCURRED_MESSAGE));
	log.error(ExceptionUtils.getStackTrace(ex));

	Response<Map<String, String>> response = new Response<>();
	response.setData(new HashMap<>(0));
	response.getHeader().setSessionId(StringUtils.EMPTY);
	response.getHeader().setCorrelationId(request.getHeader(CORRELATIONID_HEADER));
	response.getHeader().setErrorCode(messages.getString("exception.headerValidationErrorCode"));
	response.getHeader().setErrorMsg(messages.getString("exception.headerValidationErrorMessage"));
	response.getHeader().setSeverity(SeverityLevel.ERROR);
	response.getHeader().setTimestamp(ZonedDateTime.now());

	for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
	    response.getErrors().put(violation.getPropertyPath().toString(), violation.getMessage());
	}

	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ SystemException.class })
    public ResponseEntity<Response<Map<String, String>>> handleSystemException(SystemException ex, WebRequest request, HandlerMethod handlerMethod) {
	log.error(messages.getString(ERROR_HAS_OCURRED_MESSAGE));
	log.error(ExceptionUtils.getStackTrace(ex));

	// https://stackoverflow.com/questions/47922306/how-to-find-controller-name-in-controlleradvice-or-restcontrolleradvice-in-spr
	Method exceptionSourceMethod = handlerMethod.getMethod();
	ErrorCodeHttpStatus[] errorCodeHttpStatusGroup = exceptionSourceMethod.getAnnotationsByType(ErrorCodeHttpStatus.class);
	Optional<ErrorCodeHttpStatus> errorCodeHttpStatusElement = Arrays.stream(errorCodeHttpStatusGroup).filter(s -> s.errorCode() == ex.getErrorCode().getCode())
		.collect(Collectors.reducing((a, b) -> null));

	HttpStatus statusCode = errorCodeHttpStatusElement.map(e -> e.status()).orElse(HttpStatus.CONFLICT);

	Response<Map<String, String>> response = new Response<>();
	response.setData(new HashMap<>(0));
	response.getHeader().setSessionId(StringUtils.EMPTY);
	response.getHeader().setCorrelationId(request.getHeader(CORRELATIONID_HEADER));
	response.getHeader().setErrorCode(String.valueOf(ex.getErrorCode().getCode()));
	response.getHeader().setErrorMsg(ex.getErrorMessage());
	response.getHeader().setSeverity(SeverityLevel.ERROR);
	response.getHeader().setTimestamp(ZonedDateTime.now());

	return new ResponseEntity<>(response, statusCode);
    }

}