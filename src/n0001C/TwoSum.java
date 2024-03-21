package n0001C;

/**
 * 1.  <a href="https://leetcode.com/problems/two-sum">Two Sum</a>
 */

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {

        int[] sumElements = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && j != i) {
                    sumElements[0] = i;
                    sumElements[1] = j;
                }
            }
        }
        return sumElements;
    }
}