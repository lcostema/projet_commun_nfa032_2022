package Utilisateurs;

public class Admin extends Utilisateur {

    Admin(String email, String motdepasse) {
        super(email, motdepasse, Role.Administrateur);
    }


}
