package Affichage;

import Utilisateurs.Particulier;

import java.util.List;

/**
 * Classe qui a pour but d'afficher les résultats des recherches dans l'annuaire
 */
public class ResultatRecherche extends Accueil {

    /**
     * Méthode qui met en forme l'affichage pour un Particulier
     *
     * @param p particulier
     */
    public void afficherParticulier(Particulier p) {
        afficherNormal("----------------------------------------");
        afficherNormal("Nom : " + p.getNom());
        afficherNormal("Prénom : " + p.getPrenom());
        afficherNormal("Email : " + p.getEmail());
        afficherNormal("Adresse postale : " + p.getAdressePostale());
        afficherNormal("Date de Naissance : " + dateFormatter.format(p.getDateNaissance()));
        afficherNormal("Profil : " + p.getProfil().toString());
        afficherNormal("Date d'ajout : " + dateFormatter.format(p.getDateAjout()));
        afficherNormal("Date de mise à jour : " + dateFormatter.format(p.getDateMaj()));
    }

    /**
     * Polymorphisme 1 : Méthode qui affiche le résultat (1 objet Particulier) d'une recherche dans l'annuaire
     *
     * @param p particulier
     */
    public void afficherResultatRecherche(Particulier p) {
        if (p == null) {
            afficherJaune("Aucune entrée trouvée...");
        } else {
            afficherJaune("\nEntrée trouvée dans l'annuaire :");
            afficherParticulier(p);
        }
    }

    /**
     * Polymorphisme 2 : Méthode qui affiche le résultat (une liste d'objets Particulier) d'une recherche dans l'annuaire
     *
     * @param liste liste
     */
    public void afficherResultatRecherche(List<Particulier> liste) {
        if (liste.size() == 0) {
            afficherJaune("Aucune entrée trouvée...");
        } else {
            if (liste.size() > 1) {
                liste.forEach(this::afficherParticulier);
                afficherJaune("\n ^ " + liste.size() + " entrées trouvées dans l'annuaire." +
                        "\n(Par date d'ajout, plus récente en haut)");
            } else {
                afficherJaune("\nEntrée trouvée dans l'annuaire :");
                liste.forEach(this::afficherParticulier);
            }
        }
    }
}
