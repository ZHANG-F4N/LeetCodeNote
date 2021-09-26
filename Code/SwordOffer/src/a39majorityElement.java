public class a39majorityElement {
    public static void main(String[] args) {
        int[] num = {1, 2, 3, 2, 2, 2, 5, 4, 2};


        System.out.println(majorityElement(num));
    }

    public static int majorityElement(int[] nums) {

        int temp = nums[0];
        int count = 0;


        for (int i = 0; i < nums.length; i++) {
            if (count <= 0) {
                temp = nums[i];
                count = 0;
            }
            if (temp == nums[i]) {
                count++;
            } else {
                count--;
            }
        }
        return temp;
    }
}
