package quiz.projet.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import quiz.projet.util.GestionBaseDonnees;

import java.util.List;

public class ControleurEtudiant  {

    @FXML
    private TextField numQ;

    @FXML
    private VBox quizContainer;
    @FXML
    private ToggleGroup toggleGroup;


    public void initialize() {
        quizContainer = new VBox();
    }

    @FXML
    private void passer() {
        try {
            int num = Integer.parseInt(numQ.getText());
            if (num <= 0) {
                afficherMessageErreur("Veuillez entrer un numéro de quiz valide.");
                return;
            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz/projet/vue/passerquiz.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);

            List<String> questions = GestionBaseDonnees.afficherQuizE(num);
            afficherQuizUI(questions);

            stage.showAndWait();

        } catch (NumberFormatException e) {
            afficherMessageErreur("Veuillez entrer un numéro de quiz valide.");
        } catch (Exception e) {
            afficherMessageErreur("Une erreur s'est produite lors du chargement du quiz.");
            e.printStackTrace();
        }
    }

    private void afficherQuizUI(List<String> questions) {
        toggleGroup = new ToggleGroup();
        for (String question : questions) {
            Label labelQuestion = new Label(question);
            quizContainer.getChildren().add(labelQuestion);

            String[] choix = question.split("\nChoix : ");
            String[] choixPossibles = choix[1].split(",");

            for (String choixPossible : choixPossibles) {
                RadioButton radioButton = new RadioButton(choixPossible);
                radioButton.setToggleGroup(toggleGroup);
                quizContainer.getChildren().add(radioButton);
            }
        }
    }

    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
