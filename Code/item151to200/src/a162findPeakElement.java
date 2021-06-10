public class a162findPeakElement {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(findPeakElement(nums));
    }
//     线性扫描 O(n)
//    public static int findPeakElement(int[] nums) {
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] > nums[i + 1]) {
//                return i;
//            }
//        }
//        return nums.length - 1;
//    }

    //    二分查找
    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (nums[mid] < nums[mid + 1]) {
                //注意这一步
                //mid和下一个元素比较,左边右移一个
                left = mid + 1;
                continue;
            }
            //注意这一步
            right = mid;
        }
        return left;
    }
}
