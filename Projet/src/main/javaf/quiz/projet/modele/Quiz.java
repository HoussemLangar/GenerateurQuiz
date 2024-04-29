package quiz.projet.modele;

import java.util.List;
import java.time.LocalDate;

public class Quiz {
    private int id;
    private Cours cours;
    private String niveauD;
    private LocalDate dateDisponibilite;
    private int tempsMax;
    private String titre;
    private List<Question> questions;
    private int score;

    // Constructeur
    public Quiz(int id, Cours cours, String niveauD, LocalDate dateDisponibilite, int tempsMax, String titre, List<Question> questions) {
        this.id = id;
        this.cours = cours;
        this.niveauD = niveauD;
        this.dateDisponibilite = dateDisponibilite;
        this.tempsMax = tempsMax;
        this.titre = titre;
        this.questions = questions;
        this.score = 0;
    }

    // Getters et Setters pour les nouveaux attributs
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public String getNiveauD() {
        return niveauD;
    }

    public void setNiveauD(String niveauD) {
        this.niveauD = niveauD;
    }

    public LocalDate getDateDisponibilite() {
        return dateDisponibilite;
    }

    public void setDateDisponibilite(LocalDate dateDisponibilite) {
        this.dateDisponibilite = dateDisponibilite;
    }

    public int getTempsMax() {
        return tempsMax;
    }

    public void setTempsMax(int tempsMax) {
        this.tempsMax = tempsMax;
    }

    // Getters et Setters existants
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getScore() {
        return score;
    }

    // Méthodes existantes
    public void lancerQuiz() {
        // Logique pour démarrer le quiz
    }

    public void evaluerReponse(Question question, List<String> reponsesUtilisateur) {
        // Logique pour évaluer la réponse
        if (question.getRéponsesCorrectes().equals(reponsesUtilisateur)) {
            score++;
        }
    }

    public void afficherResultat() {
        System.out.println("Votre score est : " + score + "/" + questions.size());
    }
}
