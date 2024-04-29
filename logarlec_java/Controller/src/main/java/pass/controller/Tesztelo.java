package pass.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Consumer;

public class Tesztelo {

    static File workingDir = new File(System.getProperty("user.dir"));
    static File testDir = new File(workingDir, "controller_tesztek");

    static Map<String, Consumer<String[]>> commandMap = new HashMap<>();
    static {
        commandMap.put("play", Fuggvenyek::Play);
        commandMap.put("save", Fuggvenyek::Save);
        commandMap.put("load", Fuggvenyek::Load);
        commandMap.put("ajtohasznalat", Fuggvenyek::AjtoHasznalat);
        commandMap.put("hasznal", Fuggvenyek::TargyHasznal);
        commandMap.put("targyfelvesz", Fuggvenyek::TargyFelvesz);
        commandMap.put("targyeldob", Fuggvenyek::TargyEldob);
        commandMap.put("random", Fuggvenyek::Random);
        commandMap.put("tick", Fuggvenyek::Tick);
        commandMap.put("infoember", Fuggvenyek::InfoEmber);
        commandMap.put("infoszoba", Fuggvenyek::InfoSzoba);
        commandMap.put("reset", Fuggvenyek::reset);
        commandMap.put("szobafeloszt", Fuggvenyek::SzobaFeloszt);
        commandMap.put("szobaosszevon", Fuggvenyek::SzobaOsszevon);
        commandMap.put("teszt", Tesztelo::getTests);
    }
    public static void getTests(String[] strings) {
        System.out.println("Tesztek:");

        int i = 0;
        for(File file : testDir.listFiles()) {
            System.out.println((++i) + ". " + file.getName());
        }

        int testNum = testDir.listFiles().length + 1;
        System.out.println(testNum + " vissza");

        while(true){
            Scanner scanner = new Scanner(System.in);
            String test = scanner.nextLine();
            if(test.equals(String.valueOf(testNum))) break;
            runTest(testDir.listFiles()[Integer.parseInt(test) - 1].getAbsolutePath());
            Controller.reset();
        }
    }

    public static void runTest(String test){
        File selectedTestDir = new File(test);
        File bemenet = new File(selectedTestDir, "bemenet.txt");
        File elvart = new File(selectedTestDir, "elvart.txt");
        File palya = new File(selectedTestDir, "palya.txt");

        Scanner elvartScanner;
        try {
            elvartScanner = new Scanner(elvart);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        while(elvartScanner.hasNextLine()) {
            sb.append(elvartScanner.nextLine());
            sb.append(System.lineSeparator());
        }

        Scanner bemenetScanner;
        try {
            bemenetScanner = new Scanner(bemenet);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        Controller.Load(palya.getAbsolutePath());

        while(bemenetScanner.hasNextLine()) {
            String line = bemenetScanner.nextLine();
            String[] cmd = line.split(" ");
            Consumer<String[]> command = commandMap.getOrDefault(cmd[0],Tesztelo::rosszBemenet);
            command.accept(cmd);
        }
        System.setOut(old);

        if(!sb.toString().equals(baos.toString())){
            System.out.println("Bukott teszt: " + test);
        } else {
            System.out.println("Teszt sikeres: " + test);
        }

        System.out.println("----------------------\n"+ "Elvart: \n" +
                "----------------------\n");
        System.out.println(sb);
        System.out.println("----------------------\n" + "Kapott: \n" +
                "----------------------\n");
        System.out.println(baos + "----------------------\n");
    }

    public static void rosszBemenet(String[] cmd){
        System.out.println("Hibas bemenet: ");
        for(String s : cmd){
            System.out.print(s + " ");
        }
    }

}
