package Affichage;

import Utilisateurs.Particulier;

import java.util.List;

/**
 * Classe qui a pour but d'afficher les résultats des recherches dans l'annuaire
 */
public class ResultatRecherche extends Accueil{

    /**
     * Méthode qui met en forme l'affichage pour un Particulier
     * @param p particulier
     */
    private void afficherParticulier(Particulier p){
        System.out.println("----------------------------------------");
        System.out.println("Nom : " + p.getNom());
        System.out.println("Prénom : " + p.getPrenom());
        System.out.println("Email : " + p.getEmail());
        System.out.println("Adresse postale : " + p.getAdressePostale());
        System.out.println("Date de Naissance : " + dateFormatter.format(p.getDateNaissance()));
        System.out.println("Profil : " + p.getProfil().toString());
        System.out.println("Date d'ajout : " + dateFormatter.format(p.getDateAjout()));
        System.out.println("Date de mise à jour : " + dateFormatter.format(p.getDateMaj()));
    }

    /**
     * Plolymorphisme 1 : Méthode qui affiche le resultat (1 objet Particulier) d'une recherche dans l'annuaire
     * @param p particulier
     */
    public void afficherResultatRecherche(Particulier p){
        if( p == null){
            System.out.println("Aucune entrée trouvée");
        } else {
            System.out.println(1 + " entrée trouvée dans l'annuaire :");
            afficherParticulier(p);
        }
    }

    /**
     * Plolymorphisme 2 : Méthode qui affiche le résultat (une liste d'objets Particulier) d'une recherche dans l'annuaire
     * @param liste liste
     */
    public void afficherResultatRecherche(List<Particulier> liste){
        if(liste.size() == 0){
            System.out.println("Aucune entrée trouvée");
        } else {
            System.out.println(liste.size() + " entrée(s) trouvée(s) dans l'annuaire (par date d'ajout) :");
            liste.forEach(this::afficherParticulier);
        }
    }
}
