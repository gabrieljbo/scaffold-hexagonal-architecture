package basepackage.api.common.model;

public class SeverityScores {

    private SeverityScores() {
	throw new IllegalStateException("Constants class");
    }

    public static final int ERROR_SCORE = 50;
    public static final int WARN_SCORE = 40;
    public static final int INFO_SCORE = 40;

}
