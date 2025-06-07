package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class Main {
    static int n;              // длина числа C
    static int[] digitsA;      // цифры A (от младшего к старшему)
    static int[] digitsB;      // цифры B (от младшего к старшему)
    static int[] digitsC;      // цифры C (от младшего к старшему)
    static boolean[] usedA;
    static boolean[] usedB;
    static int[] answerX;
    static int[] answerY;
    static boolean found;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(" ");
            if (parts.length < 3) {
                writer.write("NO\n");
                continue;
            }

            String a = parts[0];
            String b = parts[1];
            String c = parts[2];

            // Быстрая проверка кратности 9 (необходимое условие)
            int sumA = digitSum(a);
            int sumB = digitSum(b);
            int sumC = digitSum(c);
            if ((sumA + sumB - sumC) % 9 != 0) {
                writer.write("NO\n");
                continue;
            }

            digitsA = toDigitsReversed(a);
            digitsB = toDigitsReversed(b);
            digitsC = toDigitsReversed(c);
            n = digitsC.length;

            usedA = new boolean[digitsA.length];
            usedB = new boolean[digitsB.length];

            answerX = null;
            answerY = null;
            found = false;

            // Запускаем DFS с позиции 0 и переносом 0
            if (dfs(0, 0, new ArrayList<>(), new ArrayList<>())) {
                writer.write("YES\n");
                writer.write(toNumberString(answerX) + " " + toNumberString(answerY) + "\n");
            } else {
                writer.write("NO\n");
            }
        }
        reader.close();
        writer.close();
    }

    private static boolean dfs(int pos, int carry, List<Integer> currentX, List<Integer> currentY) {
        if (found) return true; // прерывание после нахождения решения

        if (pos == n) {
            if (carry == 0 && allUsed(usedA) && allUsed(usedB)) {
                answerX = new int[currentX.size()];
                answerY = new int[currentY.size()];
                for (int i = 0; i < currentX.size(); i++) answerX[i] = currentX.get(i);
                for (int i = 0; i < currentY.size(); i++) answerY[i] = currentY.get(i);
                found = true;
                return true;
            }
            return false;
        }

        int targetDigit = digitsC[pos];

        // Перебираем i,j, i и j могут быть "пустыми" позициями (число короче)
        // Чтобы учесть "пропуск" цифры, добавляем фиктивный индекс digitsA.length и digitsB.length,
        // означающий "цифры нет (0)"
        for (int i = 0; i <= digitsA.length; i++) {
            if (i < digitsA.length && usedA[i]) continue;
            int digitA = (i < digitsA.length) ? digitsA[i] : 0;

            for (int j = 0; j <= digitsB.length; j++) {
                if (j < digitsB.length && usedB[j]) continue;
                int digitB = (j < digitsB.length) ? digitsB[j] : 0;

                int sum = digitA + digitB + carry;
                if (sum % 10 == targetDigit) {
                    // Используем цифры, если не фиктивные
                    if (i < digitsA.length) usedA[i] = true;
                    if (j < digitsB.length) usedB[j] = true;

                    currentX.add(digitA);
                    currentY.add(digitB);

                    if (dfs(pos + 1, sum / 10, currentX, currentY)) return true;

                    currentX.remove(currentX.size() - 1);
                    currentY.remove(currentY.size() - 1);

                    if (i < digitsA.length) usedA[i] = false;
                    if (j < digitsB.length) usedB[j] = false;
                }
            }
        }
        return false;
    }

    private static boolean allUsed(boolean[] used) {
        for (boolean b : used) {
            if (!b) return false;
        }
        return true;
    }

    private static boolean hasLeadingZero(int[] digits) {
        // digits в обратном порядке, ведущий ноль - последний элемент массива, если длина > 1
        return digits.length > 1 && digits[digits.length - 1] == 0;
    }

    private static int[] toDigitsReversed(String num) {
        int len = num.length();
        int[] digits = new int[len];
        for (int i = 0; i < len; i++) {
            digits[i] = num.charAt(len - 1 - i) - '0';
        }
        return digits;
    }

    private static String toNumberString(int[] digits) {
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (int i = digits.length - 1; i >= 0; i--) {
            if (leadingZero && digits[i] == 0 && digits.length > 1) continue;
            leadingZero = false;
            sb.append(digits[i]);
        }
        return sb.toString();
    }

    private static int digitSum(String num) {
        int sum = 0;
        for (char ch : num.toCharArray()) {
            sum += Character.getNumericValue(ch);
        }
        return sum;
    }
}
