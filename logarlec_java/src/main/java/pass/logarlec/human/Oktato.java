package pass.logarlec.human;

import pass.logarlec.item.Logarlec;
import pass.logarlec.item.Pohar;
import pass.logarlec.item.Rongy;
import pass.logarlec.item.TVSZ;

public class Oktato extends Ember {
    private static final int MAX_INVENTORY_MERET = 1;

    @Override
    boolean inventoryTeleE() {
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

    @Override
    public void visitLogarlec(Logarlec logarlec) {
        // Nem tortenik semmi, ha felveszi
    }

    @Override
    public void visitTVSZ(TVSZ tvsz) {
        // Nem ad vedelmet az oktatonak
    }

    @Override
    public void visitPohar(Pohar pohar) {
        // Nem ad vedelmet az oktatonak
    }

    @Override
    public void visitRongy(Rongy rongy) {
        // Nem ad vedelmet az oktatonak
    }

    public void hallgatoUldoz(Hallgato hallgato) {
        // Valami uldozesi logika
    }

    public void hallgatotMegtamad(Hallgato hallgato) {
        hallgato.tamadasElszenved();
    }
}