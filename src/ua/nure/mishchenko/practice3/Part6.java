package ua.nure.mishchenko.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    private static final String INPUT = Util.readFile("part6.txt");
    private static final String REGEX = "([\\p{L}]+)";
    private static final String REGEX2 = "(?<!\\p{L})";

    public static String convert(String input){
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile(REGEX).matcher(input);
        while (matcher.find()) {
            long counter = 0;
            Matcher m = Pattern.compile(REGEX2 + matcher.group())
                    .matcher(input);
            while (m.find()){
                counter++;
            }
            m.reset();
            if (m.find()) {
                if (counter > 1) {
                    matcher.appendReplacement(sb, "_$0");
                } else {
                    matcher.appendReplacement(sb, "$0");
                }
            }
        }
        matcher.appendTail(sb).append(System.lineSeparator());
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(convert(INPUT));
    }
}

















