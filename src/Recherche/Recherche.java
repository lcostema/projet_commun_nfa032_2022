package Recherche;

import Affichage.ResultatRecherche;
import Utilisateurs.Particulier;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static Affichage.Accueil.*;

/**
 * Classe qui regroupe les méthodes de recherche dans l'annuaire
 */
public class Recherche {

    //on instancie la class pour afficher les résultats de recherche
    final ResultatRecherche affRes = new ResultatRecherche();

    /**
     * Recherche par email.
     * Si l'utilisateur ne saisit pas un input contenant un @, il est invité à recommencer
     * Et affichage du résultat de recherche
     *
     * @param annuaire annuaire
     */
    public void chercherParEmail(HashMap<String, Particulier> annuaire) {
        String email;
        do {
            afficherCyan("Veuillez saisir un email à rechercher :");
            email = scannerClavier.next();
        } while (!email.contains("@"));

        affRes.afficherResultatRecherche(annuaire.get(email));
    }

    /**
     * Recherche par Nom.
     * L'utilisateur est invité à saisir un nom à rechercher dans l'annuaire.
     * Le champ Particulier.getNom() est comparé à l'input de l'utilisateur
     * Et affichage du résultat de recherche
     *
     * @param annuaire annuaire
     */
    public void chercherParNom(HashMap<String, Particulier> annuaire) {
        List<Particulier> liste;

        afficherCyan("Veuillez saisir un nom à rechercher :");
        String nom = scannerClavier.next();
        liste = annuaire.values().stream()
                .filter(p -> p.getNom().equalsIgnoreCase(nom))
                .sorted(Comparator.comparing(Particulier::getPrenom))
                .limit(10)
                .sorted((p1, p2) -> p2.getDateAjout().compareTo(p1.getDateAjout()))
                .toList();
        //filter : filtre sur le nom donné en input
        //sorted (1) : on trie sur le prénom du Particulier (le nom étant le meme)
        //limit : on limite la taille de la liste à 10
        //sorted (1) : on compare la date d'ajout de l'objet n+1 par rapport à n (ce qui donne que les dates les plus récentes sont en premieres dans la liste finale)
        //toList : on convertit le stream en liste

        affRes.afficherResultatRecherche(liste);
    }

    /**
     * Recherche par Profil
     * L'utilisateur est invité à choisir un profil à rechercher (Auditeur, Enseignant ou Direction)
     * Si l'input de l'utilisateur ne correspond pas à l'initiale d'un des choix, il est invité à recommencer.
     * Et Affichage du résultat de recherche
     *
     * @param annuaire annuaire
     */
    public void chercherParProfil(HashMap<String, Particulier> annuaire) {
        List<Particulier> liste;
        boolean profilConforme = false;
        String profil;
        do {
            afficherCyan("Veuillez saisir la lettre () d'un profil à rechercher\n" +
                    " (A)uditeur, (E)nseignant, (D)irection) :");
            profil = scannerClavier.next();
            if (profil.equalsIgnoreCase("a") || profil.equalsIgnoreCase("e") || profil.equalsIgnoreCase("d")) {
                profilConforme = true;
            }
        } while (!profilConforme);

        Particulier.Profil pro = null;
        switch (profil) {
            case "a", "A" -> pro = Particulier.Profil.Auditeur;
            case "e", "E" -> pro = Particulier.Profil.Enseignant;
            case "d", "D" -> pro = Particulier.Profil.Direction;
            default -> afficherRouge(ENTRER_ABCD);
        }
        Particulier.Profil finalPro = pro;

        liste = annuaire.values().stream()
                .filter(p -> p.getProfil() == finalPro)
                .sorted(Comparator.comparing(Particulier::getNom))
                .limit(10)
                .sorted((p1, p2) -> p2.getDateAjout().compareTo(p1.getDateAjout()))
                .toList();
        //filter : filtre sur le profil donné en input
        //sorted (1) : on trie sur les noms des Particuliers
        //limit : on limite la taille de la liste à 10
        //sorted (2) : on compare la date d'ajout de l'objet n+1 par rapport à n (ce qui donne que les date les plus récentes sont en premieres dans la liste finale)
        //toList : on convertit le stream en liste

        affRes.afficherResultatRecherche(liste);
    }
}
