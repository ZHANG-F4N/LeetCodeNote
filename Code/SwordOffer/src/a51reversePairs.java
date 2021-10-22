import java.util.Arrays;

public class a51reversePairs {
    public static void main(String[] args) {

        int[] num = {7, 5, 6, 4};
        System.out.println(reversePairs2(num));
    }

    // 二路归并查找
    public static int reversePairs(int[] nums) {
        int N = nums.length;

        int ans = 0;
        // 一组的长度
        int len = 1;
        int i = 0;
        while (len < N) {
            int[] tempArr = new int[N];
            while (i < N) {
                int lidx = i;
                int lend = i + len - 1;
                int ridx = i + len;
                int rend = i + len + len - 1;
                if (lend >= N) {
                    lend = N - 1;
                    while (lidx <= lend) {
                        tempArr[i++] = nums[lidx++];
                    }
                    break;
                }
                if (rend >= N) {
                    rend = N - 1;
                }
                while (lidx <= lend && ridx <= rend) {
                    if (nums[lidx] <= nums[ridx]) {
                        tempArr[i++] = nums[lidx++];
                    } else {
                        tempArr[i++] = nums[ridx++];
                        // 核心步骤
                        // 原因 子区间有序
                        ans += lend - lidx + 1;
                    }
                }
                while (lidx <= lend) {
                    tempArr[i++] = nums[lidx++];
                }
                while (ridx <= rend) {
                    tempArr[i++] = nums[ridx++];
                }
            }
            len <<= 1;
            i = 0;
            nums = tempArr;
        }

        return ans;
    }

    // 离散化树状数组
    public static int reversePairs2(int[] nums) {
        int N = nums.length;
        int ans = 0;
        int[] copy = new int[N];
        System.arraycopy(nums, 0, copy, 0, N);
        Arrays.sort(copy);

        for (int i = 0; i < nums.length; i++) {
            nums[i] = Arrays.binarySearch(copy, nums[i]) + 1;
        }
        BIT bit = new BIT(N);

        for (int i = N - 1; i >= 0; --i) {
            // 查询前面有几个比他小的数
            ans += bit.query(nums[i - 1]);
            bit.update(nums[i]);
        }
        return ans;
    }

}

// Binary Indexed Tree
// 树状数组或二叉索引树
// 现多用于高效计算数列的前缀和，区间和。
// 树状数组中修改和查询的复杂度都是O(logN)
class BIT {
    private int[] tree;
    private int n;

    public BIT(int n) {
        this.n = n;
        // 一般从 1 开始
        this.tree = new int[n + 1];
    }

    // 定义一个Lowbit函数，返回参数转为二进制后,最后一个1的位置所代表的数值.
    // 例如,Lowbit(34)的返回值将是2；而Lowbit(12)返回4；Lowbit(8)返回8。
    public static int lowbit(int x) {
        // 这一步更是妙蛙坐火箭，妙上天了。
        // 这利用了补码的性质
        // 对于整数运算 x&(-x)有:
        // - 当x为0时，即 0 & 0，结果为0；
        // - 当x为奇数时，最后一个比特位为1，
        //   取反加1没有进位，故x和-x除最后一位外前面的位正好相反，按位与结果为0。结果为1。
        // - 当x为偶数，实际上就是把x用一个奇数左移k位来表示。
        //   这时，x的二进制表示最右边有k个0，从右往左第k+1位为1。
        //   x  =   000110100
        //   −x =   111001100
        //   x&(−x)=000000100
        return x & (-x);
    }

    public int query(int x) {
        int ret = 0;
        // 这一步真是妙呀,妙蛙他妈给妙蛙开门,妙到家了。
        // 如 tree[i]
        // tree[6] = num[110] + num[100];
        // tree[7] = num[111] + num[110] + num[100];
        while (x != 0) {
            ret += tree[x];
            x -= lowbit(x);
        }
        return ret;
    }

    // 更新是插入操作
    public void update(int x) {
        // n 为数组容量
        while (x <= n) {
            // tree的节点x++,相当于统计
            // 可以用作统计 小于当前数的 个数,也可以用作求 前缀和
            tree[x]++;
            // 包含这个节点的父节点也得加1
            x += lowbit(x);
        }
        // 包含 1 的节点有 1、10、100、1000···
        // 包含 3 的节点有 11、100、1000···
    }
}

