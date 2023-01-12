package Affichage;

import Fichiers.*;
import Recherche.*;
import Utilisateurs.Compte;
import Utilisateurs.Particulier;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Accueil {

    //Mise en forme par la couleur
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    //Les strings de réponses des menus
    static String entrerABC = "Veuillez entrer les lettres a, b ou c uniquement :";
    static String entrerABCD = "Veuillez entrer les lettres a, b, c ou d uniquement :";
    static String entrer1234 = "Veuillez entrer un chiffre entre 1 et 4 uniquement :";
    static String erreurChoix = "Saisie incorrecte !";

    public static void afficherNormal(Object invite) {
        System.out.println(invite);
    }

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

    static boolean quitter = false;
    static public MenuAccueil mA = new MenuAccueil();

    //TODO: discuter l'emplacement des URI des fichiers.txt
    public static File comptesTxt = new File(System.getProperty("user.dir") + "\\src\\comptes.txt");
    public static File annuaireTxt = new File(System.getProperty("user.dir") + "\\src\\annuaire.txt");

    static public HashMap<String, Compte> comptes = new HashMap<>();
    static public HashMap<String, Particulier> annuaire = new HashMap<>();


    //ajout du formateur de date
    static public SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

    static public Scanner scannerClavier = new Scanner(System.in);

    /**
     * Méthode principale
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception, IOException, ParseException {

        EcritureFichier ef = new EcritureFichier();
        LectureFichier lf = new LectureFichier();


        //lecture des fichiers
        lf.lectureComptes(comptesTxt);
        comptes = lf.getComptes();
        lf.lectureAnnuaire(annuaireTxt);
        annuaire = lf.getAnnuaire();


        /* boucle programme */
        while (!quitter) {
            mA.ouvrirMenuAccueil();
        }
        System.out.println("Merci d'avoir utlisé le programme");
        scannerClavier.close();
    }
}
