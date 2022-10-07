//
// Created by Administrator on 2022/10/7.
//
#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int maxAscendingSum(vector<int> &nums) {
        int n = nums.size();

        int ans = nums[0], sum = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {

                ans = max(ans, sum);
                sum = nums[i];
            }
        }
        ans = max(ans, sum);
        return ans;
    }
};

int main() {
    vector<int> a = {10, 20, 30, 5, 10, 50};
    cout << Solution().maxAscendingSum(a);
    return 0;
}