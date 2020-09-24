package regExpTraining;

import java.io.*;
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
        System.out.println("---------------------------------------------");
    }

    public static List<String> myOldMatcher(String myPattern, List<String> list) throws MyException {
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

    public static List<String> myMatcher(String myPattern, String readFile) throws MyException {
        List<String> matchesList = new ArrayList<>();
        Pattern pattern = Pattern.compile(myPattern);
        Matcher matcher = pattern.matcher(readFile);
        while (matcher.find()) {
            matchesList.add(matcher.group());
        }
        if (matchesList.size() == 0) throw new MyException("Nothing is found");
        return matchesList;
    }

    public static void main(String[] args) throws MyException, IOException {

        final String myPattern = "[8]\\d{10}" + "|" + "[+3]+\\d{10}" + "|" +
                "[+3(]+\\d{3}+[)]+\\d{7}" + "|" + "[0]+\\d{2} \\d{3} \\d{2} \\d{2}" + "|" +
                "[(0]+\\d{2}+[)]\\d{3}[-]\\d{2}[-]\\d{2}" + "|" + "[0]+\\d{2} \\d{3}[-]\\d{2}[-]\\d{2}" + "|" +
                "[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";

        File file1 = new File("src\\regExpTraining\\text1.txt");
        File file2 = new File("src\\regExpTraining\\text2.txt");

        Charset myCharset = Charset.forName("windows-1251");

        //Старый хомяк
//        List<String> list1 = fileToLines(file1.toString());
//        List<String> list2 = fileToLines(file2.toString());
//        showList(list1);
//        showList(list2);
//        System.out.println(myOldMatcher(myPattern, list1));
//        System.out.println(myOldMatcher(myPattern, list2));
//        System.out.println("---------------------------------------------");

        //Новый хомяк (библиотечный java.io) - IO Stream
//        FileInputStream fileInputStream1 = new FileInputStream(file1);
//        FileInputStream fileInputStream2 = new FileInputStream(file2);
//        BufferedInputStream bufferedInputStream1 = new BufferedInputStream(fileInputStream1, fileInputStream1.available());
//        BufferedInputStream bufferedInputStream2 = new BufferedInputStream(fileInputStream2, fileInputStream2.available());
//
//        File parsedFile1 = new File("src\\regExpTraining\\parsedText1_v2.txt");
//        File parsedFile2 = new File("src\\regExpTraining\\parsedText2_v2.txt");
//        FileOutputStream fileOutputStream1 = new FileOutputStream(parsedFile1);
//        FileOutputStream fileOutputStream2 = new FileOutputStream(parsedFile2);
//
//        //Read
//        String readFile1 = new String(bufferedInputStream1.readAllBytes(), myCharset);
//        fileInputStream1.close();
//        bufferedInputStream1.close();
//        String readFile2 = new String(bufferedInputStream2.readAllBytes(), myCharset);
//        fileInputStream2.close();
//        bufferedInputStream2.close();
//
//        //Parsing
//        List<String> parsedFile1List = myMatcher(myPattern, readFile1);
//        List<String> parsedFile2List = myMatcher(myPattern, readFile2);
//
//        //Write
//        fileOutputStream1.write(parsedFile1List.toString().getBytes());
//        fileOutputStream1.close();
//        fileOutputStream2.write(parsedFile2List.toString().getBytes());
//        fileOutputStream2.close();


        //Новый хомяк (библиотечный java.io) - Reader/Writer
        FileReader reader1 = new FileReader(file1);
        FileReader reader2 = new FileReader(file2);
        BufferedReader bufferedReader1 = new BufferedReader(reader1);
        BufferedReader bufferedReader2 = new BufferedReader(reader2);
        String readFile1 = "";
        while (bufferedReader1.ready()) {
            readFile1 = readFile1.concat("\n").concat(bufferedReader1.readLine());
        }
        reader1.close();
        bufferedReader1.close();

        String readFile2 = "";
        while (bufferedReader2.ready()) {
            readFile2 = readFile2.concat("\n").concat(bufferedReader2.readLine());
        }
        reader2.close();
        bufferedReader2.close();

        //Parsing
        List<String> parsedFile1List = myMatcher(myPattern, readFile1);
        List<String> parsedFile2List = myMatcher(myPattern, readFile2);

        //Write
        File parsedFile1 = new File("src\\regExpTraining\\parsedText1_v3.txt");
        File parsedFile2 = new File("src\\regExpTraining\\parsedText2_v3.txt");
        FileWriter fileWriter1 = new FileWriter(parsedFile1);
        fileWriter1.write(parsedFile1List.toString());
        fileWriter1.close();
        FileWriter fileWriter2 = new FileWriter(parsedFile2);
        fileWriter2.write(parsedFile2List.toString());
        fileWriter2.close();

    }
}
