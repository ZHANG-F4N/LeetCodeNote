import java.util.Arrays;

public class MySort {
    public static void main(String[] args) {
        int[] arr = {7, 5, 6, 17, 2, 6, 0, 152, 8, 63, 896};
//        int[] arr = {0, 2, 5, 6, 6, 7, 8, 17, 63, 152, 896};
//        bubbling(arr);
//        selection(arr);
//        insert(arr);
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }

    /*
     * 冒泡排序: 稳定
     * 可优化的点:
     * 1. 每轮判断是否有序,如果有序了就不需要再进行冒泡
     * 2. 每轮冒泡记录最后一次交换的位置,该位置之后的肯定已经有序,下轮只需要排序到记录的位置
     * */
    public static void bubbling(int[] arr) {
        int n = arr.length - 1;
        while (true) {
            // 最后一次交换的位置
            int last = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] > arr[j + 1]) {
                    // 如果不发生交换,就说明不需要再进行后面的冒泡
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    last = j;
                }
            }
            n = last;
            if (last == 0) break;
        }
    }

    /*
     * 选择排序
     * 不稳定、
     * */
    public static void selection(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int mIdx = 0;
            for (int j = 0; j < n - i; j++) {
                if (arr[mIdx] < arr[j]) {
                    mIdx = j;
                }
            }
            int temp = arr[mIdx];
            arr[mIdx] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }

    /*
     * 插入排序 稳定
     * 性能略高于选择
     * 将数组分为已经由于和无序两部分,
     * 每次从无序选择一个元素,然后在有序部分找到合适插入位置
     * */
    public static void insert(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j = i - 1;

            while (j >= 0) {
                if (arr[j] >= temp) {
                    arr[j + 1] = arr[j];
                } else break;
                j--;
            }
            arr[j + 1] = temp;
        }
    }
    /*
     * 希尔排序
     * 利用 选择和插入排序在数组有序的情况下时间复杂度较低的特性
     * 将数组分为不同的组(比如按下标的奇偶来分),
     *
     * 代码略
     * */

    /*
     * 快速排序
     *
     * */

    public static void quickSort(int[] arr, int l, int h) {
        if (l >= h) return;
        int p = partition2(arr, l, h);
        quickSort(arr, l, p - 1);
        quickSort(arr, p + 1, h);
    }

    /*lomuto实现 单边循环快排*/
    public static int partition(int[] arr, int l, int h) {
        int pv = arr[h];
        int i = l;
        // i j 都是从左边出发,往最后面进行搜索,i代表小于pv的数的边界,j寻找小于pv的数
        // j 一搜到就进行交换
        for (int j = l; j < h; j++) {
            if (arr[j] < pv) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }
        // pv和最后的i交换
        int temp = arr[i];
        arr[i] = arr[h];
        arr[h] = temp;
        System.out.println(Arrays.toString(arr));
        return i;
    }

    /*双边循环快排*/
    public static int partition2(int[] arr, int l, int h) {
        int pv = arr[l];
        int i = l, j = h;
        while (i < j) {
            // 必须先从后往前找
            while (i < j && arr[j] > pv) j--;// 不能将j--写在while;里,会出现短路运算
            // 这里等于号要特别注意  刚开始 i指向的就是pv位置
            while (i < j && arr[i] <= pv) i++;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        int temp = arr[l];
        arr[l] = arr[i];
        arr[i] = temp;
        System.out.println(Arrays.toString(arr));
        return i;
    }


}
