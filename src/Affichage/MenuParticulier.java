package Affichage;

import Utilisateurs.Particulier;

public class MenuParticulier extends Accueil{

    public static void afficherMenuParticulier(String email){

        //Methode modifier Compte
        Particulier p = annuaire.get(email);



        //NE PAS OUBLIER DE LANCER ecrireCompte(comptes, new File(Path.of(".").toRealPath() + "\\src\\comptes.txt"))

        mA.ouvrirMenuAccueil();
    }
}
