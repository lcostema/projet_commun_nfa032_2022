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
                        case 1 -> choixAdmin();
                        case 2 -> trouverPersonne();
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

    public static void choixAdmin() throws Exception {
        //todo: passer par identification

        afficher("""
                                
                Ajouter une personne (Administrateur)
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
                    case "a" -> //annuaire.ajouterPersonne(admin);
                            afficher("Choix a...");
                    case "b" -> afficher("Choix b..."); //annuaire.ajouterPersonne(particulier);
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
//        afficher("Prénom ?");
//        prenom = lireString();
        afficher("Nom ?");
        nom = lireString();
        Personne utilisateur = new Personne(nom, null, null, null, null, null, null, null);
        afficher("'" + utilisateur + "'");
        afficher(annuaire.trouverPersonne(utilisateur));
        if (annuaire.trouverPersonne(utilisateur) == null)
            afficher("Pas dans l'annuaire !");
        else
            afficher(utilisateur + " : " + annuaire.trouverPersonne(utilisateur));
    }

    public static void trouverPersonne() throws Exception {
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
                    case "a" -> {
//                        trouverParNom();
//                        Annuaire.lireFichierPersonne();
                        Annuaire.testChargerPersonne();
//                            afficher("Choix a...");
                    }
                    case "b" -> afficher("Choix b..."); //annuaire.trouverPersonne(email);
                    case "c" -> afficher("Choix c..."); //annuaire.trouverPersonne(profil);
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

}
