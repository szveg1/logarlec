package pass.model.labyrinth;

import pass.model.CustomLogger;
import pass.model.human.Ember;

import java.util.*;
import java.util.logging.Level;

public class Ajto {
    private Szoba egyikOldal;
    private Szoba masikOldal;
    private boolean lathato;
    private Map<Szoba, Boolean> merreNyilik = new HashMap<>();
    private String nev;

    public Ajto(Szoba egyikOldal, Szoba masikOldal, String nev){
        this.egyikOldal = egyikOldal;
        this.masikOldal = masikOldal;
        merreNyilik.put(egyikOldal, true);
        merreNyilik.put(masikOldal, true);
        this.nev = nev;
    }

    public String toString(){
        return nev + " :Ajto";
    }
    public void hasznal(Ember e){
        if(!lathato) {
            CustomLogger.log(Level.WARNING,"Ajto nem lathato");
            return;
        }
        Szoba jelenlegiSzoba =  e.getJelenlegiSzoba();
        Szoba hovaMegy = (egyikOldal == jelenlegiSzoba) ? masikOldal : egyikOldal;
        if(merreNyilik.get(hovaMegy)){
            e.masikSzobabaLep(hovaMegy);
            jelenlegiSzoba.emberKivesz(e);
        }
        else CustomLogger.log(Level.WARNING,"Ajto nem nyilik ebbe az irányba");
    }

    public void setMerreNyilik(boolean b1, boolean b2) {
        merreNyilik.clear();
        merreNyilik.put(egyikOldal, b1);
        merreNyilik.put(masikOldal, b2);
    }

    public boolean getLathatosag(){
        return lathato;
    }
    public void setLathatosag(boolean a){
        lathato = a;
    }

    public void lathatosagValtoztass(){
        if (lathato){
            lathato = false;
        }
        else if (!lathato){
            lathato = true;
        }
    }

    public Szoba getSzomszed(Szoba sz){
        if (sz == egyikOldal){
            return masikOldal;
        }
        else if (sz == masikOldal){
            return egyikOldal;
        }
        return null;
    }

}