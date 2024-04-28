package pass.model.labyrinth;

import pass.model.CustomLogger;
import pass.model.Idozitett;
import pass.model.human.Ember;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;
import pass.model.item.Targy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/* Számon tartja a benne tartózkodó hallgatókat, oktatókat és tárgyakat, valamint a
szobának a tulajdonágait tárolja  */
public class Szoba implements Idozitett {
    // Csak szkeletonhoz-------------
    private String nev;
    public String getNev() {
        return nev;
    }
    /**
     * Létrehoz egy szobát a bekért adatok alapján, inicializálja a változóit.
     * @param ferohely - A szoba kapacitása
     * @param nev - Szoba neve Szkeletonhoz
     */
    public Szoba(int ferohely, String nev) {
        this.ferohely = ferohely;
        this.targyak = new ArrayList<>();
        this.bentlevok = new ArrayList<>();
        this.ajtok = new ArrayList<>();
        this.nev = nev;
    }
    public void writeToFileFirstLine(FileWriter fw, List<String> emberek_list, List<String> targyak_list) throws IOException {
        try {
            fw.append(nev).append(":");

            // Szomszédos szobák kiírása
            for (int i = 0; i < ajtok.size(); i++){
                Szoba szomszed = ajtok.get(i).getSzomszed(this);
                if (szomszed != null) {
                    fw.append(szomszed.getNev());
                }
                if(i != ajtok.size() - 1){
                    fw.append(" ");
                }
            }
            fw.append(",");
            StringBuilder sb = new StringBuilder();
            if (tiszta) {
                sb.append("tiszta ");
            }else {
                sb.append("ragacsos ");
            }
            if (mergezoE()) {
                sb.append("mergezo ");
            }
            if (atkozott) {
                sb.append("atkozott ");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(",");
            fw.append(sb.toString());
            fw.append(ferohely + "\n");

            // Emberek kiírása
            fw.append("emberek:");
            for (int i = 0; i < emberek_list.size(); i++){
                fw.append(emberek_list.get(i));
                if(i != emberek_list.size() - 1){
                    fw.append(" ");
                }
            }
            fw.append("\n");
//            // Tárgyak kiírása
//            fw.append("targyak:");
//            for (int i = 0; i < targyak.size(); i++){
//                if(!targyak_list.isEmpty()) {
//                    fw.append(targyak_list.get(i));
//                }
//
//                if(i != targyak.size() - 1){
//                    fw.append(",");
//                }
//            }
            // Tárgyak kiírása
            fw.append("targyak:");
            for (int i = 0; i < targyak_list.size(); i++){
                fw.append(targyak_list.get(i));
                if(i != targyak_list.size() - 1){
                    fw.append(",");
                }
            }
            fw.append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * A függvény kiírja az objektum nevét
     * @return String, Szkeleton kiiratashoz
     */
    @Override
    public String toString() {
        return nev + " :Szoba";
    }

    // -------------------------------
    private int meregIdo;
    private boolean atkozott;
    private boolean tiszta = true;
    private int tisztanBelepok;
    private boolean ragacsos = false;
    private int ferohely;
    private List<Targy> targyak;
    private List<Ember> bentlevok;
    private List<Ajto> ajtok;

    Map<Oktato, List<Hallgato>> immunisok = new HashMap<>();

    /*public Szoba(int ferohely) {
        this.ferohely = ferohely;
        this.targyak = new ArrayList<>();
        this.bentlevok = new ArrayList<>();
        this.ajtok = new ArrayList<>();
    }*/

    public Szoba(String nev, int ferohely, String allapotok){
        this.nev = nev;
        this.ferohely = ferohely;
        this.ajtok = new ArrayList<>();
        this.targyak = new ArrayList<>();
        this.bentlevok = new ArrayList<>();
        if(allapotok.contains("tiszta")) {
            this.tiszta = true;
        }
        if(allapotok.contains("ragacsos")) {
            this.ragacsos = true;
        }
        if(allapotok.contains("atkozott")) {
            this.atkozott = true;
        }
        if(allapotok.contains("mergezo")) {
            this.meregIdo = 10;
        }
    }
    /**
     * A függvény a megadott szobát szétosztja több szobává
     * @param masikSzoba - Szétosztáshoz a szétosztandó szoba
     */
    public Szoba(Szoba masikSzoba) {
        this.meregIdo = masikSzoba.meregIdo;
        this.ferohely = masikSzoba.ferohely;
        this.nev = "ujSzoba";
        // Szoba targyainak fele masik szobaba
        int targyakFele = masikSzoba.targyak.size() / 2;
        ArrayList<Targy> felezettTargyak = new ArrayList<>(masikSzoba.targyak.subList(0, targyakFele));

        masikSzoba.targyak.removeAll(felezettTargyak);

        // Notify the items about the room change
        for (Targy targy : felezettTargyak) {
            targy.szobaValtasrolErtesit(this);
        }

        this.targyak = felezettTargyak;

        // Bentlevok fele masik szobaba
        int bentlevokSzamaFele = masikSzoba.bentlevok.size() / 2;
        this.bentlevok = new ArrayList<>();
        for (int i = 0; i < bentlevokSzamaFele; ++i) {
            masikSzoba.getEmberek().get(0).masikSzobabaLep(this);
        }

        int ajtokFele = masikSzoba.ajtok.size() / 2;
        this.ajtok = new ArrayList<>();
        for(int i = 0; i < ajtokFele; ++i) {
            this.ajtok.add(masikSzoba.ajtok.get(0));
        }
        Ajto ujAjto = new Ajto(this, masikSzoba, "ujAjto");
        masikSzoba.ajtok.removeAll(this.ajtok);

        this.ajtok.add(ujAjto);
        masikSzoba.ajtok.add(ujAjto);
    }

    /**
     * A függgvény ezt a szobát a megadottal egyesíti
     * @param masikSzoba - Egyesítéshez a másik szoba
     */
    public void egyesit(Szoba masikSzoba){
        Szoba szomszed = null;
        for(Ajto ajto : this.ajtok){
            if(ajto.getSzomszed(this) == masikSzoba){
                szomszed = ajto.getSzomszed(this);
                break;
            }
        }

        if(szomszed == null){
            CustomLogger.info(this + " és " + masikSzoba + " nem szomszédosak. Nem lehet őket egyesíteni.");
            return;
        }

        int ujFerohely = Math.max(this.ferohely, masikSzoba.ferohely);
        if(ujFerohely < this.getEmberek().size() + masikSzoba.getEmberek().size()){
            CustomLogger.info("Nem lehet egyesíteni a szobákat, mert nem lenne elég hely mindenkinek.");
            return;
        }
        List<Ember> masikSzobaEmberek = masikSzoba.getEmberek();
        int masikSzobaBentlevokMeret = masikSzobaEmberek.size();

        for(int i = 0; i < masikSzobaBentlevokMeret; i++){
            masikSzobaEmberek.get(0).masikSzobabaLep(this);
        }

        List<Targy> targyak = masikSzoba.getItems();
        for(Targy targy : targyak){
            this.addItem(targy);
        }

        ajtok.addAll(masikSzoba.ajtok);
        Ajto kozosAjto = null;
        for(Ajto ajto : masikSzoba.ajtok){
            if(ajto.getSzomszed(this) == masikSzoba){
                kozosAjto = ajto;
                break;
            }
        }
        removeAjto(kozosAjto);

        Labirintus.szobaKivesz(masikSzoba);
    }

    /**
     * Setter függvény ami a szobát mérgezővé teszi
     * @param meregIdo - Idő ameddig mérgezett a szoba
     */
    public void setMeregIdo(int meregIdo) {
        CustomLogger.info(this + " mérgezővé vált " + meregIdo + " időre");
        this.meregIdo = meregIdo;
    }

    /**
     * A függvény visszaadja hogy mérgező e a szoba
     * @return Visszaadja hogy mérgező e a szoba
     */
    public boolean mergezoE() {
        return meregIdo > 0;
    }
    public boolean ragacsosE() {
        return ragacsos;
    }

    public boolean atkozottE() {
        return atkozott;
    }

    /**
     * A függvény a szobához hozzáad egy tárgyat
     * @param targy - A szobához hozzáadandó tárgy
     */
    public void addItem(Targy targy) {
        targyak.add(targy);
        CustomLogger.info("A " + this + "-ba bekerült a " + targy + " tárgy");
        targy.szobaValtasrolErtesit(this);
    }

    /**
     * A függvény a szobához hozzáad egy ajtót
     * @param ajto - Szobához hozzáadandó ajtó
     */
    public void addAjto(Ajto ajto) {
        ajtok.add(ajto);
        CustomLogger.info("A " + this + "-ba bekerült az " + ajto + ".");
    }

    /**
     * A függvény, a megadott ajtót eltávolítja a szobából
     * @param ajto - A szobából kitörlendő ajtó
     */
    public void removeAjto(Ajto ajto) {
        ajtok.remove(ajto);
        CustomLogger.info("A " + this + "-ból kikerült az " + ajto + ".");
    }

    /**
     * Getter függvény ami visszaadja a szobában lávő tárgyak listáját
     * @return Visszaadja a szobában lávő tárgyak listáját
     */
    public List<Targy> getItems() {
        return targyak;
    }

    /**
     * Getter függvény visszaadja mennyi ember fér a szobába
     * @return Visszaadja a szoba kapacitását
     */
    public int getFerohely() {
        return ferohely;
    }

    /**
     * A függvény eltávolítja a szobából a megadott tárgyat
     * @param targy - A szobából kitörlendő tárgy
     */
    public void removeItem(Targy targy) {
        targyak.remove(targy);
        CustomLogger.info("A " + this + "-ból kikerült a " + targy + " tárgy");
    }

    /**
     * A függvény, a szobához hozzáad egy embert
     * @param ember - Ember, amit be szeretnénk tenni a szobába
     */
    public boolean emberBetesz(Ember ember) {
        if(bentlevok.size() + 1 > ferohely) {
        CustomLogger.info(ember + " belépett volna a " + this + "-ba, de tele van.");
        return false;
        } else {
            ember.kilepSzobajabol();
            bentlevok.add(ember);
            CustomLogger.info(ember + " bekerült a bentlevők közé.");
            if(meregIdo > 0) {
                ember.ajulas();
            }
            if(tiszta){
                tisztanBelepok++;
            }
            return true;
        }
    }

    /**
     * A függvény, a szobából eltávolítja a megadott embert
     * @param ember - Ember, akit ki szeretnénk venni a szobából
     */
    public void emberKivesz(Ember ember) {
        bentlevok.remove(ember);
    }

    public List<Ember> getEmberek() {
        return  bentlevok;
    }
    public List<Ajto> getAjtok() {
        return ajtok;
    }

    /**
     * A függvény, egy oktató támadásai ellen immunis hallgatókat szedi össze
     * @param oktato - Az oktató, aki ellen immunitás lett szerezve
     * @param hallgato - A hallgató, aki az immunitást szerezte
     */
    public void immunitastAd(Oktato oktato, Hallgato hallgato) {
        if(immunisok.containsKey(oktato)) {
            immunisok.get(oktato).add(hallgato);
        } else {
            immunisok.put(oktato, new ArrayList<>(Collections.singletonList(hallgato)));
        }
    }

    /**
     * A függvény elájulttá teszi a jelenlévő embereket, amíg a szoba mérgező
     */
    @Override
    public void tick() {
        for(Ember ember : bentlevok) {
            if(meregIdo > 0)
                ember.ajulas();
            ember.tick();
        }
        if(tisztanBelepok >= 10){
            ragacsos = true;
            tiszta = false;
        }
        for (Ajto a : ajtok) {
            a.tick();
        }
        for (Targy t : targyak) {
            t.tick();
        }
    }

    public void setTiszta(boolean tiszta) {
        this.tiszta = tiszta;
    }
}
