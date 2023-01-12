package Utilisateurs;

public class Admin extends Compte {

    public Admin(String email, String motdepasse) {
        super(email, motdepasse, Compte.Role.Administrateur);
    }
}