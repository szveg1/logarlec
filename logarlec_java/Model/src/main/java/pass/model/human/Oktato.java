package pass.model.human;

import pass.model.CustomLogger;
import pass.model.CustomRecordFormatter;
import pass.model.Main;
import pass.model.item.*;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import static pass.model.Main.*;

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
    @Override
    boolean inventoryTeleE() {
        CustomLogger.info(this + " inventoryja tele van: " + (inventory.size() >= MAX_INVENTORY_MERET));
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

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

    }

    @Override
    public void visit(Logarlec logarlec) {
        CustomLogger.info("a " + logarlec + "birtoklása nincs hatással " + this + "-ra.");
        targyatEldob(logarlec);
        CustomLogger.info(this + " eldobta a " + logarlec + "-t.");
    }

    @Override
    public void visit(TVSZ tvsz) {
        CustomLogger.info("a " + tvsz + "birtoklása nincs hatással " + this + "-ra.");
    }

    @Override
    public void visit(Pohar pohar) {
        CustomLogger.info("a " + pohar + "birtoklása nincs hatással " + this + "-ra.");

    }

    @Override
    public void visit(Rongy rongy) {
        CustomLogger.info("a " + rongy + "birtoklása nincs hatással " + this + "-ra.");

    }

    public void hallgatotMegtamad(Hallgato hallgato) {
        if(meddigBena > 0) {
            CustomLogger.info(this + " nem tudja " + hallgato + "-t megtámadni, mert még " + meddigBena + " körig bénult.");
            return;
        }
        hallgato.tamadasElszenved(this);
        kitTamad = hallgato;
    }

    public Hallgato getKitTamad(){
        return kitTamad;
    }
}