package Utilisateurs;

import java.util.Date;

/**
 * Classe des particuliers
 */
public class Particulier extends Compte {
    private String nom, prenom, adressePostale;
    private Date dateNaissance;
    private final Date dateAjout;
    private Date dateMaj;

    public enum Profil {
        Auditeur, Enseignant, Direction
    }

    private Profil profil;

    /**
     * Constructeur complet d'un particulier
     *
     * @param email          Email de l'utilisateur
     * @param motDePasse     Mot de passe de l'utilisateur
     * @param nom            Nom de l'utilisateur
     * @param prenom         Prénom de l'utilisateur
     * @param adressePostale Adresse de l'utilisateur
     * @param dateNaissance  Date de naissance de l'utilisateur
     * @param dateAjout      Date d'ajout de l'utilisateur
     * @param dateMaj        Date de mise à jour des infos de l'utilisateur
     * @param profil         Profil de l'utilisateur
     */
    public Particulier(String email, String motDePasse, String nom, String prenom, String adressePostale, Date dateNaissance, Date dateAjout, Date dateMaj, Profil profil) {
        super(email, motDePasse, Compte.Role.Particulier);
        this.nom = nom;
        this.prenom = prenom;
        this.adressePostale = adressePostale;
        this.dateNaissance = dateNaissance;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
        this.profil = profil;
    }

    /**
     * Getter nom
     *
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter nom
     *
     * @param nom nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter prénom
     *
     * @return prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter prénom
     *
     * @param prenom prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter adresse postale
     *
     * @return adressePostale
     */
    public String getAdressePostale() {
        return adressePostale;
    }

    /**
     * Setter adresse postale
     *
     * @param adressePostale adressePostale
     */
    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    /**
     * Getter date de naissance
     *
     * @return dateNaissance
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Setter date de naissance
     *
     * @param dateNaissance dateNaissance
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Getter date d'ajout
     *
     * @return dateAjout
     */
    public Date getDateAjout() {
        return dateAjout;
    }

    /**
     * Getter date de mise à jour
     *
     * @return dateMaj
     */
    public Date getDateMaj() {
        return dateMaj;
    }

    /**
     * Setter date de mise à jour
     *
     * @param dateMaj dateMaj
     */
    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }

    /**
     * Getter profil
     *
     * @return profil
     */
    public Profil getProfil() {
        return profil;
    }

    /**
     * Setter profil
     *
     * @param profil profil
     */
    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}