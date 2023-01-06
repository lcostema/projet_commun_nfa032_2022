package Affichage;

public class MenuAccueil extends Accueil{

    public void afficherMenuAccueil(){
        afficherCyan("""
                                     
                 *** Bienvenue dans l’Annuaire NFA032 ***
                                
                Administrateur :
                    1. Se connecter en Admin
   
                Particulier :
                    2. Modifier mes informations personnelles
                
                Rechercher :
                    3. Rechercher un ou des particuliers
                    
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
                    // 1. Ajouter une personne
                    // comme seulement un admin peut faire cela, on commence par l'authentifier
                    case 1 -> MenuAdmin.afficherMenuAdmin();
                    // 2. Modifier ses infos personnelles (en tant que Particulier)
                    case 2 -> MenuParticulier.afficherMenuParticulier();
                    // 3. Faire une recherche de particulier(s)
                    case 3 -> MenuRecherche.afficherMenuRecherche();
                    case 4 -> quitter = true;
                    default -> afficherRouge(entrer1234);
                }
                // gestion erreurs d'entrée


            } else {
                afficherRouge(erreurChoix);
                afficherRouge(entrer1234);
                scannerClavier.next();
            }
        }
    }
}
