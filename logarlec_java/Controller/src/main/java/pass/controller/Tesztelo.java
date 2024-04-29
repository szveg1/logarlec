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

        if (!testDir.exists() || !testDir.canRead() || !testDir.isDirectory()) {
            System.out.println("A tesztkönyvtár nem létezik vagy nem olvasható: " + testDir.getAbsolutePath());
            return;
        }

        File[] files = testDir.listFiles();
        if (files == null) {
            System.out.println("Nem sikerült beolvasni a tesztkönyvtár tartalmát: " + testDir.getAbsolutePath());
            return;
        }
        int i = 0;

        for(File file : testDir.listFiles()) {
            System.out.println((++i) + ". " + file.getName());
        }

        int testNum = files.length + 1;
        System.out.println(testNum + " vissza");

        while(true){
            Scanner scanner = new Scanner(System.in);
            String test = scanner.nextLine();
            try {
                int testIndex = Integer.parseInt(test);
                if(test.equals(String.valueOf(testNum))) break;
                if(testIndex < 1 || testIndex > files.length) {
                    System.out.println("Nem érvényes szám");
                    continue;
                }
                runTest(files[testIndex - 1].getAbsolutePath());
                Controller.reset();
            } catch (NumberFormatException e) {
                System.out.println("Kérlek, számot adj meg!");
            }
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
        elvartScanner.close();

        Scanner bemenetScanner;
        try {
            bemenetScanner = new Scanner(bemenet);
        } catch(FileNotFoundException e) {
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
        bemenetScanner.close();
        System.setOut(old);

        String testShortName = test.substring(test.lastIndexOf(File.separator) + 1);

        if(!sb.toString().equals(baos.toString())){
            System.out.println("Bukott teszt: " + testShortName);
        } else {
            System.out.println("Teszt sikeres: " + testShortName);
        }

        String expected = sb.toString();
        String actual = baos.toString();

        String[] expectedLines = expected.split(System.lineSeparator());
        String[] actualLines = actual.split(System.lineSeparator());

        int maxLength = Math.max(expectedLines.length, actualLines.length);

        System.out.printf("%-5s %-44s | %-44s%n", "", "Elvart:", "Kapott:");
        System.out.printf("%-5s %-44s | %-44s%n", "", new String(new char[44]).replace("\0", "-"), new String(new char[44]).replace("\0", "-"));

        for (int i = 0; i < maxLength; i++) {
            String expectedLine = i < expectedLines.length ? expectedLines[i] : "";
            String actualLine = i < actualLines.length ? actualLines[i] : "";
            if (!expectedLine.equals(actualLine)) {
                System.out.printf("%-5d *%-43s | *%-43s%n", i+1, expectedLine, actualLine); // Highlight different lines with *
            } else {
                System.out.printf("%-5d  %-44s | %-44s%n", i+1, expectedLine, actualLine);
            }
        }
    }

    public static void rosszBemenet(String[] cmd){
        System.out.println("Hibas bemenet: ");
        for(String s : cmd){
            System.out.print(s + " ");
        }
    }

}
