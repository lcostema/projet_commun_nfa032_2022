package Utilisateurs;

public class Utilisateur {
    private String email, motdepasse;
    public enum Role {
        Administrateur, Particulier
    }
    private Role role;

    public Utilisateur(String email, String motdepasse, Role role) {
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

    public String getRole() {
        if (this.role == Role.Administrateur){
            return "admin";
        } else {
            return "user";
        }
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }
}
