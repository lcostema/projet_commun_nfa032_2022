package Affichage;

import Recherche.Recherche;

import java.io.IOException;

/**
 * Gestion du menu recherche
 */
public class MenuRecherche extends Accueil {
    /**
     * Affichage des options du menu recherche
     *
     * @throws IOException IOException
     */
    public static void afficherMenuRecherche() throws IOException {
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
                case "a" -> {
                    re.chercherParNom(annuaire);
                    afficherMenuRecherche();
                }
                // b. Par email
                case "b" -> {
                    re.chercherParEmail(annuaire);
                    afficherMenuRecherche();
                }
                // c. Par profil
                case "c" -> {
                    re.chercherParProfil(annuaire);
                    afficherMenuRecherche();
                }
                // d. Retour au menu principal
                case "d" -> MenuAccueil.ouvrirMenuAccueil();
                default -> {
                    afficherRouge(entrerABCD);
                    afficherMenuRecherche();
                }
            }
        }
    }
}