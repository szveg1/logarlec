import pass.model.human.*;
import pass.model.labyrinth.*;

public class Fuggvenyek {

    protected static void Play(String[] cmd) {
        int db = Integer.parseInt(cmd[1]);
        Controller.Play(db);
    }

    protected static void Save(String[] cmd) {
        String file = cmd[1];
        Controller.Save(file);
    }

    protected static void Load(String[] cmd) {
        String file = cmd[1];
        Controller.Load(file);
    }

    protected static void Lepes(String[] cmd) {
        Ajto a = cmd[1];
        Ember e = Labirintus.Keres(cmd[2]);
        Controller.Lepes(a, e);
    }
}
