import java.util.ArrayList;
import java.util.Arrays;

public class merge_88 {
    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums1 = {1};
        int m = 1;
//        int[] nums2 = {2, 5, 6};
        int[] nums2 = {};
        int n = 0;
        merge(nums1, m, nums2, n);
        ArrayList<Integer> nums=new ArrayList<Integer>();
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = n - 1, i = m - 1, k = m + n - 1;
        for (; i >= 0 && j >= 0 && k >= 0; k--) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
        }
        if (i == -1) {
            for (;k>=0;k--){
                nums1[k] =nums2[j];
                j--;
            }
            System.out.println(Arrays.toString(nums1));
            return;
        }
        if (j == -1){
            for (;k>=0;k--){
                nums1[k] =nums1[i];
                i--;
            }
            System.out.println(Arrays.toString(nums1));
            return;
        }
    }
}
