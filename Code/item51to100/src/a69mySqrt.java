public class a69mySqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
/*
    X=15	1	    2	    3	    4	    5	6	7	8
    R1	    Left		            Mid				   Right
			    	            16 Mid^2>X
    R2	    Left    Mid	  Right
		        4 Mid^2<X
    R3			        Left/Right
                          Mid^2<X
*/

    public static int mySqrt(int x) {
        //1特殊处理
        if (x == 1) {
            return 1;
        }
        int left = 1, right = x >> 1;
        int mid = (left + right) >> 1;
        //<=才能向下取整
        while (left <= right) {
            //防爆x=2147395599 21亿 10位
            if ((long)mid * mid == x) {
                return mid;
            }
            if ((long)mid * mid > x) {
                right = mid - 1;
            }
            if ((long)mid * mid < x) {
                left = mid + 1;
            }
            mid = (left + right) >> 1;
        }
        return mid;
    }
}
