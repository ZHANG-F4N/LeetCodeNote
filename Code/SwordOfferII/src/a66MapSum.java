import java.util.Map;

public class a66MapSum {
    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        int app =mapSum.sum("ap");           // return 3 (apple = 3)
        mapSum.insert("app", 2);
        app =mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)

    }
}

class MapSum {

    MapSum[] child;
    int val;
    static int ans = 0;

    /**
     * Initialize your data structure here.
     */
    public MapSum() {
        val = -1;
        child = new MapSum[26];
    }

    public void insert(String key, int val) {
        MapSum temp = this;
        for (char ch : key.toCharArray()) {
            if (temp.child[ch - 'a'] == null) {
                temp.child[ch - 'a'] = new MapSum();
            }
            temp = temp.child[ch - 'a'];
        }
        temp.val = val;
    }

    public int sum(String prefix) {
        MapSum temp = this;
        for (char ch : prefix.toCharArray()) {
            int idx = ch - 'a';
            if (temp.child[idx] == null) {
                return 0;
            }
            temp = temp.child[idx];
        }
        ans = 0;
        DFS(temp);
        return ans;
    }

    public static void DFS(MapSum root) {
        if (root == null) return;
        if (root.val != -1) ans += root.val;
        for (int i = 0; i < 26; i++) {
            if (root.child[i] != null) {
                DFS(root.child[i]);
            }
        }
    }


}