package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class MainOptimize {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));

        String[] parts = reader.readLine().trim().split(" ");
        String a = parts[0], b = parts[1], c = parts[2];

        boolean found; //false

        // Пробуем переставить цифры в `a`, чтобы найти возможный x
        char[] aChars = a.toCharArray();
        Arrays.sort(aChars); // Сортировка
        boolean[] usedA = new boolean[a.length()];

        found = generateAndCheck(aChars, usedA, "", b, c, true, writer);

        // Если не`A`, то идем в `B`
        if (!found) {
            char[] bChars = b.toCharArray();
            Arrays.sort(bChars);
            boolean[] usedB = new boolean[b.length()];

            found = generateAndCheck(bChars, usedB, "", a, c, false, writer);
        }

        // Если ни A, ни B, то NO
        if (!found) {
            writer.write("NO\n");
        }
        reader.close();
        writer.close();

    }

    static boolean generateAndCheck(char[] chars, boolean[] used, String prefix,
                                    String other, String c, boolean aFirst, BufferedWriter writer) throws IOException {
        if (prefix.length() == chars.length) {
            String xStr = prefix;
            int x = Integer.parseInt(xStr);

            int target;
            try {
                target = Integer.parseInt(c);
            } catch (NumberFormatException e) {
                return false;
            }

            int y = target - x;
            if (y < 0) return false;
            String yStr = String.valueOf(y);

            String fromStr = new String(chars);

            boolean can = aFirst
                    ? isCanBuild(xStr, fromStr) && isCanBuild(yStr, other)
                    : isCanBuild(yStr, fromStr) && isCanBuild(xStr, other);

            if (can) {
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

        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            if (generateAndCheck(chars, used, prefix + chars[i], other, c, aFirst, writer)) {
                return true;
            }
            used[i] = false;
        }

        return false;
    }

    static boolean isCanBuild(String value, String source) {
        int[] countSource = new int[10];
        int[] countValue = new int[10];

        for (int i = 0; i < source.length(); i++) {
            char ch = source.charAt(i);
            int digit = Character.getNumericValue(ch);
            countSource[digit]++;
        }

        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            int digit = Character.getNumericValue(ch);
            countValue[digit]++;
        }
        // Проверка: хватает ли каждой цифры в source
        for (int i = 0; i < 10; i++) {
            if (countSource[i] < countValue[i]) return false;
        }
        // Проверка: в source не остались неиспользованные НЕ-нули
        for (int i = 1; i < 10; i++) {
            if (countSource[i] > countValue[i]) return false;
        }

        return true;
    }


}
