import java.util.Arrays;

public class a2043Bank {
    static class Bank {
        private long[] balance;
        private int n;

        public Bank(long[] balance) {
            n = balance.length;
            this.balance = new long[n];
            this.balance = Arrays.copyOf(balance, n);
        }

        public boolean transfer(int account1, int account2, long money) {
            if (withdraw(account1, money)) {
                if (deposit(account2, money)) {
                    return true;
                } else {
                    deposit(account1, money);
                    return false;
                }
            } else {
                return false;
            }
        }

        public boolean deposit(int account, long money) {
            if (account > 0 && account <= n) {
                balance[account - 1] += money;
                return true;
            } else {
                return false;
            }
        }

        public boolean withdraw(int account, long money) {
            if (account > 0 && account <= n && balance[account - 1] >= money) {
                balance[account - 1] -= money;
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {

    }
}
