package leetcode.special.number._1.two.sum;

import java.util.Arrays;

//1. Two Sum
//        https://leetcode.com/problems/two-sum/description/
public class TwoSum {

    public int []array = {3,3};
    public int target = 6;
    public int[] twoSum(int[] nums, int target) {

        int[] sumElements = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && j != i) {
                    sumElements[0] = i;
                    sumElements[1] = j;
                }
            }
        }
        return sumElements;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(twoSum.array, twoSum.target)));
    }

}
