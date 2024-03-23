package pass.szkeleton;


import pass.model.human.*;
import pass.model.labyrinth.*;
import pass.model.item.*;



public class Oktato_tamad {
        private static Labirintus labirintus;
        private static Szoba szoba;
        private static Oktato oktato;
        private static Hallgato hallgato;


        public static void init_test() {
            labirintus = new Labirintus();
            szoba = new Szoba(10);
            oktato = new Oktato();
            hallgato = new Hallgato();

            labirintus.addSzoba(szoba);
            szoba.emberBetesz(oktato);
            szoba.emberBetesz(hallgato);
        }


        public void Oktato_tamad() {
            // Act
            oktato.hallgatotMegtamad(hallgato);

            // Assert

            //assertTrue(hallgato.meghal()); // A hallgató meghal
        }
}

