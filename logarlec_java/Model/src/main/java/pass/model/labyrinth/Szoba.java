package pass.model.labyrinth;

import pass.model.CustomLogger;
import pass.model.Idozitett;
import pass.model.human.Ember;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;
import pass.model.item.Targy;

import java.util.*;

/* Számon tartja a benne tartózkodó hallgatókat, oktatókat és tárgyakat, valamint a
szobának a tulajdonágait tárolja  */
public class Szoba implements Idozitett {
    // Csak szkeletonhoz-------------
    private String nev;

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

    @Override
    public String toString() {
        return nev + " :Szoba";
    }

    // -------------------------------
    private int meregIdo;
    private boolean atkozott;
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

    /**
     *
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
        for (int i = 0; i <= bentlevokSzamaFele; ++i) {
            masikSzoba.getEmberek().get(i).masikSzobabaLep(this);
        }

        int ajtokFele = masikSzoba.ajtok.size() / 2;
        this.ajtok = new ArrayList<>();
        for(int i = 0; i <= ajtokFele; ++i) {
            this.ajtok.add(masikSzoba.ajtok.get(i));
        }
    }

    /**
     *
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
     *
     * @param meregIdo - Idő ameddig mérgezett a szoba
     */
    public void setPoisonous(int meregIdo) {
        CustomLogger.info(this + " mérgezővé vált " + meregIdo + " időre");
        this.meregIdo = meregIdo;
    }

    public boolean isPoisonous() {
        return meregIdo > 0;
    }

    /**
     *
     * @param targy - A szobához hozzáadandó tárgy
     */
    public void addItem(Targy targy) {
        targyak.add(targy);
        CustomLogger.info("A " + this + "-ba bekerült a " + targy + " tárgy");
        targy.szobaValtasrolErtesit(this);
    }

    /**
     *
     * @param ajto - Szobához hozzáadandó ajtó
     */
    public void addAjto(Ajto ajto) {
        ajtok.add(ajto);
        CustomLogger.info("A " + this + "-ba bekerült az " + ajto + ".");
    }

    /**
     * s
     * @param ajto - A szobából kitörlendő ajtó
     */
    public void removeAjto(Ajto ajto) {
        ajtok.remove(ajto);
        CustomLogger.info("A " + this + "-ból kikerült az " + ajto + ".");
    }

    public List<Targy> getItems() {
        return targyak;
    }

    public int getFerohely() {
        return ferohely;
    }

    /**
     *
     * @param targy - A szobából kitörlendő tárgy
     */
    public void removeItem(Targy targy) {
        targyak.remove(targy);
        CustomLogger.info("A " + this + "-ból kikerült a " + targy + " tárgy");
    }

    /**
     *
     * @param ember - Ember, amit be szeretnénk tenni a szobába
     */
    public boolean emberBetesz(Ember ember) {
        if(bentlevok.size()  + 1 > ferohely) {
        CustomLogger.info(ember + " belépett volna a " + this + "-ba, de tele van.");
        return false;
        } else {
            ember.kilepSzobajabol();
            bentlevok.add(ember);
            CustomLogger.info(ember + " bekerült a bentlevők közé.");
            if(meregIdo > 0) {
                ember.ajulas();
            }
            return true;
        }
    }

    /**
     *
     * @param ember - Ember, akit ki szeretnénk venni a szobából
     */
    public void emberKivesz(Ember ember) {
        bentlevok.remove(ember);
    }

    public List<Ember> getEmberek() {
        return  bentlevok;
    }

    /**
     *
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

    @Override
    public void tick() {

    }
}
