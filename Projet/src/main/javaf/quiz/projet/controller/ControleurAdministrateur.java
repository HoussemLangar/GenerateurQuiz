package quiz.projet.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import quiz.projet.modele.Cours;
import quiz.projet.modele.Enseignant;
import quiz.projet.modele.Etudiant;
import quiz.projet.modele.Groupe;
import quiz.projet.util.GestionBaseDonnees;

import java.io.IOException;
import java.util.List;

public class ControleurAdministrateur {
    @FXML
    private TextField nomCoursField;
    @FXML
    private TextField descriptionCoursField;

    @FXML
    private void creer() {
        String nom = nomCoursField.getText();
        String description = descriptionCoursField.getText();
        if (nom.isEmpty() || description.isEmpty()) {
            afficherMessageErreur("Veuillez remplir tous les champs.");
        } else {
            Stage stage = (Stage) nomCoursField.getScene().getWindow();
            if (GestionBaseDonnees.creerCours(nom, description)) {
                afficherMessageConfirmation("Le cours a été créé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("Le cours n'a pas été créé.");
            }
        }
    }

    @FXML
    private TextField nomCoursField1;

    @FXML
    private void supprimercours() {
        String nomCours = nomCoursField1.getText();
        int id = GestionBaseDonnees.obtenirIdCoursParNom(nomCours);
        if (nomCours.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le nom du cours.");
        } else {
            Stage stage = (Stage) nomCoursField1.getScene().getWindow();
            if (GestionBaseDonnees.supprimerCours(id)) {
                afficherMessageConfirmation("Le cours a été supprimé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("Le cours n'a pas été supprimé.");
            }
        }
    }

    @FXML
    private TextField nomCoursMField;
    @FXML
    public void chercher() {
        String nomCours = nomCoursMField.getText();
        int id = GestionBaseDonnees.obtenirIdCoursParNom(nomCours);
        if (nomCours.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le nom du cours.");
        } else {
            if (id == -1) {
                afficherMessageErreur("Cours inexistant.");
            } else {
                ouvrirInterface(id);
            }
        }
    }
    @FXML
    private AnchorPane mainContainer;
    private void ouvrirInterface(int idCours) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz/projet/vue/modifiercours.fxml"));
            loader.setControllerFactory(param -> {
                if (param.equals(ModifierCours.class)) {
                    ModifierCours controller = new ModifierCours();
                    controller.setCoursId(idCours);
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
            mainContainer.getChildren().setAll(nouvelleVue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class ModifierCours{
        private int idCours;
        public void setCoursId(int idCours) {
            this.idCours = idCours;
        }
        @FXML
        private TextField nomCoursAmField;
        @FXML
        private TextArea descCoursAmField;
        @FXML
        public void modifiercours() {
            String nouveauNom = nomCoursAmField.getText();
            String nouvelleDescription = descCoursAmField.getText();
            Stage stage = (Stage) nomCoursAmField.getScene().getWindow();
            if (GestionBaseDonnees.modifierCours(idCours, nouveauNom, nouvelleDescription)) {
                afficherMessageConfirmation("Le cours a été modifié avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("Le cours n'a pas été modifié.");
            }

        }
    }
    @FXML
    private VBox coursContainer;
    @FXML
    public void afficherListeCours() {
        List<Cours> listeCours = GestionBaseDonnees.obtenirListeCours();
        for (Cours cours : listeCours) {
            Label labelNom = new Label("Nom du cours N°" + cours.getId() + " : " + cours.getNom());
            Label labelDescription = new Label("Description : " + cours.getDescription());

            labelNom.setFont(Font.font("System Bold", 14));
            labelDescription.setFont(Font.font("System Bold", 14));
            coursContainer.getChildren().addAll(labelNom, labelDescription);
        }
    }

    @FXML
    private TextField nomGroupeField;
    @FXML
    private TextField niveauGroupeField;

    @FXML
    private void creergroupe() {
        String nom = nomGroupeField.getText();
        String niveau = niveauGroupeField.getText();
        if (nom.isEmpty() || niveau.isEmpty()) {
            afficherMessageErreur("Veuillez remplir tous les champs.");
        } else {
            Stage stage = (Stage) nomGroupeField.getScene().getWindow();
            if (GestionBaseDonnees.creerGroupe(nom, niveau)) {
                afficherMessageConfirmation("Le groupe a été créé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("Le groupe n'a pas été créé.");
            }
        }
    }
    @FXML
    private TextField nomGroupeField1;

    @FXML
    private void supprimergroupe() {
        String nomGroupe = nomGroupeField1.getText();
        int id = GestionBaseDonnees.obtenirIdGroupeParNom(nomGroupe);
        if (nomGroupe.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le nom du groupe.");
        } else {
            Stage stage = (Stage) nomGroupeField1.getScene().getWindow();
            if (GestionBaseDonnees.supprimerGroupe(id)) {
                afficherMessageConfirmation("Le groupe a été supprimé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("Le groupe n'a pas été supprimé.");
            }
        }
    }
    @FXML
    private TextField nomGroupeMField;
    @FXML
    public void cherchergroupe() {
        String nomGroupe = nomGroupeMField.getText();
        int id = GestionBaseDonnees.obtenirIdGroupeParNom(nomGroupe);
        if (nomGroupe.isEmpty()) {
            afficherMessageErreur("Veuillez saisir le nom du groupe.");
        } else {
            if (id == -1) {
                afficherMessageErreur("Groupe inexistant.");
            } else {
                ouvrirInterfaceG(id);
            }
        }
    }
    @FXML
    private AnchorPane mainContainerG;
    private void ouvrirInterfaceG(int idGroupe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz/projet/vue/modifiergroupe.fxml"));
            loader.setControllerFactory(param -> {
                if (param.equals(ModifierGroupe.class)) {
                    ModifierGroupe controller = new ModifierGroupe();
                    controller.setGroupeId(idGroupe);
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
            mainContainerG.getChildren().setAll(nouvelleVue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class ModifierGroupe{
        private int idGroupe;
        public void setGroupeId(int idGroupe) {
            this.idGroupe = idGroupe;
        }
        @FXML
        private TextField nomGroupeAmField;
        @FXML
        private TextField niveauGroupeAmField;
        @FXML
        public void modifiergroupe() {
            String nouveauNom = nomGroupeAmField.getText();
            String nouveauNiveau = niveauGroupeAmField.getText();
            Stage stage = (Stage) nomGroupeAmField.getScene().getWindow();
            if (GestionBaseDonnees.modifierGroupe(idGroupe, nouveauNom, nouveauNiveau)) {
                afficherMessageConfirmation("Le groupe a été modifié avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("Le groupe n'a pas été modifié.");
            }

        }
    }
    @FXML
    private VBox groupeContainer;
    @FXML
    public void afficherListeGroupe() {
        List<Groupe> listeGroupes = GestionBaseDonnees.obtenirListeGroupes();
        for (Groupe groupe : listeGroupes) {
            Label labelNom = new Label("Nom du Groupe N°" + groupe.getId() + " : " + groupe.getNom());
            Label labelDescription = new Label("Niveau : " + groupe.getNiveau());

            labelNom.setFont(Font.font("System Bold", 14));
            labelDescription.setFont(Font.font("System Bold", 14));
            groupeContainer.getChildren().addAll(labelNom, labelDescription);
        }
    }
    @FXML
    private TextField nomEnseignantField;
    @FXML
    private TextField passEnseignantField;

    @FXML
    private void creerenseignant() {
        String nom = nomEnseignantField.getText();
        String pass = passEnseignantField.getText();
        if (nom.isEmpty() || pass.isEmpty()) {
            afficherMessageErreur("Veuillez remplir tous les champs.");
        } else {
            Stage stage = (Stage) nomEnseignantField.getScene().getWindow();
            if (GestionBaseDonnees.creerEnseignant(nom, pass)) {
                afficherMessageConfirmation("L'enseignant a été créé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("L'enseignant n'a pas été créé.");
            }
        }
    }
    @FXML
    private TextField nomEnseignantField1;

    @FXML
    private void supprimerenseignant() {
        String nomEnseignant = nomEnseignantField1.getText();
        int id = GestionBaseDonnees.obtenirIdEnseignantParNom(nomEnseignant);
        if (nomEnseignant.isEmpty()) {
            afficherMessageErreur("Veuillez saisir l'username.");
        } else {
            Stage stage = (Stage) nomEnseignantField1.getScene().getWindow();
            if (GestionBaseDonnees.supprimerEnseignant(id)) {
                afficherMessageConfirmation("L'enseignant a été supprimé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("L'enseignant n'a pas été supprimé.");
            }
        }
    }
    @FXML
    private TextField nomEnseignantMField;
    @FXML
    public void chercherenseignant() {
        String username = nomEnseignantMField.getText();
        int id = GestionBaseDonnees.obtenirIdEnseignantParNom(username);
        if (username.isEmpty()) {
            afficherMessageErreur("Veuillez saisir l'username.");
        } else {
            if (id == -1) {
                afficherMessageErreur("Username inexistant.");
            } else {
                ouvrirInterfaceEn(id);
            }
        }
    }
    @FXML
    private AnchorPane mainContainerEn;
    private void ouvrirInterfaceEn(int idEnseignant) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz/projet/vue/modifierenseignant.fxml"));
            loader.setControllerFactory(param -> {
                if (param.equals(ModifierEnseignant.class)) {
                    ModifierEnseignant controller = new ModifierEnseignant();
                    controller.setEnseignantId(idEnseignant);
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
            mainContainerEn.getChildren().setAll(nouvelleVue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class ModifierEnseignant{
        private int idEnseignant;
        public void setEnseignantId(int idEnseignant) {
            this.idEnseignant = idEnseignant;
        }
        @FXML
        private TextField passEnseignantAmField;
        @FXML
        public void modifierenseignant() {
            String nouveauPass = passEnseignantAmField.getText();
            Stage stage = (Stage) passEnseignantAmField.getScene().getWindow();
            if (GestionBaseDonnees.modifierEnseignant(idEnseignant, nouveauPass)) {
                afficherMessageConfirmation("L'enseignant a été modifié avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("L'enseignant n'a pas été modifié.");
            }

        }
    }
    @FXML
    private VBox enseignantContainer;
    @FXML
    public void afficherListeEnseignant() {
        List<Enseignant> listeEnseignants = GestionBaseDonnees.obtenirListeEnseignants();
        for (Enseignant enseignant : listeEnseignants) {
            Label labelNom = new Label("Username N°" + enseignant.getId() + " : " + enseignant.getNomUtilisateur());
            Label labelDescription = new Label("Password : " + enseignant.getMotDePasse());

            labelNom.setFont(Font.font("System Bold", 14));
            labelDescription.setFont(Font.font("System Bold", 14));
            enseignantContainer.getChildren().addAll(labelNom, labelDescription);
        }
    }
    @FXML
    private TextField nomEtudiantField;
    @FXML
    private TextField passEtudiantField;
    @FXML
    private TextField groupeEtudiantField;
    @FXML
    private void creeretudiant() {
        String nom = nomEtudiantField.getText();
        String pass = passEtudiantField.getText();
        String groupe = groupeEtudiantField.getText();
        if (nom.isEmpty() || pass.isEmpty()) {
            afficherMessageErreur("Veuillez remplir tous les champs.");
        } else {
            Stage stage = (Stage) nomEtudiantField.getScene().getWindow();
            if (GestionBaseDonnees.creerEtudiant(nom, pass, groupe)) {
                afficherMessageConfirmation("L'etudiant a été créé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("L'etudiant n'a pas été créé.");
            }
        }
    }
    @FXML
    private TextField nomEtudiantField1;

    @FXML
    private void supprimeretudiant() {
        String nomEtudiant = nomEtudiantField1.getText();
        int id = GestionBaseDonnees.obtenirIdEtudiantParNom(nomEtudiant);
        if (nomEtudiant.isEmpty()) {
            afficherMessageErreur("Veuillez saisir l'username.");
        } else {
            Stage stage = (Stage) nomEtudiantField1.getScene().getWindow();
            if (GestionBaseDonnees.supprimerEtudiant(id)) {
                afficherMessageConfirmation("L'etudiant a été supprimé avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("L'etudiant n'a pas été supprimé.");
            }
        }
    }
    @FXML
    private TextField nomEtudiantMField;
    @FXML
    public void chercheretudiant() {
        String username = nomEtudiantMField.getText();
        int id = GestionBaseDonnees.obtenirIdEtudiantParNom(username);
        if (username.isEmpty()) {
            afficherMessageErreur("Veuillez saisir l'username.");
        } else {
            if (id == -1) {
                afficherMessageErreur("Username inexistant.");
            } else {
                ouvrirInterfaceE(id);
            }
        }
    }
    @FXML
    private AnchorPane mainContainerE;
    private void ouvrirInterfaceE(int idEtudiant) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/quiz/projet/vue/modifieretudiant.fxml"));
            loader.setControllerFactory(param -> {
                if (param.equals(ModifierEtudiant.class)) {
                    ModifierEtudiant controller = new ModifierEtudiant();
                    controller.setEtudiantId(idEtudiant);
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
            mainContainerE.getChildren().setAll(nouvelleVue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public class ModifierEtudiant{
        private int idEtudiant;
        public void setEtudiantId(int idEtudiant) {
            this.idEtudiant = idEtudiant;
        }
        @FXML
        private TextField passEtudiantAmField;
        @FXML
        private TextField groupeEtudiantAmField;
        @FXML
        public void modifieretudiant() {
            String nouveauPass = passEtudiantAmField.getText();
            String nouveauGroupe = groupeEtudiantAmField.getText();
            Stage stage = (Stage) passEtudiantAmField.getScene().getWindow();
            if (GestionBaseDonnees.modifierEtudiant(idEtudiant, nouveauPass, nouveauGroupe)) {
                afficherMessageConfirmation("L'etudiant a été modifié avec succès.");
                stage.close();
            } else {
                afficherMessageErreur("L'enseignant n'a pas été modifié.");
            }

        }
    }
    @FXML
    private VBox etudiantContainer;
    @FXML
    public void afficherListeEtudiant() {
        List<Etudiant> listeEtudiants = GestionBaseDonnees.obtenirListeEtudiants();
        for (Etudiant etudiant : listeEtudiants) {
            Label labelNom = new Label("Username N°" + etudiant.getId() + " : " + etudiant.getNomUtilisateur());
            Label labelDescription = new Label("Password : " + etudiant.getMotDePasse());
            Label labelgroupe = new Label("Groupe : " + etudiant.getGroupe());

            labelNom.setFont(Font.font("System Bold", 14));
            labelDescription.setFont(Font.font("System Bold", 14));
            labelgroupe.setFont(Font.font("System Bold", 14));
            etudiantContainer.getChildren().addAll(labelNom, labelDescription, labelgroupe);
        }
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
