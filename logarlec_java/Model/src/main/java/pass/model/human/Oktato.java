package pass.model.human;

import pass.model.CustomLogger;
import pass.model.CustomRecordFormatter;
import pass.model.Main;
import pass.model.item.*;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import static pass.model.Main.*;
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
    boolean inventoryTeleE() {
        CustomLogger.info(this +  ((inventory.size() >= MAX_INVENTORY_MERET) ? " inventoryja tele van." : " inventoryja nincs tele."));
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

    /**
     * A függvény a rongy hatásait
     * applikálja az oktatóra, ami bénítja
     * @param rongy - a rongy aminek a hatása alá kerül az okató
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
     * A függvény meglátogatja a megadott logarlécet,
     * és mivel az oktatók nem birtokolhatnak ilyen tárgyat ezért eldobják
     * @param logarlec - a meglátogatott logarléc
     */
    @Override
    public void visit(Logarlec logarlec) {
        CustomLogger.info("a " + logarlec + " birtoklása nincs hatással " + this + "-ra.");
        targyatEldob(logarlec);
    }

    /**
     *A függvény meglátogatja a megadott tvsz-t,
     * amit nem tud használni az oktató
     * @param tvsz - a meglátogatott tvsz
     */
    @Override
    public void visit(TVSZ tvsz) {
        CustomLogger.info("a " + tvsz + "birtoklása nincs hatással " + this + "-ra.");
    }

    /**
     * A függvény meglátogatja a megadott poharat,
     * amit nem tud használni az oktató
     * @param pohar - a meglátogatott pohár
     */
    @Override
    public void visit(Pohar pohar) {
        CustomLogger.info("a " + pohar + "birtoklása nincs hatással " + this + "-ra.");

    }

    /**
     * A függvény meglátogatja a megadott rongyot,
     * amit nem tud használni az oktató
     * @param rongy - a meglátogatott rongy
     */
    @Override
    public void visit(Rongy rongy) {
        CustomLogger.info("a " + rongy + "birtoklása nincs hatással " + this + "-ra.");

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
}