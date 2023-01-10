package Authentification;

import Affichage.Accueil;

import java.util.Objects;
import java.util.Scanner;

public class Connexion {

    static int nbErreurs;
    static int maxErreurs = 3;
    static String roleInput;
    static Scanner scanner = new Scanner(System.in);

    public static boolean authentification(String role) {
        roleInput = role;
        String authentEmail, authentRole;
        if (Objects.equals(roleInput, "admin")) {
            System.out.print("email administrateur :\n");
        } else if (Objects.equals(roleInput, "user")) {
            System.out.print("email utilisateur :\n");
        }
        if (scanner.hasNext()) {
            authentEmail = scanner.next();
            if (Accueil.lf.getComptes().get(authentEmail) == null) {
                Accueil.afficherJaune("Pas trouvé cet email ...!");
                nbErreurs += 1;
                checkErreurs();
            } else {
                authentRole = String.valueOf(Accueil.lf.getComptes().get(authentEmail).getRole());
                if (roleInput.equals("admin")) {
                    if (!authentRole.equals("Administrateur")) {
                        Accueil.afficherJaune("Vous n'êtes pas administrateur ...!");
                        nbErreurs += 1;
                        checkErreurs();
                    }
                }
                if (roleInput.equals("user")) {
                    if (!authentRole.equals("Particulier")) {
                        Accueil.afficherRouge("Ceci est un compte Administrateur, \n" +
                                " veuillez utiliser une adresse mail Particulier");
                        nbErreurs += 1;
                        checkErreurs();
                    }
                }
                nbErreurs = 0;
                checkMDP(authentEmail);
            }
        }
        return true;
    }

    private static void checkMDP(String email) {
        System.out.print("Mot de passe :\n");
        String authentMDP = scanner.next();
        if (!Objects.equals(String.valueOf(Accueil.lf.getComptes().get(email).getMotDePasse()), authentMDP)) {
            Accueil.afficherJaune("Mot de passe erroné ...!");
            nbErreurs += 1;
            checkErreursMDP(email);
        }
    }

    private static void checkErreursMDP(String email) {
        if (nbErreurs < maxErreurs) {
            checkMDP(email);
        } else {
            nbErreurs = 0;
            Accueil.afficherRouge("Trop de tentatives erronées !");
            Accueil.mA.ouvrirMenuAccueil();
        }
    }

    private static void checkErreurs() {
        if (nbErreurs < maxErreurs) {
            authentification(roleInput);
        } else {
            nbErreurs = 0;
            Accueil.afficherRouge("Trop de tentatives erronées !");
            Accueil.mA.ouvrirMenuAccueil();
        }
    }

}
