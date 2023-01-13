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



public class AjouterAdmin extends MenuAdmin {

    /**
     * Ajout d'un administrateur
     * Selon role = Administrateur || Particulier ajout au seul fichier compte ou également au fichier annuaire.
     * @throws IOException
     * @throws ParseException
     */
    public static void creationAdmin(HashMap<String, Compte> comptes) throws IOException, ParseException{

    //renseignement et vérification de l'email
        afficherVert("Veuillez indiquer l'email du compte administrateur à ajouter : ");

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
            
    //Création du Compte Admin + enregistrement dans le fichier comptes.txt
        Admin nouvelAdmin = new Admin(email, motDePasse);
           comptes.put(email, nouvelAdmin);

        EcritureFichier ef = new EcritureFichier();
        ef.ecrireComptes(comptes, comptesTxt);

        LectureFichier lf = new LectureFichier();
        lf.lectureComptes(comptesTxt);
        comptes = lf.getComptes();

    //Controle de l'enregistrement et fin du processus pour la création d'Admin
        if ((comptes.get(email) != null)){
            afficherRouge("Administrateur ajouté !\n"); 
            afficherMenuAdmin();
        } 
  
    }
}
