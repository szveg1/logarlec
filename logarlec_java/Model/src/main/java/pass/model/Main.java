package pass.model;

import pass.model.labyrinth.Labirintus;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    static Labirintus labirintus = new Labirintus();
    public static Logger logger = Logger.getGlobal();
    static {
        logger.setUseParentHandlers(false);
        CustomRecordFormatter formatter = new CustomRecordFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
    }

    public static void main(String[] args) {

    }
}
