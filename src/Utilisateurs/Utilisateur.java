package Utilisateurs;

public class Utilisateur {
    private String email, motdepasse;
    protected enum Role {
        Administrateur, Particulier
    }
    private Role role;

    Utilisateur(String email, String motdepasse, Role role) {
        this.email = email;
        this.motdepasse = motdepasse;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
