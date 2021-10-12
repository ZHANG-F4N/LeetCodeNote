
//
public class a14cuttingRope {
    public static void main(String[] args) {
        System.out.println(cuttingRope(5));
    }

    public static int cuttingRope(int n) {

        if (n <= 3) {
            return n - 1;
        }
        int residue = n % 3;
        int quotient = n / 3;
        if (residue == 0) {
            return (int) (Math.pow(3,quotient));
        } else if (residue == 1) {
            return (int) (Math.pow(3,quotient-1))*4;
        } else {
            return (int) (Math.pow(3,quotient)) *2 ;
        }
    }
}
