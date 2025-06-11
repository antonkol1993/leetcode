package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class MainOptimize {
    public static void main(String[] args) throws IOException {
        printMemory("Before");

        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));

        String[] parts = reader.readLine().trim().split(" ");
        String a = parts[0], b = parts[1], c = parts[2];

        boolean found = false;

        char[] aChars = a.toCharArray();
        Arrays.sort(aChars);  // для минимального X
        boolean[] usedA = new boolean[a.length()];

        found = generateAndCheck(aChars, usedA, "", b, c, true, writer);

        if (!found) {
            char[] bChars = b.toCharArray();
            Arrays.sort(bChars);  // для минимального X
            boolean[] usedB = new boolean[b.length()];

            found = generateAndCheck(bChars, usedB, "", a, c, false, writer);
        }

        if (!found) {
            writer.write("NO\n");
        }

        reader.close();
        writer.close();

        printMemory("After");
    }

    static boolean generateAndCheck(char[] from, boolean[] used, String prefix,
                                    String other, String c, boolean aFirst, BufferedWriter writer) throws IOException {
        if (prefix.length() == from.length) {
            int x = Integer.parseInt(prefix); // Даже если prefix = "0011", x = 11
            int y;
            try {
                y = Integer.parseInt(c) - x;
            } catch (NumberFormatException e) {
                return false; // если c слишком большое для int
            }
            if (y < 0) return false;

// Проверяем, можно ли из other собрать y
            if (canForm(String.valueOf(y), other)) {
                writer.write("YES\n");
                if (aFirst) {
                    writer.write(x + " " + y + "\n");
                } else {
                    writer.write(y + " " + x + "\n");
                }
                return true;
            }


            return false;
        }

        for (int i = 0; i < from.length; i++) {
            if (used[i]) continue;
            if (i > 0 && from[i] == from[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            if (generateAndCheck(from, used, prefix + from[i], other, c, aFirst, writer)) {
                return true;
            }
            used[i] = false;
        }

        return false;
    }

    static boolean canForm(String value, String source) {
        int[] count = new int[10];
        for (char ch : source.toCharArray()) {
            count[ch - '0']++;
        }
        for (char ch : value.toCharArray()) {
            if (--count[ch - '0'] < 0) return false;
        }
        return true;
    }

    static void printMemory(String label) {
        Runtime runtime = Runtime.getRuntime();
        long total = runtime.totalMemory() / 1024;
        long free = runtime.freeMemory() / 1024;
        long used = total - free;
        long max = runtime.maxMemory() / 1024;
        System.out.printf("[%s] Used memory: %d KB | Free memory: %d KB | Max memory: %d KB%n",
                label, used, free, max);
    }
}
