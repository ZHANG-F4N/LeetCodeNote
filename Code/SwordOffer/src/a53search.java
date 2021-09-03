public class a53search {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 1;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        //加上等于号是要判断是否不存在，
        //不存在的话，下标会往后移动
        while (left <= right) {
            mid = left + ((right - left) >> 2);
            if (nums[mid] == target) {
                break;
            }
            if (nums[mid] > target) {
                right = mid - 1;

            }
            if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        if (left > right) {
            return 0;
        }
        int ans = 1;
        left = mid - 1;
        right = mid + 1;
        while (left >= 0 && nums[left] == target) {
            left--;
            ans++;
        }
        while (right < nums.length && nums[right] == target) {
            ans++;
            right++;
        }
        return ans;
    }

}
