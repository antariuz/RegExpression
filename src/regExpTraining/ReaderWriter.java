package regExpTraining;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReaderWriter {

    private String fileToReadPath;
    private String fileNameToWrite;
    private String parsePattern;
    private Charset charset;

    public ReaderWriter(String fileToReadPath, String fileNameToWrite, String parsePattern, Charset charset) {
        this.fileToReadPath = fileToReadPath;
        this.fileNameToWrite = fileNameToWrite;
        this.parsePattern = parsePattern;
        this.charset = charset;
    }

    public void parseFile() {
        try {
            //Read
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileToReadPath, charset));
            String readFile = null;
            while (bufferedReader.ready()) {
                if (readFile == null) readFile = bufferedReader.readLine();
                else readFile = readFile.concat("\n").concat(bufferedReader.readLine());
            }
            bufferedReader.close();

            //Parsing
            Matcher matcher = Pattern.compile(parsePattern).matcher(readFile);
            List<String> matchesList = new ArrayList<>();
            while (matcher.find()) {
                matchesList.add(matcher.group());
            }
            if (matchesList.size() == 0) throw new MyException("Nothing is found");

            //Write
            FileWriter fileWriter = new FileWriter(new File(fileNameToWrite));
            fileWriter.write(matchesList.toString());
            fileWriter.close();

        } catch (IOException | MyException e) {
            e.printStackTrace();
        }

    }

    public String getFileToReadPath() {
        return fileToReadPath;
    }

    public void setFileToReadPath(String fileToReadPath) {
        this.fileToReadPath = fileToReadPath;
    }

    public String getFileNameToWrite() {
        return fileNameToWrite;
    }

    public void setFileNameToWrite(String fileNameToWrite) {
        this.fileNameToWrite = fileNameToWrite;
    }

    public String getParsePattern() {
        return parsePattern;
    }

    public void setParsePattern(String parsePattern) {
        this.parsePattern = parsePattern;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

}
