package Authentification;

import Affichage.Accueil;
import Utilisateurs.Compte;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class Connexion {

    static int nbErreurs;
    static int maxErreurs = 3;
    static String roleInput;
    static Scanner scanner = new Scanner(System.in);

    public static boolean authentification(String role, HashMap<String, Compte> comptes){
        roleInput = role;
        String authentEmail, authentRole;
        if (Objects.equals(roleInput, "admin")) {
            System.out.print("email administrateur :\n");
        } else if (Objects.equals(roleInput, "user")) {
            System.out.print("email utilisateur :\n");
        }
        if (scanner.hasNext()) {
            authentEmail = scanner.next();
            if (comptes.get(authentEmail) == null) {
                Accueil.afficherJaune("Pas trouvé cet email ...!");
                nbErreurs += 1;
                checkErreurs(comptes);
            } else {
                authentRole = String.valueOf(comptes.get(authentEmail).getRole());
                if (roleInput.equals("admin")) {
                    if (!authentRole.equals("Administrateur")) {
                        Accueil.afficherJaune("Vous n'êtes pas administrateur ...!");
                        nbErreurs += 1;
                        checkErreurs(comptes);
                    }
                }
                if (roleInput.equals("user")) {
                    if (!authentRole.equals("Particulier")) {
                        Accueil.afficherRouge("Ceci est un compte Administrateur, \n" +
                                " veuillez utiliser une adresse mail Particulier");
                        nbErreurs += 1;
                        checkErreurs(comptes);
                    }
                }
                nbErreurs = 0;
                checkMDP(authentEmail, comptes);
            }
        }
        return true;
    }

    private static void checkMDP(String email, HashMap<String, Compte> comptes) {
        System.out.print("Mot de passe :\n");
        String authentMDP = scanner.next();
        if (!Objects.equals(String.valueOf(comptes.get(email).getMotDePasse()), authentMDP)) {
            Accueil.afficherJaune("Mot de passe erroné ...!");
            nbErreurs += 1;
            checkErreursMDP(email, comptes);
        }
    }

    private static void checkErreursMDP(String email, HashMap<String, Compte> comptes) {
        if (nbErreurs < maxErreurs) {
            checkMDP(email, comptes);
        } else {
            nbErreurs = 0;
            Accueil.afficherRouge("Trop de tentatives erronées !");
            Accueil.mA.ouvrirMenuAccueil();
        }
    }

    private static void checkErreurs(HashMap<String, Compte> comptes) {
        if (nbErreurs < maxErreurs) {
            authentification(roleInput, comptes);
        } else {
            nbErreurs = 0;
            Accueil.afficherRouge("Trop de tentatives erronées !");
            Accueil.mA.ouvrirMenuAccueil();
        }
    }

}
