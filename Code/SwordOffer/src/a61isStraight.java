public class a61isStraight {
    public static void main(String[] args) {
        int[] num = {1,0,3,4,5};
        System.out.println(isStraight(num));
    }

    public static boolean isStraight(int[] nums) {
        int[] temp = new int[14];

        for (int i = 0; i < nums.length; i++) {
            temp[nums[i]]++;
            if (nums[i] != 0 && temp[nums[i]] > 1) {
                return false;
            }
        }

        boolean ans = false;
        int empty = 0;
        int count = 0;

        for (int i = 1; i < temp.length; i++) {
            if (count >= 5 - temp[0]) {
                break;
            }
            if (temp[i] != 0) {
                count += temp[i];
            }
            if (count != 0 && count != 5 - temp[0] && temp[i] == 0) {
                empty++;
            }
        }
        if (temp[0] >= empty) {
            return true;
        }
        return ans;
    }
}
