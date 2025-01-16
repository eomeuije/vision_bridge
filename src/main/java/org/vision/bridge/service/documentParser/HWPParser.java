package org.vision.bridge.service.documentParser;

import kr.dogfoot.hwplib.object.HWPFile;
import kr.dogfoot.hwplib.reader.HWPReader;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractMethod;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractOption;
import kr.dogfoot.hwplib.tool.textextractor.TextExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HWPParser {

    public String parseHWP(InputStream file) throws Exception {
        HWPFile hwpFile = HWPReader.fromInputStream(file);
        StringBuilder extractedText = new StringBuilder();


        TextExtractOption option = new TextExtractOption();
        option.setMethod(TextExtractMethod.InsertControlTextBetweenParagraphText);
        option.setWithControlChar(false);
        option.setAppendEndingLF(true);

        return TextExtractor.extract(hwpFile, option);
    }

    public String parseHWP(String filePath) throws Exception{
        File file = new File(filePath);
        try (InputStream inputStream = new FileInputStream(file)) {
            return this.parseHWP(inputStream);
        }
    }
//
//    public String parseHWPX(InputStream file) throws Exception {
//        HWPFile hwpFile = HWPReader.fromInputStream(file);
//        StringBuilder extractedText = new StringBuilder();
//
//        for (Section section : hwpFile.getBodyText().getSectionList()) {
//            for (Paragraph paragraph : section.getParagraphList()) {
//                extractedText.append(getParagraphText(paragraph)).append("\n");
//            }
//        }
//    }
//
//    public String parseHWPX(String filePath) throws IOException{
//        File file = new File(filePath);
//        try (InputStream inputStream = new FileInputStream(file)) {
//            return this.parse(inputStream);
//        }
//    }
}
