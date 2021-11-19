public class contest267a1 {
    public static void main(String[] args) {
        System.out.println(timeRequiredToBuy(new int[]{2,3,2}, 2));
        System.out.println(timeRequiredToBuy(new int[]{5, 1, 1, 1}, 0));
        System.out.println(timeRequiredToBuy(new int[]{5, 1, 1, 1}, 3));
    }

    public static int timeRequiredToBuy(int[] tickets, int k) {
        int[] ans = new int[tickets.length];
        int time = 0;


        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] != 0) {
                    flag = true;
                    tickets[i]--;
                    time++;
                    ans[i] = time;
                }
            }
        }
        return ans[k];


    }
}
