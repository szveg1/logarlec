package pass.controller;

import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;

import java.io.Console;

public class Fuggvenyek {

    protected static void Play(String[] cmd) {
        Controller.reset();
        int db = Integer.parseInt(cmd[1]);
        Controller.Play(db);
    }

    protected static void Save(String[] cmd) {
        String file = cmd[1];
        Controller.Save(file);
    }

    protected static void Load(String[] cmd) {
        Controller.reset();
        String file = cmd[1];
        Controller.Load(file);
    }

    protected static void AjtoHasznalat(String[] cmd) {
        Ajto a = Controller.getAjto(cmd[1]);
        Ember e = Controller.getEmber(cmd[2]);
        Controller.AjtoHasznalat(a, e);
    }

    protected static void TargyHasznal(String[] cmd) {
        Targy t = Controller.getTargy(cmd[1]);
        Ember e = Controller.getEmber(cmd[2]);
        Controller.Hasznal(t, e);

    }

    protected static void TargyEldob(String[] cmd) {
        Targy t = Controller.getTargy(cmd[1]);
        Ember e = Controller.getEmber(cmd[2]);
        Controller.TargyEldob(t, e);
    }

    protected static void TargyFelvesz(String[] cmd) {
        Targy t = Controller.getTargy(cmd[1]);
        Ember e = Controller.getEmber(cmd[2]);
        Controller.TargyFelvesz(t, e);
    }

    protected static void Random(String[] cmd) {
        boolean b = Boolean.parseBoolean(cmd[1]);
        Controller.Random(b);
    }

    protected static void Tick(String[] cmd) {
        Controller.Tick();
    }

    public static void InfoEmber(String[] cmd) {
        Ember e = Controller.getEmber(cmd[1]);
        if (!e.getEletbenVan()) {
            System.out.println("nincs ilyen ember: " + cmd[1]);
        } else {
            Controller.InfoEmber(e);
        }
    }

    public static void InfoSzoba(String[] cmd) {
        Szoba sz = Controller.getSzoba(cmd[1]);
        if (sz == null) {
            System.out.println("nincs ilyen szoba: " + cmd[1]);
        } else {
            Controller.InfoSzoba(sz);
        }
    }
    public static void reset(String[] cmd){
        Controller.reset();
    }

    public static void SzobaFeloszt(String[] cmd) {
        Szoba sz = Controller.getSzoba(cmd[1]);
        Controller.SzobaFeloszt(sz);
    }

    public static void SzobaOsszevon(String[] cmd) {
        Szoba sz1 = Controller.getSzoba(cmd[1]);
        Szoba sz2 = Controller.getSzoba(cmd[2]);
        Controller.SzobaOsszevon(sz1, sz2);
    }

}
