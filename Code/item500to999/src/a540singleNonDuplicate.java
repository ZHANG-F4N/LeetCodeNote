public class a540singleNonDuplicate {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 2, 2, 3, 3, 4, 4, 8, 8}));
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
    }

    public static int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if ((mid & 1) == 0) {
                if (mid != 0 && nums[mid] != nums[mid - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            } else {
                if (nums[mid] != nums[mid - 1]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        r = r != -1 ? r : 0;
        return nums[r];

    }
}
