public class a1613cutSquares {
    public static void main(String[] args) {

    }

    public static double[] cutSquares(int[] square1, int[] square2) {
        double midX1 = square1[0] + (square1[2] / 2.0);
        double midY1 = square1[1] + (square1[2] / 2.0);
        double midX2 = square2[0] + (square2[2] / 2.0);
        double midY2 = square2[1] + (square2[2] / 2.0);

        double[] ans = new double[4];
        if (midX1 - midX2 == 0) {
            ans[0] = midX1;
            ans[1] = Math.min(square1[1], square2[1]);
            ans[2] = midX1;
            ans[3] = Math.max(square1[1]+ square1[2], square2[1] + square2[2]);
        }else{
            double k = (midY1 - midY2) / (midX1 - midX2);
            double b = midY1 - k * midX1;
            if (Math.abs(k) >= 1) {
                ans[1] = Math.min(square1[1] , square2[1]);
                ans[0] = (ans[1] - b) / k;
                ans[3] = Math.max(square1[1] + square1[2], square2[1] + square2[2]);
                ans[2] = (ans[3] - b) / k;
            } else {
                //斜率绝对值小于等于1，说明与正方形的左边和右边相交，同理
                ans[0] = Math.min(square1[0], square2[0]);
                ans[1] = ans[0] * k + b;
                ans[2] = Math.max(square1[0] + square1[2], square2[0] + square2[2]);
                ans[3] = ans[2] * k + b;
            }
        }

        if(ans[0] > ans[2]){
            swap(ans);
        }
        return ans;
    }

    public static void swap(double t[]) {
        double temp = t[0];
        t[0] = t[2];
        t[2] = temp;
        temp = t[1];
        t[1] = t[3];
        t[3] = temp;
    }
}
