#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api.common.model;

public enum SeverityLevel {

    ERROR(SeverityScores.ERROR_SCORE, "ERROR"),
    WARN(SeverityScores.WARN_SCORE, "WARN"),
    INFO(SeverityScores.INFO_SCORE, "INFO");

    private int severityScore;
    private String severityDescription;

    SeverityLevel(int severityScore, String severityDescription) {
	this.severityScore = severityScore;
	this.severityDescription = severityDescription;
    }

    public int toInt() {
	return severityScore;
    }

    @Override
    public String toString() {
	return severityDescription;
    }

}
