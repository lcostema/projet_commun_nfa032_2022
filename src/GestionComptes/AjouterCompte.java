package GestionComptes;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import Fichiers.EcritureFichier;
import Utilisateurs.*;
import static Affichage.Accueil.*;

/**
 * Classe pour l'ajout d'un utilisateur dans le fichier compte et utilisateur
 */
public class AjouterCompte {
    /**
     * Méthode pour créer un compte ainsi que les informations correspondantes dans l'annuaire
     * @param role Role de l'utilisateur à créer
     * @return  Réussite de l'ajout
     * @throws IOException Erreur d'écriture sur les fichiers
     */
    public static boolean creationCompte(Compte.Role role) throws IOException {
        String email = "", motDePasse = "",
                nom = "", prenom = "", adressePostale = "",
                dateNaissanceInput = "", profilInput = "";
        Date dateNaissance = null, dateAjout, dateMaj;
        Particulier.Profil profil = null;

        scannerClavier.nextLine();
        while (!email.matches("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.\\p{L}{2,})$") || email.isEmpty()) {
            afficherVert("Veuillez indiquer l'email du compte " + role + " à ajouter (Laisser vide pour quitter le menu) :");
            email = scannerClavier.nextLine().toLowerCase();
            if (comptes.get(email) != null){
                Affichage.Accueil.afficherRouge("Cet email est déjà associé à un compte. \nRetour au menu administrateur.");
                return false;
            } else if (email.isEmpty()) {
                Affichage.Accueil.afficherRouge("Retour au menu administrateur.");
                return false;
            }
        }
        while (!motDePasse.matches("^\\S*$") || motDePasse.isEmpty()) {
            afficherVert("Veuillez indiquer le mot de passe du Particulier à ajouter :");
            motDePasse = scannerClavier.nextLine();
        }

        if (role == Compte.Role.Administrateur) {
            Compte admin = new Compte(email, motDePasse, Compte.Role.Administrateur);
            comptes.put(email, admin);
            return controleEnregistrement(email, role);
        }

        while (!nom.matches("^[A-zÀ-ú]*$") || nom.isEmpty()) {
            afficherVert("Veuillez indiquer le nom du Particulier à ajouter :");
            nom = scannerClavier.nextLine();
        }
        while (!prenom.matches("^[A-zÀ-ú]*$") || prenom.isEmpty()) {
            afficherVert("Veuillez indiquer le prénom du Particulier à ajouter :");
            prenom = scannerClavier.nextLine();
        }
        while (!adressePostale.matches("[A-zÀ-ú0-9 ,]*$") || adressePostale.isEmpty()) {
            afficherVert("Veuillez indiquer l'adresse postale du Particulier à ajouter :");
            adressePostale = scannerClavier.nextLine();
        }
        while (dateNaissanceInput.isEmpty()) {
            afficherVert("Veuillez indiquer la date de naissance (jj/mm/aaaa) du Particulier à ajouter :");
            dateNaissanceInput = scannerClavier.nextLine();
            try {
                dateNaissance = dateFormatter.parse(dateNaissanceInput);
            } catch (ParseException exception) {
                afficherRouge("Erreur de format de date (jj/mm/aaaa). Retour au menu Admin");
                dateNaissanceInput = "";
            }
        }
        while (profilInput.isEmpty()) {
            afficherVert("Veuillez indiquer le profil du Particulier à ajouter " + java.util.Arrays.asList(Particulier.Profil.values()) + ":");
            profilInput = scannerClavier.nextLine();
            try {
                profil = Particulier.Profil.valueOf(profilInput);
            } catch (IllegalArgumentException err) {
                afficherRouge("Erreur de format profil : " + java.util.Arrays.asList(Particulier.Profil.values()));
                profilInput = "";
            }
        }
        dateAjout = new Date(System.currentTimeMillis());
        dateMaj = new Date(System.currentTimeMillis());
        Particulier particulier = new Particulier(email, motDePasse, nom, prenom, adressePostale, dateNaissance, dateAjout, dateMaj, profil);

        comptes.put(email, particulier);
        annuaire.put(email, particulier);
        return controleEnregistrement(email, role);
    }
    public static boolean controleEnregistrement(String email, Compte.Role role) throws IOException {
        if ((comptes.get(email) != null)){
            EcritureFichier.ecrireAnnuaire(annuaire, FICHIER_ANNUAIRE);
            EcritureFichier.ecrireComptes(comptes, FICHIER_COMPTES);
            afficherRouge(role + " ajouté !");
            return true;
        } else {
            afficherRouge("Erreur lors de l'ajout du : " + role);
            return false;
        }
    }
}