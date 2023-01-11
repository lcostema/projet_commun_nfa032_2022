package Recherche;

import Affichage.ResultatRecherche;
import Utilisateurs.Particulier;
import java.util.*;

/**
 * Classe qui regroupe les méthodes de recherche dans l'annuaire
 */
public class Recherche {

    //on instancie la class pour afficher les résultats de recherche
    ResultatRecherche affRes = new ResultatRecherche();

    /**
     * Recherche par email.
     * Si l'utilisateur ne saisit pas un input contenant un @, il est invité à recommencer
     * Et affichage du resultat de recherche
     * @param annuaire
     * @param sc
     */
    public void chercherParEmail(HashMap<String, Particulier> annuaire, Scanner sc){
        String email = "";
        do{
            System.out.println("Veuillez saisir un email à rechercher : ");
            email = sc.next();
        } while (!email.contains("@"));

        //TODO: Lors de la creation d'un Compte ou Particulier : s'assurer que l'email est .toLowerCase()
        affRes.afficherResultatRecherche(annuaire.get(email.toLowerCase()));
    }

    /**
     * Recherche par Nom.
     * L'utilisateur est invité à saisir un nom à rechercher dans l'annuaire.
     * Le champ Particulier.getNom() est comparé à l'input de l'utilisateur
     * Et affichage du resultat de recherche
     * @param annuaire
     * @param sc
     */
    public void chercherParNom(HashMap<String, Particulier> annuaire, Scanner sc){
        List<Particulier> liste;

        System.out.println("Veuillez saisir un nom à rechercher : ");
        String nom = sc.next();

        liste = annuaire.values().stream()
                .filter(p -> p.getNom().equalsIgnoreCase(nom))
                .sorted((p1,p2) -> p2.getDateAjout().compareTo(p1.getDateAjout()))
                .limit(10)
                .sorted((p1,p2) -> p1.getPrenom().compareTo(p2.getPrenom()))
                .toList();
        //filter : filtre sur le nom donné en input
        //sorted (1) : on compare la date d'ajout de l'objet n+1 par rapport à n (ce qui donne que les dates les plus récentes sont en premieres dans la liste finale)
        //limit : on limite la taille de la liste à 10
        //sorted (2) : on tri sur le prénoms des Particulier (le nom étant le meme)
        //toList : on converti le stream en liste

        affRes.afficherResultatRecherche(liste);
    }

    /**
     * recherche par Profil
     * L'utilisateur est invité à choisir un profil à rechercher (Auditeur, Enseignant ou Direction)
     * Si l'input de l'utilisateur ne correspond pas à l'initiale d'un des choix, il est invité à recommencer.
     * Et Affichage du resultat de recherche
     * @param annuaire
     * @param sc
     */
    public void chercherParProfil(HashMap<String, Particulier> annuaire, Scanner sc){
        List<Particulier> liste;
        boolean profilConforme = false;
        String profil;
        do{
            System.out.println("Veuillez saisir un profil à rechercher (A)uditeur, (E)nseignant, (D)irection): ");
            profil = sc.next();
            if (profil.equalsIgnoreCase("a") || profil.equalsIgnoreCase("e")|| profil.equalsIgnoreCase("d")){
                profilConforme=true;
            }
        } while (!profilConforme);

        Particulier.Profil pro = null;
        switch (profil) {
            case "a", "A" -> pro = Particulier.Profil.Auditeur;
            case "e", "E" -> pro = Particulier.Profil.Enseignant;
            case "d", "D" -> pro = Particulier.Profil.Direction;
            default -> System.out.println("Saisissez a, e ou d");
        }
        Particulier.Profil finalPro = pro;

        liste = annuaire.values().stream()
                .filter(p -> p.getProfil() == finalPro)
                .sorted((p1,p2) -> p2.getDateAjout().compareTo(p1.getDateAjout()))
                .limit(10)
                .sorted((p1,p2) -> p1.getNom().compareTo(p2.getNom()))
                .toList();
        //filter : filtre sur le profil donné en input
        //sorted (1): on compare la date d'ajout de l'objet n+1 par rapport à n (ce qui donne que les date les plus récentes sont en premieres dans la liste finale)
        //limit : on limite la taille de la liste à 10
        //sorted (2) : on tri sur les noms des Particuliers
        //toList : on converti le stream en liste




        affRes.afficherResultatRecherche(liste);
    }
}
