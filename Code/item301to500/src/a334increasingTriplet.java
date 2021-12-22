public class a334increasingTriplet {
    public static void main(String[] args) {
        int [] nums={1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        System.out.println(increasingTriplet(nums));
    }

    public static boolean increasingTriplet(int[] nums) {
        int small = Integer.MAX_VALUE;
        int mid = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i]<=small){
                small = nums[i];
            }else if (nums[i]<=mid){
                mid = nums[i];
            }else {
                return true;
            }
        }
        return false;
    }
}
