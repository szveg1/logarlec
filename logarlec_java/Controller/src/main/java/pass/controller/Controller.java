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

        int randInt = random.ints(1, 5, 10).sum();
        int ido = randInt * 10 / hallgatoDB;
        Labirintus.getInstance().setTimeLeft(ido);
        int szobaSzam = randInt * hallgatoDB;
        Szoba kezdoSzoba = new Szoba(hallgatoDB, "kezdoszoba");
        szobaMap.put("kezdoszoba", kezdoSzoba);
        Labirintus.getInstance().addSzoba(kezdoSzoba);

        for(int i = 0; i < szobaSzam; i++){
            int randomFerohely = random.ints(1, 5, 10).sum();
            Szoba szoba = new Szoba(randomFerohely, "szoba" + i);
            Labirintus.getInstance().addSzoba(szoba);
            szobaMap.put("szoba"+ (i + 1), szoba);
        }

        int ajtocnt = 0;
        for(Szoba szoba : Labirintus.getInstance().getSzobak()){
            int hanyszomszed = random.ints(1, 1, szobaSzam).sum();;
            ArrayList<Szoba> lista = new ArrayList<>();
            lista.add(szoba);
            if(szoba.getAjtok().size() >= 0) {
                for (Ajto ajto : szoba.getAjtok()) {
                    lista.add(ajto.getSzomszed(szoba));
                }
            }
            for(int o = 0; o < hanyszomszed; o++){
                int randomszomszedszam = random.ints(1, 0, szobaSzam).sum();
                Szoba randomszomszed = Labirintus.getInstance().getSzobak().get(randomszomszedszam);
                while(lista.contains(randomszomszed)){
                    randomszomszedszam = random.ints(1, 0, szobaSzam).sum();
                    randomszomszed = Labirintus.getInstance().getSzobak().get(randomszomszedszam);
                }
                Ajto ajto = new Ajto(szoba, randomszomszed, "Ajto" + ajtocnt);
                szoba.addAjto(ajto);
                randomszomszed.addAjto(ajto);
                ajtoMap.put("ajto"+ (ajtocnt), ajto);
                ajtocnt++;
            }
        }

        int marSzobabanHallgato = 0;
        while(marSzobabanHallgato <= hallgatoDB){
            Hallgato hallgato = new Hallgato("hallgato" + marSzobabanHallgato);
            hallgato.masikSzobabaLep(kezdoSzoba);
            marSzobabanHallgato++;
            emberMap.put("hallgato" + marSzobabanHallgato, hallgato);
        }

        int randomOktatokDB = random.ints(1, 1, 5).sum();
        int marSzobabanOktato = 0;
        while(marSzobabanOktato <= randomOktatokDB){
            Oktato oktato = new Oktato("oktato" + marSzobabanOktato);
            for(Szoba szoba : Labirintus.getInstance().getSzobak()){
                if(oktato.masikSzobabaLep(szoba)) {
                    marSzobabanOktato++;
                }
            }
            emberMap.put("oktato" + marSzobabanOktato, oktato);
        }

        int randomTakaritoDB = random.ints(1, 1, 3).sum();
        int marSzobabanTakarito = 0;
        while(marSzobabanTakarito <= randomTakaritoDB){
            Takarito takarito = new Takarito("Takarito" + marSzobabanTakarito + 1);
            for(Szoba szoba : Labirintus.getInstance().getSzobak()){
                if(takarito.masikSzobabaLep(szoba)){
                    marSzobabanTakarito++;
                }
            }
            emberMap.put("takarito" + marSzobabanTakarito, takarito);
        }

        // Kezdoszobat azert itt adjuk hozza, hogy ne lehessen benne se oktato se takarito az elejen
        Labirintus.getInstance().addSzoba(kezdoSzoba);
        szobaMap.put("szoba0",kezdoSzoba);

        Logarlec logarlec = new Logarlec("logarlec");

        int randomLogarlechez = random.ints(1,0, szobaSzam).sum();
        Labirintus.getInstance().getSzobak().get(randomLogarlechez).addItem(logarlec);
        int camembertcnt = 0;
        int hamisLogarleccnt = 0;
        int hamisMaskcnt = 0;
        int hamisTVSZcnt = 0;
        int legfrissitocnt = 0;
        int maszkcnt = 0;
        int poharcnt = 0;
        int rongycnt = 0;
        int tranzisztorcnt = 0;
        int tvszcnt = 0;

        for(Szoba szoba : Labirintus.getInstance().getSzobak()) {
            int radomitemnum = random.nextInt(5);
            for (int p = 0; p < radomitemnum; p++) {
                int radomitem = random.nextInt(10);
                switch (radomitem) {
                    case 0:
                        Camembert tmp = new Camembert("Camambert" + camembertcnt);
                        camembertcnt++;
                        szoba.addItem(tmp);
                        break;
                    case 1:
                        HamisLec tmp1 = new HamisLec("HamisLec" + hamisLogarleccnt);
                        hamisLogarleccnt++;
                        szoba.addItem(tmp1);
                        break;
                    case 2:
                        HamisMaszk tmp2 = new HamisMaszk("HamisMaszk" + hamisMaskcnt);
                        hamisMaskcnt++;
                        szoba.addItem(tmp2);
                        break;
                    case 3:
                        HamisTVSZ tmp3 = new HamisTVSZ("HamisTVSZ" + hamisTVSZcnt);
                        hamisTVSZcnt++;
                        szoba.addItem(tmp3);
                        break;
                    case 4:
                        Legfrissito tmp4 = new Legfrissito("Legfrissito" + legfrissitocnt);
                        legfrissitocnt++;
                        szoba.addItem(tmp4);
                        break;
                    case 5:
                        //a védidő mennyi?
                        Maszk tmp5 = new Maszk(30, "Maszk" + maszkcnt);
                        maszkcnt++;
                        szoba.addItem(tmp5);
                        break;
                    case 6:
                        Pohar tmp6 = new Pohar("Pohar" + poharcnt);
                        poharcnt++;
                        szoba.addItem(tmp6);
                        break;
                    case 7:
                        Rongy tmp7 = new Rongy("Rongy" + rongycnt);
                        rongycnt++;
                        szoba.addItem(tmp7);
                        break;
                    case 8:
                        Tranzisztor tmp8 = new Tranzisztor("Tranzisztor" + tranzisztorcnt);
                        tranzisztorcnt++;
                        szoba.addItem(tmp8);
                        break;
                    case 9:
                        TVSZ tmp9 = new TVSZ("TVSZ" + tvszcnt);
                        tvszcnt++;
                        szoba.addItem(tmp9);
                        break;
                }
            }
        }
        betoltesEredmenyKiir(null);
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
            if(line.startsWith("szoba")) {
                szoba = szobaFeldolgoz(line, szobaSzomszedMap);
            }
            if(line.startsWith("emberek")){
                emberekFeldolgoz(line, szoba, szobaEmberMap);
            }
            if(line.startsWith("targyak")){
                targyakFeldolgoz(line, szobaTargyMap, szoba);
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

        betoltesEredmenyKiir(file);
    }

    private static void targyakFeldolgoz(String line, HashMap<Szoba, ArrayList<Targy>> szobaTargyMap, Szoba szoba) {
        szobaTargyMap.put(szoba, new ArrayList<>());
        String[] parts = line.split(":");
        String[] targyak = parts[1].split(",");

        for(String targy : targyak){
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
            targyMap.put(targy + targyMap.size(), t);
            szobaTargyMap.get(szoba).add(t);
            szoba.addItem(t);
        }
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
            sb.append(System.lineSeparator()).append(System.lineSeparator());
        }

        System.out.print(sb);
        ajtoMap.keySet().forEach(key -> System.out.println(key));
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

