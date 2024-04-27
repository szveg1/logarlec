package pass.controller;

import pass.model.item.*;
import pass.model.human.*;
import pass.model.labyrinth.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class Controller {
    private static HashMap<String,Ember> emberMap = new HashMap<>();
    private static HashMap<String,Szoba> szobaMap = new HashMap<>();
    private static HashMap<String,Ajto> ajtoMap = new HashMap<>();
    private static HashMap<String,Targy> targyMap = new HashMap<>();

    public static void Play(int db) {
        Labirintus.getInstance().setTimeLeft(20);
        for (int i = 0; i < db; i++) {
            Hallgato h = new Hallgato("hallgato" + i+1);
            Szoba sz = szobaMap.get("szoba1");
            h.masikSzobabaLep(sz);
        }
    }

    public static void Save(String file) {
        try {
            for(Szoba s : szobaMap.values()){
                List<String> emberek = new ArrayList<>();
                List<String> targyak = new ArrayList<>();
                for(Ember e : s.getEmberek()){
                    emberMap.keySet().forEach(key -> {
                        if(emberMap.get(key).equals(e)){
                            emberek.add(key);
                        }
                    });
                }
                for(Targy t : s.getItems()){
                    targyMap.keySet().forEach(key -> {
                        if(targyMap.get(key).equals(t)){
                            while(Character.isDigit(key.charAt(key.length() - 1))){
                                key = key.substring(0, key.length() - 1);
                            }
                            targyak.add(key);
                        }
                    });
                }
                s.writeToFileFirstLine(file, emberek, targyak);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void Load(String file) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        ArrayList<String> lines = new ArrayList<>();
        while(scanner.hasNext()){
            lines.add(scanner.nextLine());
        }

        HashMap<String,String[]> szobaSzomszedMap = new HashMap<>();
        HashMap<Szoba, ArrayList<Ember>> szobaEmberMap = new HashMap<>();
        HashMap<Szoba, ArrayList<Targy>> szobaTargyMap = new HashMap<>();

        Szoba szoba = null;
        for(String line : lines){
            if(line.startsWith("szoba")){
                String[] parts = line.split(":");
                String nev = parts[0];
                String[] adatok = parts[1].split(",");
                String[] szomszedok = adatok[0].split(" ");
                String allapotok = adatok[1];
                int ferohely = Integer.parseInt(adatok[2]);

                szoba = new Szoba(nev, ferohely, allapotok);

                szobaMap.put(nev, szoba);
                szobaSzomszedMap.put(nev, szomszedok);
            }
            if(line.startsWith("emberek")){
                szobaEmberMap.put(szoba, new ArrayList<>());
                String[] parts = line.split(":");

                if (parts.length > 1) {
                    String[] emberek = parts[1].split(",");
                    System.out.println(emberek.length);
                    for(String ember : emberek){
                        Ember e = ember.equals("oktato") ? new Oktato(ember + emberMap.size()) : new Takarito(ember + emberMap.size());
                        emberMap.put(ember + emberMap.size(), e);
                        e.masikSzobabaLep(szoba);
                        szobaEmberMap.get(szoba).add(e);
                    }
                }
            }
            if(line.startsWith("targyak")){
                szobaTargyMap.put(szoba, new ArrayList<>());
                String[] parts = line.split(":");
                String[] targyak = parts[1].split(",");

                for(String targy : targyak){
                    Targy t = switch(targy){
                        case "camembert" -> new Camembert(targy + targyMap.size());
                        case "hamislec" -> new HamisLec(targy + targyMap.size());
                        case "hamismaszk" -> new HamisMaszk(targy + targyMap.size());
                        case "hamistvsz" -> new HamisTVSZ(targy + targyMap.size());
                        case "legfrissito" -> new Legfrissito(targy + targyMap.size());
                        case "logarlec" -> new Logarlec(targy + targyMap.size());
                        case "maszk" -> new Maszk(3,targy + targyMap.size());
                        case "pohar" -> new Pohar(targy + targyMap.size());
                        case "rongy" -> new Rongy(targy + targyMap.size());
                        case "tranzisztor" -> new Tranzisztor(targy + targyMap.size());
                        case "tvsz" -> new TVSZ(targy + targyMap.size());
                        default -> null;
                    };
                    targyMap.put(targy + targyMap.size(), t);
                    szobaTargyMap.get(szoba).add(t);
                    szoba.addItem(t);
                }
            }
        }

        for(String s : szobaSzomszedMap.keySet()){
            String[] szomszedok = szobaSzomszedMap.get(s);
            for(String sz : szomszedok){
                Ajto a = new Ajto(szobaMap.get(s), szobaMap.get(sz), "a");
                ajtoMap.put("ajto" + s.charAt(s.length() - 1) + sz.charAt(sz.length() - 1), a);
                szobaMap.get(s).addAjto(a);
            }
        }
        
        Labirintus labirintus = Labirintus.getInstance();
        for(Szoba sz : szobaMap.values()){
            labirintus.addSzoba(sz);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("a ").append(file).append(" jatek betoltve.").append(System.lineSeparator());
        sb.append("szobak:").append(System.lineSeparator());
        for(Szoba sz : labirintus.getSzobak()){
            sb.append("\t").append(sz.getNev()).append(System.lineSeparator());
            sb.append("\t").append("szomszedok:");
            for(Ajto a : sz.getAjtok()){
                sb.append(a.getSzomszed(sz).getNev()).append(",");
            }
            sb.append(System.lineSeparator());

            sb.append("\t").append("targyak:");
            for(Targy t : sz.getItems()){
                targyMap.keySet().forEach(key -> {
                    if(targyMap.get(key).equals(t)) {
                        sb.append(key);
                    }
                });
                sb.append(",");
            }
            sb.append(System.lineSeparator());

            sb.append("\t").append("emberek:");
            sb.append("(").append(sz.getEmberek().size()).append(")");
            for(Ember e : sz.getEmberek()){
                emberMap.keySet().forEach(key -> {
                    if(emberMap.get(key).equals(e)) {
                        sb.append(key);
                    }
                });
                sb.append(",");
            }

            sb.append(System.lineSeparator());
            sb.append("\t").append("allapot:");
            sb.append(sz.ragacsosE() ?  "ragacsos," : "tiszta,");
            sb.append(sz.mergezoE() ? "mergezo," : "");
            sb.append(sz.atkozottE() ? "atkozott," : "");
            sb.append(System.lineSeparator());
        }

        System.out.print(sb);

        for(Ember e : emberMap.values()){
            System.out.println(e);
        }
    }
//
//    public static void Load2(String file) {
//        emberMap = new HashMap<>();
//        szobaMap = new HashMap<>();
//        ajtoMap = new HashMap<>();
//        targyMap = new HashMap<>();
//
//        Szoba aktSzoba = null;
//
//        try {
//            Scanner scanner = new Scanner(new File(file));
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine().trim();
//
//                if (line.isEmpty()) {
//                    aktSzoba = null;
//                    continue;
//                }
//
//                if (aktSzoba == null) {
//                    aktSzoba = inicializalSzoba(line);
//                    szobaMap.put(aktSzoba.getNev(), aktSzoba);
//                } else {
//                    if (line.startsWith("Oktatók:") || line.startsWith("Tárgyak:")) {
//                        feldolgozEmbereket(line, aktSzoba);
//                        feldolgozTargyakat(line, aktSzoba);
//                    }
//                }
//            }
//
//            scanner.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//
//        for (Szoba szoba : szobaMap.values()) {
//            System.out.println("Betöltött szoba: " + szoba);
//        }
//    }
//
//    private static Szoba inicializalSzoba(String sor) {
//        String[] parts = sor.split(": ");
//        String szobaNeve = parts[0];
//        String[] adatok = parts[1].split(", ");
//
//        String[] szomszedok = adatok[0].split(" ");
//        String allapotok = adatok[1];
//        int feroHely = Integer.parseInt(adatok[2]);
//
//        Szoba szoba = new Szoba(szobaNeve, szomszedok, allapotok, feroHely);
//        szobaMap.put(szobaNeve, szoba);
//        return szoba;
//    }
//
//    private static void feldolgozEmbereket(String sor, Szoba szoba) {
//        String[] parts = sor.split(": ");
//        String[] emberek = parts[1].split(" ");
//        for (String ember : emberek) {
//            szoba.Add(emberMap.get(ember));
//        }
//    }
//
//    private static void feldolgozTargyakat(String sor, Szoba szoba) {
//        String[] parts = sor.split(": ");
//        String[] targyak = parts[1].split(" ");
//        for (String targy : targyak) {
//            szoba.Add(targyMap.get(targy));
//        }
//    }

    public static void AjtoHasznalat(Ajto a, Ember e) {
        a.hasznal(e);
    }

    public static void TargyFelvesz(Targy t, Ember e) {
        e.targyatFelvesz(t);
    }

    public static void Hasznal(Targy t, Ember e) {
        e.targyatHasznal(t);
    }

    public static void TargyEldob(Targy t, Ember e) {
        e.targyatEldob(t);
    }

    //public void InfoEmber(Ember e) {}

    public static void Random() {}

    public static void Tick() {
        Labirintus.getInstance().tick();
        Leptetes();
    }

    public static void Leptetes() {
        for (Szoba sz : Labirintus.getInstance().getSzobak()) {
            List<Ajto> ajtok = sz.getAjtok();
            Collections.shuffle(ajtok);
            for (Ember e : sz.getEmberek()) {
                e.controllerLeptet(ajtok.getFirst());
            }
        }
    }

    public static Ajto getAjto(String s){
        return ajtoMap.get(s);
    }

    public static Ember getEmber(String s){
        return emberMap.get(s);
    }

    public static Szoba getSzoba(String s) {
        return szobaMap.get(s);
    }

    public static Targy getTargy(String s) {
        return targyMap.get(s);
    }
}

