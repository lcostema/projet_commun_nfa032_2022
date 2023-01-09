package Recherche;

import Fichiers.LectureFichier;
import Utilisateurs.Particulier;

import java.io.File;
import java.util.*;


public class Recherche {

    public Particulier chercherParEmail(HashMap<String, Particulier> annuaire, Scanner sc){
        System.out.println("Veuillez saisir un email à rechercher : ");
        String email = sc.next();

        System.out.println(annuaire.get(email));

        return annuaire.get(email);
    }

    public List<Particulier> chercherParNom(HashMap<String, Particulier> annuaire, Scanner sc){
        List<Particulier> liste;

        System.out.println("Veuillez saisir un nom à rechercher : ");
        String nom = sc.next();
        liste = annuaire.values().stream().filter(p -> p.getNom().equals(nom)).toList();

        System.out.println(liste);

        //return liste pour affichage ou appel affichage ICI ?
        return liste;
    }

    public List<Particulier> chercherParProfil(HashMap<String, Particulier> annuaire, Scanner sc){
        List<Particulier> liste;
        boolean profilConforme = false;
        String profil;
        do{
            System.out.println("Veuillez saisir un profil à rechercher (A)uditeur, (E)nseignant, (D)irection): ");
            profil = sc.next();
            if (profil.equals("A") || profil.equals("a") || profil.equals("E") || profil.equals("e")|| profil.equals("D") || profil.equals("d")){
                profilConforme=true;
            }
        } while (!profilConforme);

        Particulier.Profil pro = null;
        switch (profil) {
            case "a", "A" -> pro = Particulier.Profil.Auditeur;
            case "e", "E" -> pro = Particulier.Profil.Enseignant;
            case "d", "D" -> pro = Particulier.Profil.Direction;
        }

        Particulier.Profil finalPro = pro;
        liste = annuaire.values().stream().filter(p -> p.getProfil() == finalPro).toList();

        System.out.println(liste);

        return liste;
    }
}
