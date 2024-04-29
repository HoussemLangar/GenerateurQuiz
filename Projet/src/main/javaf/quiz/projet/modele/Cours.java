package quiz.projet.modele;

public class Cours {
    private int id;
    private String nom;
    private String description;

    public Cours(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
