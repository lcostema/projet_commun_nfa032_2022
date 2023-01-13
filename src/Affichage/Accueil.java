package Affichage;

import Fichiers.*;
import Utilisateurs.*;

import java.io.File;
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
    public static final File FICHIER_COMPTES = new File(System.getProperty("user.dir") + "\\src\\comptes.txt");
    public static final File FICHIER_ANNUAIRE = new File(System.getProperty("user.dir") + "\\src\\annuaire.txt");

    static public HashMap<String, Compte> comptes = new HashMap<>();
    static public HashMap<String, Particulier> annuaire = new HashMap<>();

    //ajout du formateur de date
    static public SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

    static public Scanner scannerClavier = new Scanner(System.in);

    //TODO: Remplacer les system out print dans les autres classes par les fonctions d'affichage du programme/

    /**
     * Méthode principale
     * @param args Pas d'argument de lancement
     * @throws Exception Exception
     */
    public static void main(String[] args) throws Exception {
        LectureFichier lf = new LectureFichier();

        // Lecture des fichiers
        lf.lectureAnnuaire(FICHIER_ANNUAIRE);
        annuaire = lf.getAnnuaire();
        lf.lectureComptes(FICHIER_COMPTES);
        comptes = lf.getComptes();

        // Boucle programme
        while (!quitter) {
            MenuAccueil.ouvrirMenuAccueil();
        }
        System.out.println("Merci d'avoir utilisé le programme");
        scannerClavier.close();
    }
}