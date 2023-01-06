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

public class LectureFichier {
//Cette classe permet de charger le contenu des fichiers annuaire.txt et comptes.txt dans des Hashmaps

    HashMap<String, Compte> comptes = new HashMap<>();

    HashMap<String, Particulier> annuaire = new HashMap<>();

    public HashMap<String, Compte> getComptes() {
        return comptes;
    }

    public HashMap<String, Particulier> getAnnuaire() {
        return annuaire;
    }


    /**
     *
     * @param file
     * @return
     * @throws IOException
     */
    public HashMap<String, Compte> lectureComptes(File file) throws IOException {



    FileReader in = new FileReader(file);
    BufferedReader br = new BufferedReader(in);

    String var = br.readLine();
    while (var != null) {

        //on recupere les champs dans un tableau de String
        String[] ligne = var.split(";");

        //on attribu la variable role en fonction de valeur du champ [2]

        //cas de enum
//        Utilisateur.Role role;
//        if (ligne[2].equals("admin")) {
//            role = Utilisateur.Role.Administrateur;
//        } else {
//            role = Utilisateur.Role.Particulier;
//        }

        //On créé le Compte
        Compte c = new Compte(ligne[0], ligne[1], ligne[2]);

        //on l'ajoute dans le hashmap
        comptes.put(ligne[0], c);

        var = br.readLine();
    }

    br.close();
    in.close();

    return comptes;
}

    /**
     *
     * @param file
     * @return
     * @throws IOException
     * @throws ParseException
     */

    public HashMap<String, Particulier> lectureAnnuaire(File file) throws IOException, ParseException {


        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);

        String var = br.readLine();
        while (var != null) {

            //on recupere les champs dans un tableau de String
            String[] ligne = var.split(";");
            String firstName = ligne[0];
            String lastName = ligne[1];
            String email = ligne[2];
            String postalAdress = ligne[3];
            String birthday = ligne[4];
            Date dBirthday = new SimpleDateFormat("dd/MM/yyyy").parse(birthday);
            String profil = ligne[5];

            //dans le cas d'un enum sur profil
//            Particulier.Profil pProfil = switch (profil) {
//                case "auditeur" -> Particulier.Profil.Auditeurs;
//                case "Enseignant" -> Particulier.Profil.Enseignants;
//                case "direction" -> Particulier.Profil.Direction;
//                default -> null;
//            };


            String joinDate = ligne[6];
            Date dJoinDate = new SimpleDateFormat("dd/MM/yyyy").parse(joinDate);
            String updateDate = ligne[7];
            Date dUpdateDate = new SimpleDateFormat("dd/MM/yyyy").parse(updateDate);

            String password = "Coder la fonction qui va chercher le mdp dans le Hasmap comptes";

            Particulier p = new Particulier(email, password, lastName, firstName, postalAdress, dBirthday, dJoinDate, dUpdateDate, profil);

            annuaire.put(email, p);

            var = br.readLine();
        }

        in.close();
        br.close();

        return annuaire;
    }

}
