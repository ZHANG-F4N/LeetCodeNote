public class a11minArray {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5};
        System.out.println(minArray(nums));
    }

    public static int minArray(int[] numbers) {
        int left = 0;
        int right = numbers.length - 1;


        int mid = 0;
        while (left < right) {
            mid = left + ((right - left) >> 1);
            if (numbers[mid] < numbers[right]) {
                right = mid;
            } else if (numbers[mid] > numbers[right]) {
                left = mid + 1;
            } else {
                right = right - 1;
            }
        }
        return numbers[left];

    }
}
