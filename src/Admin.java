import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Admin {

    static boolean emailMatch, roleMatch, mdpMatch;
    static String roleInput;
    static int nbErreurs;
    String email, mdp, role;

    public Admin(String email, String mdp, String role) {
        super();
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }

    public static void authentificationAdmin(String role) throws Exception {
        roleInput = role;
        String authentEmail, authentMdp;
        Scanner scannerMdp = new Scanner(System.in);
        System.out.print("email administrateur :\n");
        while (scannerMdp.hasNext()) {
            authentEmail = scannerMdp.nextLine();
            System.out.print("Mot de passe :\n");
            authentMdp = scannerMdp.nextLine();
            if (role.equals("admin")) {
                rechercheComptes("admin", authentEmail, authentMdp);
                if (mdpMatch) {
                    Gestion.menuAdmin();
                }
            } else if (role.equals("modifInfosPerso")) {
                rechercheComptes("user", authentEmail, authentMdp);
                if (mdpMatch) {
                    // todo: lancer menu "Modifier mes informations personnelles"
                    //Gestion.menuModifInfosPerso();
                    String zozo = "zozo";
                }
            }
        }
    }

    public static void rechercheComptes(String authentRole, String authentEmail, String authentMdp) throws Exception {
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
                authentificationAdmin(roleInput);
            } else {
                nbErreurs = 0;
                Gestion.afficherRouge("Trop de tentatives erronées !");
                Gestion.main(new String[]{"a"});
            }
        }
        if (!roleMatch) {
            nbErreurs += 1;
            Gestion.afficherYellow("Vous n'êtes pas administrateur ...!");
            if (nbErreurs < 3) {
                authentificationAdmin(roleInput);
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
                authentificationAdmin(roleInput);
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
        Admin admin = (Admin) obj;
        return email.equals(admin.email) && mdp.equals(admin.mdp) && role.equals(admin.role);
    }
}
