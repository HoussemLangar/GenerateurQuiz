package quiz.projet.modele;

import java.util.List;

public class Question {
    private String énoncé;
    private List<String> choix;
    private List<String> réponsesCorrectes;
    private String difficulte;
    private String cours;
    private String groupe;


    public Question(String énoncé, List<String> choix, List<String> réponsesCorrectes, String difficulte, String cours, String groupe) {
        this.énoncé = énoncé;
        this.choix = choix;
        this.réponsesCorrectes = réponsesCorrectes;
        this.difficulte = difficulte;
        this.cours = cours;
        this.groupe = groupe;
    }

    public String getÉnoncé() {
        return énoncé;
    }

    public void setÉnoncé(String énoncé) {
        this.énoncé = énoncé;
    }

    public List<String> getChoix() {
        return choix;
    }

    public void setChoix(List<String> choix) {
        this.choix = choix;
    }

    public List<String> getRéponsesCorrectes() {
        return réponsesCorrectes;
    }

    public void setRéponsesCorrectes(List<String> réponsesCorrectes) {
        this.réponsesCorrectes = réponsesCorrectes;
    }

    public String getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(String difficulté) {
        this.difficulte = difficulté;
    }

    public String getCours() {
        return cours;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }
}
