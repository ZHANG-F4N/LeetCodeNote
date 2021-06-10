public class a55canJump {
    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        System.out.println(canJump(nums));
    }
    //从前往后,每次更新最大可以到达的距离
    public static boolean canJump(int[] nums) {
        int maxStep = 0;
        int len = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (maxStep >= len) {
                return true;
            }
            if (maxStep >= i) {
                if (maxStep < i + nums[i]) {
                    maxStep = nums[i] + i;
                }
            } else {
                return false;
            }
        }
        return false;
    }
    //从后往前,判断每一个点是否可达
//    public static boolean canJump(int[] nums) {
//        int min = nums.length - 1;
//        for (int i = nums.length - 2; i >= 0; i--) {
//            if (i + nums[i] >= min) min = i;
//        }
//        return min == 0;
//    }

}
