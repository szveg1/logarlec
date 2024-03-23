package pass.model.human;

import pass.model.item.Logarlec;
import pass.model.item.Pohar;
import pass.model.item.Rongy;
import pass.model.item.TVSZ;

public class Oktato extends Ember {
    private static final int MAX_INVENTORY_MERET = 1;
    private int meddigBena = 0;
    private Hallgato kitTamad;
    @Override
    boolean inventoryTeleE() {
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

    @Override
    public void rongyotElszenved(Rongy rongy) {
        meddigBena = rongy.getMeddigNedves();
    }

    @Override
    public void tick() {

    }

    @Override
    public void visit(Logarlec logarlec) {
        targyatEldob(logarlec);
    }

    @Override
    public void visit(TVSZ tvsz) {
        // Nem ad vedelmet az oktatonak
    }

    @Override
    public void visit(Pohar pohar) {
        // Nem ad vedelmet az oktatonak
    }

    @Override
    public void visit(Rongy rongy) {
        // Nem ad vedelmet az oktatonak
    }

    public void hallgatotMegtamad(Hallgato hallgato) {
        if(meddigBena > 0) {
            return;
        }
        hallgato.tamadasElszenved(this);
        kitTamad = hallgato;
    }

    public Hallgato getKitTamad(){
        return kitTamad;
    }
}