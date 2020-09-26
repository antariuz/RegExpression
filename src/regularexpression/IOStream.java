package regularexpression;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOStream {

    private String fileToReadPath;
    private String fileNameToWrite;
    private String parsePattern;
    private Charset charset;

    public IOStream(String fileToReadPath, String fileNameToWrite, String parsePattern, Charset charset) {
        this.fileToReadPath = fileToReadPath;
        this.fileNameToWrite = fileNameToWrite;
        this.parsePattern = parsePattern;
        this.charset = charset;
    }

    public void parseFile() {
        try {
            //Read
            FileInputStream fileInputStream = new FileInputStream(new File(fileToReadPath));
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, fileInputStream.available());
            String readFile = new String(bufferedInputStream.readAllBytes(), charset);
            bufferedInputStream.close();

            //Parsing
            Matcher matcher = Pattern.compile(parsePattern).matcher(readFile);
            List<String> matchesList = new ArrayList<>();
            while (matcher.find()) {
                matchesList.add(matcher.group());
            }
            if (matchesList.size() == 0) throw new MyException("Nothing is found");

            //Write
            FileOutputStream fileOutputStream = new FileOutputStream(new File(fileNameToWrite));
            fileOutputStream.write(matchesList.toString().getBytes());
            fileOutputStream.close();
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
