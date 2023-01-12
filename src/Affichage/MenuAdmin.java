package Affichage;

import java.io.IOException;

import Utilisateurs.Compte.Role;

public class MenuAdmin extends Accueil {

    public static void afficherMenuAdmin() throws IOException {


        afficherCyan("""
                Menu Admin
                   a. Ajouter un Admin
                   b. Ajouter un Particulier
                   c. Modifier un Particulier
                   d. Retour au menu principal
                """);
        afficherVert("Choisir (taper la lettre puis Enter) :");


        if (scannerClavier.hasNext()) {
            String lettre = scannerClavier.next();
                switch (lettre) {
                    // a. Ajouter un Admin
                    case "a" ->
                            GestionComptes.AjouterCompte.creationCompte(comptes, Role.Administrateur);  

                    // b. Ajouter un Particulier
                    case "b" ->
                            GestionComptes.AjouterCompte.creationCompte(comptes, Role.Particulier);

                    //c. Modifier Un Particulier
                    case "c" -> afficherNormal("Choix c...");
                        //GestionComptes.ModifierParticulier(); //TODO: A ATTRIBUE

                    // c. Retour au menu principal
                    case "d" -> mA.ouvrirMenuAccueil();
                    default -> {afficherRouge(entrerABCD); afficherMenuAdmin();}
                    }
                }

        }
  }

