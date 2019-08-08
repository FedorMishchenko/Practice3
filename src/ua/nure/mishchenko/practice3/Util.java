package ua.nure.mishchenko.practice3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Util {

    private Util(){
        throw new IllegalStateException("Utility class");
    }

    public static String readFile(String fileName) {
        StringBuilder sb  = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), "cp1251"))){
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line)
                        .append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
