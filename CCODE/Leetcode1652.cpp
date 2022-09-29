//
// Created by Administrator on 2022/9/27.
//
#include <bits/stdc++.h>

using namespace std;

/*
 * vector容器
 * - vector的声明 vector<int> a(size); 元素默认初始化为 0
 *   - 也可以这样声明: vector<int> a = {6, 4, 5, 3};
 *   - 或者   vector<int> a{6, 4, 5, 3};
 *   - 如果第二
 * - vector的访问 vector[i];
 * - vector没有越界检查
 *
 * */

class Solution {
public:
    vector<int> decrypt(vector<int> &code, int k) {
        vector<int> a{6, 4, 5, 3};
        if (k == 0) {
            return vector<int>(code.size());
        }
    }
};

int main() {
    Solution s = Solution();
}