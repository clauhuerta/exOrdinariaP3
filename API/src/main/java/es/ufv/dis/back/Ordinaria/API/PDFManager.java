package es.ufv.dis.back.Ordinaria.API;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class PDFManager {

    public void generarPDF(Starship starship, int totalFilms, String filePath) {
        try {
            // Asegura que la carpeta existe
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
            PdfWriter.getInstance(doc, new FileOutputStream(file));
            doc.open();
            doc.add(new Paragraph("Name: " + starship.getName()));
            doc.add(new Paragraph("Model: " + starship.getModel()));
            doc.add(new Paragraph("Class: " + starship.getStarship_class()));
            doc.add(new Paragraph("Crew: " + starship.getCrew()));
            doc.add(new Paragraph("Total Films: " + totalFilms));
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
