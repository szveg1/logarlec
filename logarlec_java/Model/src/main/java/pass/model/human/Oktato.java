package pass.model.human;

import pass.model.CustomRecordFormatter;
import pass.model.Main;
import pass.model.item.*;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

import static pass.model.Main.*;

public class Oktato extends Ember {
    private static final Logger oktatoLogger = Logger.getLogger(Oktato.class.getSimpleName());
    static{
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomRecordFormatter());
        oktatoLogger.setUseParentHandlers(false);
        oktatoLogger.addHandler(Main.handler);
    }
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
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

    @Override
    public void rongyotElszenved(Rongy rongy) {
        meddigBena = rongy.getMeddigNedves();
        if(meddigBena > 0) {
            oktatoLogger.info(this + " megbénult a " + rongy + " miatt.");
        } else {
            oktatoLogger.info(this + "-ra nem hatott a " + rongy + ".");
        }
    }

    @Override
    public void tick() {

    }

    @Override
    public void visit(Logarlec logarlec) {
        oktatoLogger.info("a " + logarlec + "birtoklása nincs hatással " + this + "-ra.");
        targyatEldob(logarlec);
        oktatoLogger.info(this + " eldobta a " + logarlec + "-t.");
    }

    @Override
    public void visit(TVSZ tvsz) {
        oktatoLogger.info("a " + tvsz + "birtoklása nincs hatással " + this + "-ra.");
    }

    @Override
    public void visit(Pohar pohar) {
        oktatoLogger.info("a " + pohar + "birtoklása nincs hatással " + this + "-ra.");

    }

    @Override
    public void visit(Rongy rongy) {
        oktatoLogger.info("a " + rongy + "birtoklása nincs hatással " + this + "-ra.");

    }

    public void hallgatotMegtamad(Hallgato hallgato) {
        if(meddigBena > 0) {
            oktatoLogger.info(this + " nem tudja " + hallgato + "-t megtámadni, mert még " + meddigBena + " körig bénult.");
            return;
        }
        hallgato.tamadasElszenved(this);
        kitTamad = hallgato;
    }

    public Hallgato getKitTamad(){
        return kitTamad;
    }
}