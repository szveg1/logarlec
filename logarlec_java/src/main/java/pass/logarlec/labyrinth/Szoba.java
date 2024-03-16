package pass.logarlec.labyrinth;

import pass.logarlec.human.Ember;
import pass.logarlec.item.Targy;

import java.util.*;

public class Szoba {
    private boolean mergezett;
    private int meregIdo;
    private List<Targy> targyak;
    private List<Ember> bentlevok;

    public Szoba() {
        this.mergezett = false;
        this.targyak = new ArrayList<>();
        this.bentlevok = new ArrayList<>();
    }

    public boolean MergezettE() {
        return mergezett;
    }

    public void setPoisonous(boolean mergezo, int meregIdo) {
        mergezett = mergezo;
        this.meregIdo = meregIdo;
    }

    public void addItem(Targy targy) {
        targyak.add(targy);
        targy.szobaValtasrolErtesit(this);
    }

    public void removeItem(Targy targy) {
        targyak.remove(targy);
    }

    public void addOccupant(Ember ember) {
        bentlevok.add(ember);
    }

    public void removeOccupant(Ember ember) {
        bentlevok.remove(ember);
    }

    public void update() {
        if (mergezett) {
            meregIdo--;
            if (meregIdo <= 0) {
                mergezett = false;
            }
            for (Ember ember : bentlevok) {
                ember.ajulas();
            }
        }
    }
}
