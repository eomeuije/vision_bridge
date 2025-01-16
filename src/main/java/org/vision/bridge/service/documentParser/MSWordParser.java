package org.vision.bridge.service.documentParser;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.*;

public class MSWordParser {

    public String parseDOCX(InputStream inputStream) throws IOException {
        StringBuilder result = new StringBuilder();

        try (XWPFDocument document = new XWPFDocument(inputStream)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                result.append(paragraph.getText()).append("\n");
            }

        }
        return result.toString();
    }

    public String parseDOCX(String filePath) throws IOException {
        File file = new File(filePath);
        try (InputStream inputStream = new FileInputStream(file)) {
            return this.parseDOCX(inputStream);
        }
    }
}
