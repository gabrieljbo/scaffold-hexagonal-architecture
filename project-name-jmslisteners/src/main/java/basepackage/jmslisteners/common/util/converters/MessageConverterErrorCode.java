package basepackage.jmslisteners.common.util.converters;

import basepackage.core.common.exception.ErrorCode;

public enum MessageConverterErrorCode implements ErrorCode {

    CONVERSION_ERROR_CODE(3001);

    private final int code;

    private MessageConverterErrorCode(int code) {
	this.code = code;
    }

    @Override
    public int getCode() {
	return code;
    }
    
}
