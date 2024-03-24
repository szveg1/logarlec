package pass.model.human;

import pass.model.CustomLogger;
import pass.model.CustomRecordFormatter;
import pass.model.Main;
import pass.model.item.*;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class Hallgato extends Ember {

    private static final Logger hallgatoLogger = Logger.getLogger(Hallgato.class.getSimpleName());
    static{
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new CustomRecordFormatter());
        hallgatoLogger.setUseParentHandlers(false);
        hallgatoLogger.addHandler(handler);
    }
    // Csak szkeletonhoz-------------
    public Hallgato(String nev) {
        super(nev);
    }

    @Override
    public String toString() {
        return nev + " :Hallgato";
    }
    //--------------------------------
    private boolean tudVedekezni = false;
    private boolean eletbenVan = true;
    public boolean getEletbenVan(){
        return eletbenVan;
    }

    public void setEletbenVan(boolean eletbenVan){
        this.eletbenVan = eletbenVan;
    }
    public boolean gettudVedekezni(){
        return tudVedekezni;
    }

    @Override
    public void visit(Logarlec logarlec) {
        // Szol a kontrollernek hogy nyert
    }

    @Override
    public void visit(TVSZ tvsz) {
        tudVedekezni = tvsz.hasznalhatoE();
    }

    @Override
    public void visit(Pohar pohar) {
        tudVedekezni = pohar.hasznalhatoE();
    }

    @Override
    public void visit(Rongy rongy) {
        tudVedekezni = rongy.hasznalhatoE();
    }

    @Override
    boolean inventoryTeleE() {
        int MAX_INVENTORY_MERET = 5;
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

    @Override
    public void rongyotElszenved(Rongy rongy) {
        // Nem csinal semmit
    }

    // Rongy + Pohar hianyzik meg


    public void tamadasElszenved(Oktato oktato) {
        int hasznalniKivant = 0;
        for(Targy targy : inventory) {
            targy.accept(this);
            CustomLogger.info(this + " tud védekezni a " + targy + " segítségével:" + tudVedekezni);
            if(tudVedekezni){
                inventory.get(hasznalniKivant).hasznal(oktato);
                break;
            }
            hasznalniKivant++;
        }
        if(!tudVedekezni){
            meghal();
        }
    }

    private void meghal() {
        setEletbenVan(false);
    }

    @Override
    public void tick() {
        super.tick();
    }
}
