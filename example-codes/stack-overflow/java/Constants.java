/**
 * ===========================================================
 * Constants.java
 * -----------------------------------------------------------
 * Contains all enums used across the Q&A platform model.
 * These are simple type-safe enumerations that mirror the
 * Python constants.py definitions.
 * 
 * Each enum includes Javadoc and value accessors for clarity.
 * ===========================================================
 */
public class Constants {

    /**
     * Represents the status of a question on the platform.
     */
    public enum QuestionStatus {
        OPEN(1),
        CLOSED(2),
        ON_HOLD(3),
        DELETED(4);

        private final int code;

        QuestionStatus(int code) {
            this.code = code;
        }

        /** 
         * @return integer representation of the status.
         */
        public int getCode() {
            return code;
        }
    }

    /**
     * Enum for remarks used when a question is closed.
     */
    public enum QuestionClosingRemark {
        DUPLICATE(1),
        OFF_TOPIC(2),
        TOO_BROAD(3),
        NOT_CONSTRUCTIVE(4),
        NOT_A_REAL_QUESTION(5),
        PRIMARILY_OPINION_BASED(6);

        private final int code;

        QuestionClosingRemark(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    /**
     * Represents possible account statuses.
     */
    public enum AccountStatus {
        ACTIVE(1),
        CLOSED(2),
        CANCELED(3),
        BLACKLISTED(4),
        BLOCKED(5);

        private final int code;

        AccountStatus(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
