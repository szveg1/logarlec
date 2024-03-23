package pass.model.human;

import pass.model.item.*;

public class Hallgato extends Ember {

    private boolean tudVedekezni = false;

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
    }

    @Override
    public void tick() {

    }
}
