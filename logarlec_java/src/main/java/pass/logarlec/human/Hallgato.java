package pass.logarlec.human;

import pass.logarlec.item.Logarlec;
import pass.logarlec.item.Pohar;
import pass.logarlec.item.Rongy;
import pass.logarlec.item.TVSZ;

public class Hallgato extends Ember {
    private final int MAX_INVENTORY_MERET = 5;

    private boolean TVSZAltalVedett;
    private boolean RongyAltalVedett;


    @Override
    public void visitLogarlec(Logarlec logarlec) {

    }

    @Override
    public void visitTVSZ(TVSZ tvsz) {
        TVSZAltalVedett = tvsz.hasznalhatoE();
    }

    @Override
    public void visitPohar(Pohar pohar) {

    }

    @Override
    public void visitRongy(Rongy rongy) {
        RongyAltalVedett = rongy.hasznalhatoE();
    }

    @Override
    boolean inventoryTeleE() {
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

    // Rongy + Pohar hianyzik meg
    public void tamadasElszenved() {
        if(TVSZAltalVedett) {
            // tuleli
        } else {
            // meghal
        }
    }
}
