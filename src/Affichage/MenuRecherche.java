package Affichage;

import Recherche.Recherche;

import java.util.Objects;

public class MenuRecherche extends Accueil {
    public static void afficherMenuRecherche() {
        afficherCyan("""
                Rechercher un ou des particuliers
                   a. Par nom
                   b. Par email
                   c. Par profil
                   d. Retour au menu principal
                """);
        afficherVert("Choisir (taper la lettre puis Enter) :");

        if (scannerClavier.hasNext()) {
            String lettre = scannerClavier.next();
            Recherche re = new Recherche();
            switch (lettre) {

                // a. Par nom
                case "a" -> {re.chercherParNom(annuaire, scannerClavier); afficherMenuRecherche();}
                // b. Par email
                case "b" -> {re.chercherParEmail(annuaire, scannerClavier); afficherMenuRecherche();}
                // c. Par profil
                case "c" -> {re.chercherParProfil(annuaire, scannerClavier); afficherMenuRecherche();}
                // d. Retour au menu principal
                case "d" -> mA.afficherMenuAccueil();
                default -> {afficherRouge(entrerABCD); afficherMenuRecherche();}
            }
        }
    }
}
