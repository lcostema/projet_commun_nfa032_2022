public class Compte {

    public String email, mdp, role;

    public Compte(String email, String mdp, String role) {
        super();
        this.email = email;
        this.mdp = mdp;
        this.role = role;
    }

    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;
        Compte compte = (Compte) obj;
        return email.equals(compte.email) && mdp.equals(compte.mdp) && role.equals(compte.role);
    }
}
