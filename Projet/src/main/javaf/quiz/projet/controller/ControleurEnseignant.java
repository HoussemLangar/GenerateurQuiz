package quiz.projet.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import quiz.projet.modele.Cours;
import quiz.projet.modele.Etudiant;
import quiz.projet.modele.Question;
import quiz.projet.util.GestionBaseDonnees;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ControleurEnseignant {

    @FXML
    private TextField enonceField;

    @FXML
    private TextField choixField;

    @FXML
    private TextField reponseField;

    @FXML
    private TextField difficulteFieled;

    @FXML
    private TextField coursField;

    @FXML
    private TextField groupeField;

    @FXML
    private void creer() {
        String enonce = enonceField.getText();
        String[] choix = choixField.getText().split("\n");
        String[] reponse = reponseField.getText().split("\n");
        String difficulte = difficulteFieled.getText();
        String cours = coursField.getText();
        String groupe = groupeField.getText();

        if (enonce.isEmpty() || choix.length == 0 || reponse.length == 0 || difficulte.isEmpty() || cours.isEmpty()) {
            afficherMessageErreur("Veuillez remplir tous les champs.");
        } else {
            if (GestionBaseDonnees.creerQuestion(enonce, choix, reponse, difficulte, cours, groupe)) {
                afficherMessageConfirmation("La question a été créée avec succès.");
                Stage stage = (Stage) enonceField.getScene().getWindow();
                stage.close();
            } else {
                afficherMessageErreur("La question n'a pas été créée.");
            }
        }
    }
    @FXML
    private TextField enonceField1;
    @FXML
    private void supprimerquestion() {
        String nomEtudiant = enonceField1.getText();
        int id = GestionBaseDonnees.obtenirIdQuestionParNom(nomEtudiant);
        if (nomEtudiant.isEmpty()) {
            afficherMessageErreur("Veuillez saisir tous les champs.");
        } else {
            Stage stage = (Stage) enonceField1.getScene().getWindow();
            if (GestionBaseDonnees.supprimerQuestion(id)) {
                afficherMessageConfirmation("La question a été supprimé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("La question n'a pas été supprimé.");
            }
        }
    }
    @FXML
    private TextField questionMField;
    @FXML
    public void chercherquestion() {
        String enonce = questionMField.getText();
        int id = GestionBaseDonnees.obtenirIdQuestionParNom(enonce);
        if (enonce.isEmpty()) {
            afficherMessageErreur("Veuillez saisir tous les champs.");
        } else {
            if (id == -1) {
                afficherMessageErreur("La question inexistant.");
            } else {
                ouvrirInterfaceQ(id);
            }
        }
    }
    @FXML
    private AnchorPane mainContainerQ;
    private void ouvrirInterfaceQ(int idQuestion) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz/projet/vue/modifierquestion.fxml"));
            loader.setControllerFactory(param -> {
                if (param.equals(ModifierQuestion.class)) {
                    ModifierQuestion controller = new ModifierQuestion();
                    controller.setQuestionId(idQuestion);
                    return controller;
                } else {
                    try {
                        return param.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            Parent nouvelleVue = loader.load();
            mainContainerQ.getChildren().setAll(nouvelleVue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class ModifierQuestion{
        private int idQuestion;
        public void setQuestionId(int idQuestion) {
            this.idQuestion = idQuestion;
        }
        @FXML
        private TextArea choixAmField;
        @FXML
        private TextArea reponseAmField;
        @FXML
        public void modifierquestion() {
            String choix = choixAmField.getText();
            String reponse = reponseAmField.getText();
            Stage stage = (Stage) choixAmField.getScene().getWindow();
            if (GestionBaseDonnees.modifierQuestion(idQuestion, choix, reponse)) {
                afficherMessageConfirmation("La question a été modifié avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("La question n'a pas été modifié.");
            }

        }
    }
    @FXML
    private VBox questionContainer;
    @FXML
    public void afficherListeQuestion() {
        List<Question> listeQuestions = GestionBaseDonnees.obtenirListeQuestions();
        for (Question question : listeQuestions) {
            Label labelEnonce = new Label("Enonce : " + question.getÉnoncé());
            Label labelChoix = new Label("Choix : " + question.getChoix());
            Label labelReponse = new Label("Réponse correctes : " + question.getRéponsesCorrectes());
            Label labelDifficulte = new Label("Difficulté : " + question.getDifficulte());
            Label labelCours = new Label("Cours : " + question.getCours());
            Label labelgroupe = new Label("Groupe : " + question.getGroupe());

            labelEnonce.setFont(Font.font("System Bold", 14));
            labelChoix.setFont(Font.font("System Bold", 14));
            labelReponse.setFont(Font.font("System Bold", 14));
            labelCours.setFont(Font.font("System Bold", 14));
            labelgroupe.setFont(Font.font("System Bold", 14));
            questionContainer.getChildren().addAll(labelEnonce, labelChoix, labelReponse,labelDifficulte, labelCours, labelgroupe);
        }
    }

    @FXML
    private TextField courQ;
    @FXML
    private TextField niveauQ;
    @FXML
    private TextField nombreQ;
    @FXML
    private TextField tempsMax;
    @FXML
    private DatePicker datePicker;
    @FXML
    public void genererquiz() {
        String cours = courQ.getText();
        String difficulte = niveauQ.getText();
        int nombreQuestions = Integer.parseInt(nombreQ.getText());
        int tempsM = Integer.parseInt(tempsMax.getText());
        LocalDate date = datePicker.getValue();
        if (cours.isEmpty() || difficulte == null || nombreQuestions <= 0 || tempsM <= 0) {
            afficherMessageErreur("Veuillez remplir tous les champs.");
        } else {
            List<String> questions = GestionBaseDonnees.genererQuestionAlea(cours, difficulte, nombreQuestions);
            boolean quizGenerated = GestionBaseDonnees.genererQuiz(cours, difficulte, questions, date, tempsM);

            if (quizGenerated) {
                afficherMessageConfirmation("Le quiz a été généré avec succès !");
            } else {
                afficherMessageErreur("Une erreur s'est produite lors de la génération du quiz.");
            }
        }
    }
    @FXML
    private VBox quizContainer;
    @FXML
    private void afficherlistequiz() {
        List<String> listeQuiz = GestionBaseDonnees.afficherListeQuiz();

        quizContainer.getChildren().clear();

        for (String quiz : listeQuiz) {
            Label labelQuiz = new Label(quiz);
            labelQuiz.setStyle("-fx-font-size: 16;");
            quizContainer.getChildren().add(labelQuiz);
        }

    }
    @FXML
    private void telechargerPDF() {
        List<String> listeQuiz = GestionBaseDonnees.afficherListeQuiz();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String dateTimeStamp = dateFormat.format(new Date());

        String nomFichier = "liste_quiz_" + dateTimeStamp + ".pdf";
        String chemin = "C:/ProjetJava/Projet/src/main/javaf/quiz/projet/out/" + nomFichier;
        PDFGenerateur.genererPDF(listeQuiz, chemin);
        afficherMessageConfirmation("Le fichier PDF a été téléchargé avec succès !");

    }
    public void afficherMessageConfirmation(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}

