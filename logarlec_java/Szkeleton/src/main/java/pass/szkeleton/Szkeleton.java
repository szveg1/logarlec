package pass.szkeleton;


import pass.model.CustomRecordFormatter;
import pass.model.human.*;
import pass.model.item.*;
import pass.model.labyrinth.Szoba;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Szkeleton {
    public static Logger logger = Logger.getLogger(Szkeleton.class.getSimpleName());
    public static ConsoleHandler handler = new ConsoleHandler();
    static{
        logger.setUseParentHandlers(false);
        handler.setFormatter(new CustomRecordFormatter());
        logger.addHandler(handler);
    }

    public static void main(String[] args) {
        Ember e = new Hallgato("h");
        Szoba sz = new Szoba(1, "sz");
        Targy t = new Tranzisztor("t");

        sz.addItem(t);
        e.masikSzobabaLep(sz);
        e.targyatFelvesz(t);

    }
}