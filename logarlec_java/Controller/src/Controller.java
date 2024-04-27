import pass.model.item.*;
import pass.model.human.*;
import pass.model.labyrinth.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Controller {
    private static HashMap<String,Ember> emberMap;
    private static HashMap<String,Szoba> szobaMap;
    private static HashMap<String,Ajto> ajtoMap;
    private static HashMap<String,Targy> targyMap;

    public static void Play(int db) {}

    public static void Save(String file) {}

    public static void Load(String file) {
        Scanner scanner;
        try {
            scanner = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        StringBuilder sb = new StringBuilder();
        while(scanner.hasNext()){
            sb.append(scanner.nextLine());
        }

        String content = sb.toString();
        String[] lines = content.split(System.lineSeparator());

        sb = new StringBuilder();
        for(String line : lines){
            if(!line.isBlank()){
                sb.append(line);
            }
        }
    }
    

    public static void ajtoHasznalat(Ajto a, Ember e) {
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

    public static Ajto getAjto(String s){
        return ajtoMap.get(s);
    }

    public static Ember getEmber(String s){
        return emberMap.get(s);
    }

    public static Szoba getSzoba(String s) {
        return szobaMap.get(s);
    }

    public static Targy getTargy(String s) {
        return targyMap.get(s);
    }
}

