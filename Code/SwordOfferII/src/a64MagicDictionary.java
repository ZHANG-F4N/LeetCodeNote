import java.awt.event.MouseAdapter;

public class a64MagicDictionary {
    public static void main(String[] args) {
        MagicDictionary obj = new MagicDictionary();
        obj.buildDict(new String[]{"hello", "leetcode"});
        boolean param_2 = obj.search("hello");
        boolean param_3 = obj.search("hhllo");
        boolean param_4 = obj.search("hell");
        boolean param_5 = obj.search("leetcoded");
    }
}

class MagicDictionary {

    MagicDictionary[] child;
    String word;

    /**
     * Initialize your data structure here.
     */
    public MagicDictionary() {
        child = new MagicDictionary[26];
        word = null;
    }

    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            MagicDictionary temp = this;
            for (char ch : word.toCharArray()) {
                if (temp.child[ch - 'a'] == null) {
                    temp.child[ch - 'a'] = new MagicDictionary();
                }
                temp = temp.child[ch - 'a'];
            }
            temp.word = word;
        }
    }

    static boolean ans;

    public boolean search(String searchWord) {
        ans = false;
        char[] arr = searchWord.toCharArray();
        DFS(this, arr, 0, false);
        return ans;
    }

    public void DFS(MagicDictionary root, char[] arr, int idx, boolean change) {
        if (root == null) {
            return;
        }
        if (arr.length == idx) {
            if (root.word != null && change) {
                ans = ans || true;
            }else {
                ans = ans || false;
            }
            return;
        }

        int index = arr[idx] - 'a';
        if (change && root.child[index] == null) {
            ans = ans || false;
            return;
        }
        if (change && root.child[index] != null) {
            DFS(root.child[index], arr, idx + 1, true);
        }

        if (!change) {
            for (int i = 0; i < 26; i++) {
                if (root.child[i] != null && i != index) {
                    DFS(root.child[i], arr, idx + 1, true);
                }
            }
            DFS(root.child[index], arr, idx + 1, false);

        }
    }

}