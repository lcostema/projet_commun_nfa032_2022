import Utilisateurs.Admin;
import Utilisateurs.Utilisateur;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Annuaire {
    public static Path dossierLocal;

    static {
        try {
            dossierLocal = Path.of(".").toRealPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final Map<Utilisateur, Compte> map;

    public Annuaire() {
        map = new HashMap<>();
    }

    public static Map<String, Map<String, String>> chargerPersonne(File fichier) throws Exception {
        Scanner scannerFichier;
        if (!fichier.exists()) {
            System.out.println(
                    "\n" +
                            "Pas trouvé le fichier" + fichier + ", il n'existe pas,\n" +
                            "ou il a été renommé, supprimé ou déplacé !");
            return null;
        } else {
            scannerFichier = new Scanner(new File(dossierLocal + "\\" + fichier));
        }
        String[] colonne = scannerFichier.nextLine().split(";");
        Map<String, Map<String, String>> mapPersonne = new TreeMap<>();
        int j = 0;
        while (scannerFichier.hasNextLine()) {
            String[] ligne = scannerFichier.nextLine().split(";");
            Map<String, String> mapLigne = new TreeMap<>();
            for (int i = 0; i < colonne.length; i++) {
                mapLigne.put(colonne[i], ligne[i]);
            }
            j += 1;
            mapPersonne.put(String.valueOf(j), mapLigne);
        }
        scannerFichier.close();
        return mapPersonne;
    }

    public static void testChargerPersonne() throws Exception {
        System.out.println(chargerPersonne(new File("personne.txt")));
        System.out.println(chargerPersonne(new File("compte.txt")));
    }

    public void ajouterPersonne(Utilisateur utilisateur, Compte compte) {
        if (utilisateur.getClass() == Admin.class) {
            //Ajouter administrateur uniquement au fichier comptes
            map.put(null, compte);
        } else {
            //Ajouter particulier aux deux fichiers
            map.put(utilisateur, compte);
        }
    }

    public void supprimerPersonne(Utilisateur utilisateur, Compte compte) {
        map.remove(utilisateur, compte);
    }

    public void modifierPersonne(Utilisateur utilisateur, Compte compte) {
        map.remove(utilisateur);
        map.put(utilisateur, compte);
    }

    public Compte trouverPersonne(Utilisateur utilisateur) {
        return map.get(utilisateur);
    }

    public void afficherPersonne() {
        Set<Utilisateur> set = new TreeSet<>(map.keySet());
        for (Utilisateur utilisateur : set) {
            System.out.println(utilisateur + " : " + map.get(utilisateur));
        }
    }
}
