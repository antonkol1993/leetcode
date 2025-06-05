package gp.solutions.taskNo109;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader("INPUT.TXT"));
        try (FileWriter fileWriter = new FileWriter("OUTPUT.TXT")) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("/");
                int a = Integer.parseInt(parts[0]);
                int b = Integer.parseInt(parts[1]);

                String result = divideWithPeriod(a, b);
                fileWriter.write(result + "\n");
            }
        }

    }

    static String divideWithPeriod(int a, int b) {
        int integerPart = a / b;
        int remainder = a % b;

        if (remainder == 0) {
            return String.valueOf(integerPart);
        }

        StringBuilder result = new StringBuilder();
        result.append(integerPart).append(".");

        Map<Integer, Integer> seenRemainders = new HashMap<>();
        StringBuilder decimal = new StringBuilder();
        int pos = 0;

        while (remainder != 0) {
            if (seenRemainders.containsKey(remainder)) {
                int start = seenRemainders.get(remainder);
                String nonRepeating = decimal.substring(0, start);
                String repeating = decimal.substring(start);
                result.append(nonRepeating).append("(").append(repeating).append(")");
                return fixResult(result.toString());
            }

            seenRemainders.put(remainder, pos++);
            remainder *= 10;
            int digit = remainder / b;
            decimal.append(digit);
            remainder %= b;
        }

        result.append(decimal);
        return fixResult(result.toString());
    }

    static String fixResult(String s) {
        if (s.contains(".")) {
            if (s.endsWith("(0)")) {
                s = s.substring(0, s.length() - 3);
            }
            if (s.contains("(")) {
                return s;
            }
            while (s.endsWith("0")) {
                s = s.substring(0, s.length() - 1);
            }

            if (s.endsWith(".")) {
                s = s.substring(0, s.length() - 1);
            }
        }
        return s;
    }
}

