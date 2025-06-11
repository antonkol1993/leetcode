package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class Main {
    static String a, b, cStr;
    static int cInt;
    static char[] aChars, bChars;
    static boolean found = false;
    static String resX, resY;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("INPUT.TXT"));
        PrintWriter writer = new PrintWriter("OUTPUT.TXT");

        a = scanner.next();
        b = scanner.next();
        cStr = scanner.next();

        cInt = Integer.parseInt(cStr);
        aChars = a.toCharArray();
        bChars = b.toCharArray();

        // Определим, с какой строки будем генерировать перестановки
        // Чтобы меньше перебрать, возьмём строку меньшей длины
        if (a.length() <= b.length()) {
            permuteAndCheck(aChars, b, cStr);
        } else {
            permuteAndCheck(bChars, a, cStr);
        }

        if (found) {
            writer.println("YES");
            writer.println(resX + " " + resY);
        } else {
            writer.println("NO");
        }
        writer.close();
    }

    // Перебираем перестановки chars, пытаемся найти yStr из other, чтобы xStr + yStr = cStr
    static void permuteAndCheck(char[] chars, String other, String cStr) {
        Arrays.sort(chars);
        boolean[] used = new boolean[chars.length];
        StringBuilder current = new StringBuilder();
        backtrack(chars, used, current, other, cStr);
    }

    static void backtrack(char[] chars, boolean[] used, StringBuilder current, String other, String cStr) {
        if (found) return;  // если нашли — останавливаемся

        if (current.length() == chars.length) {
            if (current.charAt(0) == '0') return;  // ведущий ноль запрещён

            String xStr = current.toString();

            // Вычитаем cStr - xStr (строками) — получаем yStr
            String yStr = subtractStrings(cStr, xStr);
            if (yStr == null) return; // xStr > cStr, invalid

            if (yStr.length() > 0 && yStr.charAt(0) == '0') return; // ведущий ноль в y запрещён

            // Проверяем, что yStr можно составить из other
            if (!canBeFormedFrom(other, yStr)) return;

            // Проверяем, что xStr + yStr = cStr по цифрам (на всякий случай)
            if (!sumEquals(xStr, yStr, cStr)) return;

            // Всё хорошо, сохраняем и помечаем найдено
            resX = xStr;
            resY = yStr;
            found = true;
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (used[i]) continue;
            // Чтобы не делать одинаковые перестановки, пропускаем дубликаты
            if (i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) continue;

            used[i] = true;
            current.append(chars[i]);
            backtrack(chars, used, current, other, cStr);
            current.deleteCharAt(current.length() - 1);
            used[i] = false;
        }
    }

    // Проверка, что target можно составить из source (по количеству цифр)
    static boolean canBeFormedFrom(String source, String target) {
        int[] count = new int[10];
        for (char ch : source.toCharArray()) {
            count[ch - '0']++;
        }
        for (char ch : target.toCharArray()) {
            if (--count[ch - '0'] < 0) return false;
        }
        return true;
    }

    // Вычитание cStr - xStr в десятичной системе (строками)
    // Если xStr > cStr возвращает null
    static String subtractStrings(String cStr, String xStr) {
        // Приводим к одинаковой длине с ведущими нулями
        int n = Math.max(cStr.length(), xStr.length());
        int[] cArr = new int[n];
        int[] xArr = new int[n];

        for (int i = 0; i < cStr.length(); i++) {
            cArr[n - cStr.length() + i] = cStr.charAt(i) - '0';
        }
        for (int i = 0; i < xStr.length(); i++) {
            xArr[n - xStr.length() + i] = xStr.charAt(i) - '0';
        }

        int[] res = new int[n];
        int borrow = 0;

        for (int i = n - 1; i >= 0; i--) {
            int diff = cArr[i] - xArr[i] - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }
            res[i] = diff;
        }

        if (borrow != 0) return null; // xStr > cStr

        // Преобразуем результат обратно в строку без ведущих нулей
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (int v : res) {
            if (v != 0) leadingZero = false;
            if (!leadingZero) sb.append((char)(v + '0'));
        }
        if (sb.length() == 0) sb.append('0');
        return sb.toString();
    }

    // Проверка, что xStr + yStr == cStr (строками)
    static boolean sumEquals(String xStr, String yStr, String cStr) {
        int i = xStr.length() - 1;
        int j = yStr.length() - 1;
        int k = cStr.length() - 1;

        int carry = 0;
        while (i >= 0 || j >= 0 || k >= 0) {
            int dx = (i >= 0) ? xStr.charAt(i) - '0' : 0;
            int dy = (j >= 0) ? yStr.charAt(j) - '0' : 0;
            int dc = (k >= 0) ? cStr.charAt(k) - '0' : 0;

            int sum = dx + dy + carry;
            if (sum % 10 != dc) return false;

            carry = sum / 10;
            i--; j--; k--;
        }
        return carry == 0;
    }
}
