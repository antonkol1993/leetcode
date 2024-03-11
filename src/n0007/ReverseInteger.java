package n0007;

import static java.lang.Math.*;

/**
 * 7. <a href="https://leetcode.com/problems/reverse-integer">Reverse Integer</a>
 */

public class ReverseInteger {

    public int reverse(int x) {
        int n = 2;
        int max = (int) Math.pow(n, 31) - 1;
        int min = (int) Math.pow(-n, 31);

        if (x > max || x < min || x == 0) {
            return 0;
        }

        int countNumbers = 0;
        int delimiter = 1;
        for (int i = Math.abs(x); i > 0; i /= 10) {
            countNumbers++;
            delimiter *= 10;
        }
        countNumbers--;
        delimiter /= 10;

        int reverseNumber =0;
        int compositeNumber = 0;
        int forTens = 1;
        int xModule = Math.abs(x);

        for (int j = 0; j < countNumbers; j++, delimiter /= 10,forTens *= 10) {

            compositeNumber += xModule / delimiter;
            xModule %= delimiter;
            reverseNumber += compositeNumber * forTens;
            if (reverseNumber > max || reverseNumber < min) {
                return 0;
            }

            compositeNumber = 0;
        }

        if (x < 0 ) {
            return -reverseNumber;
        }


        return reverseNumber ;
    }

    public static void main(String[] args) {
        int n = 2;
        int i = 31;
        ReverseInteger reverseInteger = new ReverseInteger();

        System.out.println(reverseInteger.reverse(1534236466));

        int adasads = Math.abs(-354);


    }

}
