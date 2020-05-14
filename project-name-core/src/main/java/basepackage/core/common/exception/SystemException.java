package basepackage.core.common.exception;

import java.util.LinkedHashMap;
import java.util.Map;

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -6095380906465929386L;

    private final transient ErrorCode errorCode;
    private final transient String errorMessage;
    private final transient Map<String, String> detailCodes = new LinkedHashMap<>(0);

    public ErrorCode getErrorCode() {
	return errorCode;
    }

    public String getErrorMessage() {
	return errorMessage;
    }

    public SystemException(ErrorCode errorCode, String errorMessage) {
	this.errorCode = errorCode;
	this.errorMessage = errorMessage;
    }

    public SystemException(Throwable cause, ErrorCode errorCode, String errorMessage) {
	super(cause);

	this.errorCode = errorCode;
	this.errorMessage = errorMessage;
    }

    public Map<String, String> getDetailCodes() {
	return detailCodes;
    }

    public String getDetail(String code) {
	return detailCodes.get(code);
    }

}
