import java.util.HashSet;

public class a575distributeCandies {
    public static void main(String[] args) {
        System.out.println(distributeCandies(new int[]{1, 1, 2, 2, 3, 3}));
        System.out.println(distributeCandies(new int[]{1, 1, 2, 3}));
    }

    public static int distributeCandies(int[] candyType) {
        int N = candyType.length >> 1;
        int type = 0;
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i : candyType) {
            hashSet.add(i);
        }

        type = hashSet.size();
        return type < N ? type : N;
    }

    public static int distributeCandies2(int[] candyType) {
        int N = candyType.length >> 1;
        int type = 0;
        boolean[] flag = new boolean[200001];

        for (int i : candyType) {
            if (!flag[i + 100000]) type++;
            flag[i + 100000] = true;
        }


        return type < N ? type : N;
    }
}
