package org.example;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.sound.midi.SoundbankResource;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDateTime;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// /usr/share/fonts/truetype/freefont/FreeSans.ttf
public class Main {
    public static final String FONT= "/usr/share/fonts/truetype/dejavu/DejaVuSans.ttf";
    public static final String FONT1 = "src/resources/DejaVuSans.ttf";


    public static void main(String args[]) throws IOException {
        Main m = new Main();
        String font = "DejaVuSans.ttf";
        String font1 = "ARIALUNI.TTF";
        String font2 = "times.ttf";

        try {
            String qq = m.getFileFromResource(font2).toString();
            System.out.println("qq: " + qq);
            createPdf("so.pdf", qq);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void createPdf(String dest, String font) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();

        Paragraph pTime = new Paragraph(LocalDateTime.now().toString());
        document.add(pTime);

        pTime = new Paragraph("\n");
        document.add(pTime);

        Font f1 = FontFactory.getFont(font, "Cp1251", true);
        Paragraph p1 = new Paragraph("Testing of Cp1251: Гепи пич!", f1);
        document.add(p1);

        Font f2 = FontFactory.getFont(font, BaseFont.IDENTITY_H, true);
        Paragraph p2 = new Paragraph("Testing of UTF: Гепи пич!", f2);
        document.add(p2);

        document.close();
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }
}