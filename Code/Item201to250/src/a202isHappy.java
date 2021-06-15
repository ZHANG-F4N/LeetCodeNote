import java.util.HashSet;

public class a202isHappy {
    public static void main(String[] args) {
        int num = 2;
        System.out.println(isHappy_Point(num));
    }

    public static int getNext(int val) {
        int ans = 0;
        //用val / 10 == 0 判断需要最后判断一位数
        while (val > 0) {
            ans += (val % 10) * (val % 10);
            val /= 10;
        }
        return ans;
    }

    //HashSet
    //有3种情况:
    //第一种:回到1
    //第二种:最后产生循环
    //第三种:无限大
    //对于 3 位数的数字，它不可能大于 (999的快乐数243) 243。这意味着它要么被困在 243 以下的循环内，要么跌到 1。
    // 4 位或 4 位以上的数字在每一步都会丢失一位,这是因为每个位数的平方最大为81，不可能达到三位,所以位数会越来越少,直到降到 3 位为止。
    // 所以我们知道，最坏的情况下，算法可能会在 243 以下的所有数字上循环，然后回到它已经到过的一个循环或者回到 1。
    // 但它不会无限期地进行下去，所以我们排除第三种选择。
    public static boolean isHappy(int n) {
        HashSet<Integer> hashSet = new HashSet<>();
        while (!hashSet.contains(getNext(n)) && n != 1) {
            hashSet.add(getNext(n));
            n = getNext(n);
        }
        return n == 1;
    }

    //快慢指针法
    public static boolean isHappy_Point(int n) {
        int slow = n;
        int fast = getNext(n);
        while (fast != 1 && fast != slow) {
            fast = getNext(getNext(fast));
            slow = getNext(slow);
        }
        return fast == 1;
    }
}
