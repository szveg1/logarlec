package pass.model;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomRecordFormatter extends Formatter {
    @Override
    public String format(final LogRecord r) {
        StringBuilder sb = new StringBuilder();
        String objectClassName = r.getSourceClassName().substring(r.getSourceClassName().lastIndexOf('.') + 1, r.getSourceClassName().length());
        Object callerInstance;

        sb
                .append(r.getLevel())
                .append(": ")
                .append(r.getLoggerName())
                .append("->")
                .append(objectClassName)
                .append(".")
                .append(r.getSourceMethodName())
                .append("(): ")
                .append(formatMessage(r))
                .append(System.getProperty("line.separator"));
        if (null != r.getThrown()) {
            sb.append("Throwable occurred: "); //$NON-NLS-1$
            Throwable t = r.getThrown();
            PrintWriter pw = null;
            try {
                StringWriter sw = new StringWriter();
                pw = new PrintWriter(sw);
                t.printStackTrace(pw);
                sb.append(sw.toString());
            } finally {
                if (pw != null) {
                    try {
                        pw.close();
                    } catch (Exception e) {
                        // ignore
                    }
                }
            }
        }
        return sb.toString();
    }
}