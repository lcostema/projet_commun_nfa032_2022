package Affichage;

public class MenuAdmin extends Accueil {

    public static void afficherMenuAdmin() {

        afficherCyan("""
                Ajouter un utilisateur (Administrateur)
                   a. Ajouter un Admin
                   b. Ajouter un Particulier
                   c. Modifier un Particulier
                   c. Retour au menu principal
                """);
        afficherVert("Choisir (taper la lettre puis Enter) :");

        if (scannerClavier.hasNext()) {
            String lettre = scannerClavier.next();
            switch (lettre) {
                // a. Ajouter un Admin
                case "a" -> afficherNormal("Choix a...");
                //GestionComptes.AjouterAdmin()  //TODO: ISABELLE EST DESSUS

                // b. Ajouter un Particulier
                case "b" -> afficherNormal("Choix b...");

                //GestionComptes.AjouterParticulier() //TODO:ISABELLE EST DESSUS

                //c. Modifier Un Particulier
                case "c" -> afficherNormal("Choix c...");
                //GestionComptes.ModifierParticulier(); //TODO: A ATTRIBUE

                // c. Retour au menu principal
                case "d" -> mA.ouvrirMenuAccueil();
                default -> {
                    afficherRouge(entrerABCD);
                    afficherMenuAdmin();
                }
            }
        }
    }
}

