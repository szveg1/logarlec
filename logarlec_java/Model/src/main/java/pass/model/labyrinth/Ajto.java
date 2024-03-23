package pass.model.labyrinth;

import pass.model.human.Ember;

import java.util.*;

public class Ajto {
    private Szoba egyikOldal;
    private Szoba masikOldal;
    private boolean lathato;
    private Map<Szoba, Boolean> merreNyilik = new HashMap<>();

    public Ajto(Szoba egyikOldal, Szoba masikOldal){
        this.egyikOldal = egyikOldal;
        this.masikOldal = masikOldal;
        merreNyilik.put(egyikOldal, true);
        merreNyilik.put(masikOldal, true);
    }

    public void hasznal(Ember e){
        Szoba jelenlegiSzoba =  e.getJelenlegiSzoba();
        Szoba hovaMegy = (egyikOldal == jelenlegiSzoba) ? masikOldal : egyikOldal;
        if(merreNyilik.get(hovaMegy)){
            e.masikSzobabaLep(hovaMegy);
        }
    }
}