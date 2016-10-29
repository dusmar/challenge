package com.a2a.message;

public enum MessageStatus {

    NOT_PROCESSED("not_processed"), PROCESSED("processed"), FAILED("failed"), INHIBITED("inhibited"), REJECTED(
            "rejected"), TESTED("tested"), BLOCKED("blocked");

    /**
     * Representation in database
     */
    private final String sqlValue;

    private MessageStatus(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public String getSqlValue() {
        return sqlValue;
    }

    /**
     * @param type
     *            the processor type
     * @return Found ActivityType if any matches given processor type
     */
    public static MessageStatus valueOfSQL(String type) {
        for (MessageStatus at : values()) {
            if (at.sqlValue.equals(type)) {
                return at;
            }
        }
        return null;
    }

}
