package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> digitsA;
    static List<Integer> digitsB;
    static List<Integer> digitsC;
    static boolean found = false;
    static int[] resultA;
    static int[] resultB;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
        FileWriter writer = new FileWriter("OUTPUT.TXT");

        String[] parts = reader.readLine().trim().split(" ");
        String num1 = parts[0];
        String num2 = parts[1];
        String num3 = parts[2];


        digitsA = toDigits(Integer.parseInt(num1));
        digitsB = toDigits(Integer.parseInt(num2));
        digitsC = toDigits(Integer.parseInt(num3));

        boolean[] usedA = new boolean[digitsA.size()];
        boolean[] usedB = new boolean[digitsB.size()];
        resultA = new int[digitsC.size()];
        resultB = new int[digitsC.size()];

        dfs(digitsC.size() - 1, 0, usedA, usedB, new ArrayList<>(), new ArrayList<>());

        if (found) {
            writer.write("YES\n");
            writer.write(join(resultA) + " " + join(resultB) + "\n");
        } else {
            writer.write("NO\n");
        }

        writer.close();
    }

    static List<Integer> toDigits(int n) {
        List<Integer> res = new ArrayList<>();
        if (n == 0) res.add(0);
        while (n > 0) {
            res.add(n % 10);
            n /= 10;
        }
        return res;
    }

    static void dfs(int pos, int carry, boolean[] usedA, boolean[] usedB,
                    List<Integer> pathA, List<Integer> pathB) {
        if (found) return;
        if (pos < 0) {
            if (carry == 0 && pathA.size() == digitsC.size()) {
                Collections.reverse(pathA);
                Collections.reverse(pathB);
                for (int i = 0; i < digitsC.size(); i++) {
                    resultA[i] = pathA.get(i);
                    resultB[i] = pathB.get(i);
                }
                if (resultA[0] != 0 && resultB[0] != 0) {
                    found = true;
                }
            }
            return;
        }

        int target = digitsC.get(pos);
        for (int i = 0; i < digitsA.size(); i++) {
            if (usedA[i]) continue;
            int da = digitsA.get(i);

            for (int j = 0; j < digitsB.size(); j++) {
                if (usedB[j]) continue;
                int db = digitsB.get(j);

                int sum = da + db + carry;
                if (sum % 10 == target) {
                    usedA[i] = true;
                    usedB[j] = true;
                    pathA.add(da);
                    pathB.add(db);

                    dfs(pos - 1, sum / 10, usedA, usedB, pathA, pathB);

                    pathA.remove(pathA.size() - 1);
                    pathB.remove(pathB.size() - 1);
                    usedA[i] = false;
                    usedB[j] = false;
                }
            }
        }
    }

    static String join(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int d : arr) sb.append(d);
        return sb.toString();
    }
}
