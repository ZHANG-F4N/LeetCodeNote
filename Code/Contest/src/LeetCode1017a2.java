public class LeetCode1017a2 {
    public static void main(String[] args) {
        Bank obj = new Bank(new long[]{10, 100, 20, 50, 30});
        boolean param_3 = obj.withdraw(3, 10);
        boolean param_1 = obj.transfer(5, 1, 20);
        boolean param_2 = obj.deposit(5, 20);
        boolean param_4 = obj.transfer(3, 4, 15);
        boolean param_6 = obj.withdraw(10, 50);
    }

}

class Bank {
    long[] balance;
    int countNum;

    public Bank(long[] balance) {
        this.balance = balance;
        this.countNum = balance.length;
    }


    public boolean transfer(int account1, int account2, long money) {

        if (account1 > countNum || account1 < 1) {
            return false;
        }
        if (account2 > countNum || account2 < 1) {
            return false;
        }
        if (this.balance[account1 - 1] < money) {
            return false;
        }
        this.balance[account1 - 1] -= money;
        this.balance[account2 - 1] += money;
        return true;
    }

    public boolean deposit(int account, long money) {
        if (account > countNum || account < 1) {
            return false;
        }
        this.balance[account - 1] += money;
        return true;
    }

    public boolean withdraw(int account, long money) {
        if (account > countNum || account < 1) {
            return false;
        }
        if (this.balance[account - 1] < money) {
            return false;
        }
        this.balance[account - 1] -= money;
        return true;

    }
}
