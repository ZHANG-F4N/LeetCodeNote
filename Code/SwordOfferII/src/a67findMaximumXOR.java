import java.security.cert.TrustAnchor;
import java.util.Timer;

public class a67findMaximumXOR {
    public static void main(String[] args) {
        System.out.println(findMaximumXOR(new int[]{3, 10, 5, 25, 2, 8}));
        System.out.println(findMaximumXOR(new int[]{8, 10, 2}));
        System.out.println(findMaximumXOR(new int[]{14, 70, 53, 83, 49, 91, 36, 80, 92, 51, 66, 70}));
    }

    public static int findMaximumXOR(int[] nums) {
        Trie trie = new Trie();
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            trie.build(nums[i]);
            int val = trie.search(nums[i]);
            max = max > val ? max : val;
        }
        return max;
    }

    static class Trie {
        Trie[] child;

        public Trie() {
            child = new Trie[2];
        }

        public void build(Integer num) {
            Trie temp = this;
            for (int i = 30; i >= 0; i--) {
                int flag = (num & (1 << i)) == 0 ? 0 : 1;
                if (temp.child[flag] == null) {
                    temp.child[flag] = new Trie();
                }
                temp = temp.child[flag];
            }
        }

        public int search(Integer val) {
            Trie temp = this;
            int ans = 0;
            for (int i = 30; i >= 0; i--) {
                int flag = (val & (1 << i)) == 0 ? 0 : 1;
                if (flag == 1) {
                    if (temp.child[0] != null) {
                        temp = temp.child[0];
                        ans = ans | (1 << i);
                    } else {
                        temp = temp.child[1];
                    }
                } else {
                    if (temp.child[1] != null) {
                        temp = temp.child[1];
                        ans = ans | (1 << i);
                    } else {
                        temp = temp.child[0];
                    }
                }
            }
            return ans;
        }
    }
}

