import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Compte {
// Note : le fichier (donc, sa map) "comptes" doit contenir au minimum
//  les adresses email des utilisateurs du fichier (map) personnes
    static boolean emailMatch, roleMatch, mdpMatch;
    static String roleInput;
    static int nbErreurs;
    String email, mdp, role;

    public Compte(String email, String mdp, String role) {
        super();
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }

    // authentification(String role) fonctionne pour admin ou user selon le "role" passé en argument
    public static void authentification(String role) throws Exception {
        roleInput = role;
        String authentEmail, authentMdp;
        Scanner scannerMdp = new Scanner(System.in);
        if(Objects.equals(roleInput, "admin")) {
            System.out.print("email administrateur :\n");
        } else if (Objects.equals(roleInput, "user")) {
            System.out.print("email utilisateur :\n");
        }
        while (scannerMdp.hasNext()) {
            authentEmail = scannerMdp.nextLine();
            System.out.print("Mot de passe :\n");
            authentMdp = scannerMdp.nextLine();
            if (role.equals("admin")) {
                authentiCompte("admin", authentEmail, authentMdp);
                if (mdpMatch) {
                    // comme il est maintenant authentifié, on lance le menu de l'admin
                    Gestion.menuAdmin();
                }
            } else if (role.equals("user")) {
                authentiCompte("user", authentEmail, authentMdp);
                if (mdpMatch) {
                    // comme il est maintenant authentifié, on laisse l'utilisateur faire ses modifs
                    Gestion.main(new String[]{"d"}); // pour test
                }
            }
        }
    }

    // authentiCompte() vérifie que les arguments passés matchent avec les infos du fichier
    // ...en fait, de la map issue du fichier compte
    public static void authentiCompte(String authentRole, String authentEmail, String authentMdp) throws Exception {
        // on initialise les Match à false dès le début pour qu'ils perdent une valeur true précédente
        emailMatch = false;
        roleMatch = false;
        mdpMatch = false;
        for (LinkedHashMap<String, String> readMap : Annuaire.mapCompte) {
            for (Map.Entry<String, String> ignored : readMap.entrySet()) {
                if (readMap.get("email").equals(authentEmail)) {
                    emailMatch = true;
                    if (readMap.get("role").equals(authentRole)) {
                        roleMatch = true;
                        if (readMap.get("mdp").equals(authentMdp)) {
                            mdpMatch = true;
                        }
                    }
                }
            }
        }
        // ci-dessous, gère les erreurs (!xxxMatch) et retourne à main() au bout de 3 essais infructueux
        if (!emailMatch) {
            nbErreurs += 1;
            Gestion.afficherYellow("Pas trouvé cet email ...!");
            if (nbErreurs < 3) {
                authentification(roleInput);
            } else {
                nbErreurs = 0;
                Gestion.afficherRouge("Trop de tentatives erronées !");
                Gestion.main(new String[]{"a"});
            }
        }
        if (!roleMatch) {
            nbErreurs += 1;
            if(Objects.equals(roleInput, "admin")) {
                Gestion.afficherYellow("Vous n'êtes pas administrateur ...!");
            } else if (Objects.equals(roleInput, "user")) {
                Gestion.afficherRouge("Ceci est un compte administrateur, \n" +
                        " veuillez utiliser une adresse mail utilisateur");
            }
            if (nbErreurs < 3) {
                authentification(roleInput);
            } else {
                nbErreurs = 0;
                Gestion.afficherRouge("Trop de tentatives erronées !");
                Gestion.main(new String[]{"a"});
            }
        }
        if (!mdpMatch) {
            nbErreurs += 1;
            Gestion.afficherYellow("Mot de passe erroné ...!");
            if (nbErreurs < 3) {
                authentification(roleInput);
            } else {
                nbErreurs = 0;
                Gestion.afficherRouge("Trop de tentatives erronées !");
                Gestion.main(new String[]{"a"});
            }
        }
    }

    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        Compte admin = (Compte) obj;
        return email.equals(admin.email) && mdp.equals(admin.mdp) && role.equals(admin.role);
    }
}
