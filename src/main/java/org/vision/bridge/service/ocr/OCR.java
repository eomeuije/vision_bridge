package org.vision.bridge.service.ocr;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class OCR {

    private final Tesseract tesseract;

    private final String TESS_PATH;

    @Autowired
    public OCR(@Value("${tesseract.path}") String TESS_PATH) {
        this.TESS_PATH = TESS_PATH;
        tesseract = new Tesseract();
        tesseract.setDatapath(TESS_PATH);
        tesseract.setLanguage(OCRLang.KOR);
    }

    public OCR(@Value("${tesseract.path}") String TESS_PATH, String langType) {
        this.TESS_PATH = TESS_PATH;
        tesseract = new Tesseract();
        tesseract.setDatapath(TESS_PATH);
        tesseract.setLanguage(langType);
    }

    public String recognize(String filePath) throws TesseractException {
        File imageFile = new File(filePath);
        return tesseract.doOCR(imageFile);
    }

    public String recognize(InputStream inputStream) throws TesseractException, IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        return tesseract.doOCR(bufferedImage);
    }
}