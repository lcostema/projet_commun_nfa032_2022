package Affichage;

import GestionComptes.ModifierParticulier;

import java.io.IOException;

/**
 * Gestion du menu particulier
 */
public class MenuParticulier extends Accueil {
    /**
     * Méthode principale pour le menu particulier
     *
     * @param email Email du particulier authentifié
     * @throws IOException Erreur d'écriture sur les fichiers
     */
    public static void ouvrirMenuParticulier(String email) throws IOException {
        ModifierParticulier.ModificationDonnees(email);
    }
}