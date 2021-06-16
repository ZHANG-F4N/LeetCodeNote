public class a169majorityElement {
    public static void main(String[] args) {
        int nums[] = {2, 2, 1, 1, 1, 2, 2, 3};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (candidate == nums[i]) {
                count++;
            }else {
                if (count == 0) {
                    candidate = nums[i];
                    count = 1;
                    continue;
                }
                count--;
            }
        }

        return candidate;
    }
}
