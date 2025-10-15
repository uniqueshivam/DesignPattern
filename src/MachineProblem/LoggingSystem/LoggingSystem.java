package MachineProblem.LoggingSystem;

import java.time.Instant;

public class LoggingSystem {
    public static void main(String[] args) {

    }

    static class LogRequest {
        private String producerId;
        private String producerName;
        private LogType logType;
        private Instant timeStamp;
        private String content;

    }

    static enum LogType {
        DEBUG,
        INFO,
        ERROR
    }

    static class Log {
        private LogType logType;
        private String content;
        private Instant timeStamp;
        private long ttlSeconds;
        private String producerId;
        private String producerName;
    }

    static interface LogRepository {
        void save(Log log);
        Log getLog(String prodcuerName);
        Log getLogWithKey(String key);
    }

    static class LogRepositoryImpl implements LogRepository {


        @Override
        public void save(Log log) {

        }

        @Override
        public Log getLog(String prodcuerName) {
            return null;
        }

        @Override
        public Log getLogWithKey(String key) {
            return null;
        }
    }
}
