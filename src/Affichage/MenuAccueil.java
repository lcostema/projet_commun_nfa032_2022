package Affichage;

import Authentification.Connexion;
import Utilisateurs.Compte;

import java.io.IOException;

/**
 * Gestion du Menu Accueil
 */
public class MenuAccueil extends Accueil {

    /**
     * Affichage des options du menu accueil
     * @throws IOException
     */
    public static void ouvrirMenuAccueil() throws IOException {
        afficherNormal("""            
                *** Bienvenue dans l’Annuaire NFA032 ***
                  """);
        afficherCyan("""
                     Recherche :
                        1. Rechercher un ou des particuliers
                    Particulier :
                        2. Modifier mes informations personnelles
                    Administrateur :
                        3. Accéder au menu Admin
                    Quitter le programme:
                       4. Quitter le programme
                """);

        afficherVert("Choisir (taper le chiffre puis Enter) :");
        if (scannerClavier.hasNext()) {
            int chiffre;
            if (scannerClavier.hasNextInt()) {
                // try/catch obligatoire pour éviter de crasher en cas d'entrée de caractère autre qu'int
                try {
                    chiffre = scannerClavier.nextInt();
                } catch (Exception exception) {
                    afficherRouge(erreurChoix);
                    afficherRouge(entrer1234);
                    return;
                }
                switch (chiffre) {
                    // 1. Faire une recherche de particulier(s)
                    case 1 -> MenuRecherche.afficherMenuRecherche();

                    // 2. Modifier ses infos personnelles (en tant que Particulier)
                    case 2 -> {
                        if (Connexion.authentification(Compte.Role.Particulier)) {
                            MenuParticulier.ouvrirMenuParticulier(Connexion.authentEmail);
                            Connexion.authentEmail = null;
                        }
                    }
                    // 3. Accéder au menu Admin
                    case 3 -> {
                        if (Connexion.authentification(Compte.Role.Administrateur)) {
                            MenuAdmin.afficherMenuAdmin();
                            Connexion.authentEmail = null;
                        }
                    }
                    case 4 -> quitter = true;
                    default -> afficherRouge(entrer1234);
                }

            } else {
                // gestion erreurs d'entrée
                afficherRouge(entrer1234);
                scannerClavier.next();
            }
        }
    }
}
