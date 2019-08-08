package ua.nure.mishchenko.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {

    private static final String REGEX = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    private static final String ROMAN = "(M|CM|D|CD|C|XC|L|XL|X|IX|V|IV|I)";
    private static final int FORTY = 40;
    private static final int FIFTY = 50;
    private static final int NINETY = 90;
    private static final int HUNDRED = 100;
    private static final int FOUR_HUNDRED = 400;
    private static final int FIVE_HUNDRED = 500;
    private static final int NINE_HUNDRED = 900;
    private static final int THOUSAND = 1000;

    private static int[] decimalValues =
            {1,4,5,9,10,FORTY,FIFTY,NINETY,HUNDRED,FOUR_HUNDRED,FIVE_HUNDRED
                    ,NINE_HUNDRED,THOUSAND};
    private static String[] romanNumerals =
            {"I","IV","V","IX","X","XL","L","XC","C","CD","D","CM","M"};

    public static String decimal2Roman(int x){
        StringBuilder sb = new StringBuilder();
        for (int i = romanNumerals.length - 1; i >= 0; i--) {
            while (x >= decimalValues[i]) {
                sb.append(romanNumerals[i]);
                x -= decimalValues[i];
            }
        }
        return sb.toString();
    }

    public static String roman2Decimal(String s) {
        if (s == null || s.isEmpty() || !s.matches(REGEX)) {
            return String.valueOf(-1);
        }

        Matcher matcher = Pattern.compile(ROMAN).matcher(s);
        int result = 0;

        while (matcher.find()) {
            for (int i = 0; i < romanNumerals.length; i++) {
                if (romanNumerals[i].equals(matcher.group(0))) {
                    result += decimalValues[i];
                }
            }
        }

        return String.valueOf(result);
    }

    public static void main(String[] args) {
        System.out.println(decimal2Roman(NINETY));
        System.out.println(roman2Decimal("XC"));
    }
}
