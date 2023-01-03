import Utilisateurs.Particulier;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Gestion {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    static Scanner scannerClavier = new Scanner(System.in);
    static String entrerABC = "Veuillez entrer les lettres a, b ou c uniquement :";
    static String entrerABCD = "Veuillez entrer les lettres a, b, c ou d uniquement :";
    static String entrer123 = "Veuillez entrer les chiffres 1, 2 ou 3 uniquement :";
    static String erreurChoix = "Saisie incorrecte !";
    static Annuaire annuaire = new Annuaire();

//    public static final String ANSI_BLACK = "\u001B[30m";
//    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
//    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
//    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
//    public static final String ANSI_YELLOW = "\u001B[33m";
//    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
//    public static final String ANSI_BLUE = "\u001B[34m";
//    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
//    public static final String ANSI_PURPLE = "\u001B[35m";
//    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
//    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
//    public static final String ANSI_WHITE = "\u001B[37m";
//    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    static int chiffre = 0;

    public static void afficherCyan(Object invite) {
        System.out.println(ANSI_CYAN + invite + ANSI_RESET);
    }

    public static void afficherRouge(Object invite) {
        System.out.println(ANSI_RED + invite + ANSI_RESET);
    }

    public static void afficherVert(Object invite) {
        System.out.println(ANSI_GREEN + invite + ANSI_RESET);
    }

    public static int lireInt() {
        return scannerClavier.nextInt();
    }

    public static String lireString() {
        return scannerClavier.next();
    }

    public static void main(String[] args) throws Exception {
        Annuaire.chargerPersonne(new File(Annuaire.dossierLocal + "\\personne.txt"));
        Annuaire.chargerCompte(new File(Annuaire.dossierLocal + "\\compte.txt"));

        afficherCyan("""
                                    
                Bienvenue dans l’Annuaire NFA032
                                    
                Administrateur :
                   1. Ajouter une personne
                                    
                Particulier :
                   2. Rechercher un ou des particuliers
                   3. Modifier mes informations personnelles
                """);
        afficherVert("Choisir (taper le chiffres puis Enter) :");

        while (scannerClavier.hasNext()) {
            if (scannerClavier.hasNextInt()) {
                try {
                    chiffre = lireInt();
                } catch (Exception exception) {
                    afficherRouge(erreurChoix);
                    afficherRouge(entrer123);
                    return;
                }
                if (chiffre == 1 || chiffre == 2 || chiffre == 3) {
                    switch (chiffre) {
                        // 1. Ajouter une personneOld
                        case 1 -> menuAdmin();
                        // 2. Rechercher un ou des particuliers
                        case 2 -> menuChercherPersonne();
                        // 3. Modifier mes informations personnelles
                        case 3 -> afficherVert("Choix 3..."); //annuaire.modifierPersonne();
                        default -> {
                        }
                    }
                } else {
                    afficherRouge(entrer123);
                }
            } else {
                afficherRouge(erreurChoix);
                afficherRouge(entrer123);
                scannerClavier.next();
            }
        }
    }

    public static void menuAdmin() throws Exception {
        //todo: passer par identification

        afficherCyan("""                                
                Ajouter une utilisateur (Administrateur)
                   a. Ajouter un Admin
                   b. Ajouter un Particulier
                   c. Retour au menu principal
                """);
        afficherVert("Choisir (taper la lettre puis Enter) :");

        while (scannerClavier.hasNext()) {
            String lettre = lireString();
            if (Objects.equals(lettre, "a")
                    || Objects.equals(lettre, "b") || Objects.equals(lettre, "c")) {
                switch (lettre) {
                    // a. Ajouter un Admin
                    case "a" -> // todo : annuaire.ajouterPersonne(new Admin(email, motdepasse));
                            afficherCyan("Choix a...");
                    // b. Ajouter un Particulier
                    case "b" ->  // todo : annuaire.ajouterPersonne(new Particulier(email, motdepasse, nom, prenom, adressePostale, dateNaissance, dateAjout, dateMaj, profil));
                            afficherCyan("Choix b...");
                    // c. Retour au menu principal
                    case "c" -> main(new String[]{"a"});
                    default -> {
                    }
                }
            } else {
                afficherRouge(entrerABC);
            }
        }
    }

    public static void trouverParNom() {
        String prenom, nom;
        afficherCyan("Prénom ?");
        prenom = lireString();
        afficherCyan("Nom ?");
        nom = lireString();
        Particulier particulier = new Particulier(null, null, nom, prenom, null, null, null, null, null);
        afficherCyan("'" + particulier + "'");
        afficherCyan(annuaire.trouverPersonne(particulier));
        if (annuaire.trouverPersonne(particulier) == null)
            afficherCyan("Pas dans l'annuaire !");
        else
            afficherCyan(particulier + " : " + annuaire.trouverPersonne(particulier));
    }

    public static void menuChercherPersonne() throws Exception {
        afficherCyan("""                                
                Rechercher un ou des particuliers
                   a. Par nom
                   b. Par email
                   c. Par profil
                   d. Retour au menu principal
                """);
        afficherVert("Choisir (taper la lettre puis Enter) :");

        while (scannerClavier.hasNext()) {
            String lettre = lireString();
            if (Objects.equals(lettre, "a") || Objects.equals(lettre, "b")
                    || Objects.equals(lettre, "c") || Objects.equals(lettre, "d")) {
                switch (lettre) {
                    // a. Par nom
                    case "a" -> chercherParNom(); //Annuaire.testsMapPersonne();
                    // b. Par email
                    case "b" -> chercherParEmail(); //annuaire.menuChercherPersonne(profil);
                    // c. Par profil
                    case "c" -> chercherParProfil();
                    // d. Retour au menu principal
                    case "d" -> main(new String[]{"a"});
                    default -> {
                    }
                }
            } else {
                afficherRouge(erreurChoix);
                afficherRouge(entrerABCD);
            }
        }
    }

    public static void chercherParNom() throws Exception {
        afficherCyan("Entrer le nom à rechercher :");
        String value = lireString();
        Annuaire.recherchePersonnes("nom", value);
        menuChercherPersonne();
    }

    public static void chercherParEmail() throws Exception {
        afficherCyan("Entrer l'email à rechercher :");
        String value = lireString();
        Annuaire.recherchePersonnes("email", value);
        menuChercherPersonne();
    }

    public static void chercherParProfil() throws Exception {
        afficherCyan("""                                
                Rechercher par profil
                   1. Auditeur
                   2. Enseignant
                   3. Direction
                """);
        while (scannerClavier.hasNext()) {
            try {
                chiffre = lireInt();
            } catch (Exception exception) {
                afficherRouge(erreurChoix);
                afficherRouge(entrer123);
                return;
            }
            if (chiffre == 1 || chiffre == 2 || chiffre == 3) {
                switch (chiffre) {
                    // 1. Auditeur
                    case 1 -> {
                        Annuaire.recherchePersonnes("profil", "auditeur");
                        menuChercherPersonne();
                    }
                    // 2. Enseignant
                    case 2 -> {
                        Annuaire.recherchePersonnes("profil", "enseignant");
                        menuChercherPersonne();
                    }
                    // 3. Direction
                    case 3 -> {
                        Annuaire.recherchePersonnes("profil", "direction");
                        menuChercherPersonne();
                    }
                    default -> {
                    }
                }
            } else {
                afficherRouge(entrer123);
            }
        }
    }
}
