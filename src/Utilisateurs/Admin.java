package Utilisateurs;

import java.util.Date;

public class Admin extends Utilisateur {

    Admin(String email, String motdepasse) {
        super(email, motdepasse, Role.Administrateur);
    }
}
