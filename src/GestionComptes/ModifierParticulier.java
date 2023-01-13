package GestionComptes;

import Affichage.Accueil;
import Affichage.ResultatRecherche;
import Fichiers.EcritureFichier;
import Utilisateurs.Particulier;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static Affichage.Accueil.*;

/**
 * Classe pour modifier
 */
public class ModifierParticulier {

    /**
     * Permet la saisie de l'email
     * @return Réussite de la modification des données
     * @throws IOException Erreur d'écriture dans les fichiers
     */
    public static boolean AffichageSaisieEmail() throws IOException {
        String email;
        do {
            Accueil.afficherNormal("Veuillez saisir l'email du particulier à modifier : ");
            email = scannerClavier.next();
        } while (!email.contains("@"));
        return ModificationDonnees(email);
    }

    /**
     * Modifie les données du particulier
     * @param email Email du particulier à modifier
     * @return Réussite de la modification des données
     * @throws IOException Erreur d'écriture dans les fichiers
     */
    public static boolean ModificationDonnees(String email) throws IOException {
        Particulier particulier = annuaire.get(email);
        new ResultatRecherche().afficherResultatRecherche(particulier);
        scannerClavier.nextLine();
        String line = "";
        while (!line.matches("^[A-zÀ-ú]*$") || line.isEmpty()) {
            afficherVert("Modifier Nom (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()){ break; }
            particulier.setNom(line);
        }
        line = "";
        while (!line.matches("^[A-zÀ-ú]*$") || line.isEmpty()) {
            afficherVert("Modifier Prénom (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()){ break; }
            particulier.setPrenom(line);
        }
        line = "";
        while (!line.matches("^[a-z@.]*$") || line.isEmpty()) {
            afficherVert("Modifier Email (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()){ break; }
            particulier.setEmail(line);
        }
        line = "";
        while (!line.matches("^[A-zÀ-ú0-9 ,]*$") || line.isEmpty()) {
            afficherVert("Modifier Adresse postale (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()){ break; }
            particulier.setAdressePostale(line);
        }
        line = "";
        while (line.isEmpty()) {
            afficherVert("Modifier Date de Naissance (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()){ break; }
            try {
                Date dateNaissance = dateFormatter.parse(line);
                particulier.setDateNaissance(dateNaissance);
            } catch (ParseException exception) {
                afficherRouge("Erreur de format de date (jj/mm/aaaa). Retour au menu Admin");
                line = "";
            }
        }
        line = "";
        while (line.isEmpty()) {
            afficherVert("Modifier Profil (Laisser vide en cas de non modification) " + java.util.Arrays.asList(Particulier.Profil.values()) + ":");
            line = scannerClavier.nextLine();
            if (line.isEmpty()) {
                break;
            }
            try {
                particulier.setProfil(Particulier.Profil.valueOf(line));
            } catch (IllegalArgumentException err) {
                afficherRouge("Erreur de format profil : " + java.util.Arrays.asList(Particulier.Profil.values()));
                line = "";
            }
        }
        particulier.setDateMaj(new Date(System.currentTimeMillis()));
        EcritureFichier.ecrireAnnuaire(annuaire, FICHIER_ANNUAIRE);
        EcritureFichier.ecrireComptes(comptes, FICHIER_COMPTES);
        afficherJaune("Les données ont été modifiées.");
        return true;
    }
}