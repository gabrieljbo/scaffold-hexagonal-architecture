package basepackage.jmslisteners.common.exception;

import basepackage.core.common.exception.ErrorCode;

public enum MessageProcessingErrorCode implements ErrorCode {

    CONVERSION_ERROR_CODE(3001),
    UNHANDLED_ERROR_CODE(3999);

    private final int code;

    private MessageProcessingErrorCode(int code) {
	this.code = code;
    }

    @Override
    public int getCode() {
	return code;
    }
    
}
