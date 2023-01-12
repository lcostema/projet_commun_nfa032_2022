package GestionComptes;

import Affichage.Accueil;
import Affichage.MenuAdmin;
import Fichiers.*;
import Utilisateurs.*;
import Utilisateurs.Compte.Role;

import java.util.HashMap;



public class AjouterCompte {

    /**
     * Ajout de compte.
     * Test d'un email valide avec la présence d'un "@" et d'un "."
     * Selon role = Administrateur || Particulier ajout au seul fichier compte ou également au fichier annuaire.
     */
    public static void creationCompte(HashMap<String, Compte> comptes, Role role){

        //renseignement et vérification de l'email
        Affichage.Accueil.afficherVert("Veuillez indiquer l'email du compte " + role + " à ajouter : ");

        String email = Affichage.Accueil.scannerClavier.next();

        while (!email.contains("@")) {
            Affichage.Accueil.afficherRouge("Veuillez saisir un email valide. ");
            email = Affichage.Accueil.scannerClavier.next();
        } 

        if (comptes.get(email) != null){
            Affichage.Accueil.afficherRouge("Cet email est déjà associé à un compte. \nRetour au menu administrateur."); 
            Affichage.MenuAdmin.afficherMenuAdmin();
        }


        //renseignement du mot de passe
        Affichage.Accueil.afficherVert("Veuillez indiquer le mot de passe du nouveau compte :"); 
        String motDePasse =  Affichage.Accueil.scannerClavier.next();
            

        //Création du compte + enregistrement dans le fichier comptes.txt
        Compte nouveauCompte = new Compte(email, motDePasse, role);
        comptes.put(email, nouveauCompte);
        //Fichiers.EcritureFichier.ecrireComptes(comptes, Affichage.Accueil.comptesTxt);
            
        /* 
        //Pour les particuliers, ajout à l'annuaire
        if (nouveauCompte.getRole() == Role.Particulier){
            Affichage.Accueil.afficherVert("Veuillez indiquer le nom du Particulier à ajouter :"); 
            String nom =  Affichage.Accueil.scannerClavier.next();
            Affichage.Accueil.afficherVert("Veuillez indiquer le prénom du Particulier à ajouter :"); 
            String prenom =  Affichage.Accueil.scannerClavier.next();
            Affichage.Accueil.afficherVert("Veuillez indiquer l'adresse postale du Particulier à ajouter :"); 
            String adressePostale =  Affichage.Accueil.scannerClavier.next();
            Affichage.Accueil.afficherVert("Veuillez indiquer la date de Naissance du Particulier à ajouter :"); 
            Date dateNaissance =  Affichage.Accueil.scannerClavier.next();
            Affichage.Accueil.afficherVert("Veuillez indiquer le profil du Particulier à ajouter (Auditeur, Enseignant ou Direction) :"); 
            String profil =  Affichage.Accueil.scannerClavier.next();

            //gestion de la dateAjout = dateMaj

            Particulier nouveauParticulier = new Particulier (email,);
            
            

        }*/

  
    }
}
