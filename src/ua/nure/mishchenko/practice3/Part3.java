package ua.nure.mishchenko.practice3;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part3 {
    private static final String INPUT = Util.readFile("part3.txt");
    private static final String REGEX = "((?m)[\\p{L}]{3,})";
    private static final String REGEX2 = "(,\\s|\\[|])";

    public static String convert(String input) {
        StringBuffer sb = new StringBuffer();
        Matcher matcher = Pattern.compile(REGEX).matcher(input);
        while (matcher.find()) {
            matcher.appendReplacement(sb, Stream.of(matcher.group())
                    .map(Part3::convertFirstChar)
                    .collect(Collectors.joining()));
        }
        return sb.toString();
    }

    private static String convertFirstChar(String x) {
        if (x.length() > 2) {
            if (Character.isUpperCase(x.charAt(0))) {
                x = String.valueOf(x.charAt(0))
                        .toLowerCase(Locale.ENGLISH)
                        .concat(x.substring(1))
                        .replaceAll(REGEX2, "");
            } else {
                x = String.valueOf(x.charAt(0))
                        .toUpperCase(Locale.ENGLISH)
                        .concat(x.substring(1))
                        .replaceAll(REGEX2, "");
            }
        }
        return x.replaceAll(REGEX2, "");
    }

    public static void main(String[] args) {
        System.out.println(convert(INPUT));
    }
}
