package Affichage;

import Fichiers.*;
import Recherche.*;
import Utilisateurs.Compte;
import Utilisateurs.Particulier;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
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

    public static void afficherYellow(Object invite) {
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
    static MenuAccueil mA = new MenuAccueil();

    //TODO: discuter l'emplacement des URI des fichiers.txt
    static File comptesTxt = new File(System.getProperty("user.dir") + "\\src\\comptes.txt");
    static File annuaireTxt = new File(System.getProperty("user.dir") + "\\src\\annuaire.txt");

    //ajout du formateur de date
    static public SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

    static public Scanner scannerClavier = new Scanner(System.in);

    /**
     * Méthode principale
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        EcritureFichier ef = new EcritureFichier();
        LectureFichier lf = new LectureFichier();

//lecture des fichiers
//        lf.lectureComptes(comptesTxt);
//        lf.lectureAnnuaire(annuaireTxt);

//Test Lecture des fichiers
//        System.out.println(dateFormatter.format(lf.getAnnuaire().get("cocobello@cnam.net").getDateAjout()));
//        System.out.println(lf.getAnnuaire().get("cocobello@cnam.net").getDateAjout());
//        System.out.println(lf.getComptes().get("tototutu@cnam.fr").getMotDePasse());

//Test Ecriture des fichiers
//        lf.getComptes().put("mailDeTest@test.com", new Compte("mailDeTest@test.com", "mdpTest", Compte.Role.Administrateur));
//        lf.getAnnuaire().put("mailDeTest@test.com", new Particulier("mailDeTest@test.com", "mdpTest","Dupont","Jean","1 place de la gare 75000 Paris",dateFormatter.parse("24/06/2000") ,dateFormatter.parse("03/07/2001"),dateFormatter.parse("05/07/2001"), Particulier.Profil.Direction));
//        ef.ecrireComptes(lf.getComptes(), comptesTxt);
//        ef.ecrireAnnuaire(lf.getAnnuaire(), annuaireTxt);


// Test Recherche dans annuaire
//        Recherche recherche = new Recherche();
//        //par profil
//        recherche.rechercherDansAnnuaire(lf.getAnnuaire(), Particulier.Profil.Auditeur);
//        System.out.println(recherche.rechercherDansAnnuaire(lf.getAnnuaire(), "tototutu@cnam.fr"));
//        lf.getAnnuaire().put("encoreUnTest@test.com", new Particulier("encoreUnTest@test.com", "mdpTest","Dupont","michel","3 rue du parc 44000 Nantes",dateFormatter.parse("24/06/2000") ,dateFormatter.parse("03/07/2001"),dateFormatter.parse("05/07/2001"), Particulier.Profil.Enseignant));
//        ef.ecrireAnnuaire(lf.getAnnuaire(), annuaireTxt);
//        System.out.println(recherche.rechercherDansAnnuaire(lf.getAnnuaire(), "Dupont"));



        /** boucle programme */
        while (!quitter){
            mA.afficherMenuAccueil();
        }
        System.out.println("Merci d'avoir utliser le programme");
    }
}
