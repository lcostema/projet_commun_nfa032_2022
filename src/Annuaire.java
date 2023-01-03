import Utilisateurs.Admin;
import Utilisateurs.Utilisateur;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;

public class Annuaire {
    static Path dossierLocal;
    static List<LinkedHashMap<String, String>> mapPersonne = new ArrayList<>();
    static List<LinkedHashMap<String, String>> mapCompte = new ArrayList<>();

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

    /* "chargerPersonne(File fichier)" mets le contenu de "fichier"
     * dans une map "mapPersonne", donc dans la mémoire en local
     * ce qui évite de faire toutes les opérations directement sur les fichiers
     * une fois qu'une opération est terminée, on met à jour les fichiers */
    public static List<LinkedHashMap<String, String>> chargerPersonne(File fichier) throws Exception {
        mapPersonne.clear();
        Scanner scannerFichier;
        if (!fichier.exists()) {
            System.out.println(
                    "\n" +
                            "Pas trouvé le fichier" + fichier + ", il n'existe pas,\n" +
                            "ou il a été renommé, supprimé ou déplacé !");
            return null;
        } else {
            scannerFichier = new Scanner(fichier);
        }
        String[] colonne = scannerFichier.nextLine().split(";");
        while (scannerFichier.hasNextLine()) {
            String[] ligne = scannerFichier.nextLine().split(";");
            LinkedHashMap<String, String> mapLigne = new LinkedHashMap<>();
            for (int i = 0; i < colonne.length; i++) {
                mapLigne.put(colonne[i], ligne[i]);
            }
            mapPersonne.add(mapLigne);
        }
        scannerFichier.close();
        return mapPersonne;
    }

    // chargerCompte(File fichier) nécessaire pour les matchs d'adresses email entre les 2 maps
    public static List<LinkedHashMap<String, String>> chargerCompte(File fichier) throws Exception {
        mapCompte.clear();
        Scanner scannerFichier;
        if (!fichier.exists()) {
            System.out.println(
                    "\n" +
                            "Pas trouvé le fichier" + fichier + ", il n'existe pas,\n" +
                            "ou il a été renommé, supprimé ou déplacé !");
            return null;
        } else {
            scannerFichier = new Scanner(fichier);
        }
        String[] colonne = scannerFichier.nextLine().split(";");
        while (scannerFichier.hasNextLine()) {
            String[] ligne = scannerFichier.nextLine().split(";");
            LinkedHashMap<String, String> mapLigne = new LinkedHashMap<>();
            for (int i = 0; i < colonne.length; i++) {
                mapLigne.put(colonne[i], ligne[i]);
            }
            mapCompte.add(mapLigne);
        }
        scannerFichier.close();
        return mapCompte;
    }

    public static void testChargerPersonne() throws Exception {
        System.out.println(chargerPersonne(new File(dossierLocal + "\\personne.txt")));
        System.out.println();
        System.out.println(chargerCompte(new File(dossierLocal + "\\compte.txt")));
    }

    static int matchValue = 0;
    static String erreurRecherche = null;

    public static void recherchePersonnes(String keyColonne, String valueLigne) {
        matchValue = 0;
        for (LinkedHashMap<String, String> readMap : mapPersonne) {
            for (Entry<String, String> stringEntry : readMap.entrySet()) {
                if (stringEntry.getKey().equals(keyColonne)) {
                    if (stringEntry.getValue().equalsIgnoreCase(valueLigne)) {
                        switch (keyColonne) {
                            case "nom" -> {
                                erreurRecherche = "Ce nom n'existe pas ...!";
                                System.out.println(
                                        "Prénom : \t" + readMap.get("prenom") + "\n"
                                                + "Nom : \t\t" + readMap.get("nom") + "\n"
                                                + "email : \t" + readMap.get("email") + "\n"
                                );
                                matchValue += 1;
                            }
                            case "email" -> {
                                erreurRecherche = "Pas trouvé cet email ...!";
                                System.out.println(
                                        "email : \t" + readMap.get("email") + "\n"
                                                + "Prénom : \t" + readMap.get("prenom") + "\n"
                                                + "Nom : \t\t" + readMap.get("nom") + "\n"
                                );
                                matchValue += 1;
                            }
                            case "profil" -> {
                                erreurRecherche = "Personne trouvé avec ce profil ...!";
                                System.out.println(
                                        "Profil : \t" + readMap.get("profil") + "\n"
                                                + "Prénom : \t" + readMap.get("prenom") + "\n"
                                                + "Nom : \t\t" + readMap.get("nom") + "\n"
                                );
                                matchValue += 1;
                            }
                        }
                    }
                }
            }
        }
        if (matchValue < 1) {
            matchValue = 0;
            System.out.println(erreurRecherche);
        }
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
