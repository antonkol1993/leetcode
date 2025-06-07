package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memBefore = runtime.totalMemory() - runtime.freeMemory();



        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(" ");
            String a = parts[0];
            String b = parts[1];
            String c = parts[2];
            int cVal = Integer.parseInt(c);

            int sumA = digitSum(a);
            int sumB = digitSum(b);
            int sumC = digitSum(c);

            if ((sumA + sumB - sumC) % 9 != 0) {
                writer.write("NO\n");
                continue;
            }

            // result[0] = minX, result[1] = bestX, result[2] = bestY
            int[] result = {Integer.MAX_VALUE, -1, -1};

            permuteA(a.toCharArray(), 0, b.toCharArray(), cVal, result);

            if (result[1] != -1) {
                writer.write("YES\n");
                writer.write(result[1] + " " + result[2] + "\n");
            } else {
                writer.write("NO\n");
            }
        }
        writer.close();
        reader.close();



        long endTime = System.currentTimeMillis();
        runtime.gc();
        long memAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Время выполнения: " + (endTime - startTime) + " ms");
        System.out.println("Использовано памяти: " + ((memAfter - memBefore) / 1024) + " KB");
    }

    private static void permuteA(char[] aChars, int index, char[] bChars, int cVal, int[] result) {
        if (index == aChars.length) {
            String normalized = normalizeNumber(new String(aChars));
            int x = Integer.parseInt(normalized);

            permuteB(bChars, 0, cVal, x, result);
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = index; i < aChars.length; i++) {
            if (!used.contains(aChars[i])) {
                used.add(aChars[i]);
                swap(aChars, index, i);
                permuteA(aChars, index + 1, bChars, cVal, result);
                swap(aChars, index, i);
            }
        }
    }

    private static void permuteB(char[] bChars, int index, int cVal, int x, int[] result) {
        if (index == bChars.length) {
            String normalized = normalizeNumber(new String(bChars));

            int y = Integer.parseInt(new String(bChars));
            if (x + y == cVal && x < result[0]) {
                result[0] = x;
                result[1] = x;
                result[2] = y;
            }
            return;
        }
        Set<Character> used = new HashSet<>();
        for (int i = index; i < bChars.length; i++) {
            if (!used.contains(bChars[i])) {
                used.add(bChars[i]);
                swap(bChars, index, i);
                permuteB(bChars, index + 1, cVal, x, result);
                swap(bChars, index, i);
            }
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int digitSum(String num) {
        int sum = 0;
        for (char ch : num.toCharArray()) {
            sum += Character.getNumericValue(ch);
        }
        return sum;
    }

    private static String normalizeNumber(String num) {
        int i = 0;
        while (i < num.length() - 1 && num.charAt(i) == '0') {
            i++;
        }
        return num.substring(i);
    }

}
