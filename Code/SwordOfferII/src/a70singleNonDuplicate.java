public class a70singleNonDuplicate {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 2, 3}));
        System.out.println(singleNonDuplicate(new int[]{1, 2, 2}));
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{3, 3, 7, 7, 10, 11, 11}));
    }

    public static int singleNonDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        //  0   1   2   3   4   5   6   7   8
        //  1   1   2   3   3   4   4   8   8
        //  l               m               r
        //  l   m       r
        //      l   m   r
        //  0   1   2   3   4   5   6
        //  3   3   7   7   10  11  11
        //  l           m           r
        //              l   m       r
        while (l < r) {
            int mid = (l + r) >> 1;
            if ((mid == 0 && nums[mid] != nums[mid + 1]) || (mid == nums.length - 1 && nums[mid] != nums[mid - 1])) {
                return nums[mid];
            }
            if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1]) {
                return nums[mid];
            }
            if (mid - 1 >= 0 && nums[mid] == nums[mid - 1]) {
                if ((mid - l) % 2 == 0) {
                    r = mid - 2;
                } else {
                    l = mid + 1;
                }
            }
            if (mid + 1 < nums.length && nums[mid] == nums[mid + 1]) {
                if ((r - mid) % 2 == 0) {
                    l = mid + 2;
                } else {
                    r = mid - 1;
                }
            }
        }
        return nums[l];
    }
}
