package gp.solutions.taskNo346;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main_2TEST_YOURS {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("INPUT.TXT"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("OUTPUT.TXT"))) {


            String line;
            while ((line = reader.readLine()) != null) {
                boolean can = true;

                String[] parts = line.trim().split(" ");
                if (parts.length < 3) {
                    can = false;
                }

                String a = parts[0];
                String b = parts[1];
                String c = parts[2];

                List<Integer> aArr = toDigits(a, true);
                int aSize = aArr.size();
                List<Integer> bArr = toDigits(b, true);
                int bSize = bArr.size();
                List<Integer> cArr = toDigits(c);

                int sumA = digitsSum(aArr);
                int sumB = digitsSum(bArr);
                int sumC = digitsSum(cArr);
                if ((sumA + sumB - sumC) % 9 != 0) {
                    can = false;
                }

                int ups = (sumA + sumB - sumC) / 9;

                if (cArr.size() > Math.max(aArr.size(), bArr.size()) + 1) {
                    can = false;
                }



                if (cArr.get(0) > 1 && cArr.size() == Math.max(aArr.size(), bArr.size()) + 1) {
                    can = false;
                }

                while (aArr.size() < cArr.size()) {
                    aArr.add(0, 0);
                }

                while (bArr.size() < cArr.size()) {
                    bArr.add(0, 0);
                }

                while (aArr.size() > cArr.size()) {
                    if (aArr.contains(0)) {
                        aArr.remove(0);
                    }
                    else {
                        can = false;
                        break;
                    }
                }

                while (bArr.size() > cArr.size()) {
                    if (bArr.contains(0)) {
                        bArr.remove(0);
                    } else {
                        can = false;
                        break;
                    }
                }

                List<Integer> aResult = new ArrayList<>();
                List<Integer> bResult = new ArrayList<>();

                if (can) {
                    boolean found = find(aArr, bArr, cArr, ups, false, aResult, bResult);
                    if (found) {
                        String aString = toNumberString(aResult);
                        String bString = toNumberString(bResult);
                        if (aString.length() <= aSize && bString.length() <= bSize) {
                            writer.write("YES\n");
                            writer.write(aString + " " + bString + "\n");
                        } else {
                            writer.write("NO\n");
                        }
                    } else {
                        writer.write("NO\n");
                    }
                } else {
                    writer.write("NO\n");
                }
            }
        }
    }

    private static List<Integer> toDigits(String num) {
        return toDigits(num, false);
    }

    private static List<Integer> toDigits(String num, boolean sort) {
        Stream<Integer> ints = Arrays.stream(num.split(""))
                .mapToInt(Integer::valueOf)
                .boxed();

        if (sort) {
            ints = ints.sorted();
        }

        return ints.collect(Collectors.toList());
    }


    private static boolean find(List<Integer> a, List<Integer> b, List<Integer> c, int upsLeft, boolean forcedUp, List<Integer> aResult, List<Integer> bResult) {
        if (upsLeft >= c.size()) {
            return false;
        }

        if (c.size() > 0) {
            Integer cNum = c.get(0);
            if (forcedUp) {
                cNum += 10;
            }

            boolean forced = false;
            int ups = upsLeft;


            if (upsLeft > 0 && cNum > 0 && c.size() > 1) {
                cNum -= 1;
                forced = true;

                ups -= 1;


                for (int i = 0; i < a.size() && a.get(i) <= cNum; i++) {
                    Integer aNum = a.get(i);
                    for (int j = 0; j < b.size() && b.get(j) <= cNum - aNum; j++) {
                        Integer bNum = b.get(j);
                        if (aNum + bNum == cNum) {
                            if (c.size() == 1) {
                                aResult.add(aNum);
                                bResult.add(bNum);
                                return true;
                            } else {
                                List<Integer> newA = new ArrayList<>(a);
                                newA.remove(aNum);
                                List<Integer> newB = new ArrayList<>(b);
                                newB.remove(bNum);
                                List<Integer> newC = new ArrayList<>(c);
                                newC.remove(0);

                                List<Integer> aR = new ArrayList<>(aResult);
                                aR.add(aNum);
                                List<Integer> bR = new ArrayList<>(bResult);
                                bR.add(bNum);
                                boolean found = find(newA, newB, newC, ups, forced, aR, bR);
                                if (found) {
                                    aResult.clear();
                                    aResult.addAll(aR);
                                    bResult.clear();
                                    bResult.addAll(bR);
                                    return found;
                                }

                            }
                        }
                    }
                }
                cNum += 1;
                forced = false;

                ups += 1;

            }

            for (int i = 0; i < a.size() && a.get(i) <= cNum; i++) {
                Integer aNum = a.get(i);
                for (int j = 0; j < b.size() && b.get(j) <= cNum - aNum; j++) {
                    Integer bNum = b.get(j);
                    if (aNum + bNum == cNum) {
                        if (c.size() == 1) {
                            aResult.add(aNum);
                            bResult.add(bNum);
                            return true;
                        } else {
                            List<Integer> newA = new ArrayList<>(a);
                            newA.remove(aNum);
                            List<Integer> newB = new ArrayList<>(b);
                            newB.remove(bNum);
                            List<Integer> newC = new ArrayList<>(c);
                            newC.remove(0);

                            List<Integer> aR = new ArrayList<>(aResult);
                            aR.add(aNum);
                            List<Integer> bR = new ArrayList<>(bResult);
                            bR.add(bNum);
                            boolean found = find(newA, newB, newC, ups, forced, aR, bR);
                            if (found) {
                                aResult.clear();
                                aResult.addAll(aR);
                                bResult.clear();
                                bResult.addAll(bR);
                                return found;
                            }

                        }
                    }
                }
            }

        }
        return false;
    }

    private static String toNumberString(List<Integer> nums) {
        int result = 0;
        for (Integer num : nums) {
            result = result * 10 + num;
        }
        return String.valueOf(result);
    }

    private static int digitsSum(List<Integer> nums) {
        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }
        return sum;
    }
}