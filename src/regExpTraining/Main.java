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
        Matcher matcher = pattern.matcher(list.get(0));
        List<String> matchesList = new ArrayList<>();
        if (matcher.find()) {
            matchesList.add(matcher.group());
        } else throw new MyException("Nothing is found");
        return matchesList;
    }

    public static void main(String[] args) throws MyException {

        final String myFile = "src\\regExpTraining\\text1.txt";
        final String myPattern = "^текст";

        List<String> list = fileToLines(myFile);
        showList(list);
        System.out.println(myMatcher(myPattern, list));

    }
}
