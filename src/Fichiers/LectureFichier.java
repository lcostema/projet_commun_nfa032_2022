package Fichiers;

import Utilisateurs.Compte;
import Utilisateurs.Particulier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import static Affichage.Accueil.*;

/**
 * Classe pour charger le contenu des fichiers annuaire.txt et comptes.txt dans des Hashmaps
 */
public class LectureFichier {
    public HashMap<String, Compte> getComptes() {
        return comptes;
    }
    public HashMap<String, Particulier> getAnnuaire() {
        return annuaire;
    }

    /**
     * Méthode pour lire le fichier comptes et stocker les données dans la Hashmap comptes
     * @param file file
     * @throws IOException IOException
     */
    public void lectureComptes(File file) throws IOException {
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);

        String var = br.readLine();
        while (var != null) {
            //on récupère les champs dans un tableau de String
            String[] ligne = var.split(";");
            Compte c = null;
            //on crée le Compte
            Particulier particulier = annuaire.get(ligne[0]);
            particulier.setMotDePasse(ligne[1]);
            /* Cas d'un admin présent dans l'annuaire */
            if (Compte.Role.valueOf(ligne[2]) == Compte.Role.Administrateur) {
                particulier.setRole(Compte.Role.Administrateur);
            }
            c = particulier.getCompte();
            //on l'ajoute dans le hashmap
            comptes.put(ligne[0], c);
            var = br.readLine();
        }
        br.close();
        in.close();
    }

    /**
     * Méthode pour lire le fichier annuaire et stocker les données dans la Hashmap annuaire
     * @param file file
     * @throws IOException IOException
     * @throws ParseException ParseException
     */
    public void lectureAnnuaire(File file) throws IOException, ParseException {
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);

        String var = br.readLine();
        while (var != null) {
            //on récupère les champs dans un tableau de String
            String[] ligne = var.split(";");
            String lastName = ligne[0];
            String firstName = ligne[1];
            String email = ligne[2];
            String postalAdress = ligne[3];
            Date birthday = dateFormatter.parse(ligne[4]);
            Particulier.Profil profil = Particulier.Profil.valueOf(ligne[5]);
            Date joinDate = dateFormatter.parse(ligne[6]);
            Date updateDate = dateFormatter.parse(ligne[7]);

            Particulier p = new Particulier(email, null, lastName, firstName, postalAdress, birthday, joinDate, updateDate, profil);

            annuaire.put(email, p);

            var = br.readLine();
        }
        in.close();
        br.close();
    }
}