package pass.controller;

import pass.model.human.Ember;

import java.util.ArrayList;
import java.util.List;

public class EmberQueue {
    private List<Ember> queue = new ArrayList<>();
    private int idx = 0;

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
        for(Ember e : queue) {
            e.setLepett(false);
        }
    }

    public void clear(){
        queue.clear();
    }

    public void next() {
        idx = (idx + 1) % queue.size();
    }

}
