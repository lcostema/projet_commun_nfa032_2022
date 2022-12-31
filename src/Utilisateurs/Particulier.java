package Utilisateurs;

import java.util.Date;

public class Particulier extends Utilisateur {
    private String nom, prenom, adressePostale;
    private Date dateNaissance, dateAjout, dateMaj;
    public enum Profil {
        Auditeurs, Enseignants, Direction
    }
    private Profil profil;

    public Particulier(String email, String motdepasse, String nom, String prenom, String adressePostale, Date dateNaissance, Date dateAjout, Date dateMaj, Profil profil) {
        super(email, motdepasse, Role.Particulier);
        this.nom = nom;
        this.prenom = prenom;
        this.adressePostale = adressePostale;
        this.dateNaissance = dateNaissance;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
        this.profil = profil;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setAdressePostale(String adressePostale) {
        this.adressePostale = adressePostale;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public Date getDateMaj() {
        return dateMaj;
    }

    public void setDateMaj(Date dateMaj) {
        this.dateMaj = dateMaj;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}
