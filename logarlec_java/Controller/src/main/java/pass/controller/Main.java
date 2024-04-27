package pass.controller;

import java.util.Scanner;

public class Main extends Fuggvenyek {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line == null) break;
            String[] cmd = line.split(" ");

            switch(cmd[0]) {
                case "play":
                    Fuggvenyek.Play(cmd);
                    break;
                case "save":
                    Fuggvenyek.Save(cmd);
                    break;
                case "load":
                    Fuggvenyek.Load(cmd);
                    break;
                case "ajtohasznalat":
                    Fuggvenyek.AjtoHasznalat(cmd);
                    break;
                case "hasznal":
                    Fuggvenyek.TargyHasznal(cmd);
                    break;
                case "targyfelvesz":
                    Fuggvenyek.TargyFelvesz(cmd);
                    break;
                case "targyeldob":
                    Fuggvenyek.TargyEldob(cmd);
                    break;
                case "tick":
                    Fuggvenyek.Tick(cmd);
                    break;
                default:
                    // Handle unknown command
                    break;
            }
        }
        scanner.close();

        }
    }