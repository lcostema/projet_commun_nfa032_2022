import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Compte {

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
                    Gestion.menuAdmin();
                }
            } else if (role.equals("user")) {
                authentiCompte("user", authentEmail, authentMdp);
                if (mdpMatch) {
                    // todo: lancer menu "Modifier mes informations personnelles"
                    //Gestion.menuModifInfosPerso();
                    String zozo = "zozo";
                    Gestion.main(new String[]{"d"}); // pour test
                }
            }
        }
    }

    public static void authentiCompte(String authentRole, String authentEmail, String authentMdp) throws Exception {
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
