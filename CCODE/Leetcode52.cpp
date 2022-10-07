//
// Created by Administrator on 2022/10/7.
//

#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    int maxSubArray(vector<int> &nums) {
        int ans = nums[0],n = nums.size(),sum = 0;
        for (int i = 0; i < n; ++i) {
            if (sum + nums[i] > nums[i]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            ans = max(ans,sum);
        }
        return ans;
    }

};

int main() {
    vector<int> a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    cout << Solution().maxSubArray(a);
    return 0;
}
