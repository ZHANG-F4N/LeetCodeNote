import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class a73setZeroes {
    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        setZeroes(matrix);
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }

    public static void setZeroes(int[][] matrix) {
            HashSet<Integer> xHashSet = new LinkedHashSet<>();
            HashSet<Integer> yHashSet = new LinkedHashSet<>();
            int xlen = matrix.length;
            int ylen = matrix[0].length;
            for (int i = 0; i < xlen; i++) {
                for (int j = 0; j < ylen; j++) {
                    if(matrix[i][j] == 0){
                        xHashSet.add(i);
                        yHashSet.add(j);
                    }
                }
            }
        for (int i = 0; i < xlen; i++) {
            for (int j = 0; j < ylen; j++) {
                if(xHashSet.contains(i)||yHashSet.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }
//        for (int i = 0; i < matrix.length; i++) {
//            if(xHashSet.contains(i)){
//                for (int j = 0; j < matrix[0].length; j++) {
//                    matrix[i][j] = 0;
//                }
//            }
//        }
//        for (int i = 0; i < matrix[0].length; i++) {
//            if(yHashSet.contains(i)){
//                for (int j = 0; j < matrix.length; j++) {
//                    matrix[j][i] = 0;
//                }
//            }
//        }
    }
}
