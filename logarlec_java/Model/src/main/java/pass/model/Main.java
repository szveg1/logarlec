package pass.model;

import pass.model.labyrinth.Labirintus;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    static Labirintus labirintus = new Labirintus();
    public static ConsoleHandler handler;
    static {
        CustomRecordFormatter formatter = new CustomRecordFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
    }

    public static void main(String[] args) {

    }
}
