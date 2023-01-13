package Affichage;

import GestionComptes.ModifierParticulier;
import Utilisateurs.Compte;

import java.io.IOException;

/**
 * Gestion du menu admin
 */
public class MenuAdmin extends Accueil {
    /**
     * Affichage des options du menu admin
     * @throws IOException IOException
     */
    public static void afficherMenuAdmin() throws IOException {
        afficherCyan("""
                Ajouter un utilisateur (Administrateur)
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
                case "a" -> {
                    if (GestionComptes.AjouterCompte.creationCompte(Compte.Role.Administrateur)) {
                        afficherMenuAdmin();
                    }
                }
                // b. Ajouter un Particulier
                case "b" -> {
                    if (GestionComptes.AjouterCompte.creationCompte(Compte.Role.Particulier)) {
                        afficherMenuAdmin();
                    }
                }
                //c. Modifier Un Particulier
                case "c" -> {
                    if (!ModifierParticulier.AffichageSaisieEmail()) {
                        afficherMenuAdmin();
                    }
                }
                // d. Retour au menu principal
                case "d" -> MenuAccueil.ouvrirMenuAccueil();
                default -> {afficherRouge(entrerABCD); afficherMenuAdmin();}
            }
        }
    }
}