public class a316singleNumber {
    public static void main(String[] args) {
        int[] nums={4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }
    public static int singleNumber(int[] nums) {
//          4 	1	0	0
//          1	0	0	1
//         异或------------
//              1	0	1
//          2	0	1	0
//          异或-----------
//              1	1	1
//          1	0	0	1
//          异或-----------
//              1	1	0
//          2	0	1	0
//           异或----------
//              1	0	0

        int ans = 0;
        //偶数次相同的数异或为 0000
        for (int i = 0; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }
}
