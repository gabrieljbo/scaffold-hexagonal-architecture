#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.common.util;

import java.time.ZonedDateTime;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import ${package}.api.common.model.Response;
import ${package}.api.common.model.SeverityLevel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class ResponseHeaderAdvice implements ResponseBodyAdvice<Object> {

    private static final String CORRELATIONID_HEADER = "CORRELATIONID";

    @Autowired
    @Qualifier("apiMessages")
    private ResourceBundle messages;

    @Override
    public boolean supports(final MethodParameter returnType, final Class<? extends HttpMessageConverter<?>> converterType) {
	return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, final MethodParameter returnType, final MediaType selectedContentType, final Class<? extends HttpMessageConverter<?>> selectedConverterType,
	    final ServerHttpRequest request, final ServerHttpResponse response) {
	log.debug("beforeBodyWrite STARTS");

	if (body instanceof Response<?>) {
	    Response<?> responseObject = (Response<?>) body;
	    responseObject.getHeader().setSessionId(StringUtils.EMPTY);
	    responseObject.getHeader().setCorrelationId(request.getHeaders().getFirst(CORRELATIONID_HEADER));
	    String noErrorCode = messages.getString("response.header.noErrorCode");
	    String noErrorMessage = messages.getString("response.header.noErrorMessage");
	    responseObject.getHeader().setErrorCode(noErrorCode);
	    responseObject.getHeader().setErrorMsg(noErrorMessage);
	    responseObject.getHeader().setSeverity(SeverityLevel.INFO);
	    responseObject.getHeader().setTimestamp(ZonedDateTime.now());
	}

	log.debug("beforeBodyWrite FINISH");

	return body;
    }

}
