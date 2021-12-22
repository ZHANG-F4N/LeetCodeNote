public class a1003search {
    public static void main(String[] args) {
        System.out.println(search(new int[]{1, -2}, -2));
        System.out.println(search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 11));
        System.out.println(search(new int[]{5, 5, 5, 1, 2, 3, 4, 5}, 5));
        System.out.println(search(new int[]{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}, 5));
    }

    public static int search(int[] arr, int target) {
        // 一个数组如果只从一个点旋转,不管转多少次,
        // 都可以分为有序的两部分
        // 所以进行二分,二分的两段,肯定有一段是有序的.

        int l = 0;
        int r = arr.length - 1;
        if (r == -1) return -1;
        int idx = -1;
        while (r != 0 && arr[l] == arr[r]) r--;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (arr[mid] == target) {
                idx = mid;
                break;
            }
            if (l == r && arr[mid] != target) {
                idx = -1;
                break;
            }
            // 左边有序
            if (arr[l] <= arr[mid]) {
                if (arr[mid] > target && target >= arr[l]) {
                    r = mid ;
                } else {
                    l = mid + 1;
                }
                // 右边有序
            } else if (arr[mid] < arr[r]) {
                if (arr[mid] < target && target <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid ;
                }
            }
        }
        // 往前找个最小
        if (idx == -1) return -1;
        while (idx != 0 && arr[idx - 1] == target) {
            idx--;
        }
        return idx;


    }
}
