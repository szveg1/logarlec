package pass.controller;

import pass.model.CustomLogger;

import java.util.Scanner;
import java.util.*;
import java.util.function.Consumer;

public class Main extends Fuggvenyek {
    public static void main(String[] args) {
        CustomLogger.suppress();
        Scanner scanner = new Scanner(System.in);
        Map<String, Consumer<String[]>> commandMap = new HashMap<>();
        fillMap(commandMap);
        while (true) {
            String line = scanner.nextLine();
            if (line == null) break;
            String[] cmd = line.split(" ");

            Consumer<String[]> command = commandMap.getOrDefault(cmd[0], Main::handleUnknownCommand);
            command.accept(cmd);
        }
        scanner.close();
    }

    private static void fillMap(Map<String, Consumer<String[]>> commandMap) {
        commandMap.put("play", Fuggvenyek::Play);
        commandMap.put("save", Fuggvenyek::Save);
        commandMap.put("load", Fuggvenyek::Load);
        commandMap.put("ajtohasznalat", Fuggvenyek::AjtoHasznalat);
        commandMap.put("hasznal", Fuggvenyek::TargyHasznal);
        commandMap.put("targyfelvesz", Fuggvenyek::TargyFelvesz);
        commandMap.put("targyeldob", Fuggvenyek::TargyEldob);
        commandMap.put("random", Fuggvenyek::Random);
        commandMap.put("tick", Fuggvenyek::Tick);
        commandMap.put("InfoEmber", Fuggvenyek::InfoEmber);
    }
    private static void handleUnknownCommand(String[] cmd) {
        System.out.println("Unknown command: " + cmd[0]);
    }

}
