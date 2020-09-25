package regExpTraining;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private String myPattern;
    private String pathFile;

    public Main(String myPattern, String pathFile) {
        this.myPattern = myPattern;
        this.pathFile = pathFile;
    }

    public static List<String> myMatcher(String myPattern, String readFile) {
        List<String> matchesList = new ArrayList<>();
        Pattern pattern = Pattern.compile(myPattern);
        Matcher matcher = pattern.matcher(readFile);
        while (matcher.find()) {
            matchesList.add(matcher.group());
        }
        try {
            if (matchesList.size() == 0) throw new MyException("Nothing is found");
        } catch (MyException e) {
            e.printStackTrace();
        }
        return matchesList;
    }

    public static void main(String[] args) {

        final String myPattern = "[8]\\d{10}" + "|" + "[+3]+\\d{10}" + "|" +
                "[+3(]+\\d{3}+[)]+\\d{7}" + "|" + "[0]+\\d{2} \\d{3} \\d{2} \\d{2}" + "|" +
                "[(0]+\\d{2}+[)]\\d{3}[-]\\d{2}[-]\\d{2}" + "|" + "[0]+\\d{2} \\d{3}[-]\\d{2}[-]\\d{2}" + "|" +
                "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

        final String pathFile = "src\\regExpTraining\\text1.txt";

        Main main = new Main()

        //Новый хомяк (библиотечный java.io) - IO Stream


//        //Новый хомяк (библиотечный java.io) - Reader/Writer
//        FileReader reader1;
//        FileReader reader2;
//        BufferedReader bufferedReader1;
//        BufferedReader bufferedReader2;
//        String readFile1 = null;
//        String readFile2 = null;
//        try {
//            reader1 = new FileReader(file1, myCharset);
//            reader2 = new FileReader(file2, myCharset);
//            bufferedReader1 = new BufferedReader(reader1);
//            bufferedReader2 = new BufferedReader(reader2);
//
//            while (bufferedReader1.ready()) {
//                if (readFile1 == null) readFile1 = bufferedReader1.readLine();
//                else readFile1 = readFile1.concat("\n").concat(bufferedReader1.readLine());
//            }
//            reader1.close();
//            bufferedReader1.close();
//
//            while (bufferedReader2.ready()) {
//                if (readFile2 == null) readFile2 = bufferedReader2.readLine();
//                else readFile2 = readFile2.concat("\n").concat(bufferedReader2.readLine());
//            }
//            reader2.close();
//            bufferedReader2.close();
//
//        } catch (IOException | NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        //Parsing
//        List<String> parsedFile1List = myMatcher(myPattern, readFile1);
//        List<String> parsedFile2List = myMatcher(myPattern, readFile2);
//
//        //Write
//        File parsedFile1 = new File("src\\regExpTraining\\parsedText1_v3.txt");
//        File parsedFile2 = new File("src\\regExpTraining\\parsedText2_v3.txt");
//        FileWriter fileWriter1;
//        FileWriter fileWriter2;
//        try {
//            fileWriter1 = new FileWriter(parsedFile1);
//            fileWriter1.write(parsedFile1List.toString());
//            fileWriter1.close();
//
//            fileWriter2 = new FileWriter(parsedFile2);
//            fileWriter2.write(parsedFile2List.toString());
//            fileWriter2.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public String getMyPattern() {
        return myPattern;
    }

    public void setMyPattern(String myPattern) {
        this.myPattern = myPattern;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

}
