package Affichage;

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
            switch (lettre) {
                // a. Par nom
                case "a" -> afficherNormal("Choix a..."); //chercherParNom();
                // b. Par email
                case "b" -> afficherNormal("Choix a..."); //chercherParEmail();
                // c. Par profil
                case "c" -> afficherNormal("Choix a..."); //chercherParProfil();
                // d. Retour au menu principal
                case "d" -> mA.ouvrirMenuAccueil();
                default -> afficherRouge(entrerABCD);
            }
        }
    }
}
