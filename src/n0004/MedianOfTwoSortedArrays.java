package n0004;

import java.util.*;

/**
 * 1.  <a href="https://leetcode.com/problems/median-of-two-sorted-arrays">MedianOfTwoSortedArrays</a>
 */
public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median = 0;
        List<Integer> allarr = new ArrayList<>();

        if (nums1.length > nums2.length) {
            for (int i = 0; i < nums1.length; i++) {


                allarr.add(nums1[i]);
                if (i >= nums2.length) {
                    continue;
                }
                allarr.add(nums2[i]);
            }
        }
        else {
                for (int i = 0; i < nums2.length; i++ ) {


                    allarr.add(nums2[i]);
                    if (i < nums1.length) {
                        allarr.add(nums1[i]);
                    }

                }

            }


        TreeSet<Integer> aasdads = (TreeSet<Integer>) allarr;

        System.out.println(allarr);
        System.out.println();
        System.out.println(aasdads);
        return median;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
        medianOfTwoSortedArrays.findMedianSortedArrays(new int[]{1,2,3,4,}, new int[]{5,9,8,6,7,});

    }
}
