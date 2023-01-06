package Utilisateurs;

public class Compte {
    private String email, motDePasse, role;

//Constructeur
public Compte(String email, String motDePasse, String profil) {
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = profil;
    }

//Getters/setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//Méthodes
    // isAdmin()....

}
