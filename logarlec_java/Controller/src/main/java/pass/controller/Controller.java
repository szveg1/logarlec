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
    /**
     *  A függvény létrhozza a játékot
     *  a szobákat, ajtókat, tárgyakat és az embereket is létrehozza és elhelyezi a pályán
     * @param hallgatoDB - hány játékos(hallgató) lesz a játékban
     */
    public static void Play(int hallgatoDB) {
        Random random = deterministic ? new Random(0) : new Random();
        Labirintus labirintus = Labirintus.getInstance();

        int randInt = random.ints(1, 5, 10).sum();
        labirintus.setTimeLeft(/*randInt * 10*/ /*100 / hallgatoDB*/ 20);

        Szoba kezdoSzoba = new Szoba(hallgatoDB, "kezdoszoba");
        szobaMap.put("kezdoszoba", kezdoSzoba);
        labirintus.addSzoba(kezdoSzoba);

        int szobaSzam = randInt * hallgatoDB;
        for(int i = 0; i < szobaSzam; i++){
            int randomFerohely = random.ints(1, 5, 10).sum();
            Szoba szoba = new Szoba(randomFerohely, "szoba" + (i + 1));
            if(i % 4 == 0){
                szoba.megAtkoz();
            }
            else if(i % 5 == 0){
                szoba.setMeregIdo(10);
            }
            else{
                szoba.setTiszta(true);
            }
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
            int hanyszomszed = random.ints(1, 1, szobaSzam).sum();
            ArrayList<Szoba> lista = new ArrayList<>();
            lista.add(szoba);
            if(!szoba.getAjtok().isEmpty()) {
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

        int kellEgyCounter = 0;
        boolean egyikvege = true;
        boolean masikvege = true;
        for(Szoba szoba : labirintus.getSzobak()){
            for(Ajto ajto : szoba.getAjtok()){
                if(kellEgyCounter % 7 == 0){
                    if(kellEgyCounter % 2 == 0){
                        egyikvege = false;
                    }
                    else if(kellEgyCounter % 2 == 1){
                        masikvege = false;
                    }
                    ajto.setMerreNyilik(egyikvege, masikvege);
                }
                else{
                    ajto.setMerreNyilik(egyikvege, masikvege);
                }
                egyikvege = true;
                masikvege = true;
                kellEgyCounter++;
            }
        }

        for(Szoba szoba : labirintus.getSzobak()){
            if(!szoba.vaneKijarat(szoba)){
                szoba.getAjtok().get(0).setMerreNyilik(true, true);
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
        betoltesEredmenyKiir();
        Game.startGame();
    }

    /**
     * A megadott fájlba menti a játékállapotot.
     * @param file A fájl elérési útja, ahova a játékállapotot menteni kívánjuk.
     * @throws RuntimeException Ha bármilyen hiba lép fel a fájl írása során.
     */
    public static void Save(String file) {
        try (FileWriter fw = new FileWriter(file)) {
            for(Szoba s : szobaMap.values()){
                List<String> emberek = new ArrayList<>();
                List<String> targyak = new ArrayList<>();
                for(Ember e : s.getEmberek()){
                    emberMap.keySet().forEach(key -> {
                        if(emberMap.get(key).equals(e)){
                            while(Character.isDigit(key.charAt(key.length() - 1))){
                                key = key.substring(0, key.length() - 1);
                            }
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
    /**
     * Betölti a megadott fájlból a játékállapotot.
     * @param file A fájl elérési útja, ahonnan a játékállapotot betöltjük.
     * @throws RuntimeException Ha a fájl nem található, vagy bármilyen hiba lép fel a betöltés során.
     */
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

        HashMap<String,ArrayList<String>> szobaSzomszedMap = new HashMap<>();
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

        ajtokLegyart(szobaSzomszedMap);

        Labirintus labirintus = Labirintus.getInstance();
        for(Szoba sz : szobaMap.values()){
            labirintus.addSzoba(sz);
        }

        betoltesEredmenyKiir();
    }

    /**
     * Az ajtó használatát végzi, azaz az embert az ajtón keresztül átvezeti a másik szobába.
     * @param a Az ajtó, amelyen keresztül az ember átlép.
     * @param e Az ember, aki átlép az ajtón.
     */
    public static void AjtoHasznalat(Ajto a, Ember e) {
        Szoba ujSzoba = a.getSzomszed(e.getJelenlegiSzoba());
        a.hasznal(e);
        if(ujSzoba != null)
            System.out.println(getEmberNevFromMap(e) + ": atlep az " + getAjtoNevFromMap(a) + " ajton, a " + getSzobaNevFromMap(ujSzoba) + " szobaba kerul.");
    }

    /**
     * Egy tárgy felvételét végzi az adott személy által.
     * @param t A felvenni kívánt tárgy.
     * @param e Az az ember, aki fel akarja venni a tárgyat.
     */
    public static void TargyFelvesz(Targy t, Ember e) {
        int prevItemNum = e.getItems().size();
        if(e.inventoryTeleE()){
            System.out.println(getEmberNevFromMap(e) + ": az inventoryd teli van, nem fer bele a " + getTargyNevFromMap(t) + " targy.");
        }
        e.targyatFelvesz(t);
        int currItemNum = e.getItems().size();
        if(currItemNum == prevItemNum && !e.inventoryTeleE()){
            System.out.println(getEmberNevFromMap(e) + ": a szoba ragacsos, a " + getTargyNevFromMap(t) + " targy nem veheto fel.");
        }
        else if(currItemNum > prevItemNum){
            System.out.println(getEmberNevFromMap(e) + ": az inventorydba tetted a " + getTargyNevFromMap(t) + " targyat.");
        }
    }

    /**
     * Egy tárgy használatát végzi az adott személy által.
     * @param t A használni kívánt tárgy.
     * @param e Az az ember, aki használni akarja a tárgyat.
     */
    public static void Hasznal(Targy t, Ember e) {
        e.targyatHasznal(t);
        System.out.println(getEmberNevFromMap(e) + ": hasznaltad a " + getTargyNevFromMap(t) + " targyat.");
    }

    /**
     * Az adott személy eldob egy tárgyat az inventoryjából.
     * @param t A eldobni kívánt tárgy.
     * @param e Az az ember, aki el akarja dobni a tárgyat.
     */
    public static void TargyEldob(Targy t, Ember e) {
        if(e.getItems().isEmpty()){
            System.out.println(getEmberNevFromMap(e) + ": az inventoryd ures nem dobhatsz el targyat.");
        } else if (!e.getItems().contains(t)) {
            System.out.println(getEmberNevFromMap(e) + ": nincs ilyen targy az inventorydban.");
        } else {
            System.out.println(getEmberNevFromMap(e) + ": eldobtad a " + getTargyNevFromMap(t) + " targyat.");
        }
        e.targyatEldob(t);
    }
    /**
     * Kiírja az adott ember tárgyait és jelenlegi tartózkodási helyét, valamint állapotát.
     * @param e Az az ember, akiről információt szeretnénk kapni.
     */
    public static void InfoEmber(Ember e) {
        String targyak = getEmberNevFromMap(e) + " targyai:";
        for(int i = 0; i < e.getItems().size(); i++){
            Targy t = e.getItems().get(i);
            targyak += getTargyNevFromMap(t);
            if(i != e.getItems().size() - 1)
                targyak+=",";
        }
        System.out.println(targyak);
        System.out.println("itt van: " + e.getJelenlegiSzoba().getNev());

        System.out.println(e.getAjult() ? "ajult" : "eber");
    }
    /**
     * Kiírja a megadott szoba állapotát, a szobában található tárgyakat, embereket és azokat az ajtókat, amelyeken keresztül lehet elhagyni a szobát.
     * @param sz A szoba, amelyről információt szeretnénk kapni.
     */
    public  static  void InfoSzoba(Szoba sz) {

        String s1 = sz.atkozottE() ? "atkozott" : "nem atkozott";
        String s2 = sz.mergezoE() ? "mergezett" : "nem mergezett";
        String s3 = sz.ragacsosE() ? "ragacsos" : "nem ragacsos";
        System.out.println("A " + sz.getNev() + " allapota:");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println("A szobaban levo targyak:");
        for (Targy t : sz.getItems()) {
            System.out.println(getTargyNevFromMap(t));
        }
        System.out.println("A szobaban levo emberek:");
        for (Ember e : sz.getEmberek()) {
            System.out.println(getEmberNevFromMap(e));
        }
        System.out.println("A szobaban levo ajtok:");
        for (Ajto a : sz.getAjtok()) {
            System.out.println(getAjtoNevFromMap(a));
        }
        System.out.println("A jatekbol hatralevo ido:");
        System.out.println(Labirintus.getInstance().getTimeLeft());
    }


    /**
     * Beállítja hogy a játék determinisztikus-e
     */
    public static void Random(boolean b) {
        deterministic = !b;
        if (deterministic) System.out.println("mostantol determinisztikus a jatek.");
        else System.out.println("mostantol veletlenszeru a jatek.");
    }
    /**
     * Minden játékmenet lépésnél hívódik meg, hogy frissítse a játék állapotát.
     */
    public static void Tick(int n) {
        for (int i =0; i < n; i++) {
            Labirintus.getInstance().tick();
            Leptetes();
            if (Labirintus.getInstance().getTimeLeft() == 0) System.out.println("a jateknak vege, az ido lejart.");
        }
    }
    /**
     * A paraméterként kapott szoba felosztását végzi el a labirintusban.
     * Az újonnan létrehozott szobát hozzáadja a szobaMap-hoz és kiírja a konzolra az új szoba nevét.
     * Ezután végigmegy az új szoba ajtóin, és ha az ajtó még nem szerepel az ajtoMap-ben, akkor hozzáadja.
     * @param sz A felosztandó szoba
     */
    public static void SzobaFeloszt(Szoba sz) {
        Szoba ujszoba = Labirintus.getInstance().szobaFeloszt(sz);
        szobaMap.put(ujszoba.getNev(), ujszoba);
        System.out.println("uj szoba jott letre: " + ujszoba.getNev());

        for (Ajto ajto : ujszoba.getAjtok()) {
            if (!ajtoMap.containsValue(ajto)) {
                ajtoMap.put(ajto.getNev(), ajto);
            }
        }
    }
    /**
     * A paraméterként kapott két szoba összevonását végzi el a labirintusban.
     * Az összevont szobát eltávolítja a szobaMap-ből és kiírja a konzolra az összevont szobák nevét.
     * Ezután végigmegy az egyik szoba ajtóin, és eltávolítja azokat az ajtokat az ajtoMap-ből, amelyek az összevont szobára mutatnak.
     * @param sz1 Az egyik összevont szoba
     * @param sz2 A másik összevont szoba
     */
    public static void SzobaOsszevon(Szoba sz1, Szoba sz2) {
        Labirintus.getInstance().szobakOsszevon(sz1, sz2);
        System.out.println(getSzobaNevFromMap(sz1) + " es " + getSzobaNevFromMap(sz2) + " ossze lett vonva");
        szobaMap.remove(sz2.getNev());

        for (Ajto ajto : sz2.getAjtok()) {
            ajtoMap.entrySet().removeIf(entry -> entry.getValue().equals(ajto));
        }

    }

    /**
     * A függvény lépteti az embereket
     */
    public static void Leptetes() {
        for (Szoba sz : Labirintus.getInstance().getSzobak()) {
            List<Ajto> ajtok = sz.getAjtok();
            Collections.shuffle(ajtok);
            ArrayList<Ember> emberek = new ArrayList<>(sz.getEmberek());
            for (Ember e : emberek) {
                if(!ajtok.isEmpty())
                    e.controllerLeptet(ajtok.get(0));
            }
        }
    }
    /**
     * Getter függvény ami visszaadja a kért ajtót a mapból
     * @param s keresett ajtó neve
     * @return a keresett ajtó
     */
    public static Ajto getAjto(String s){
        return ajtoMap.get(s);
    }

    /**
     * Getter függvény ami visszaadja a kért ajtó nevét a mapból
     * @param a keresett ajtó
     * @return ajtó mapban tárolt neve
     */
    public static String getAjtoNevFromMap(Ajto a){
        for (Map.Entry<String, Ajto> entry : ajtoMap.entrySet()) {
            String key = entry.getKey();
            Ajto value = entry.getValue();
            if (value.equals(a)) {
                return key;
            }
        }
        return null;
    }

    /**
     * Getter függvény ami visszaadja a kért embert a mapból
     * @param s keresett ember neve
     * @return a keresett ember
     */
    public static Ember getEmber(String s){
        return emberMap.get(s);
    }

    /**
     * Getter függvény ami visszaadja a kért ember nevét a mapból
     * @param e keresett ember
     * @return ember mapban tárolt neve
     */
    public static String getEmberNevFromMap(Ember e){
        for (Map.Entry<String, Ember> entry : emberMap.entrySet()) {
            String key = entry.getKey();
            Ember value = entry.getValue();
            if (value.equals(e)) {
                return key;
            }
        }
        return null;
    }

    /**
     * Getter függvény ami visszaadja a kért szobát a mapból
     * @param s keresett szoba neve
     * @return a keresett szoba
     */
    public static Szoba getSzoba(String s) {
        return szobaMap.get(s);
    }

    /**
     * Getter függvény ami visszaadja a kért szoba nevét a mapból
     * @param sz keresett szoba
     * @return szoba mapban tárolt neve
     */
    public static String getSzobaNevFromMap(Szoba sz){
        for (Map.Entry<String, Szoba> entry : szobaMap.entrySet()) {
            String key = entry.getKey();
            Szoba value = entry.getValue();
            if (value.equals(sz)) {
                return key;
            }
        }
        return null;
    }

    /**
     * Getter függvény ami visszaadja a kért tárgyat a mapból
     * @param s keresett tárgy neve
     * @return a keresett tárgy
     */
    public static Targy getTargy(String s) {
        return targyMap.get(s);
    }

    /**
     * Getter függvény ami visszaadja a kért tárgy nevét a mapból
     * @param t keresett tárgy
     * @return tárgy mapban tárolt neve
     */
    public static String getTargyNevFromMap(Targy t){
        for (Map.Entry<String, Targy> entry : targyMap.entrySet()) {
            String key = entry.getKey();
            Targy value = entry.getValue();
            if (value.equals(t)) {
                return key;
            }
        }
        return null;
    }

    /**
     * A játék állapotát eltörli
     */
    public static void reset(){
        emberMap.clear();
        szobaMap.clear();
        ajtoMap.clear();
        targyMap.clear();
        Labirintus.reset();
        System.out.println("a jatek visszaallt a kiindulo allapotba.");
    }

    /**
     * Kiírja a betöltött játék adatait a konzolra,
     * beleértve a szobákat, azok szomszédait,
     * a szobákban található tárgyakat és embereket,
     * valamint a szobák állapotát.
     */
    private static void betoltesEredmenyKiir(){
        StringBuilder sb = new StringBuilder();
        sb.append("a jatek betoltve.").append(System.lineSeparator());

        sb.append("szobak:").append(System.lineSeparator());
        for(Szoba sz : Labirintus.getInstance().getSzobak()){
            sb.append("\t").append(sz.getNev()).append(System.lineSeparator());

            sb.append("\t").append("szomszedok:");
            for(Ajto a : sz.getAjtok()){
                if(a.getSzomszed(sz) != null){
                    sb.append(a.getSzomszed(sz).getNev()).append(",");
                }
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
            for(String s : a.getOldalak()){
                System.out.println(s + " nyithato: " + (a.getSzomszed(getSzoba(s)) == null ? "nem" : "igen"));
            }
        }
    }
    /**
     * Létrehozza és hozzáadja az ajtókat a szomszédos szobákhoz, a megadott szobák map-je alapján.
     * @param szobaSzomszedMap A szobák és szomszédjaik közötti kapcsolatokat tartalmazó map.
     */
    private static void ajtokLegyart(HashMap<String, ArrayList<String>> szobaSzomszedMap) {
        int i = 0;
        for(String szobaNev : szobaSzomszedMap.keySet()){
            ArrayList<String> szomszedokNevei = szobaSzomszedMap.get(szobaNev);
            for(String szomszedNev : szomszedokNevei){
                if(szomszedNev.isEmpty()) {
                    break;
                }
                Szoba szoba = szobaMap.get(szobaNev);
                Szoba szomszed = szobaMap.get(szomszedNev);
                if (!ajtoExists(szoba, szomszed)) {
                    Ajto a = new Ajto(szoba, szomszed, "ajto" + (++i));
                    a.setMerreNyilik(true, false);
                    boolean ketiranyu = false;
                    if(szobaSzomszedMap.containsKey(szomszedNev)){
                        for(String szomszedSzomszedNev : szobaSzomszedMap.get(szomszedNev)){
                            if(szomszedSzomszedNev.equals(szobaNev) && !szomszedSzomszedNev.isEmpty()){
                                ketiranyu = true;
                                break;
                            }
                        }
                    }
                    if(ketiranyu){
                        a.setMerreNyilik(true, true);
                    }
                    szoba.addAjto(a);
                    szomszed.addAjto(a);
                    ajtoMap.put("ajto" + i, a);
                }
            }
        }
    }
    /**
     * Ellenőrzi, hogy létezik-e már ajtó a két megadott szoba között.
     * @param szoba1 Az egyik szoba.
     * @param szoba2 A másik szoba.
     * @return Igaz, ha már létezik ajtó a két szoba között, különben hamis.
     */
    private static boolean ajtoExists(Szoba szoba1, Szoba szoba2) {
        for (Ajto ajto : ajtoMap.values()) {
            if ((ajto.getSzomszed(szoba1) == szoba2) || (ajto.getSzomszed(szoba2) == szoba1)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Feldolgozza a szobához tartozó tárgyakat, és hozzáadja őket a megfelelő szobához.
     * @param line           A feldolgozandó sor.
     * @param szobaTargyMap  A szobákhoz tartozó tárgyakat tároló térkép.
     * @param szoba          Az aktuális szoba, amelyhez a tárgyak tartoznak.
     */
    private static void targyakFeldolgoz(String line, HashMap<Szoba, ArrayList<Targy>> szobaTargyMap, Szoba szoba) {
        szobaTargyMap.put(szoba, new ArrayList<>());
        String[] parts = line.split(":");
        if(parts.length <= 1) return;
        String[] targyak = parts[1].split(",");

        for(String targyTipus : targyak){
            Targy t;
            t = ujTargyGyart(targyTipus);
            targyMap.put(targyTipus + (targyMap.size() + 1), t);
            szobaTargyMap.get(szoba).add(t);
            szoba.addItem(t);
        }
    }
    /**
     * Új tárgyat hoz létre a megadott típus alapján.
     * @param targy A tárgy típusa.
     * @return Az újonnan létrehozott tárgy.
     */
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
    /**
     * Feldolgozza a bemeneti sort, létrehoz egy új szobát a megadott adatok alapján, és hozzáadja a maphez.
     * @param line           A bemeneti sor, amely tartalmazza a szoba adatait.
     * @param szobaSzomszedMap A szomszédossági térkép, amelyhez hozzá kell adni a szoba szomszédait.
     * @return Az újonnan létrehozott szoba.
     */
    private static Szoba szobaFeldolgoz(String line, HashMap<String,ArrayList<String>> szobaSzomszedMap) {
        String[] parts = line.split(":");
        String nev = parts[0];
        String[] adatok = parts[1].split(",");
        String[] szomszedok = adatok[0].split(" ");
        ArrayList<String> szomszedokList = new ArrayList<>(Arrays.asList(szomszedok));
        String allapotok = adatok[1];
        int ferohely = Integer.parseInt(adatok[2]);

        Szoba szoba = new Szoba(nev, ferohely, allapotok);

        szobaMap.put(nev, szoba);
        szobaSzomszedMap.put(nev, szomszedokList);
        return szoba;
    }
    /**
     * Feldolgozza a bemeneti sort, és létrehozza az embereket az adatok alapján, majd hozzáadja őket a megfelelő szobához és az ember map-hez.
     * @param line           A bemeneti sor, amely tartalmazza az emberek adatait.
     * @param szoba          A szoba, amelyhez az embereket hozzá kell adni.
     * @param szobaEmberMap  A szobák és az azokhoz tartozó emberek map.
     */
    private static void emberekFeldolgoz(String line, Szoba szoba, HashMap<Szoba, ArrayList<Ember>> szobaEmberMap) {
        szobaEmberMap.put(szoba, new ArrayList<>());
        String[] parts = line.split(":");

        int hallgatoSzam = 1;
        int oktatokSzam = 1;
        int takaritoSzam = 1;
        for (Map.Entry<String, Ember> entry : emberMap.entrySet()) {
            String key = entry.getKey();
            if (key.contains("hallgato")) {
                hallgatoSzam++;
            }
            if (key.contains("oktato")) {
                oktatokSzam++;
            }
            if (key.contains("takarito")) {
                takaritoSzam++;
            }
        }
        if (parts.length > 1) {
            String[] emberek = parts[1].split(",");
            for(String ember : emberek){
                Ember e;
                if(ember.startsWith("hallgato")){
                    e = new Hallgato(ember);
                    emberMap.put(ember + (hallgatoSzam), e);
                    hallgatoSzam++;
                }
                else if(ember.startsWith("oktato")){
                    e = new Oktato(ember);
                    emberMap.put(ember + (oktatokSzam), e);
                    oktatokSzam++;
                }
                else{
                    e = new Takarito(ember);
                    emberMap.put(ember + (takaritoSzam), e);
                    takaritoSzam++;
                }

                e.masikSzobabaLep(szoba);
                szobaEmberMap.get(szoba).add(e);
            }
        }
    }

    /**
     * Létrehoz és visszaad egy HashMap-et, amely az elem neveket társítja azok darabszámához, kezdetben nullát állítva be.
     * @return Az a HashMap, amely az elem neveket tartalmazza kulcsként, és ezekhez tartozó darabszámokat értékként.
     */
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
}