import GestionFiles.GestionFiles;
import Utilisateurs.Particulier;
import Utilisateurs.Utilisateur;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Gestion {

    static Scanner scannerClavier = new Scanner(System.in);
    static String entrerABC = "Veuillez entrer les lettres a, b ou c uniquement :";
    static String entrerABCD = "Veuillez entrer les lettres a, b, c ou d uniquement :";
    static String entrer123 = "Veuillez entrer les chiffres 1, 2 ou 3 uniquement :";
    static String erreurChoix = "Saisie incorrecte !";
    static Annuaire annuaire = new Annuaire();

    public static void afficher(Object invite) {
        System.out.println(invite);
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

//#####################################################################################################################################################################
//######################################################## Test Guillaume #############################################################################################
//#####################################################################################################################################################################


        //Création du Hashmap des comptes qui contient tous les objets Utilisateurs
        GestionFiles gf = new GestionFiles();
        HashMap<String, Utilisateur> comptes = new HashMap<>();
        comptes = gf.chargerUtilisateurs(new File(Annuaire.dossierLocal + "\\compte.txt"));
        comptes.forEach((u, utilisateur) -> System.out.println(u + " ==> " + utilisateur.getEmail() + " " + utilisateur.getMotdepasse() + " " + utilisateur.getRole()));

        //On ajoute un Utilisateur au Hasmap des comptes et on le sauvegarde
        Utilisateur u = new Utilisateur("monAdminTest@test.com","pwTest", Utilisateur.Role.Administrateur);
        comptes.put(u.getEmail(),u);
        gf.sauvergarderUtilisateurs(comptes,new File(Annuaire.dossierLocal + "\\comptes.Guillaume.txt"));



        //Création du Hashmap des Particuliers (annuaire) qui contient tous les objets Particuliers
        HashMap<String, Particulier> annuaire = new HashMap<>();
        annuaire = gf.chargerParticuliers(new File(Annuaire.dossierLocal + "\\personne.txt"));
        annuaire.forEach((p, particulier) -> System.out.println(p + particulier.toString()));

        //on ajoute un particulier et on sauvegarde
        Date today = new Date();
        Particulier p = new Particulier("particulierTest@test.com","pwTest","parti","Culier","12 rue des sables 44000 Nantes",new Date("18/05/2000"), today, today, Particulier.Profil.Auditeurs);
        annuaire.put(p.getEmail(),p);
        gf.sauvegarderParticuliers(annuaire,new File(Annuaire.dossierLocal + "\\annuaire.Guillaume.txt") );

//#####################################################################################################################################################################
//######################################################## Fin test Guillaume #########################################################################################
//#####################################################################################################################################################################






        afficher("""
                                    
                Bienvenue dans l’Annuaire NFA032
                                    
                Administrateur :
                   1. Ajouter une personneOld
                                    
                Particulier :
                   2. Rechercher un ou des particuliers
                   3. Modifier mes informations personnelles
                """);
        afficher("Choisir (taper le chiffres puis Enter) :");

        while (scannerClavier.hasNext()) {
            if (scannerClavier.hasNextInt()) {
                int chiffre = lireInt();
                if (chiffre == 1 || chiffre == 2 || chiffre == 3) {
                    switch (chiffre) {
                        // 1. Ajouter une personneOld
                        case 1 -> menuAdmin();
                        // 2. Rechercher un ou des particuliers
                        case 2 -> menuChercherPersonne();
                        // 3. Modifier mes informations personnelles
                        case 3 -> afficher("Choix 3..."); //annuaire.modifierPersonne();
                        default -> {
                        }
                    }
                } else {
                    afficher(entrer123);
                }
            } else {
                afficher(erreurChoix);
                afficher(entrer123);
                scannerClavier.next();
            }
        }
    }

    public static void menuAdmin() throws Exception {
        //todo: passer par identification

        afficher("""
                                
                Ajouter une utilisateur (Administrateur)
                   a. Ajouter un Admin
                   b. Ajouter un Particulier
                   c. Retour au menu principal
                """);
        afficher("Choisir (taper la lettre puis Enter) :");

        while (scannerClavier.hasNext()) {
            String lettre = lireString();
            if (Objects.equals(lettre, "a")
                    || Objects.equals(lettre, "b") || Objects.equals(lettre, "c")) {
                switch (lettre) {
                    // a. Ajouter un Admin
                    case "a" -> // todo : annuaire.ajouterPersonne(new Admin(email, motdepasse));
                            afficher("Choix a...");
                    // b. Ajouter un Particulier
                    case "b" ->  // todo : annuaire.ajouterPersonne(new Particulier(email, motdepasse, nom, prenom, adressePostale, dateNaissance, dateAjout, dateMaj, profil));
                            afficher("Choix b...");
                    // c. Retour au menu principal
                    case "c" -> main(new String[]{"a"});
                    default -> {
                    }
                }
            } else {
                afficher(entrerABC);
            }
        }
    }

    public static void trouverParNom() {
        String prenom, nom;
        afficher("Prénom ?");
        prenom = lireString();
        afficher("Nom ?");
        nom = lireString();
        Particulier particulier = new Particulier(null, null, nom, prenom, null, null, null, null, null);
        afficher("'" + particulier + "'");
        afficher(annuaire.trouverPersonne(particulier));
        if (annuaire.trouverPersonne(particulier) == null)
            afficher("Pas dans l'annuaire !");
        else
            afficher(particulier + " : " + annuaire.trouverPersonne(particulier));
    }

    public static void menuChercherPersonne() throws Exception {
        afficher("""
                                
                Rechercher un ou des particuliers
                   a. Par nom
                   b. Par email
                   c. Par profil
                   d. Retour au menu principal
                """);
        afficher("Choisir (taper la lettre puis Enter) :");

        while (scannerClavier.hasNext()) {
            String lettre = lireString();
            if (Objects.equals(lettre, "a") || Objects.equals(lettre, "b")
                    || Objects.equals(lettre, "c") || Objects.equals(lettre, "d")) {
                switch (lettre) {
                    // a. Par nom
                    case "a" -> {
                        // seulement pour tester;
                        // le chargement des fichiers se fera au démmarrage de l'appli
                        chercherParNom(); //Annuaire.testsMapPersonne();
                    }
                    // b. Par email
                    case "b" -> { afficher("Choix b..."); //annuaire.menuChercherPersonne(email);
                    }
                    // c. Par profil
                    case "c" -> afficher("Choix c..."); //annuaire.menuChercherPersonne(profil);
                    // d. Retour au menu principal
                    case "d" -> main(new String[]{"a"});
                    default -> {
                    }
                }
            } else {
                afficher(erreurChoix);
                afficher(entrerABCD);
            }
        }
    }

    public static void chercherParNom() {
        afficher("Entrer le Nom à rechercher :");
        String nom = lireString();
        Annuaire.rechercheParNom(nom);
    }

}
