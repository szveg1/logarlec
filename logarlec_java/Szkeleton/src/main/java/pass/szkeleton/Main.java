package pass.szkeleton;


import pass.model.CustomRecordFormatter;
import pass.model.human.*;
import pass.model.item.*;
import pass.model.labyrinth.Szoba;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static Logger logger = Logger.getGlobal();

    public static void main(String[] args) {
        SzobaFelosztTest t = new SzobaFelosztTest();
        t.test();

    }
}