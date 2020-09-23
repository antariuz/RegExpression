package regExpTraining;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static List<String> fileToLines(String filePath) {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(Paths.get(filePath), Charset.forName("windows-1251"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void showList(List<String> list) {
        for (String line : list) {
            System.out.println(line);
        }
    }

    public static List<String> myMatcher(String myPattern, List<String> list) throws MyException {
        Pattern pattern = Pattern.compile(myPattern);
        List<String> matchesList = new ArrayList<>();
        Matcher matcher;

        for (String line : list) {
            matcher = pattern.matcher(line);
            while (matcher.find()) {
                matchesList.add(matcher.group());
            }
        }
        if (matchesList.size() == 0) throw new MyException("Nothing is found");
        return matchesList;
    }

    public static void main(String[] args) throws MyException {

        final String myFile1 = "src\\regExpTraining\\text1.txt";
        final String myFile2 = "src\\regExpTraining\\text2.txt";
        final String myPattern = "[8]\\d{10}" + "|" + "[+3]+\\d{10}" + "|" +
                "[+3(]+\\d{3}+[)]+\\d{7}" + "|" + "[0]+\\d{2} \\d{3} \\d{2} \\d{2}" + "|" +
                "[(0]+\\d{2}+[)]\\d{3}[-]\\d{2}[-]\\d{2}" + "|" + "[0]+\\d{2} \\d{3}[-]\\d{2}[-]\\d{2}" + "|" +
                "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

        List<String> list1 = fileToLines(myFile1);
        List<String> list2 = fileToLines(myFile2);
        //showList(list1);
        //showList(list2);
        System.out.println(myMatcher(myPattern, list1));
        System.out.println(myMatcher(myPattern, list2));


    }
}
