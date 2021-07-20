public class a11maxArea {
    public static void main(String[] args) {
        int[] height = {1, 1};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int ans = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {

            int temp = (right - left) * Math.min(height[left], height[right]);
            if (temp > ans) {
                ans = temp;
            }
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }
}
