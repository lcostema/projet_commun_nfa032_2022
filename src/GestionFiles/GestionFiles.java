package GestionFiles;


import Utilisateurs.Particulier;
import Utilisateurs.Utilisateur;

import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;

public class GestionFiles {

    public HashMap<String, Utilisateur> chargerUtilisateurs(File file) throws IOException {

        HashMap<String, Utilisateur> comptes = new HashMap<>();

        FileReader in = new FileReader(file);
        BufferedReader br = new BufferedReader(in);

        String var = br.readLine();
        while (var != null) {

            //on recupere les champs dans un tableau de String
            String[] ligne = var.split(";");

            //on attribu la variable role en fonction de valeur du champ [2]
            Utilisateur.Role role;
            if (ligne[2].equals("admin")) {
                role = Utilisateur.Role.Administrateur;
            } else {
                role = Utilisateur.Role.Particulier;
            }

            //On créé l'utilisateur
            Utilisateur u = new Utilisateur(ligne[0], ligne[1], role);

            //on l'ajoute dans le hashmap
            comptes.put(ligne[0], u);

            var = br.readLine();
        }

        br.close();
        in.close();

        return comptes;
    }

    public HashMap<String, Particulier> chargerParticuliers(File file) throws IOException, ParseException {
        HashMap<String, Particulier> annuaire = new HashMap<>();

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
            Particulier.Profil pProfil;
            if (profil.equals("auditeur")) {
                pProfil = Particulier.Profil.Auditeurs;
            } else if (profil.equals("Enseignant")) {
                pProfil = Particulier.Profil.Enseignants;
            } else if (profil.equals("direction")) {
                pProfil = Particulier.Profil.Direction;
            } else {
                pProfil = null;
            }


            String joinDate = ligne[6];
            Date dJoinDate = new SimpleDateFormat("dd/MM/yyyy").parse(joinDate);
            String updateDate = ligne[7];
            Date dUpdateDate = new SimpleDateFormat("dd/MM/yyyy").parse(updateDate);

            String password = "Coder la fonction qui va chercher le mdp dans le Hasmap comptes";

            Particulier p = new Particulier(email, password, lastName, firstName, postalAdress, dBirthday, dJoinDate, dUpdateDate, pProfil);

            annuaire.put(email, p);

            var = br.readLine();
        }

        in.close();
        br.close();

        return annuaire;
    }

    public void sauvergarderUtilisateurs(HashMap<String, Utilisateur> comptes, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        comptes.forEach((u, utilisateur) -> {
            String email = u;
            String password = utilisateur.getMotdepasse();
            String role = utilisateur.getRole();
            String ligne = email+";"+password+";"+role;
            try {
                bw.write(ligne);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        bw.flush();

        bw.close();
        fw.close();
    }

    public void sauvegarderParticuliers(HashMap<String, Particulier> annuaire, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        annuaire.forEach((p, particulier) -> {
            String lastName = particulier.getNom();
            String firstName = particulier.getPrenom();
            String email = particulier.getEmail();
            String postalAdress = particulier.getAdressePostale();
            String birthday = particulier.getDateNaissance().toString();
            String profil = particulier.getProfil().toString();
            String joinDate = particulier.getDateAjout().toString();
            String updateDate = particulier.getDateMaj().toString();

            String ligne = lastName +";"+ firstName +";"+ email +";"+ postalAdress +";"+ birthday +";"+ profil +";"+ joinDate +";"+ updateDate;
            try {
                bw.write(ligne);
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        bw.flush();

        bw.close();
        fw.close();
    }

}

