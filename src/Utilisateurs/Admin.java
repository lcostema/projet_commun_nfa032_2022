package Utilisateurs;

public class Admin extends Compte {

    Admin(String email, String motdepasse) {
        super(email, motdepasse, Compte.Role.Administrateur);
    }
}