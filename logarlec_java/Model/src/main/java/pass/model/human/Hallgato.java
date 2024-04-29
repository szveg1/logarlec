package pass.model.human;

import pass.model.CustomLogger;
import pass.model.CustomRecordFormatter;
import pass.model.item.*;
import pass.model.labyrinth.Labirintus;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

/**
 * A Hallgató osztály felelős a játékos karakterének kezeléséért a labirintusban. Ez az
 * osztály rögzíti és kezeli a hallgatóval kapcsolatos információkat, a felvett tárgyak
 * listáját. Emellett felelős a hallgató cselekedeteinek végrehajtásáért, beleértve a tárgyak
 * felvételét és letételét, valamint a szobák közötti mozgást. A Hallgató osztály biztosítja
 * a felhasználói interakciót a játék során.
 */
public class Hallgato extends Ember {

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

    @Override
    public boolean getLepett() {return lepett;}

    /**
     * A függvény meglátogatja a megadott logarlécet
     * és a meghívja a labirintus játékNyert függvényét
     * amivel véget vet a játéknak
     * @param logarlec -  a logarléc amit meglátogat
     */
    @Override
    public void visit(Logarlec logarlec) {
        super.visit(logarlec);
        Labirintus.jatekNyert();
    }

    /**
     * A függvény meglátogatja a megadott tvsz-t
     * és megvédi a hallgatót ha a tvsz még működőképes
     * @param tvsz - a tbsz amit meglátogat
     */
    @Override
    public void visit(TVSZ tvsz) {
        tudVedekezni = tvsz.hasznalhatoE();
        CustomLogger.info(this + (tudVedekezni ? " tud védekezni a " + tvsz + " segítségével." : " nem tud védekezni, mert a " + tvsz + " már nem használható."));
    }

    /**
     * A függvény meglátogatja a megadott poharat
     * és megvédi a hallgatót ha
     * a pohár még nem járt le
     * @param pohar - a pohár amit meglátogat
     */
    @Override
    public void visit(Pohar pohar) {
        tudVedekezni = pohar.hasznalhatoE();
        CustomLogger.info(this + (tudVedekezni ? " tud védekezni a " + pohar + " segítségével." : " nem tud védekezni, mert a " + pohar + " már nem használható."));
    }

    /**
     * A függvény meglátogatja a megadott ronygot
     * és megvédi a hallgatót a szükség esetén
     * @param rongy - a rongy amit melátogat
     */
    @Override
    public void visit(Rongy rongy) {
        tudVedekezni = rongy.hasznalhatoE();
        CustomLogger.info(this + (tudVedekezni ? " tud védekezni a " + rongy + " segítségével." : " nem tud védekezni, mert a " + rongy + " már nem használható."));
    }

    /**
     * A függvény megmondja hogy van e még hely a hallgató inventoryban
     * @return boolean - igaz vagy hamis hogy az inventory tele van
     */
    @Override
    public boolean inventoryTeleE() {
        int MAX_INVENTORY_MERET = 5;
        CustomLogger.info(this +  ((inventory.size() >= MAX_INVENTORY_MERET) ? " inventoryja tele van." : " inventoryja nincs tele."));
        return inventory.size() >= MAX_INVENTORY_MERET;
    }

    /**
     * A függvény alkalmazza az oktató támadásából
     * eredő hatásokat a hallgatóra, majd ellenőrzi,
     * hogy van-e védelme ezekkel szemben.
     * @param oktato - a támadó oktató
     */
    public Hallgato tamadasElszenved(Oktato oktato) {
        int hasznalniKivant = 0;
        for(Targy targy : inventory) {
            targy.accept(this);
            if(tudVedekezni){
                inventory.get(hasznalniKivant).hasznal(oktato);
                CustomLogger.info(this + " a " + inventory.get(hasznalniKivant) + "-val védekezett.");
                break;
            }
            hasznalniKivant++;
        }
        if(!tudVedekezni){
            CustomLogger.info(this + " nem tudott védekezni, ezért meghalt.");
            meghal();
        }
        return this;
    }

    /**
     * A függvény a hallgatót megöli
     */
    private void meghal() {
        setEletbenVan(false);
    }

    /**
     * A függvény a hallgatónál lévő árgyakonmeghívja a tick függvény.
     * Tehát a tárgyakon is lép az idő
     */
    @Override
    public void tick() {
        super.tick();
        for(Targy t : inventory){
            t.tick();
        }
    }
}
