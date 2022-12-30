public class Personne {

    public String nom, prenom, adressePostale;
    public String dateNaissance, profil, email;
    public String dateAjout, dateMaj;

    public Personne(String nom, String prenom, String email
            , String adressePostale, String dateNaissance
            , String profil, String dateAjout, String dateMaj) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adressePostale = adressePostale;
        this.dateNaissance = dateNaissance;
        this.profil = profil;
        this.dateAjout = dateAjout;
        this.dateMaj = dateMaj;
    }

    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        Personne personne = (Personne) obj;
        return nom.equals(personne.nom) && prenom.equals(personne.prenom)
                && email.equals(personne.email) && adressePostale.equals(personne.adressePostale)
                && dateNaissance.equals(personne.dateNaissance) && profil.equals(personne.profil)
                && dateAjout.equals(personne.dateAjout) && dateMaj.equals(personne.dateMaj);
    }
}
