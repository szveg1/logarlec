package pass.controller;

import pass.model.human.Ember;
import pass.model.item.Targy;
import pass.model.labyrinth.Ajto;
import pass.model.labyrinth.Szoba;

public class Fuggvenyek {

    protected static void Play(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("Nem megfelelő számú argumentum: play <db>");
            return;
        }
        Controller.reset();
        int db = Integer.parseInt(cmd[1]);
        Controller.Play(db);
    }

    protected static void Save(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("Nem megfelelő számú argumentum: save <file>");
            return;
        }
        String file = cmd[1];
        Controller.Save(file);
    }

    protected static void Load(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("Nem megfelelő számú argumentum: load <file>");
            return;
        }
        Controller.reset();
        String file = cmd[1];
        Controller.Load(file);
    }

    protected static void AjtoHasznalat(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("Nem megfelelő számú argumentum: ajtohasznalat <ajto> <ember>");
            return;
        }
        Ajto a = Controller.getAjto(cmd[1]);
        Ember e = Controller.getEmber(cmd[2]);
        Controller.AjtoHasznalat(a, e);
    }

    protected static void TargyHasznal(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("Nem megfelelő számú argumentum: hasznal <targy> <ember>");
            return;
        }
        Targy t = Controller.getTargy(cmd[1]);
        Ember e = Controller.getEmber(cmd[2]);
        Controller.Hasznal(t, e);

    }

    protected static void TargyEldob(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("Nem megfelelő számú argumentum: targyeldob <targy> <ember>");
            return;
        }
        Targy t = Controller.getTargy(cmd[1]);
        Ember e = Controller.getEmber(cmd[2]);
        Controller.TargyEldob(t, e);
    }

    protected static void TargyFelvesz(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("Nem megfelelő számú argumentum: targyfelvesz <targy> <ember>");
            return;
        }
        Targy t = Controller.getTargy(cmd[1]);
        Ember e = Controller.getEmber(cmd[2]);
        Controller.TargyFelvesz(t, e);
    }

    protected static void Random(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("Nem megfelelő számú argumentum: random <true/false>");
            return;
        }
        boolean b = Boolean.parseBoolean(cmd[1]);
        Controller.Random(b);
    }

    protected static void Tick(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("Nem megfelelő számú argumentum: tick <n>");
            return;
        }
        int n = 1;
        if (cmd.length > 1)
            n = Integer.parseInt(cmd[1]);
        Controller.Tick(n);
    }

    public static void InfoEmber(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("Nem megfelelő számú argumentum: infoember <ember>");
            return;
        }
        Ember e = Controller.getEmber(cmd[1]);
        if (!e.getEletbenVan()) {
            System.out.println("nincs ilyen ember: " + cmd[1]);
        } else {
            Controller.InfoEmber(e);
        }
    }

    public static void InfoSzoba(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("Nem megfelelő számú argumentum: infoszoba <szoba>");
            return;
        }
        Szoba sz = Controller.getSzoba(cmd[1]);
        if (sz == null) {
            System.out.println("nincs ilyen szoba: " + cmd[1]);
        } else {
            Controller.InfoSzoba(sz);
        }
    }

    public static void reset(String[] cmd) {
        if (cmd.length != 1)
            return;

        Controller.reset();
    }

    public static void SzobaFeloszt(String[] cmd) {
        if (cmd.length != 2) {
            System.out.println("Nem megfelelő számú argumentum: szobafeloszt <szoba>");
            return;
        }
        Szoba sz = Controller.getSzoba(cmd[1]);
        Controller.SzobaFeloszt(sz);
    }

    public static void SzobaOsszevon(String[] cmd) {
        if (cmd.length != 3) {
            System.out.println("Nem megfelelő számú argumentum: szobaosszevon <szoba1> <szoba2>");
            return;
        }
        Szoba sz1 = Controller.getSzoba(cmd[1]);
        Szoba sz2 = Controller.getSzoba(cmd[2]);
        Controller.SzobaOsszevon(sz1, sz2);
    }

}
