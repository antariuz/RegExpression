package regularexpression;

import java.nio.charset.Charset;

public class Main {

    public static void main(String[] args) {

        String fileToReadPath1 = "src\\regularexpression\\text1.txt";
        String fileToReadPath2 = "src\\regularexpression\\text2.txt";
        String fileNameToWrite1 = "src\\regularexpression\\parsedText1_IOStream.txt";
        String fileNameToWrite2 = "src\\regularexpression\\parsedText2_ReaderWriter.txt";
        String parsePattern = "[8]\\d{10}" + "|" + "[+3]+\\d{10}" + "|" +
                "[+3(]+\\d{3}+[)]+\\d{7}" + "|" + "[0]+\\d{2} \\d{3} \\d{2} \\d{2}" + "|" +
                "[(0]+\\d{2}+[)]\\d{3}[-]\\d{2}[-]\\d{2}" + "|" + "[0]+\\d{2} \\d{3}[-]\\d{2}[-]\\d{2}" + "|" +
                "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
        Charset charset = Charset.forName("windows-1251");

        IOStream ioStream = new IOStream(fileToReadPath1,fileNameToWrite1, parsePattern, charset);
        ioStream.parseFile();

        ReaderWriter readerWriter = new ReaderWriter(fileToReadPath2,fileNameToWrite2, parsePattern, charset);
        readerWriter.parseFile();

    }
}
