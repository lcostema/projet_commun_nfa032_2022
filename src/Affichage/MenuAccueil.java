package Affichage;

import Authentification.Connexion;
import Utilisateurs.Particulier;

public class MenuAccueil extends Accueil {

    public void ouvrirMenuAccueil() {
        afficherCyan("""
                                     
                 *** Bienvenue dans l’Annuaire NFA032 ***
                                
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
                        if (Connexion.authentification("user")) {
                            afficherNormal("\n Particulier authentifié !\n");

                            String email = "toto";
                            MenuParticulier.afficherMenuParticulier(email);
                        }
                    }

                    // 3. Accéder au menu Admin
                    // seulement un admin peut faire cela, on commence par l'authentifier
                    case 3 -> {
                        if (Connexion.authentification("admin")) {
                            afficherNormal("\n Administrateur authentifié !\n");
                            MenuAdmin.afficherMenuAdmin();
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
