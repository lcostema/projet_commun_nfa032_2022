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

        affRes.afficherResultatRecherche(annuaire.get(email));
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
        liste = annuaire.values().stream().filter(p -> p.getNom().equals(nom)).toList();

        //TODO: remonter les 10 derniers ajoutés (date ajout)
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

        liste = annuaire.values().stream().filter(p -> p.getProfil() == finalPro).toList();
        //TODO: remonter les 10 derniers ajoutés (date ajout)

        affRes.afficherResultatRecherche(liste);
    }
}
