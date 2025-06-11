package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("INPUT.TXT"));
        PrintWriter writer = new PrintWriter("OUTPUT.TXT");

        String a = scanner.next();
        String b = scanner.next();
        int c = scanner.nextInt();
        Runtime runtime = Runtime.getRuntime();

        long totalMemory = runtime.totalMemory();       // Всего памяти выделено JVM (байты)
        long freeMemory = runtime.freeMemory();         // Свободной памяти в выделенной области (байты)
        long usedMemory = totalMemory - freeMemory;     // Используется памяти (байты)
        long maxMemory = runtime.maxMemory();           // Максимально доступная JVM память (байты)

        System.out.println("Used memory: " + usedMemory / 1024 + " KB");
        System.out.println("Max memory: " + maxMemory / 1024 + " KB");

        Result result = solve(a, b, c);
        System.out.println("________________________");
        System.out.println("Used memory: " + usedMemory / 1024 + " KB");
        System.out.println("Max memory: " + maxMemory / 1024 + " KB");


        writer.println(result);
        writer.close();
    }

    public static Result solve(String a, String b, int c) {
        List<Integer> permutations = (a.length() < b.length()) ? generatePermutations(a) : generatePermutations(b);

        // Сортировка для минимального X
        Collections.sort(permutations);

        for (int i = 0; i < permutations.size(); i++) {
            int j = (a.length() < b.length()) ? i : permutations.size() - 1 - i;
            int x = permutations.get(j);
            int y = c - x;

            if (y < 0) {
                if (a.length() < b.length()) return new Result(false);
                continue;
            }

            String secondStr = (a.length() < b.length()) ? b : a;
            if (canBeFormedFrom(secondStr, Integer.toString(y))) {
                return new Result(true,
                        a.length() < b.length() ? x : y,
                        a.length() < b.length() ? y : x);
            }
        }

        return new Result(false);
    }

    private static List<Integer> generatePermutations(String s) {
        Set<Integer> result = new HashSet<>();
        permute(s.toCharArray(), 0, result);
        return new ArrayList<>(result);
    }

    private static void permute(char[] chars, int index, Set<Integer> result) {
        if (index == chars.length) {
            if (chars[0] != '0') {
                result.add(Integer.parseInt(new String(chars)));
            }
            return;
        }

        for (int i = index; i < chars.length; i++) {
            swap(chars, i, index);
            permute(chars, index + 1, result);
            swap(chars, i, index); // backtrack
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static boolean canBeFormedFrom(String source, String target) {
        int[] count = new int[10];
        for (char ch : source.toCharArray()) {
            count[ch - '0']++;
        }
        for (char ch : target.toCharArray()) {
            if (--count[ch - '0'] < 0) return false;
        }
        return true;
    }

    static class Result {
        boolean answer;
        int x, y;

        public Result(boolean answer) {
            this.answer = answer;
        }

        public Result(boolean answer, int x, int y) {
            this.answer = answer;
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return answer ? String.format("YES%n%d %d", x, y) : "NO";
        }
    }
}