package pass.controller;

import pass.model.item.*;
import pass.model.human.*;
import pass.model.labyrinth.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Controller {
    private static final HashMap<String,Ember> emberMap = new HashMap<>();
    private static final HashMap<String,Szoba> szobaMap = new HashMap<>();
    private static final HashMap<String,Ajto> ajtoMap = new HashMap<>();
    private static final HashMap<String,Targy> targyMap = new HashMap<>();

    private static boolean deterministic = false;

    public static void Play(int hallgatoDB) {
        Random random = deterministic ? new Random(0) : new Random();
        Labirintus labirintus = Labirintus.getInstance();

        int randInt = random.ints(1, 5, 10).sum();
        labirintus.setTimeLeft(randInt * 10 / hallgatoDB);

        Szoba kezdoSzoba = new Szoba(hallgatoDB, "kezdoszoba");
        szobaMap.put("kezdoszoba", kezdoSzoba);
        labirintus.addSzoba(kezdoSzoba);

        int szobaSzam = randInt * hallgatoDB;
        for(int i = 0; i < szobaSzam; i++){
            int randomFerohely = random.ints(1, 5, 10).sum();
            Szoba szoba = new Szoba(randomFerohely, "szoba" + i);
            labirintus.addSzoba(szoba);
            szobaMap.put("szoba"+ (i + 1), szoba);
        }

        int ajtocnt = 0;
        for(Szoba szoba : labirintus.getSzobak()){
            if(labirintus.getSzobak().size() < labirintus.getSzobak().indexOf(szoba) + 1){
                Ajto ajto = new Ajto(szoba, labirintus.getSzobak().get(labirintus.getSzobak().indexOf(szoba) + 1), "Ajto" + ajtocnt);
                szoba.addAjto(ajto);
                labirintus.getSzobak().get(labirintus.getSzobak().indexOf(szoba) + 1).addAjto(ajto);
                ajtoMap.put("ajto"+ (ajtocnt), ajto);
                ajtocnt++;
            }
        }

        for(Szoba szoba : labirintus.getSzobak()){
            int hanyszomszed = random.ints(1, 1, szobaSzam).sum();;
            ArrayList<Szoba> lista = new ArrayList<>();
            lista.add(szoba);
            if(szoba.getAjtok().size() > 0) {
                for (Ajto ajto : szoba.getAjtok()) {
                    lista.add(ajto.getSzomszed(szoba));
                }
            }
            for(int o = 0; o < hanyszomszed - szoba.getAjtok().size(); o++){
                int randomszomszedszam = random.ints(1, 0, szobaSzam).sum();
                Szoba randomszomszed = labirintus.getSzobak().get(randomszomszedszam);
                while(lista.contains(randomszomszed)){
                    randomszomszedszam = random.ints(1, 0, szobaSzam).sum();
                    randomszomszed = labirintus.getSzobak().get(randomszomszedszam);
                }
                lista.add(randomszomszed);
                Ajto ajto = new Ajto(szoba, randomszomszed, "Ajto" + ajtocnt);
                szoba.addAjto(ajto);
                randomszomszed.addAjto(ajto);
                ajtoMap.put("ajto"+ (ajtocnt), ajto);
                ajtocnt++;
            }
        }



        int marSzobabanHallgato = 0;
        while(marSzobabanHallgato < hallgatoDB){
            Hallgato hallgato = new Hallgato("hallgato" + (marSzobabanHallgato + 1));
            hallgato.masikSzobabaLep(kezdoSzoba);
            marSzobabanHallgato++;
            emberMap.put("hallgato" + marSzobabanHallgato, hallgato);
        }

        int randomOktatokDB = random.ints(1, 1, 5).sum();
        int marSzobabanOktato = 0;
        while(marSzobabanOktato < randomOktatokDB){
            Oktato oktato = new Oktato("oktato" + (marSzobabanOktato + 1));
            for(Szoba szoba : labirintus.getSzobak()){
                if(oktato.masikSzobabaLep(szoba)) {
                    marSzobabanOktato++;
                    break;
                }
            }
            emberMap.put("oktato" + marSzobabanOktato, oktato);
        }

        int randomTakaritoDB = random.ints(1, 1, 3).sum();
        int marSzobabanTakarito = 0;
        while(marSzobabanTakarito < randomTakaritoDB){
            Takarito takarito = new Takarito("Takarito" + (marSzobabanTakarito + 1));
            for(Szoba szoba : labirintus.getSzobak()){
                if(takarito.masikSzobabaLep(szoba)){
                    marSzobabanTakarito++;
                    break;
                }
            }
            emberMap.put("takarito" + marSzobabanTakarito, takarito);
        }


        Logarlec logarlec = new Logarlec("logarlec");

        int logarlecSzobaszam = random.ints(1,0, szobaSzam).sum();
        labirintus.getSzobak().get(logarlecSzobaszam).addItem(logarlec);
        targyMap.put("logarlec", logarlec);

        HashMap<String, Integer> targyCount = targyCountMapKeszit();

        for(Szoba szoba : labirintus.getSzobak()) {
            int randomItemNum = random.nextInt(5);
            for (int p = 0; p < randomItemNum; p++) {
                String randomTargyTipus = (String)targyCount.keySet().toArray()[random.nextInt(10)];
                Targy t = ujTargyGyart(randomTargyTipus);
                szoba.addItem(t);
                targyMap.put(randomTargyTipus + (p+1), t);
            }
        }
        betoltesEredmenyKiir(null);
    }

    private static HashMap<String, Integer> targyCountMapKeszit() {
        HashMap<String, Integer> targyCount = new HashMap<>();
        targyCount.put("camembert",0);
        targyCount.put("hamislec",0);
        targyCount.put("hamismaszk",0);
        targyCount.put("hamistvsz",0);
        targyCount.put("legfrissito",0);
        targyCount.put("maszk",0);
        targyCount.put("pohar",0);
        targyCount.put("rongy",0);
        targyCount.put("tranzisztor",0);
        targyCount.put("tvsz",0);
        return targyCount;
    }

    public static void Save(String file) {
        try (FileWriter fw = new FileWriter(file)) {
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
                s.writeToFileFirstLine(fw, emberek, targyak); // Átadás megnyitott FileWriter objektummal
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
            if(line.contains("szoba")) {
                szoba = szobaFeldolgoz(line, szobaSzomszedMap);
            }
            if(line.startsWith("emberek")){
                emberekFeldolgoz(line, szoba, szobaEmberMap);
            }
            if(line.startsWith("targyak")){
                targyakFeldolgoz(line, szobaTargyMap, szoba);
            }
        }

        HashMap<String, String> szobaNevParok = new HashMap<>();
        int i = 0;
        for(String egyikSzobaNev : szobaSzomszedMap.keySet()) {
            String[] szomszedok = szobaSzomszedMap.get(egyikSzobaNev);
            for(String masikSzobaNev : szomszedok){
                if(!szobaNevParok.containsKey(masikSzobaNev)) {
                    szobaNevParok.put(egyikSzobaNev,masikSzobaNev);
                    Ajto a = new Ajto(szobaMap.get(egyikSzobaNev),szobaMap.get(masikSzobaNev), "ajto" + (++i));
                    a.setMerreNyilik(true, false);
                    szobaMap.get(egyikSzobaNev).addAjto(a);
                    szobaMap.get(masikSzobaNev).addAjto(a);
                }
                else {
                    List<Ajto> masikSzobaAjtok = szobaMap.get(masikSzobaNev).getAjtok();
                    Ajto keresettAjto = null;
                    for(Ajto a : masikSzobaAjtok){
                        if(true){
                            keresettAjto = a;
                        }
                    }
                    keresettAjto.setMerreNyilik(true, true);
                }
            }
        }

        Labirintus labirintus = Labirintus.getInstance();
        for(Szoba sz : szobaMap.values()){
            labirintus.addSzoba(sz);
        }

        betoltesEredmenyKiir(file);
    }

    private static void targyakFeldolgoz(String line, HashMap<Szoba, ArrayList<Targy>> szobaTargyMap, Szoba szoba) {
        szobaTargyMap.put(szoba, new ArrayList<>());
        String[] parts = line.split(":");
        if(parts.length <= 1) return;
        String[] targyak = parts[1].split(",");

        for(String targyTipus : targyak){
            Targy t;
            t = ujTargyGyart(targyTipus);
            targyMap.put(targyTipus + targyMap.size(), t);
            szobaTargyMap.get(szoba).add(t);
            szoba.addItem(t);
        }
    }

    private static Targy ujTargyGyart(String targy) {
        Targy t;
        switch (targy) {
            case "camembert":
                t = new Camembert(targy + targyMap.size());
                break;
            case "hamislec":
                t = new HamisLec(targy + targyMap.size());
                break;
            case "hamismaszk":
                t = new HamisMaszk(targy + targyMap.size());
                break;
            case "hamistvsz":
                t = new HamisTVSZ(targy + targyMap.size());
                break;
            case "legfrissito":
                t = new Legfrissito(targy + targyMap.size());
                break;
            case "logarlec":
                t = new Logarlec(targy + targyMap.size());
                break;
            case "maszk":
                t = new Maszk(3, targy + targyMap.size());
                break;
            case "pohar":
                t = new Pohar(targy + targyMap.size());
                break;
            case "rongy":
                t = new Rongy(targy + targyMap.size());
                break;
            case "tranzisztor":
                t = new Tranzisztor(targy + targyMap.size());
                break;
            case "tvsz":
                t = new TVSZ(targy + targyMap.size());
                break;
            default:
                t = null;
                break;
        }
        return t;
    }

    private static Szoba szobaFeldolgoz(String line, HashMap<String,String[]> szobaSzomszedMap) {
        String[] parts = line.split(":");
        String nev = parts[0];
        String[] adatok = parts[1].split(",");
        String[] szomszedok = adatok[0].split(" ");
        String allapotok = adatok[1];
        int ferohely = Integer.parseInt(adatok[2]);

        Szoba szoba = new Szoba(nev, ferohely, allapotok);

        szobaMap.put(nev, szoba);
        szobaSzomszedMap.put(nev, szomszedok);
        return szoba;
    }

    private static void emberekFeldolgoz(String line, Szoba szoba, HashMap<Szoba, ArrayList<Ember>> szobaEmberMap) {
        szobaEmberMap.put(szoba, new ArrayList<>());
        String[] parts = line.split(":");

        if (parts.length > 1) {
            String[] emberek = parts[1].split(",");
            for(String ember : emberek){
                Ember e = ember.equals("oktato") ? new Oktato(ember + emberMap.size()) : new Takarito(ember + emberMap.size());
                emberMap.put(ember + emberMap.size(), e);
                e.masikSzobabaLep(szoba);
                szobaEmberMap.get(szoba).add(e);
            }
        }
    }

    private static void betoltesEredmenyKiir(String file){
        StringBuilder sb = new StringBuilder();
        if(file != null)
            sb.append("a ").append(file).append(" jatek betoltve.").append(System.lineSeparator());

        sb.append("szobak:").append(System.lineSeparator());
        for(Szoba sz : Labirintus.getInstance().getSzobak()){
            sb.append("\t").append(sz.getNev()).append(System.lineSeparator());

            sb.append("\t").append("szomszedok:");
            for(Ajto a : sz.getAjtok()){
                sb.append(a.getSzomszed(sz).getNev()).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
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
            if(!sz.getItems().isEmpty()) sb.deleteCharAt(sb.length() - 1);
            sb.append(System.lineSeparator());

            sb.append("\t").append("emberek:");
            for(Ember e : sz.getEmberek()){
                emberMap.keySet().forEach(key -> {
                    if(emberMap.get(key).equals(e)) {
                        sb.append(key);
                    }
                });
                sb.append(",");
            }
            if (!sz.getEmberek().isEmpty()) sb.deleteCharAt(sb.length() - 1);
            sb.append(System.lineSeparator());

            sb.append("\t").append("allapot:");
            sb.append(sz.ragacsosE() ?  "ragacsos," : "tiszta,");
            sb.append(sz.mergezoE() ? "mergezo," : "");
            sb.append(sz.atkozottE() ? "atkozott," : "");
            sb.deleteCharAt(sb.length() - 1);
            sb.append(System.lineSeparator()).append(System.lineSeparator());
        }

        System.out.print(sb);
        for (String key : ajtoMap.keySet()) {
            System.out.println(key);
            Ajto a = getAjto(key);
            System.out.println(a.getOldalak());
        }
    }

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

    public static void InfoEmber(Ember e) {

        for(int i = 0; i < e.getItems().size(); i++){
            Targy t = e.getItems().get(i);
            System.out.println(t.toString());
            if(i != e.getItems().size() - 1)
                System.out.println(", ");
        }
        System.out.println(e.getJelenlegiSzoba().getNev());

        String s = e.getAjult() ? "ajult" : "eber";

        System.out.println(s);
    }

    public static void Random(boolean b) {
        deterministic = !b;
    }

    public static void Tick() {
        Labirintus.getInstance().tick();
        Leptetes();
    }

    public static void Leptetes() {
        for (Szoba sz : Labirintus.getInstance().getSzobak()) {
            List<Ajto> ajtok = sz.getAjtok();
            Collections.shuffle(ajtok);
            for (Ember e : sz.getEmberek()) {
                e.controllerLeptet(ajtok.get(0));
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

