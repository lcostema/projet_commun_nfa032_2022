package Utilisateurs;

import Affichage.Accueil;

/**
 * Classe des comptes
 */
public class Compte extends Accueil {
    private final Role role;
    private String email, motDePasse;
    public enum Role {
        Administrateur, Particulier
    }

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
     * @param email email
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
     * @param motDePasse motDePasse
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

    /**
     * Getter du compte
     * @return Le compte
     */
    public Compte getCompte() {
        return this;
    }
}