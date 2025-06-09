package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class MainNew {
    static int[] digitsA, digitsB, digitsC;
    static boolean[] usedA, usedB;
    static int nA, nB, nC;
    static int carry;
    static int[] resA, resB;
    static boolean found;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("INPUT.TXT"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.TXT"));

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split(" ");
            if (parts.length < 3) {
                bw.write("NO\n");
                continue;
            }

            String a = parts[0];
            String b = parts[1];
            String c = parts[2];

            digitsA = toDigits(a);
            digitsB = toDigits(b);
            digitsC = toDigits(c);

            nA = digitsA.length;
            nB = digitsB.length;
            nC = digitsC.length;

            // Проверка длины
            if (!(nC == Math.max(nA, nB) || nC == Math.max(nA, nB) + 1)) {
                bw.write("NO\n");
                continue;
            }

            // Отсортируем для минимизации результата a
            Arrays.sort(digitsA);
            Arrays.sort(digitsB);

            usedA = new boolean[nA];
            usedB = new boolean[nB];
            resA = new int[nC];
            resB = new int[nC];
            found = false;

            if (dfs(0, 0)) {
                // Проверяем ведущие нули
                if (leadingZero(resA, nC) || leadingZero(resB, nC)) {
                    bw.write("NO\n");
                } else {
                    bw.write("YES\n");
                    bw.write(toNumberString(resA, nC) + " " + toNumberString(resB, nC) + "\n");
                }
            } else {
                bw.write("NO\n");
            }
        }
        bw.close();
        br.close();
    }

    // Рекурсивный DFS
    static boolean dfs(int pos, int carry) {
        if (found) return true;

        if (pos == nC) {
            if (carry == 0 && allUsed(usedA) && allUsed(usedB)) {
                found = true;
                return true;
            }
            return false;
        }

        int target = digitsC[pos];

        for (int i = 0; i < nA; i++) {
            if (usedA[i]) continue;
            for (int j = 0; j < nB; j++) {
                if (usedB[j]) continue;

                int sum = digitsA[i] + digitsB[j] + carry;
                if (sum % 10 == target) {
                    usedA[i] = true;
                    usedB[j] = true;
                    resA[pos] = digitsA[i];
                    resB[pos] = digitsB[j];

                    if (dfs(pos + 1, sum / 10)) return true;

                    usedA[i] = false;
                    usedB[j] = false;
                }
            }
        }
        return false;
    }

    static int[] toDigits(String s) {
        int len = s.length();
        int[] d = new int[len];
        for (int i = 0; i < len; i++) {
            d[len - 1 - i] = s.charAt(i) - '0';
        }
        return d;
    }

    static boolean allUsed(boolean[] used) {
        for (boolean b : used)
            if (!b) return false;
        return true;
    }

    static boolean leadingZero(int[] arr, int length) {
        // Проверяем самый старший разряд (последний в массиве)
        return length > 1 && arr[length - 1] == 0;
    }

    static String toNumberString(int[] arr, int length) {
        StringBuilder sb = new StringBuilder();
        boolean leading = true;
        for (int i = length - 1; i >= 0; i--) {
            if (leading && arr[i] == 0 && length > 1) continue;
            leading = false;
            sb.append(arr[i]);
        }
        if (sb.length() == 0) sb.append('0');
        return sb.toString();
    }
}
