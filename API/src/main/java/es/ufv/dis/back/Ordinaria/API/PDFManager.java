package es.ufv.dis.back.Ordinaria.API;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class PDFManager {

    public static void generatePDF(Starship s, String outputDir) {
        try {
            String safeName = s.getName().replaceAll("\\s+","_");
            File out = new File(outputDir, safeName + ".pdf");
            Document doc = new Document(PageSize.A4, 50, 50, 100, 72);
            PdfWriter.getInstance(doc, new FileOutputStream(out));
            doc.open();
            doc.add(new Paragraph("Starship Report",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
            doc.add(Chunk.NEWLINE);
            doc.add(new Paragraph("Name: " + s.getName()));
            doc.add(new Paragraph("Model: " + s.getModel()));
            doc.add(new Paragraph("Class: " + s.getStarshipClass()));
            doc.add(new Paragraph("Crew: " + s.getCrew()));
            doc.add(new Paragraph("Films appeared in: " + s.getFilms().size()));
            doc.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
