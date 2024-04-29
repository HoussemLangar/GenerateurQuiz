package quiz.projet.controller;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.util.List;


public class PDFGenerateur {
    public static void genererPDF(List<String> listeQuiz, String chemin) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                PDType1Font font = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
                contentStream.setFont(font, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                for (String quiz : listeQuiz) {
                    contentStream.showText(quiz);
                    contentStream.newLine();
                }

                contentStream.endText();
            }

            document.save(chemin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
