package Affichage;

import Fichiers.*;
import Utilisateurs.Compte;
import Utilisateurs.Particulier;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class Accueil {

    //Mise en forme par la couleur
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void afficherCyan(Object invite) {
        System.out.println(ANSI_CYAN + invite + ANSI_RESET);
    }
    public static void afficherJaune(Object invite) {
        System.out.println(ANSI_YELLOW + invite + ANSI_RESET);
    }
    public static void afficherRouge(Object invite) {
        System.out.println(ANSI_RED + invite + ANSI_RESET);
    }
    public static void afficherVert(Object invite) {
        System.out.println(ANSI_GREEN + invite + ANSI_RESET);
    }


    //Les strings de réponses des menus
    static String entrerABC = "Veuillez entrer les lettres a, b ou c uniquement :";
    static String entrerABCD = "Veuillez entrer les lettres a, b, c ou d uniquement :";
    static String entrer123 = "Veuillez entrer un chiffre entre 1 et 3 uniquement :";
    static String entrer1234 = "Veuillez entrer un chiffre entre 1 et 4 uniquement :";
    static String erreurChoix = "Saisie incorrecte !";

    static boolean quitter = false;
    protected static MenuAccueil mA = new MenuAccueil();

    static HashMap<String, Compte> comptes = new HashMap<>();
    static HashMap<String, Particulier> annuaire = new HashMap<>();

    static Scanner scannerClavier = new Scanner(System.in);

    /**
     * Méthode principale
     * @param args
     * @throws Exception
     */
    protected static LectureFichier lf = new LectureFichier();

    public static void main(String[] args) throws Exception {

        EcritureFichier ef = new EcritureFichier();

        lf.lectureComptes(new File(Path.of(".").toRealPath() + "\\src\\comptes.txt"));
//        System.out.println(lf.getComptes().get("admin@cnam.fr").getRole());

        /** boucle programme */
        while (!quitter){
            mA.afficherMenuAccueil();
        }
        System.out.println("Merci d'avoir utlisé le programme");
    }
}
