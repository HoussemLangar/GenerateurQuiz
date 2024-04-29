package quiz.projet.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import quiz.projet.util.GestionBaseDonnees;

import java.io.IOException;

public class ControleurConnexion {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private void gererConnexion() {
        String nomUtilisateur = usernameField.getText();
        String motDePasse = passwordField.getText();
        if (GestionBaseDonnees.verifierInformationsConnexion(nomUtilisateur, motDePasse)) {
            String role = GestionBaseDonnees.obtenirRole(nomUtilisateur, motDePasse);

            if (role != null) {
                switch (role) {
                    case "administrateur":
                        ouvrirInterface("/quiz/projet/vue/administrateur.fxml");
                        break;
                    case "enseignant":
                        ouvrirInterface("/quiz/projet/vue/enseignant.fxml");
                        break;
                    case "etudiant":
                        ouvrirInterface("/quiz/projet/vue/etudiant.fxml");
                        break;
                    default:
                        afficherMessageErreur("Rôle non reconnu.");
                }
            } else {
                afficherMessageErreur("Accès refusé, nom d'utilisateur ou mot de passe incorrect.");
            }

            Stage stage = getStage();
            stage.setMinWidth(800);
            stage.setMinHeight(850);
        }
    }
    private void afficherMessageErreur(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private Stage getStage() {
        return (Stage) mainContainer.getScene().getWindow();
    }

    private void ouvrirInterface(String cheminFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(cheminFXML));
            Parent nouvelleVue = loader.load();

            mainContainer.getChildren().setAll(nouvelleVue);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
