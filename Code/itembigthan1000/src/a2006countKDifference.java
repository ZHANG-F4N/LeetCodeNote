public class a2006countKDifference {
    public static void main(String[] args) {
        System.out.println(countKDifference(new int[]{3, 2, 1, 5, 4}, 2));
        System.out.println(countKDifference(new int[]{1, 2, 2, 1}, 1));
    }

    public static int countKDifference(int[] nums, int k) {
        int n = nums.length;
        int[] arr = new int[101];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            arr[nums[i]]++;

            
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0 || i + k >= arr.length || arr[i + k] == 0) continue;
            ans += arr[i] * arr[i + k];
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                if (Math.abs(nums[i] - nums[j]) == k) ans++;
//
//            }
//        }

        return ans;
    }
}
