import pass.model.item.*;
import pass.model.human.*;
import pass.model.labyrinth.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.*;

public class Controller {
    public static void Play(int db) {}

    public static void Save(String file) {}

    public static void Load(String file) {}

    public static void Lepes(Ajto a, Ember e) {
        a.hasznal(e);
    }

    public static void TargyFelvesz(Targy t, Ember e) {
        e.targyatFelvesz(t);
    }

    public static void Hasznal(Targy t, Hallgato h) {
        h.targyatHasznal(t);
    }

    public static void TargyEldob(Targy t, Ember e) {
        e.targyatEldob(t);
    }

    //public void InfoEmber(Ember e) {}

    public static void Random() {}

    public static void Tick() {}
}

