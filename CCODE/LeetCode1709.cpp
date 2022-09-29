//
// Created by Administrator on 2022/9/28.
//
#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int getKthMagicNumber(int k) {
        vector<int> factors = {3, 5, 7};
        unordered_set<long> set;

        priority_queue<long, vector<long>, greater<long>> heap;
        set.insert(1L);
        heap.push(1L);

        int magic = 0;
        for (int i = 0; i < k; ++i) {
            long cur = heap.top();
            heap.pop();
            magic = (int) cur;
            for (int factor: factors) {
                long next = cur * factor;
                if (!set.count(next)) {
                    set.insert(next);
                    heap.push(next);
                }
            }
        }
        return magic;

    }
};

int main() {
    Solution s = Solution();
    cout << s.getKthMagicNumber(5);
    return 0;
}
