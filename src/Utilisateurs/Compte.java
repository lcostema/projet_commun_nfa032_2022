package Utilisateurs;

import Affichage.Accueil;

import java.util.Objects;
import java.util.Scanner;

;

/**
 * Classe des comptes
 */
public class Compte extends Accueil {
    static int nbErreurs;
    static String roleInput;
    static Scanner scanner = new Scanner(System.in);
    private final Role role;
    private String email, motDePasse;

    /**
     * Constructeur d'un compte
     *
     * @param email      Email de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @param role       Role de l'utilisateur
     */
    public Compte(String email, String motDePasse, Role role) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    public static boolean authentification(String role) {
        roleInput = role;
        nbErreurs = 0;
        String authentEmail, authentRole;
        if (Objects.equals(roleInput, "admin")) {
            System.out.print("email administrateur :\n");
        } else if (Objects.equals(roleInput, "user")) {
            System.out.print("email utilisateur :\n");
        }
        if (scanner.hasNext()) {
            authentEmail = scanner.next();
            if (lf.getComptes().get(authentEmail) == null) {
                Accueil.afficherJaune("Pas trouvé cet email ...!");
                nbErreurs += 1;
                checkErreurs();
            } else {
                authentRole = String.valueOf(lf.getComptes().get(authentEmail).getRole());
                if (roleInput.equals("admin")) {
                    if (!authentRole.equals("Administrateur")) {
                        Accueil.afficherJaune("Vous n'êtes pas administrateur ...!");
                        nbErreurs += 1;
                        checkErreurs();
                    }
                }
                if (roleInput.equals("user")) {
                    if (!authentRole.equals("Particulier")) {
                        Accueil.afficherRouge("Ceci est un compte administrateur, \n" +
                                " veuillez utiliser une adresse mail utilisateur");
                        nbErreurs += 1;
                        checkErreurs();
                    }
                }
                checkMDP(authentEmail);
            }
        }
        return true;
    }

    private static void checkMDP(String email) {
        System.out.print("Mot de passe :\n");
        String authentMDP = scanner.next();
        if (!Objects.equals(String.valueOf(lf.getComptes().get(email).getMotDePasse()), authentMDP)) {
            Accueil.afficherJaune("Mot de passe erroné ...!");
            nbErreurs += 1;
            if (nbErreurs < 3) {
                checkMDP(email);
            } else {
                nbErreurs = 0;
                Accueil.afficherRouge("Trop de tentatives erronées !");
                checkErreurs();
            }
        }
    }

    private static void checkErreurs() {
        if (nbErreurs < 3) {
            authentification(roleInput);
        } else {
            nbErreurs = 0;
            Accueil.afficherRouge("Trop de tentatives erronées !");
            Accueil.mA.afficherMenuAccueil();
        }

    }

    /**
     * Getter Email
     *
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter email
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter Mot de passe
     *
     * @return motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Setter Mot de passe
     *
     * @param motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Getter Role
     *
     * @return role
     */
    public Role getRole() {
        return role;
    }

    public enum Role {
        Administrateur, Particulier
    }

}