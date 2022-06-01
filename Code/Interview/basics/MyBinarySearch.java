public class MyBinarySearch {

    /*
     *  搜索次数 log2(M) + 1 向上取整 即就是搜索树的树深
     *
     * */
    public static void main(String[] args) {
        int[] arr = {1, 5, 8, 11, 19, 22, 31, 35, 40, 45, 48, 49, 50};
        int tar = 47;


        int l = 0, r = arr.length - 1;

        while (l <= r) {
            int mid = (l + r) >>> 1;

            if (arr[mid] == tar) {
                System.out.println(mid);
                return;
            }

            if (arr[mid] < tar) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(-1);

    }

}
