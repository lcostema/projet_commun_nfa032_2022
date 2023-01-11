package Affichage;

import Utilisateurs.Particulier;

import java.util.Objects;

public class MenuParticulier extends Accueil {

    public static void ouvrirMenuParticulier(String email) {

        //Methode modifier Compte
        Particulier p = annuaire.get(email);

        //test fonctionnement envoi email par Connexion.authentification()
        afficherJaune("Email du particulier authentifié: " + email);
        afficherVert("\n Taper un caractère puis Enter pour sortir:");
        if (scannerClavier.hasNext()) {
            if (Objects.equals(scannerClavier.next(), "toto")) {
                mA.ouvrirMenuAccueil();
            }
        }


        //TODO: NE PAS OUBLIER DE LANCER ecrireCompte(comptes, new File(Path.of(".").toRealPath() + "\\src\\comptes.txt"))

    }
}
