package org.vision.bridge.service.documentParser;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class PDFParser {

    public String parse(InputStream file) throws IOException{
        String text = null;
        try (PDDocument document = PDDocument.load(file)) {
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                text = stripper.getText(document);
            }
        }
        return text;
    }

    public String parse(String filePath) throws IOException{
        File file = new File(filePath);
        try (InputStream inputStream = new FileInputStream(file)) {
            return this.parse(inputStream);
        }
    }
}
