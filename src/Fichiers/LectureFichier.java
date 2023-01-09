package Fichiers;

import Utilisateurs.Compte;
import Utilisateurs.Particulier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Classe pour charger le contenu des fichiers annuaire.txt et comptes.txt dans des Hashmaps
 */
public class LectureFichier {
    HashMap<String, Compte> comptes = new HashMap<>();
    HashMap<String, Particulier> annuaire = new HashMap<>();

    public HashMap<String, Compte> getComptes() {
        return comptes;
    }
    public HashMap<String, Particulier> getAnnuaire() {
        return annuaire;
    }

    /**
     * Méthode pour lire le fichier comptes et stocker les données dans la Hashmap comptes
     * @param file
     * @throws IOException
     */
    public void lectureComptes(File file) throws IOException {
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);

        String var = br.readLine();
        while (var != null) {
            //on récupère les champs dans un tableau de String
            String[] ligne = var.split(";");
            //on crée le Compte
            Compte c = new Compte(ligne[0], ligne[1], Compte.Role.valueOf(ligne[2]));
            //on l'ajoute dans le hashmap
            comptes.put(ligne[0], c);
            var = br.readLine();
        }
        br.close();
        in.close();
    }

    /**
     * Méthode pour lire le fichier annuaire et stocker les données dans la Hashmap annuaire
     * @param file
     * @throws IOException
     * @throws ParseException
     */
    public void lectureAnnuaire(File file) throws IOException, ParseException {
        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);

        String var = br.readLine();
        while (var != null) {
            //on récupère les champs dans un tableau de String
            String[] ligne = var.split(";");
            String firstName = ligne[0];
            String lastName = ligne[1];
            String email = ligne[2];
            String postalAdress = ligne[3];
            String birthday = ligne[4];
            Date dBirthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
            Particulier.Profil profil = Particulier.Profil.valueOf(ligne[5]);
            String joinDate = ligne[6];
            Date dJoinDate = new SimpleDateFormat("dd/MM/yyyy").parse(joinDate);
            String updateDate = ligne[7];
            Date dUpdateDate = new SimpleDateFormat("dd/MM/yyyy").parse(updateDate);

            String password = "Coder la fonction qui va chercher le mdp dans le Hashmap comptes";

            Particulier p = new Particulier(email, password, lastName, firstName, postalAdress, dBirthday, dJoinDate, dUpdateDate, profil);

            annuaire.put(email, p);

            var = br.readLine();
        }
        in.close();
        br.close();
    }
}