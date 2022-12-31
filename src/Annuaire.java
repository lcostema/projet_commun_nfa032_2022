import Utilisateurs.Admin;
import Utilisateurs.Particulier;
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

    public static Map<String, Map<String, String>> chargerPersonne(File file) throws FileNotFoundException {
        Scanner scannerFichier = new Scanner(file);
        String[] colonne = scannerFichier.nextLine().split(";");
        Map<String, Map<String, String>> mapPersonne = new TreeMap<>();
        while (scannerFichier.hasNextLine()) {
            String[] ligne = scannerFichier.nextLine().split(";");
            Map<String, String> mapLigne = new TreeMap<>();
            for (int i = 0; i < colonne.length && i + 1 < ligne.length; i++)
                mapLigne.put(colonne[i], ligne[i + 1]);
            mapPersonne.put(ligne[0], mapLigne);
        }
        scannerFichier.close();
        return mapPersonne;
    }

    public static void testChargerPersonne() throws IOException {
        System.out.println(chargerPersonne(new File(dossierLocal + "\\personne.txt")));
    }

    //todo: test lecture fichier
    // à modifier pour charger les données localement
    public static void lireFichierPersonne() {
        try {
            File fichierPersonne = new File(dossierLocal + "\\personne.txt");
            if (fichierPersonne.exists()) {
                FileReader fr = new FileReader(fichierPersonne);
                BufferedReader br = new BufferedReader(fr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                fr.close();
                System.out.println(sb);

            } else {
                System.out.println("Pas trouvé le fichier \"personne.txt\", il n'existe pas,");
                System.out.println("ou il a été renommé, supprimé ou déplacé !");
                Gestion.main(new String[]{"b"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ajouterPersonne(Object utilisateur) {
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
