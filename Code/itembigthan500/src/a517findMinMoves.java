import java.util.Arrays;

public class a517findMinMoves {
    public static void main(String[] args) {

        int[] nums = {0, 0, 11,5};
        System.out.println(findMinMoves(nums));


    }
    /**
     * 将每个洗衣机中的衣服数量相等可以转化为：差值数组中每一项都变为0，差值数组为diff[i]=machines[i]-target
     * 在把差值数组每一项变为0的操作中，只需要求出其中所需移动衣服最多的洗衣机，就是最少的移动次数。
     * 当diff[i] < 0 时，可以从左右两边的洗衣机获取衣服，取左右中的最大值；
     * 当diff[i] > 0 时，需要把洗衣机的衣服向左右转移，此时移动次数等于diff[i]
     *
     * 我们从左向右依次把差值数组中的每一项变为0：考虑到与该洗衣机非相邻的洗衣机可能需要经过该洗衣
     * 机来转移衣服，因此
     *   用balance记录当前洗衣机上所要经过的流量。
     *   balance += diff[i];
     *   balance < 0 说明需要从右边获取衣服，balance > 0 说明需要向右边转移衣服。
     *   那么该洗衣机上的最大操作数为： max(diff[i], Math.abs(balance))
     * */

    public static int findMinMoves(int[] machines) {
        int N = machines.length;
        int count = 0;
//        count = Arrays.stream(machines).sum();
//        for (int machine : machines) {
//            count += machine;
//        }
        if (count % N != 0) {
            return -1;
        }
        int ans = 0;

        int status = 0;
        int sum = 0;
        int average = count / N;

        for (int i = 0; i < N; i++) {
            status = machines[i] - average;
            sum += status;
            ans = Math.max(ans, Math.max(status, Math.abs(sum)));
        }


        return ans;
    }
}
