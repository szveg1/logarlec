package pass.model.labyrinth;

import pass.model.CustomRecordFormatter;
import pass.model.Idozitett;
import pass.model.human.Ember;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;
import pass.model.item.Targy;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;


public class Szoba implements Idozitett {
    private static Logger szobaLogger = Logger.getLogger(Szoba.class.getSimpleName());
    static{
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomRecordFormatter());
        szobaLogger.setUseParentHandlers(false);
        szobaLogger.addHandler(handler);
    }
    // Csak szkeletonhoz-------------
    private String nev;
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

    public Szoba(Szoba masikSzoba) {
        this.meregIdo = masikSzoba.meregIdo;
        this.ferohely = masikSzoba.ferohely;

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
    }

    public void setPoisonous(int meregIdo) {
        szobaLogger.info(this + " mérgezővé vált " + meregIdo + " időre");
        this.meregIdo = meregIdo;
    }

    public boolean isPoisonous() {
        return meregIdo > 0;
    }

    public void addItem(Targy targy) {
        targyak.add(targy);
        szobaLogger.info("A " + this + " szobába bekerült a " + targy + " tárgy");
        targy.szobaValtasrolErtesit(this);
    }

    public List<Targy> getItems() {
        return targyak;
    }

    public int getFerohely() {
        return ferohely;
    }

    public void removeItem(Targy targy) {
        targyak.remove(targy);
        szobaLogger.info("A " + this + " szobából kikerült a " + targy + " tárgy");
    }

    public void emberBetesz(Ember ember) {
        if(bentlevok.size()  + 1 > ferohely) {
        } else {
            ember.kilepSzobajabol();
            bentlevok.add(ember);
            if(meregIdo > 0) {
                ember.ajulas();
            }
        }
    }

    public void emberKivesz(Ember ember) {
        bentlevok.remove(ember);
    }

    public List<Ember> getEmberek() {
        return  bentlevok;
    }

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
