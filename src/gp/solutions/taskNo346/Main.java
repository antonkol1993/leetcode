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
        try (BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"))) {


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

                digitsA = toDigitsReversed(a, true);
                digitsB = toDigitsReversed(b, true);
                digitsC = toDigitsReversed(c);
                n = digitsC.length;

                // Быстрая проверка: сумма цифр (необходимое условие)
                int sumA = digitSum(a);
                int sumB = digitSum(b);
                int sumC = digitSum(c);
                if ((sumA + sumB - sumC) % 9 != 0) {
                    writer.write("NO\n");
                    return;
                }

                // Если длина c меньше максимума длины a или b — не подходит
                // (т.к. сумма может быть не меньше по длине)
                if (digitsC.length < Math.max(digitsA.length, digitsB.length) ||
                        digitsC.length > Math.max(digitsA.length, digitsB.length) + 1) {
                    writer.write("NO\n");
                    continue;
                }

                usedA = new boolean[digitsA.length];
                usedB = new boolean[digitsB.length];

                answerX = null;
                answerY = null;
                found = false;

                // Сортируем digitsA и digitsB по возрастанию для минимизации x
                Arrays.sort(digitsA);
                Arrays.sort(digitsB);

                if (dfs(0, 0, new ArrayList<>(), new ArrayList<>())) {
                    // Проверка на ведущие нули в ответах
                    if (hasLeadingZero(answerX) || hasLeadingZero(answerY)) {
                        writer.write("NO\n");
                    } else {
                        writer.write("YES\n");
                        writer.write(toNumberString(answerX) + " " + toNumberString(answerY) + "\n");
                    }
                } else {
                    writer.write("NO\n");
                }
            }
        }
    }

    // DFS по позициям числа C, переносу, текущим цифрам x и y
    private static boolean dfs(int pos, int carry, List<Integer> currentX, List<Integer> currentY) {
        if (found) return true;

        // Если достигли конца числа C
        if (pos == n) {
            // Перенос должен быть 0
            if (carry == 0 && allUsed(usedA) && allUsed(usedB)) {
                // Сохраняем ответ (копируем списки в массивы)
                answerX = new int[currentX.size()];
                answerY = new int[currentY.size()];
                for (int i = 0; i < currentX.size(); i++) answerX[i] = currentX.get(i);
                for (int i = 0; i < currentY.size(); i++) answerY[i] = currentY.get(i);

                found = true;
                return true;
            }
            return false;
        }

        int target = digitsC[pos];

        // Перебираем цифры для a и b
        for (int i = 0; i < digitsA.length; i++) {
            if (usedA[i]) continue;
            for (int j = 0; j < digitsB.length; j++) {
                if (usedB[j]) continue;

                int sum = digitsA[i] + digitsB[j] + carry;
                if (sum % 10 != target) continue;

                // Отметить цифры как использованные
                usedA[i] = true;
                usedB[j] = true;
                currentX.add(digitsA[i]);
                currentY.add(digitsB[j]);

                if (dfs(pos + 1, sum / 10, currentX, currentY)) return true;

                // Откат
                usedA[i] = false;
                usedB[j] = false;
                currentX.remove(currentX.size() - 1);
                currentY.remove(currentY.size() - 1);
            }
        }

        // Дополнительно: если длина c больше длины a или b, нужно проверить возможность "0" использовать как цифру из a или b,
        // но только если количество цифр c больше длины a/b (т.е. цифры a/b не все использованы)
        // Но в условии задачи все цифры a и b должны быть использованы, значит не нужно добавлять лишние 0.

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
        return toDigitsReversed(num, false);
    }

    private static int[] toDigitsReversed(String num, boolean sort) {
        int[] digits = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            digits[num.length() - 1 - i] = num.charAt(i) - '0';
        }
        if (sort) {
            Arrays.sort(digits);
        }
        return digits;
    }

    private static String toNumberString(int[] digits) {
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        // digits в обратном порядке - печатаем с конца
        for (int i = digits.length - 1; i >= 0; i--) {
            if (leadingZero && digits[i] == 0 && digits.length > 1) continue;
            leadingZero = false;
            sb.append(digits[i]);
        }
        // если все были нулями
        if (sb.length() == 0) sb.append('0');
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