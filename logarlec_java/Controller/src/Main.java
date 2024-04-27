import java.util.Scanner;

public class Main extends Fuggvenyek {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line == null) break;
            String[] cmd = line.split(" ");

            if (cmd[0].equals("Play"))
                Fuggvenyek.Play(cmd);
            else if(cmd[0].equals("Save"))
                Fuggvenyek.Save(cmd);
            else if(cmd[0].equals("Load"))
                Fuggvenyek.Load(cmd);
            else if(cmd[0].equals("Hasznal"))
                Fuggvenyek.TargyHasznal(cmd);
            else if(cmd[0].equals("Targyfelvesz"))
                Fuggvenyek.TargyFelvesz(cmd);
            else if(cmd[0].equals("Targyfelvesz"))
                Fuggvenyek.TargyFelvesz(cmd);
        }
        scanner.close();

        }
    }