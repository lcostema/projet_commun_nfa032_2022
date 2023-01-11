package Affichage;

import Authentification.Connexion;

public class MenuAccueil extends Accueil {

    public void ouvrirMenuAccueil() {

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

                // try/catch pour éviter de crasher en cas d'entrée de caractère autre qu'int
                try {
                    chiffre = scannerClavier.nextInt();
                } catch (Exception exception) {
                    return;
                }

                switch (chiffre) {
                    // 1. Faire une recherche de particulier(s)
                    case 1 -> MenuRecherche.afficherMenuRecherche();

                    // 2. Modifier mes infos personnelles (authentification du Particulier)
                    case 2 -> {
                        if (Connexion.authentification("user", comptes)) {
                            afficherNormal("\n Particulier authentifié !\n");
                            Connexion.nbErreurs = 0;

                            // récupération email entré à l'authentification
                            MenuParticulier.ouvrirMenuParticulier(Connexion.inputEmail);
                            // ...puis on réinitialise
                            Connexion.inputEmail = "";
                            scannerClavier.reset();
                        }
                    }

                    // 3. Accéder au menu Admin
                    // Seulement un admin peut faire cela, on commence par l'authentifier
                    case 3 -> {
                        if (Connexion.authentification("admin", comptes)) {
                            afficherNormal("\n Administrateur authentifié !\n");
                            Connexion.nbErreurs = 0;
                            MenuAdmin.afficherMenuAdmin();
                        }
                    }

                    // 4. Quitter le programme
                    case 4 -> quitter = true;
                    default -> afficherRouge(entrer1234);
                }

            } else {
                // gestion erreurs d'entrée
                afficherRouge(erreurChoix);
                afficherRouge(entrer1234);
                scannerClavier.next();
            }
        }
    }
}
