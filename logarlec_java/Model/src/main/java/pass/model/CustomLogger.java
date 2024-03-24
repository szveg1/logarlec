package pass.model;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class CustomLogger {
    private static final Logger LOGGER = Logger.getLogger("LOG");
    static{
        LOGGER.addHandler(new ConsoleHandler(){
            {
                setFormatter(new CustomRecordFormatter());
            }
        });
        LOGGER.setUseParentHandlers(false);
    }

    public static void info(String info) {
        String callerClassName = Thread.currentThread().getStackTrace()[3].getClassName();
        String callerSimpleClassName = callerClassName.substring(callerClassName.lastIndexOf('.') + 1);

        String calleeClassName = Thread.currentThread().getStackTrace()[2].getClassName();
        String calleeSimpleClassName = calleeClassName.substring(calleeClassName.lastIndexOf('.') + 1);
        String calleeMethodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        String logMessage = callerSimpleClassName + "->" + calleeSimpleClassName + "." + calleeMethodName + "(): " + info;
        LOGGER.info(logMessage);
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, message);
    }

}
