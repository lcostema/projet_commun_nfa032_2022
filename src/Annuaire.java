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

    private final Map<Personne, Compte> map;

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

    public void ajouterPersonne(Personne personne, Compte compte) {
        map.put(personne, compte);
    }

    public void supprimerPersonne(Personne personne, Compte compte) {
        map.remove(personne, compte);
    }

    public void modifierPersonne(Personne personne, Compte compte) {
        map.remove(personne);
        map.put(personne, compte);
    }

    public Compte trouverPersonne(Personne personne) {
        return map.get(personne);
    }

    public void afficherPersonne() {
        Set<Personne> set = new TreeSet<>(map.keySet());
        for (Personne personne : set) {
            System.out.println(personne + " : " + map.get(personne));
        }
    }
}
