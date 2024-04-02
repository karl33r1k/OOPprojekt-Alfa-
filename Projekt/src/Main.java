import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sisesta nimi: ");
        String nimi = scanner.nextLine();
        System.out.println("Sisesta tüüp: ");
        String tüüp = scanner.nextLine();
        System.out.println("Sisesta raskusaste: ");
        String raskusaste = scanner.nextLine();

        Karakter karakter = karakterityyp(nimi, tüüp);
        List<Vaenlane> vaenlased = vaenlased(raskusaste);

        boolean mängustaatus = true;

        int skoor = 0;
        int raund = 0;

        while (karakter.getElud() > 0) {
            //Suvalise vaenlase saamine
            Random random = new Random();
            int suva = random.nextInt(0, vaenlased.size());
            Vaenlane vaenlane = vaenlased.get(suva);
            // määrab, kas esimesena alustab mängija või vaenlane
            int suva2 = random.nextInt(0, 2);
            if (suva2 == 0) { // kui on 0, alustab mängija
                while(vaenlane.getElud() > 0){
                    System.out.println(karakter);
                    if (karakter.getElud() > 0) {
                        System.out.println(vaenlane);
                        System.out.println("Valikud on: R - ründa, K - kaitse, P - parane");
                        System.out.println("Sisesta valik: ");
                        String valik = scanner.nextLine();
                        if (valik.equals("R")) {
                            karakter.rynda(vaenlane);
                        } else if (valik.equals("K")) {
                            karakter.kaitse(vaenlane);
                        } else if (valik.equals("P")) {
                            karakter.ravi();
                        }
                        //vaenlase kaik
                        vaenlane.tegevus(karakter);
                    }else {
                        mängustaatus = false;
                        System.out.println(nimi + " saavutas " + skoor + " punkti.");
                        break;
                    }
                }

            } else { // alustab vaenlane
                while(vaenlane.getElud() > 0) {
                    if (karakter.getElud() > 0) {
                        System.out.println(karakter);
                        vaenlane.tegevus(karakter);
                        System.out.println(vaenlane);
                        //Mangija kaik
                        System.out.println("Valikud on: R - ründa, K - kaitse, P - parane");
                        System.out.println("Sisesta valik: ");
                        String valik = scanner.nextLine();
                        if (valik.equals("R")) {
                            karakter.rynda(vaenlane);
                        } else if (valik.equals("K")) {
                            karakter.kaitse(vaenlane);
                        } else if (valik.equals("P")) {
                            karakter.ravi();
                        }

                    } else {
                        mängustaatus = false;
                        System.out.println(nimi + " saavutas " + skoor + " punkti.");
                        break;
                    }
                }
            }
            if (vaenlane.getElud() <= 0){
                System.out.println("Võitsid " + vaenlane.getNimi() + " vastu. Sind ootab ees uus vastane.");
            }
        }
    }
        public static Karakter karakterityyp(String nimi, String tüüp) { // määrab karakteri tüübi
            Karakter karakter = null;
            if (tüüp.equals("Sõdur")) {
                karakter = new Sõdur(nimi);
            } else if (tüüp.equals("Meedik")) {
                karakter = new Meedik(nimi);
            } else if (tüüp.equals("Vibulaskja")) {
                karakter = new Vibulaskja(nimi);
            }
            return karakter;
        }
        public static List<Vaenlane> vaenlased(String raskusaste) {
            List<Vaenlane> vaenlased = new ArrayList<Vaenlane>();
            if (raskusaste.equals("Kerge")) {
                vaenlased.add(new Nahkhiir("nahkhiir"));
                vaenlased.add(new Rott("rott"));
                vaenlased.add(new Vihmauss("vihmauss"));
            } else if (raskusaste.equals("Raske")) {
                vaenlased.add(new Raisakotkas("raisakotkas"));
                vaenlased.add(new Hunt("hunt"));
                vaenlased.add(new Draakon("draakon"));
            }
            return vaenlased;
        }


}

