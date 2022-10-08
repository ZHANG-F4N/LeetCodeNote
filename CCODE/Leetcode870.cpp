//
// Created by Administrator on 2022/10/8.
//

#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    struct node {
        int val;
        int idx;
    };

    static bool cmp(node &a, node &b) {
        return a.val < b.val;
    }

    vector<int> advantageCount(vector<int> &nums1, vector<int> &nums2) {
        int n = nums1.size();
        sort(nums1.begin(), nums1.end(), less<int>());
        node arr[n];
        for (int i = 0; i < nums2.size(); ++i) {
            arr[i].val = nums2[i];
            arr[i].idx = i;
        }
        sort(arr, arr + n, cmp);
        vector<int> ans(n);
        int l = 0, r = n - 1;
        for (int i = n - 1; i >= 0; --i) {
            if (nums1[r] > arr[i].val) {
                ans[arr[i].idx] = nums1[r--];
            } else {
                ans[arr[i].idx] = nums1[l++];
            };
        }
        return ans;
    }
};

int main() {
    vector<int> a{2, 7, 11, 15};
    vector<int> b{1, 10, 4, 11};
    vector<int> ans = Solution().advantageCount(a, b);
    for (const auto &item: ans) {
        cout << item << " ";
    }
}