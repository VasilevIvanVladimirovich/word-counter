import java.io.*;
import java.util.Vector;
import java.util.StringTokenizer;

public class FileProcessing {
    private int countWordInText = 0;

    public FileProcessing(File inputfile) throws FileNotFoundException, IOException {
        BufferedReader buff_reader = new BufferedReader(new FileReader(inputfile));
        int countInLine;
        for (String line; (line = buff_reader.readLine()) != null; ) {
            countInLine = new StringTokenizer(line).countTokens();
            this.countWordInText += countInLine;
        }
    }

    public int getCountWordInText() {
        return countWordInText;
    }
}
