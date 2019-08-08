package ua.nure.mishchenko.practice3;

import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part2 {
    private static final String INPUT = Util.readFile("part2.txt");
    private static final String REGEX = "[\\s'-;\r]";
    private static final String REGEX_MIN = "[\\s\r]+";

    public static String convert(String input) {
        int min = Pattern.compile(REGEX_MIN)
                .splitAsStream(input)
                .min(Comparator.comparingInt(String::length)).orElse("").length();
        int max = Pattern.compile(REGEX)
                .splitAsStream(input)
                .max(Comparator.comparingInt(String::length)).orElse("").length();
        StringBuilder sb = new StringBuilder();
        String minWords = Pattern.compile(REGEX)
                .splitAsStream(input)
                .distinct()
                .filter(s -> s.length() == min)
                .map(s -> s + ", ")
                .collect(Collectors.joining());
        sb.append("Min: ")
                .append(minWords.subSequence(0, minWords.length() - 2).toString());
        String maxWords = Pattern.compile(REGEX)
                .splitAsStream(input)
                .distinct()
                .filter(s -> s.length() == max)
                .map(s -> s + ", ")
                .collect(Collectors.joining());
        return sb.append(System.lineSeparator())
                .append("Max: ")
                .append(maxWords.subSequence(0, maxWords.length() - 2).toString())
                .append(System.lineSeparator()).toString();
    }

    public static void main(String[] args) {
        System.out.println(convert(INPUT));
    }
}
