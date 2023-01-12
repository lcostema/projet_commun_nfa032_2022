package Fichiers;

import Utilisateurs.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

import static Affichage.Accueil.dateFormatter;

/**
 * Cette classe permet de sauvegarder le contenu des Hashmaps annuaire et comptes dans des fichiers .txt
 */
public class EcritureFichier {

    /**
     * Méthode pour écrire dans le fichier comptes
     * @param comptes
     * @param file
     * @throws IOException
     */
    public static void ecrireComptes(HashMap<String, Compte> comptes, File file) throws IOException {
        //Méthode qui prend en argument la variable comptes(Hashmap<String, Utilisateur>) où String est l'email

        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        comptes.forEach((u, compte) -> {
            String email = u;
            String password = compte.getMotDePasse();
            String role = compte.getRole().toString();

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

    /**
     * Méthode qui prend en argument la variable annuaire(Hashmap<String, User>) où String est l'email
     * @param annuaire
     * @param file
     * @throws IOException
     */
    public void ecrireAnnuaire(HashMap<String, Particulier> annuaire, File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        annuaire.forEach((p, particulier) -> {
            String lastName = particulier.getNom();
            String firstName = particulier.getPrenom();
            String email = particulier.getEmail();
            String postalAdress = particulier.getAdressePostale();
            String birthday = dateFormatter.format(particulier.getDateNaissance());
            String profil = particulier.getProfil().toString();
            String joinDate = dateFormatter.format(particulier.getDateAjout());
            String updateDate = dateFormatter.format(particulier.getDateMaj());

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