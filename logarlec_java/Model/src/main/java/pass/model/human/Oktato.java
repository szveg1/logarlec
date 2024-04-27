package pass.model.human;

import pass.model.CustomLogger;
import pass.model.item.*;
import pass.model.labyrinth.*;

/**
 * Az Oktató osztály felelős a nem játékos karakterek kezeléséért a labirintusban. Ez az
 * osztály felelős azért, hogy megnehezítse a játékos dolgát a játék során.
 */
public class Oktato extends Ember {
    private static final int MAX_INVENTORY_MERET = 1;
    private int meddigBena = 0;
    private Hallgato kitTamad;
    // Csak szkeletonhoz-------------
    public Oktato(String nev) {
        super(nev);
    }

    @Override
    public String toString() {
        return nev + " :Oktato";
    }
    //--------------------------------

    /**
     * A függvény megnézi hogy az oktató zsebe tele van-e
     * @return Boolean értékű visszajelzés, hogy van-e hely az inventory-jában
     */
    @Override
    public boolean inventoryTeleE() {
        CustomLogger.info(this +  ((inventory.size() >= MAX_INVENTORY_MERET) ? " inventoryja tele van." : " inventoryja nincs tele."));
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

    /**
     * A függvény a rongy hatásait
     * applikálja az oktatóra, ami bénítja
     * @param rongy  a rongy aminek a hatása alá kerül az okató
     */
    @Override
    public void rongyotElszenved(Rongy rongy) {
        meddigBena = rongy.getMeddigNedves();
        if(meddigBena > 0) {
            CustomLogger.info(this + " megbénult a " + rongy + " miatt." + meddigBena + " körig.");
        } else {
            CustomLogger.info(this + "-ra nem hatott a " + rongy + ".");
        }
    }

    @Override
    public void tick() {
        if(!inventory.isEmpty()) inventory.get(0).accept(this);
    }

    /**
     * A függvény az oktató hallgató megtámadását valósítja meg
     * @param hallgato - A célzott hallgató, akit megtámad az oktató lélekelvétel céljával.
     */
    public void hallgatotMegtamad(Hallgato hallgato) {
        if(meddigBena > 0) {
            CustomLogger.info(this + " nem tudja " + hallgato + "-t megtámadni, mert még " + meddigBena + " körig bénult.");
            return;
        }
        hallgato.tamadasElszenved(this);
        kitTamad = hallgato;
    }

    /**
     *  A függvény egy getter függvény ami,
     *  visszaadja a hallgatót, akit támad az oktató
     * @return Visszaadja a hallgatót, akit támad az oktató
     */
    public Hallgato getKitTamad(){
        return kitTamad;
    }

    @Override
    public void controllerLeptet(Ajto a) { a.hasznal(this);}
}