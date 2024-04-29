module quiz.projet {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires pdfbox.app;
    exports quiz.projet.controller;
    exports quiz.projet.modele;
    opens quiz.projet.modele to javafx.fxml;

    opens quiz.projet.controller;
    opens quiz.projet to javafx.fxml;
    exports quiz.projet;
}
