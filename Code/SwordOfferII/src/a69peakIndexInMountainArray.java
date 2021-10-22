public class a69peakIndexInMountainArray {
    public static void main(String[] args) {
        int[] arr = {0,1,0};
        System.out.println(peakIndexInMountainArray2(arr));
    }

    public static int peakIndexInMountainArray(int[] arr) {
        int i = 0;
        while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
            i++;
        }
        return i;
    }

    // log n
    public static int peakIndexInMountainArray2(int[] arr) {
        int l = 0;
        int r = arr.length - 1;

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            System.out.println(mid);
            if (mid == l || mid == r) {
                return mid;
            }
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }

            if (arr[mid] > arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                l = mid;
            }
            if (arr[mid] < arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                r = mid ;
            }
        }
        return l;


    }

}
