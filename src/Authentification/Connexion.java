package Authentification;

import Affichage.Accueil;
import Affichage.MenuAccueil;
import Utilisateurs.Compte;
import Utilisateurs.Particulier;

import java.io.IOException;

/**
 * Classe de connexion
 */
public class Connexion extends MenuAccueil {
    public static int nbErreurs = 0;
    private final static int MAX_ERREURS = 3;
    public static String authentEmail = null;

    /**
     * Fonction principale d'authentification
     *
     * @param role Role à authentifier
     * @return La connexion est établie
     */
    public static boolean authentification(Particulier.Role role) throws IOException {
        while (checkErreurs()) {
            Accueil.afficherCyan("Email " + role + " :");
            if (scannerClavier.hasNext()) {
                authentEmail = scannerClavier.next().toLowerCase();
                if (comptes.get(authentEmail) == null) {
                    Accueil.afficherJaune("Pas trouvé cet email ...!");
                    nbErreurs++;
                    return authentification(role);
                }
                Compte.Role authentRole = comptes.get(authentEmail).getRole();
                if (role == Compte.Role.Administrateur && authentRole != Compte.Role.Administrateur) {
                    Accueil.afficherJaune("Vous n'êtes pas administrateur ...!");
                    ouvrirMenuAccueil();
                }
                while (!checkMDP()) {
                    if (!checkErreurs()) {
                        nbErreurs = 0;
                        Accueil.afficherRouge("Trop de tentatives erronées !");
                        ouvrirMenuAccueil();
                    }
                }
                nbErreurs = 0;
                afficherNormal("\n " + role + " authentifié !");
                return true;
            }
        }
        Accueil.afficherRouge("Trop de tentatives erronées !");
        nbErreurs = 0;
        ouvrirMenuAccueil();
        return false;
    }

    /**
     * Fonction de saisie et vérification du mot de passe
     *
     * @return Validité après 3 essais du mot de passe
     */
    private static boolean checkMDP() {
        afficherCyan("Mot de passe :");
        String authentMDP = scannerClavier.next();
        if (!comptes.get(authentEmail).getMotDePasse().equals(authentMDP)) {
            Accueil.afficherJaune("Mot de passe erroné ...!");
            nbErreurs++;
            return false;
        }
        return true;
    }

    /**
     * Fonction de vérification des erreurs
     *
     * @return Retourne vrai tant que les erreurs sont < MAX_ERREURS
     */
    private static boolean checkErreurs() {
        return nbErreurs < MAX_ERREURS;
    }
}