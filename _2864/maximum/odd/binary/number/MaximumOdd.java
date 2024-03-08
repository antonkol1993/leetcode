package leetcode.special.number._2864.maximum.odd.binary.number;

//2864. Maximum Odd Binary Number
//
//https://leetcode.com/problems/maximum-odd-binary-number/?envType=daily-question&envId=2024-03-06

public class MaximumOdd {

    public String s = "000011101110";


    public String maximumOddBinaryNumber(String s) {

        String[] split = s.split("");


        for (int i = 0; i < split.length; i++) {
            if (split[split.length - 1].equals("1")) {
                break;
            } else if (split[split.length - 1].equals("0")) {
                for (int j = split.length - 2; j >= 0; j--) {
                    if (split[j].equals("1")) {
                        String temp = split[j];
                        split[j] = split[split.length - 1];
                        split[split.length - 1] = temp;
                        break;
                    }
                }
            }
        }

        int i = 0;
        for (int j = split.length - 2; j >= 0; j--) {

            if (split[j].equals("1")) {
                for (; i < split.length; i++) {
                    if (j <= i) {
                        break;
                    }

                    if (split[i].equals("0")) {
                        String temp = split[j];
                        split[j] = split[i];
                        split[i] = temp;
                        break;
                    }

                }
            }
        }


        String newS = "";
        for (i = 0; i < split.length; i++) {
            newS = newS.concat(split[i]);
        }


        return newS;
    }


}

