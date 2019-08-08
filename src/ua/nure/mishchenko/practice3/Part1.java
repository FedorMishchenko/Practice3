package ua.nure.mishchenko.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {

    private static final String INPUT = Util.readFile("part1.txt");
    private static final String REGEX = "\\s*(([\\p{L}]+)\\s*;)\\s*" +
            "(([\\p{L}]+)\\s*([\\p{L}]+))\\s*;" +
            "\\s*([\\w]+(@)(\\w+\\.([\\w]+)))";

    private static final int GROUP_LOGIN = 2;
    private static final int GROUP_EMAIL = 6;
    private static final int GROUP_NAME = 5;
    private static final int GROUP_LAST_NAME = 4;
    private static final int GROUP_DOMAIN = 8;
    private static final String DELIMITER = ";";
    private static final String DELIMITER3 = ": ";
    private static final String DELIMITER2 = " ==> ";
    private static final int BOUND = 4;
    private static final int SIZE = 4;

    public static String convert1(String input) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = Pattern.compile(REGEX).matcher(input);
        while (matcher.find()) {
            sb.append(matcher.group(GROUP_LOGIN))
                    .append(DELIMITER3)
                    .append(matcher.group(GROUP_EMAIL)
                            .replaceAll("\\s", ""))
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String convert2(String input) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = Pattern.compile(REGEX).matcher(input);
        while (matcher.find()) {
            sb.append(matcher.group(GROUP_NAME))
                    .append(" ")
                    .append(matcher.group(GROUP_LAST_NAME))
                    .append(" (email: ")
                    .append(matcher.group(GROUP_EMAIL)
                            .replaceAll("\\s", ""))
                    .append(")").append(System.lineSeparator());
        }
        return sb.toString();
    }

    public static String convert3(String input) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = Pattern.compile(REGEX).matcher(input);

        Object[] domain = new String[SIZE];
        int index = 0;
        while (matcher.find()) {
            domain[index++] = (matcher.group(GROUP_DOMAIN)
                    .replaceAll("\\s", ""));
        }
        domain = Stream.of(domain)
                .filter(x -> x instanceof String)
                .distinct()
                .toArray();
        StringBuilder[] buff = new StringBuilder[domain.length];
        for (int i = 0; i < buff.length; i++) {
            buff[i] = new StringBuilder();
        }
        matcher.reset();
        while (matcher.find()) {
            index = 0;
            for (Object obj : domain) {
                if (obj.equals(matcher.group(GROUP_DOMAIN).replaceAll("\\s", ""))) {
                    buff[index].append(matcher.group(GROUP_LOGIN)).append(", ");
                }
                index++;
            }
        }
        index = 0;
        for (Object obj : domain) {
            buff[index].setLength(buff[index].length() - 2);
            sb.append(obj).append(DELIMITER2).append(buff[index]).append(System.lineSeparator());
            index++;
        }
        return sb.toString().trim();

    }

    public static String convert4(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append("Login;Name;Email;Password");
        Matcher matcher = Pattern.compile(REGEX).matcher(input);
        while (matcher.find()) {
            sb.append(matcher.group())
                    .append(DELIMITER)
                    .append(Stream.generate(new SecureRandom()::nextInt)
                            .map(x -> x / 100)
                            .map(String::valueOf)
                            .filter(x -> x.length() == BOUND && Integer.parseInt(x) > 0)
                            .limit(1)
                            .collect(Collectors.joining()));
        }
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert1(INPUT));
        System.out.println(convert2(INPUT));
        System.out.println(convert3(INPUT));
        System.out.println();
        System.out.println(convert4(INPUT));
    }
}
