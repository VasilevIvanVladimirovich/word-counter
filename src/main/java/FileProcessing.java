import java.io.*;
import java.io.FileInputStream;
import java.util.Locale;
import java.util.Vector;
import java.util.StringTokenizer;


import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class FileProcessing {
    private int countWordInText = 0;

    public FileProcessing(File inputfile) throws FileNotFoundException, IOException {
        if (getFileExtension(inputfile).toLowerCase().equals("txt")) {
            BufferedReader buff_reader = new BufferedReader(new FileReader(inputfile));
            int countInLine;
            for (String line; (line = buff_reader.readLine()) != null; ) {
                countInLine = new StringTokenizer(line).countTokens();
                this.countWordInText += countInLine;
            }
        }
        if (getFileExtension(inputfile).toLowerCase().equals("doc") || getFileExtension(inputfile).toLowerCase().equals("docx")) {
            XWPFDocument doc = new XWPFDocument(new FileInputStream(inputfile));
            XWPFWordExtractor extract = new XWPFWordExtractor(doc);
            this.countWordInText = new StringTokenizer(extract.getText()).countTokens();

        }
    }

    public int getCountWordInText() {
        return countWordInText;
    }

    private static String getFileExtension(File file) {
        String fileName = file.getName();
        // если в имени файла есть точка и она не является первым символом в названии файла
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            // то вырезаем все знаки после последней точки в названии файла, то есть ХХХХХ.txt -> txt
            return fileName.substring(fileName.lastIndexOf(".") + 1);
            // в противном случае возвращаем заглушку, то есть расширение не найдено
        else return "";
    }
}
