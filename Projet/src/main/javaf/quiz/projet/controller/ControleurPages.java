package quiz.projet.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControleurPages {

    @FXML
    private void initialize() {
        ControleurAdministrateur controleurAdministrateur = new ControleurAdministrateur();
    }

    @FXML
    private void creerCours() {
        redirigerVersPageXML("/quiz/projet/vue/creercours.fxml");
    }

    @FXML
    private void supprimerCours() {
        redirigerVersPageXML("/quiz/projet/vue/supprimercours.fxml");
    }

    @FXML
    private void modifierCours() {
        redirigerVersPageXML("/quiz/projet/vue/cherchercours.fxml");
    }

    @FXML
    private void afficherListeCours() {
        redirigerVersPageXML("/quiz/projet/vue/afficherlistecours.fxml"); }

    @FXML
    private void creerGroupe() {
        redirigerVersPageXML("/quiz/projet/vue/creergroupe.fxml");
    }

    @FXML
    private void supprimerGroupe() {
        redirigerVersPageXML("/quiz/projet/vue/supprimergroupe.fxml");
    }

    @FXML
    private void modifierGroupe() {
        redirigerVersPageXML("/quiz/projet/vue/cherchergroupe.fxml");
    }

    @FXML
    private void afficherListeGroupes() {
        redirigerVersPageXML("/quiz/projet/vue/afficherlistegroupe.fxml");
    }

    @FXML
    private void creerEnseignant() {
        redirigerVersPageXML("/quiz/projet/vue/creerenseignant.fxml");
    }

    @FXML
    private void supprimerEnseignant() {
        redirigerVersPageXML("/quiz/projet/vue/supprimerenseignant.fxml");
    }

    @FXML
    private void modifierEnseignant() {
        redirigerVersPageXML("/quiz/projet/vue/chercherenseignant.fxml");
    }

    @FXML
    private void afficherListeEnseignants() {
        redirigerVersPageXML("/quiz/projet/vue/afficherlisteenseignant.fxml");
    }

    @FXML
    private void creerEtudiant() {
        redirigerVersPageXML("/quiz/projet/vue/creeretudiant.fxml");
    }

    @FXML
    private void supprimerEtudiant() {
        redirigerVersPageXML("/quiz/projet/vue/supprimeretudiant.fxml");
    }

    @FXML
    private void modifierEtudiant() {
        redirigerVersPageXML("/quiz/projet/vue/chercheretudiant.fxml");
    }

    @FXML
    private void afficherListeEtudiants() {
        redirigerVersPageXML("/quiz/projet/vue/afficherlisteetudiant.fxml");
    }

    @FXML
    private void creerQuestion() {
        redirigerVersPageXML("/quiz/projet/vue/creerquestion.fxml");
    }

    @FXML
    private void supprimerQuestion() {
        redirigerVersPageXML("/quiz/projet/vue/supprimerquestion.fxml");
    }

    @FXML
    private void modifierQuestion() {
        redirigerVersPageXML("/quiz/projet/vue/chercherquestion.fxml");
    }

    @FXML
    private void afficherListeQuestion() {
        redirigerVersPageXML("/quiz/projet/vue/afficherlistequestion.fxml");
    }
    @FXML
    private void genererquiz() {
        redirigerVersPageXML("/quiz/projet/vue/genererquiz.fxml");
    }
    @FXML
    private void afficherlistequiz() {
        redirigerVersPageXML("/quiz/projet/vue/afficherlistequiz.fxml");
    }
    @FXML
    private void telechargerpdf() {
        redirigerVersPageXML("/quiz/projet/vue/telecharger.fxml");
    }
    @FXML
    private void passerquiz() {
        redirigerVersPageXML("/quiz/projet/vue/choixquiz.fxml");
    }
    @FXML
    private void affichercorrection() {
        redirigerVersPageXML("/quiz/projet/vue/telecharger.fxml");
    }

    public void redirigerVersPageXML(String cheminFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
