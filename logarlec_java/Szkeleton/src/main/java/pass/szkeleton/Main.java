package pass.szkeleton;


import pass.model.CustomRecordFormatter;
import pass.model.human.*;
import pass.model.item.*;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Logger logger = Logger.getGlobal();
    static {
        logger.setUseParentHandlers(false);
        CustomRecordFormatter formatter = new CustomRecordFormatter();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(formatter);
        logger.addHandler(handler);
    }
    public static void main(String[] args) {
        TargyFelveszTest test = new TargyFelveszTest();
        test.setUp();
        test.test();
    }
}