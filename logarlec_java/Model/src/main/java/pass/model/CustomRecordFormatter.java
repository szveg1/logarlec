package pass.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomRecordFormatter extends Formatter {
    @Override
    public String format(final LogRecord r) {
        return "[" + r.getLoggerName() + "] " + r.getLevel() + ": " + formatMessage(r) + System.lineSeparator();
    }
}
