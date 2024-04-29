package quiz.projet.modele;

public class Etudiant {
    private int id;
    private String nomUtilisateur;
    private String motDePasse;
    private String Groupe;


    public Etudiant(int id, String nomUtilisateur, String motDePasse, String Groupe) {
        this.id = id;
        this.nomUtilisateur = nomUtilisateur;
        this.motDePasse = motDePasse;
        this.Groupe = Groupe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getGroupe() {
        return Groupe;
    }

    public void setGroupe(String Groupe) {
        this.Groupe = Groupe;
    }
}
