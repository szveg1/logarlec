package pass.controller;

import pass.model.TargyVisitor;
import pass.model.human.Ember;
import pass.model.item.Logarlec;
import pass.model.item.Targy;
import pass.model.labyrinth.Labirintus;

import java.util.ArrayList;
import java.util.List;

/**
 * Az EmberQueue osztály egy sor struktúrát valósít meg, amelyben az Ember objektumokat tárolja.
 * A sorban lévő emberek sorban lépnek, és a sor végére érve a sor újraindul.
 */
public class EmberQueue implements TargyVisitor {

    /**
     * Ember objektumok listája, amelyek a sorban vannak.
     */
    private List<Ember> queue = new ArrayList<>();

    /**
     * Az aktuálisan soron következő Ember indexe a listában.
     */
    private int idx = 0;

    /**
     * Jelzi, hogy a játék véget ért-e.
     */
    private boolean end = false;

    /**
     * Hozzáad egy Ember objektumot a sorhoz.
     *
     * @param e Az Ember objektum, amit hozzáadunk a sorhoz.
     */
    public void add(Ember e) {
        queue.add(e);
    }

    /**
     * Visszaadja a sorban következő Ember objektumot.
     *
     * @return Az Ember objektum, aki a sorban következik.
     */
    public Ember getNext() {
        return queue.get(idx);
    }

    /**
     * Újraindítja a sort, minden Ember objektum lepését visszaállítja, és az end változót hamisra állítja.
     */
    public void reset() {
        for (Ember e : queue) {
            e.setLepett(false);
        }
        end = false;
        idx = 0;
    }

    /**
     * Törli a sort, azaz eltávolítja az összes Ember objektumot a listából.
     */
    public void clear(){
        queue.clear();
    }

    /**
     * A sorban következő Ember objektumra lép.
     */
    public void next() {
        idx = (idx + 1) % queue.size();
        if (idx == 0) {
            Controller.Tick(1);
            reset();
        }
    }

    /**
     * Ellenőrzi, hogy a megadott Ember objektum nyert-e.
     *
     * @param ember Az Ember objektum, amit ellenőrizünk.
     * @return Igaz, ha a megadott Ember objektum nyert, hamis egyébként.
     */
    public boolean checkForWin(Ember ember) {
        for (Targy targy : ember.getItems()) {
            targy.accept(this);
        }
        return end;
    }

    /**
     * Ellenőrzi, hogy a játékosok vesztettek-e.
     *
     * @return Igaz, ha a játékosok vesztettek, hamis egyébként.
     */
    public boolean checkForLoss() {
        if(Labirintus.getTimeLeft() == 0){
            return true;
        }

        boolean vanvalakieletben = false;
        for (Ember ember : queue) {
            if(ember.getEletbenVan()){
                vanvalakieletben = true;
            }
        }
        return !vanvalakieletben;
    }

    /**
     * A Logarlec objektumokat kezeli, amikor azokat a TargyVisitor interfész metódusai hívják.
     *
     * @param logarlec A Logarlec objektum, amit kezelni kell.
     */
    @Override
    public void visit(Logarlec logarlec) {
        end = true;
    }

}
