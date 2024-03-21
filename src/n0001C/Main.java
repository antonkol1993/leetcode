package n0001C;

import java.util.Arrays;

public class Main {

    public int[] array = {3, 3};
    public int target = 6;

    public static void main(String[] args) {
        int[] array = {3, 3};
        int target = 6;

        int[] ints = new TwoSum().twoSum(array, target);

        System.out.println(Arrays.toString(ints));
    }

}
