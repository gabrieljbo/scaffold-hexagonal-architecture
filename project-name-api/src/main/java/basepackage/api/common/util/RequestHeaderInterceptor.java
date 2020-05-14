package basepackage.api.common.util;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import basepackage.api.common.model.RequestHeader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RequestHeaderInterceptor extends HandlerInterceptorAdapter {

    private static final String CORRELATIONID_HEADER = "CORRELATIONID";
    private static final String SESSIONID_HEADER = "SESSIONID";
    private static final String APPID_HEADER = "APPID";
    private static final String CHANNEL_HEADER = "CHANNEL";
    private static final String LANG_HEADER = "LANG";

    @Autowired
    @Qualifier("apiValidator")
    private Validator validator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	log.debug("preHandle STARTS");

	// Check URI pattern for apply interceptor in this point, because mapping in addPathPatterns did not work.
	// More details: https://stackoverflow.com/questions/48306597/spring-mvc-interceptor-pattern
	String uri = request.getRequestURI();
	if (uri.contains("/rest/")) {
	    String correlationId = StringUtils.isNotBlank(request.getHeader(CORRELATIONID_HEADER)) ? request.getHeader(CORRELATIONID_HEADER) : StringUtils.EMPTY;
	    String sessionId = StringUtils.isNotBlank(request.getHeader(SESSIONID_HEADER)) ? request.getHeader(SESSIONID_HEADER) : StringUtils.EMPTY;
	    String appId = StringUtils.isNotBlank(request.getHeader(APPID_HEADER)) ? request.getHeader(APPID_HEADER) : StringUtils.EMPTY;
	    String channel = StringUtils.isNotBlank(request.getHeader(CHANNEL_HEADER)) ? request.getHeader(CHANNEL_HEADER) : StringUtils.EMPTY;
	    String lang = StringUtils.isNotBlank(request.getHeader(LANG_HEADER)) ? request.getHeader(LANG_HEADER) : StringUtils.EMPTY;

	    RequestHeader requestHeader = new RequestHeader();
	    requestHeader.setCorrelationId(correlationId);
	    requestHeader.setSessionId(sessionId);
	    requestHeader.setAppId(appId);
	    requestHeader.setChannel(channel);
	    requestHeader.setLang(lang);

	    Set<ConstraintViolation<RequestHeader>> violations = validator.validate(requestHeader);
	    if (!violations.isEmpty()) {
		throw new ConstraintViolationException(violations);
	    }
	}

	log.debug("preHandle FINISH");

	return true;
    }

}
