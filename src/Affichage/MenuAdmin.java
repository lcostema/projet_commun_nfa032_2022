package Affichage;

import java.util.Objects;

public class MenuAdmin extends Accueil {

    public static void afficherMenuAdmin(){



        afficherCyan("""
                Ajouter un utilisateur (Administrateur)
                   a. Ajouter un Admin
                   b. Ajouter un Particulier
                   c. Retour au menu principal
                """);
        afficherVert("Choisir (taper la lettre puis Enter) :");


        if (scannerClavier.hasNext()) {
            String lettre = scannerClavier.next();
            if (Objects.equals(lettre, "a")
                    || Objects.equals(lettre, "b") || Objects.equals(lettre, "c")) {
                switch (lettre) {
                    // a. Ajouter un Admin
                    case "a" -> // todo : compte.ajouterPersonne(new Admin(email, motdepasse));
                            afficherCyan("Choix a...");
                    // b. Ajouter un Particulier
                    case "b" ->  // todo : compte.ajouterPersonne(new Particulier(email, motdepasse, nom, prenom, adressePostale, dateNaissance, dateAjout, dateMaj, profil));
                            afficherCyan("Choix b...");
                    // c. Retour au menu principal
                    case "c" -> mA.afficherMenuAccueil();
                    default -> {
                    }
                }
            } else {
                afficherRouge(entrerABC);
            }
        }
    }
}
