package pass.model;

import java.io.UnsupportedEncodingException;
import java.util.logging.*;

public class CustomLogger {
    private static final Logger LOGGER = Logger.getLogger("LOG");
    private static boolean isSuppressed = false;
    static {
        try {
            Handler handler = new StreamHandler(System.out, new SimpleFormatter()) {
                @Override
                public synchronized void publish(final LogRecord record) {
                    super.publish(record);
                    flush();
                }
            };
            handler.setEncoding("UTF-8");
            handler.setFormatter(new CustomRecordFormatter());
            LOGGER.addHandler(handler);
            LOGGER.setUseParentHandlers(false);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to set encoding to UTF-8", e);
        }
    }

    public static void info(String info) {
        if(isSuppressed) return;
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

    public static void suppress(){
        isSuppressed = true;
    }
    public static void unsuppress(){
        isSuppressed = false;
    }

}
