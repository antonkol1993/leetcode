package n0015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<Integer> variants = new ArrayList<>();
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ads = new ArrayList<>();
        List<Integer> listList = new ArrayList<>();

        for (int num : nums) {

            listList.add(num);
        }
        System.out.println(listList);

        listList.sort(Integer::compareTo);

        for (int i = 0 ; i < nums.length; i++) {
            nums[i] = listList.get(i);
        }

        System.out.println();

        System.out.println(listList);

        for (int i = 0 ; i < nums.length-2; i++) {
            for (int j = i + 1 ; j < nums.length; j++) {
                for (int k = i + 2 ; k < nums.length; k++) {
                    if (i + 2 == nums.length - 1 && nums[i] + nums [j] + nums[k] == 0) {
                        listList = List.of(nums[i], nums[i + 1], nums[i + 2]);
                        ads.add(listList);
                        return ads;
                    }

                    if (nums[i] + nums [j] + nums[k] == 0) {

//                        if ()
                        listList = List.of(nums[i], nums [i+1], nums[i+2]);
                        ads.add(listList);

                    }
                }
            }
        }

//        System.out.print(nums[i] + " ");



        return ads;
    }

    public static void main(String[] args) {

        ThreeSum threeSum = new ThreeSum();

        int[] nums = {1,3,5,8,2,0,231,-3,-2,-5,-6,-143,-55,-2};
//        System.out.println(Arrays.toString(nums));
//        System.out.println();
//
        System.out.println(threeSum.threeSum(nums));



    }

}
