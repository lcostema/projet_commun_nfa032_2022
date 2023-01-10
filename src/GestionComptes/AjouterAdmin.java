package GestionComptes;

import Affichage.Accueil;
import Fichiers.*;
import Utilisateurs.Compte;

import java.util.HashMap;



public class AjouterAdmin {
    
    //Authentification admin effectuée lors de l'accès au menu admin

    /**
     * Méthode pour ajouter un compte Admin
     */
    public static void ajoutAdmin(HashMap<String, Compte> comptes){
        
        //vérification de l'email
        Affichage.Accueil.afficherCyan("Veuillez indiquer l'email de l'administrateur à ajouter : ");
 
        String email = Affichage.Accueil.scannerClavier.nextLine();

        //Pour consulter le Hashmap :
//        comptes.get(KEY)

        //Pour ajoute au hashamp
//        comptes.put(STRING, COMPTE)


        //test pour vérifier si c'est un email ? Par exemple en testant si contient un "@" 
            
        //if (email == lf.getComptes().get(email)){
            //Affichage.Accueil.afficherRouge("Cet email est déjà utilisé");
            //Retour menu admin
        //}

        //else {
            // Affichage.Accueil.afficherCyan("Veuillez indiquer le mot de passe du nouveau compte :"); 
            //String mdp =  Affichage.Accueil.scannerClavier.nextLine();
            //AjoutAdmin : new compte (email, mdp, admin) + ajout dans le hasmap comptes
            //}

            
        //enregistrement de comptes.txt
        //retour menu admin


       }       

}
