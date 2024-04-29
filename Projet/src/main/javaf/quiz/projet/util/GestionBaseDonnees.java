package quiz.projet.util;
import javafx.fxml.FXML;
import quiz.projet.modele.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GestionBaseDonnees {
    public static boolean verifierInformationsConnexion(String nomUtilisateur, String motDePasse) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {

            String query = "SELECT * FROM utilisateurs WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomUtilisateur);
            statement.setString(2, motDePasse);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String obtenirRole(String nomUtilisateur, String motDePasse) {
        String role = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD);
            String query = "SELECT role FROM utilisateurs WHERE username = ? AND password = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, nomUtilisateur);
            statement.setString(2, motDePasse);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                role = resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return role;
    }

    public static boolean creerCours(String nom, String description) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "INSERT INTO cours (nom, description) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, description);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int obtenirIdCoursParNom(String nomCours) {
        int idCours = -1;
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT id FROM cours WHERE nom = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomCours);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idCours = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idCours;
    }

    public static boolean supprimerCours(int id) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "DELETE FROM cours WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modifierCours(int idCours, String nouveauNom, String nouvelleDescription) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "UPDATE cours SET nom = ?, description = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nouveauNom);
            statement.setString(2, nouvelleDescription);
            statement.setInt(3, idCours);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Cours> obtenirListeCours() {
        List<Cours> listeCours = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT * FROM cours";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                Cours cours = new Cours(id, nom, description);
                listeCours.add(cours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeCours;
    }

    public static boolean creerGroupe(String nom, String niveau) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "INSERT INTO groupes (nom, niveau) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, niveau);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int obtenirIdGroupeParNom(String nomGroupe) {
        int idgroupe = -1;
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT id FROM groupes WHERE nom = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomGroupe);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idgroupe = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idgroupe;
    }

    public static boolean supprimerGroupe(int idGroupe) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "DELETE FROM groupes WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idGroupe);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modifierGroupe(int idGroupe, String nouveauNom, String nouveauNiveau) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "UPDATE groupes SET nom = ?, niveau = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nouveauNom);
            statement.setString(2, nouveauNiveau);
            statement.setInt(3, idGroupe);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Groupe> obtenirListeGroupes() {
        List<Groupe> listeGroupes = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT * FROM groupes";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int niveau = resultSet.getInt("niveau");
                Groupe groupe = new Groupe(id, nom, niveau);
                listeGroupes.add(groupe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeGroupes;
    }


    public static boolean creerEnseignant(String nomUtilisateur, String motDePasse) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "INSERT INTO enseignant (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomUtilisateur);
            statement.setString(2, motDePasse);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int obtenirIdEnseignantParNom(String nomEnseignant) {
        int idEnseignant = -1;
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT id FROM enseignant WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomEnseignant);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idEnseignant = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEnseignant;
    }

    public static boolean supprimerEnseignant(int idEnseignant) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "DELETE FROM enseignant WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEnseignant);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modifierEnseignant(int idEnseignant, String nouveauMotDePasse) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "UPDATE enseignant SET password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nouveauMotDePasse);
            statement.setInt(2, idEnseignant);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Enseignant> obtenirListeEnseignants() {
        List<Enseignant> listeEnseignants = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT * FROM enseignant";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomUtilisateur = resultSet.getString("username");
                String motDePasse = resultSet.getString("password");
                Enseignant enseignant = new Enseignant(id, nomUtilisateur, motDePasse);
                listeEnseignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEnseignants;
    }


    public static boolean creerEtudiant(String nomUtilisateur, String motDePasse, String Groupe) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "INSERT INTO etudiant (username, password, groupe) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomUtilisateur);
            statement.setString(2, motDePasse);
            statement.setString(3, Groupe);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int obtenirIdEtudiantParNom(String nomEtudiant) {
        int idEnseignant = -1;
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT id FROM etudiant WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nomEtudiant);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idEnseignant = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEnseignant;
    }

    public static boolean supprimerEtudiant(int idEtudiant) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "DELETE FROM etudiant WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idEtudiant);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean modifierEtudiant(int idEtudiant, String nouveauMotDePasse, String nouveauGroupe) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "UPDATE etudiant SET password = ?, groupe = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nouveauMotDePasse);
            statement.setString(2, nouveauGroupe);
            statement.setInt(3, idEtudiant);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Etudiant> obtenirListeEtudiants() {
        List<Etudiant> listeEtudiants = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT * FROM etudiant";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nomUtilisateur = resultSet.getString("username");
                String motDePasse = resultSet.getString("password");
                String Groupe = resultSet.getString("groupe");
                Etudiant etudiant = new Etudiant(id, nomUtilisateur, motDePasse, Groupe);
                listeEtudiants.add(etudiant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeEtudiants;
    }

    public static boolean creerQuestion(String enonceText, String[] choixArray, String[] rChoixArray, String niveauDifficulte, String coursText, String groupeText) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "INSERT INTO questions (enonce, choix, RCorrect, niveauD, cour, groupe) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, enonceText);
            statement.setString(2, String.join(",", choixArray));
            statement.setString(3, String.join(",", rChoixArray));
            statement.setString(4, niveauDifficulte);
            statement.setString(5, coursText);
            statement.setString(6, groupeText);

            int lignesAffectees = statement.executeUpdate();

            if (lignesAffectees > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int obtenirIdQuestionParNom(String enonce) {
        int idQuestion = -1;
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT id FROM questions WHERE enonce = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, enonce);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                idQuestion = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idQuestion;
    }

    public static boolean supprimerQuestion(int idQuestion) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "DELETE FROM questions WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idQuestion);

            int lignesAffectees = statement.executeUpdate();

            if (lignesAffectees > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modifierQuestion(int idQuestion, String nouveauxChoix, String nouveauxRCorrect) {
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "UPDATE questions SET  choix = ?, RCorrect = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, String.join(",", nouveauxChoix));
            statement.setString(2, String.join(",", nouveauxRCorrect));
            statement.setInt(3, idQuestion);

            int lignesAffectees = statement.executeUpdate();

            if (lignesAffectees > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Question> obtenirListeQuestions() {
        List<Question> listeQuestions = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT * FROM questions";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String enonce = resultSet.getString("enonce");
                String choixString = resultSet.getString("choix");
                List<String> choix = new ArrayList<>(Arrays.asList(choixString.split(",")));
                String reponsesCorrectesString = resultSet.getString("RCorrect");
                List<String> reponsesCorrectes = new ArrayList<>(Arrays.asList(reponsesCorrectesString.split(",")));
                String difficulte = resultSet.getString("niveauD");
                String cours = resultSet.getString("cour");
                String groupe = resultSet.getString("groupe");

                Question question = new Question(enonce, choix, reponsesCorrectes, difficulte, cours, groupe);
                listeQuestions.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listeQuestions;
    }

    public static List<String> genererQuestionAlea(String cours, String difficulte, int nombreQuestions) {
        List<String> questionsAleatoires = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE cour = ? AND niveauD = ? ORDER BY RAND() LIMIT ?";

        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cours);
            statement.setString(2, difficulte);
            statement.setInt(3, nombreQuestions);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String enonce = resultSet.getString("enonce");
                    questionsAleatoires.add(enonce);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return questionsAleatoires;

    }
    public static boolean genererQuiz(String cours, String difficulte, List<String> questions, LocalDate date, int tempsM) {
        String insertQuery = "INSERT INTO quiz (cour, niveauD, dateDisponibilite, tempsMax) VALUES (?, ?, ?, ?)";
        String insertQuestionQuery = "INSERT INTO quizquestions (id, idenonce) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cours);
            statement.setString(2, difficulte);
            statement.setObject(3, date);
            statement.setInt(4, tempsM);
            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating quiz failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int quizId = generatedKeys.getInt(1);

                    for (String question : questions) {
                        PreparedStatement questionStatement = connection.prepareStatement(insertQuestionQuery);
                        questionStatement.setInt(1, quizId);
                        int questionId = obtenirIdQuestionDepuisBaseDeDonnees(connection, question);
                        questionStatement.setInt(2, questionId);
                        questionStatement.executeUpdate();
                        questionStatement.close();
                    }

                    return true;
                } else {
                    throw new SQLException("Creating quiz failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static int obtenirIdQuestionDepuisBaseDeDonnees(Connection connection, String question) throws SQLException {
        int idQuestion = -1;

        String query = "SELECT id FROM questions WHERE enonce = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, question);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    idQuestion = resultSet.getInt("id");
                }
            }
        }
        return idQuestion;
    }
    public static List<String> afficherListeQuiz() {
        List<String> listeQuiz = new ArrayList<>();

        String query = "SELECT * FROM quiz ";
        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cours = resultSet.getString("cour");
                String niveau = resultSet.getString("niveauD");
                String disponible = resultSet.getString("dateDisponibilite");
                String temps = resultSet.getString("tempsMax");
                listeQuiz.add(id + ": Cours : " + cours + ", Niveau : " + niveau + ", Disponible En : " + disponible + ", Temps maximum : " + temps + "H");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeQuiz;
    }

    public static List<String> afficherQuizE(int num) {
        List<String> listeQuiz = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(Configuration.DB_URL, Configuration.DB_USER, Configuration.DB_PASSWORD)) {
            String query = "SELECT idenonce FROM quizquestions WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, num);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int idEnonce = resultSet.getInt("idenonce");

                        String query1 = "SELECT enonce, choix FROM questions WHERE id = ?";
                        try (PreparedStatement statement1 = connection.prepareStatement(query1)) {
                            statement1.setInt(1, idEnonce);
                            try (ResultSet resultSet1 = statement1.executeQuery()) {
                                while (resultSet1.next()) {
                                    String enonce = resultSet1.getString("enonce");
                                    String choix = resultSet1.getString("choix");
                                    listeQuiz.add("Enonce : " + enonce + "\nChoix : " + choix+"\n");
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listeQuiz;
    }
}




