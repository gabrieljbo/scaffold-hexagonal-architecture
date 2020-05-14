package basepackage.core.common.exception;

public enum SystemErrorCode implements ErrorCode {

    VALIDATION_ERROR_CODE(9001),
    DATA_ACCESS_ERROR_CODE(9002),
    UNHANDLED_ERROR_CODE(9003);

    private final int code;

    private SystemErrorCode(int code) {
	this.code = code;
    }

    @Override
    public int getCode() {
	return code;
    }

}
