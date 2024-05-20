package pass.controller;

import pass.model.TargyVisitor;
import pass.model.human.Ember;
import pass.model.item.Logarlec;
import pass.model.item.Targy;
import pass.model.labyrinth.Labirintus;

import java.util.ArrayList;
import java.util.List;

public class EmberQueue implements TargyVisitor {
    private List<Ember> queue = new ArrayList<>();
    private int idx = 0;
    private boolean end = false;

    public void add(Ember e) {
        queue.add(e);
    }

    public Ember getNext() {
//        for(Ember e : queue) {
//            if(!e.getLepett()) {
//                return e;
//            }
//        }
//        return null;
        return queue.get(idx);
    }

    public void reset() {
        for (Ember e : queue) {
            e.setLepett(false);
        }
        end = false;
        idx = 0;
    }

    public void clear(){
        queue.clear();
    }

    public int size() {return queue.size();}

    public void next() {
        idx = (idx + 1) % queue.size();
        if (idx == 0) {
            Controller.Tick(1);
            reset();
        }
    }

    public boolean checkForWin(Ember ember) {
        for (Targy targy : ember.getItems()) {
            targy.accept(this);
        }
        return end;
    }

    public boolean checkForLoss() {
        if(Labirintus.getInstance().getTimeLeft() == 0){
            return true;
        }

        boolean vanvalakieletben = false;
        for (Ember ember : queue) {
            if(ember.getEletbenVan()){
                vanvalakieletben = true;
            }
        }
        return !vanvalakieletben;
    }

    @Override
    public void visit(Logarlec logarlec) {
        end = true;
    }

}
