package leetcode.special.number._268.missing.number;


//268. Missing Number
//
//        https://leetcode.com/problems/missing-number/description/?envType=daily-question&envId=2024-03-06

public class MissingNumber {

    public int[] nums = new int[2];

    public MissingNumber() {
//        9,6,4,2,3,5,7,0,1
        nums[0] = 0;
        nums[1] = 1;


    }


    public int missingNumber(int[] nums) {
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
