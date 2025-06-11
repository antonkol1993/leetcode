package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class Main_8_Memory_Limit_Exceeded {


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));

        String[] parts = reader.readLine().trim().split(" ");
        String a = parts[0], b = parts[1], c = parts[2];

        List<String> aPerms = permutations(a);
        List<String> bPerms = permutations(b);

        Collections.sort(aPerms); // Чтобы минимальный x нашёлся первым


        outer:
        for (String x : aPerms) {
            for (String y : bPerms) {
                if (checkSum(x, y, c)) {
                    printMemory("After solving");
                    writer.write("YES\n");
                    writer.write(x + " " + y);
                    break outer;
                } else {
                    writer.write("NO\n");
                    break outer;
                }
            }
        }



        reader.close();
        writer.close();
    }


    static List<String> permutations(String num) {
        List<String> result = new ArrayList<>();
        permute(num.toCharArray(), 0, result, new HashSet<>());
        return result;
    }

    static void permute(char[] arr, int index, List<String> result, Set<String> seen) {
        if (index == arr.length) {
            String s = new String(arr);
            if (!s.startsWith("0") || s.equals("0")) {
                if (seen.add(s)) {
                    result.add(s);
                }
            }
            return;
        }

        for (int i = index; i < arr.length; i++) {
            swap(arr, i, index);
            permute(arr, index + 1, result, seen);
            swap(arr, i, index);
        }
    }

    static void swap(char[] arr, int i, int j) {
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
    }

    static boolean checkSum(String x, String y, String c) {
        int carry = 0;
        int i = x.length() - 1, j = y.length() - 1, k = c.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int a = i >= 0 ? x.charAt(i--) - '0' : 0;
            int b = j >= 0 ? y.charAt(j--) - '0' : 0;
            int sum = a + b + carry;
            int digit = sum % 10;
            carry = sum / 10;

            if (k < 0 || digit != c.charAt(k--) - '0') {
                return false;
            }
        }

        return k < 0;
    }

    static void printMemory(String stage) {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc(); // запуск сборки мусора для более точного измерения
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.printf("%s - Used memory: %.2f MB%n", stage, usedMemory / (1024.0 * 1024.0));
    }
}
