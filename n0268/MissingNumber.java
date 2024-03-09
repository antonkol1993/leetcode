package n0268;


//268. Missing Number
//
//        https://leetcode.com/problems/missing-number/description/?envType=daily-question&envId=2024-03-06

public class MissingNumber {

    public int missingNumber(int[] nums) {
//        int result = nums.length * (nums.length + 1) / 2;
//        for (int num : nums) {
//            result -= num;
//        }
//        return result;

        int missingNumber;

        int summ = 0;
        int summarr = 0;
        int numbers = 0;
        for (int num : nums) {
            numbers++;
            summ += numbers;

            summarr += num;

        }

        missingNumber = summ - summarr;

        return missingNumber;
    }


}
