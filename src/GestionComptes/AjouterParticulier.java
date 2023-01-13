package GestionComptes;

import Affichage.MenuAdmin;
import Fichiers.*;
import Utilisateurs.*;
import Utilisateurs.Compte.Role;
import Utilisateurs.Particulier.Profil;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class AjouterParticulier extends MenuAdmin {

    public static void creationParticulier(HashMap<String, Compte> comptes, HashMap<String, Particulier> annuaire) throws IOException, ParseException{

    //renseignement et vérification de l'email
        afficherVert("Veuillez indiquer l'email du particulier à ajouter : ");

        String email = scannerClavier.next();

        while (!email.contains("@")) {
            afficherRouge("Veuillez saisir un email valide. ");
            email = scannerClavier.next();
        } 

        if (comptes.get(email) != null){
            afficherRouge("Cet email est déjà associé à un compte. \nRetour au menu administrateur."); 
            afficherMenuAdmin();
        }


    //renseignement du mot de passe 
    //TODO : mettre des contraintes sur le mot de passe ? Nombre minimum de caractère à minima.
        afficherVert("Veuillez indiquer le mot de passe du nouveau compte :"); 
        String motDePasse =  scannerClavier.next();
            

    //Création du Particuliers + enregistrements dans les fichiers
        afficherVert("Veuillez indiquer le nom du Particulier à ajouter :"); 
        String nom =  scannerClavier.next();

        afficherVert("Veuillez indiquer le prénom du Particulier à ajouter :"); 
        String prenom =  scannerClavier.next();

        afficherVert("Veuillez indiquer l'adresse postale du Particulier à ajouter :");
        scannerClavier.nextLine();
        String adressePostale = scannerClavier.nextLine();
        while (!adressePostale.matches("^[A-zÀ-ú0-9 ]*$") || adressePostale.isEmpty()) {
            afficherRouge("Veuillez saisir une adresse valide. ");
            adressePostale = scannerClavier.nextLine();
        }

        afficherVert("Veuillez indiquer la date de Naissance du Particulier à ajouter :"); 
        Date dateDeNaissance = dateFormatter.parse(scannerClavier.next());

        Profil profil = null;
        String reponse = "";
        afficherVert("Veuillez indiquer le profil du Particulier à ajouter (a pour Auditeur, e pour Enseignant ou d pour Direction) :"); 
            do{
                reponse = scannerClavier.next();
                switch (reponse){
                    case "a" -> profil = Profil.Auditeur;
                    case "e" -> profil = Profil.Enseignant;
                    case "d" -> profil = Profil.Direction;
                    default -> afficherRouge("Veuillez entrer les lettres a, e ou d uniquement :");
                }
            }while (profil == null);

        Calendar calendrier = Calendar.getInstance();
        final Date dateAjout = calendrier.getTime();
        Date dateMaj = dateAjout;

    //Création du Particulier + enregistrement dans le fichier Annuaire
        Particulier nouveauParticulier = new Particulier (email, motDePasse, nom, prenom, adressePostale, dateDeNaissance, dateAjout, dateMaj, profil);
            
        comptes.put(email, nouveauParticulier);
        annuaire.put(email, nouveauParticulier);   
            
        EcritureFichier ef = new EcritureFichier();
        ef.ecrireComptes(comptes, comptesTxt);
        ef.ecrireAnnuaire(annuaire, annuaireTxt);

        LectureFichier lf = new LectureFichier();
        lf.lectureComptes(comptesTxt);
        comptes = lf.getComptes();
        lf.lectureAnnuaire(annuaireTxt);
        annuaire = lf.getAnnuaire();

    //Controle de l'enregistrement et fin du processus pour la création d'Admin
        if ((comptes.get(email) != null)&&(annuaire.get(email) != null)){
            afficherRouge("Particulier ajouté !\n"); 
            afficherMenuAdmin();
        }

    }
}
