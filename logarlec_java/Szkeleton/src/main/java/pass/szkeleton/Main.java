package pass.szkeleton;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            TargyFelveszTest tft = new TargyFelveszTest();
            tft.setUp();
            tft.test();
            TargyEldobTest tet = new TargyEldobTest();
            tet.test();
    }
}