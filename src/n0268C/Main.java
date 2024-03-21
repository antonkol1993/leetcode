package n0268C;


public class Main {
    public static void main(String[] args) {
        int result = new MissingNumber().missingNumber(getInts());

        System.out.println(result);
    }

    private static int[] getInts() {
        int[] nums = new int[2];
        //        9,6,4,2,3,5,7,0,1
        nums[0] = 0;
        nums[1] = 1;
        return nums;
    }

}
