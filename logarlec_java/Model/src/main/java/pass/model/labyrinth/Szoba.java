package pass.model.labyrinth;

import pass.model.Idozitett;
import pass.model.human.Ember;
import pass.model.human.Hallgato;
import pass.model.human.Oktato;
import pass.model.item.Targy;

import java.util.*;

public class Szoba implements Idozitett {
    private int meregIdo;
    private boolean atkozott;
    private int ferohely;
    private List<Targy> targyak;
    private List<Ember> bentlevok;
    private List<Ajto> ajtok;

    Map<Oktato, List<Hallgato>> immunisok = new HashMap<>();

    public Szoba(int ferohely) {
        this.ferohely = ferohely;
        this.targyak = new ArrayList<>();
        this.bentlevok = new ArrayList<>();
        this.ajtok = new ArrayList<>();
    }

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
        ArrayList<Ember> felezettBentlevok = new ArrayList<>(masikSzoba.bentlevok.subList(0, bentlevokSzamaFele));
        masikSzoba.bentlevok.removeAll(felezettBentlevok);
        this.bentlevok = felezettBentlevok;
        for(Ember ember : felezettBentlevok) {
            ember.masikSzobabaLep(this);
        }
    }

    public void setPoisonous(int meregIdo) {
        this.meregIdo = meregIdo;
    }

    public boolean isPoisonous() {
        return meregIdo > 0;
    }

    public void addItem(Targy targy) {
        targyak.add(targy);
        targy.szobaValtasrolErtesit(this);
    }

    public List<Targy> getItems() {
        return targyak;
    }

    public void addEmber(Ember ember) {
        bentlevok.add(ember);
        ember.setJelenlegiSzoba(this);
    }

    public void removeItem(Targy targy) {
        targyak.remove(targy);
    }

    public void emberBetesz(Ember ember) {
        if(bentlevok.size()  + 1 > ferohely) {
            return;
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
