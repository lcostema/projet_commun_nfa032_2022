package GestionComptes;

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
     * Permet la saisie de l'email du particulier à modifier (Administrateur uniquement!)
     *
     * @return Réussite de la modification des données
     * @throws IOException Erreur d'écriture dans les fichiers
     */
    public static boolean AffichageSaisieEmail() throws IOException {
        String email = "";
        while (email.isEmpty()) {
            afficherCyan("Veuillez saisir l'email du particulier à modifier \n(Laisser vide pour quitter le menu) : ");
            email = scannerClavier.next().toLowerCase();
            if (email.isEmpty()) {
                afficherJaune("Aucun email entré...");
                return false;
            } else if (annuaire.get(email) == null) {
                afficherRouge("Cet email n'est pas associé à un particulier.");
                email = "";
            }
        }
        return ModificationDonnees(email);
    }

    /**
     * Modifie les données du particulier
     *
     * @param email Email du particulier à modifier
     * @return Réussite de la modification des données
     * @throws IOException Erreur d'écriture dans les fichiers
     */
    public static boolean ModificationDonnees(String email) throws IOException {
        Particulier particulier = annuaire.get(email);
        new ResultatRecherche().afficherParticulier(particulier);
        scannerClavier.nextLine();
        String line = "";
        while (!line.matches("^[A-zÀ-ú]*$") || line.isEmpty()) {
            afficherCyan("\nModifier Nom (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()) {
                break;
            }
            particulier.setNom(line);
        }
        line = "";
        while (!line.matches("^[A-zÀ-ú]*$") || line.isEmpty()) {
            afficherCyan("Modifier Prénom (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()) {
                break;
            }
            particulier.setPrenom(line);
        }
        line = "";
        while (!line.matches("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.\\p{L}{2,})$") || line.isEmpty()) {
            afficherCyan("Modifier Email (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()) {
                break;
            }
            particulier.setEmail(line);
        }
        line = "";
        while (!line.matches("^[A-zÀ-ú0-9 ,]*$") || line.isEmpty()) {
            afficherCyan("Modifier Adresse postale (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()) {
                break;
            }
            particulier.setAdressePostale(line);
        }
        line = "";
        while (line.isEmpty()) {
            afficherCyan("Modifier Date de Naissance (Laisser vide en cas de non modification) :");
            line = scannerClavier.nextLine();
            if (line.isEmpty()) {
                break;
            }
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
            afficherCyan("Modifier Profil (Laisser vide en cas de non modification) " + java.util.Arrays.asList(Particulier.Profil.values()) + ":");
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