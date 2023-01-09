package Recherche;

import Utilisateurs.Particulier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe Recherche va permettre de requêter les Objets Particulier contenus dans le Hashmap annuaire.
 * La recherche peut se faire selon plusieurs critères:
 *  - Par nom (par String)
 *  - Par email (par String) On va vérifier la présence de '@' dans le String donné en paramètre
 *  - Par Profil
 */
public class Recherche {


    public List<Particulier> rechercherDansAnnuaire(HashMap<String, Particulier> annuaire, String str){
        List<Particulier> liste = new ArrayList<>();

        if (str.contains("@")){
            liste.add(annuaire.get(str));
        } else {
            liste = annuaire.values().stream().filter(p -> p.getNom().equals(str)).toList();
        }

        //return liste pour affichage ou appel affichage ICI ?
        return liste;
    }

    public List<Particulier> rechercherDansAnnuaire(HashMap<String, Particulier> annuaire, Particulier.Profil profil) {
        List<Particulier> liste = new ArrayList<>();

        for (Particulier p : annuaire.values()){
            if (p.getProfil() == profil){
                liste.add(p);
            }
        }

        //return liste pour affichage ou appel affichage ICI ?
        return liste;
    }


}
