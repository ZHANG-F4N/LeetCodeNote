//
// Created by Administrator on 2022/9/29.
//


#include <bits/stdc++.h>

using namespace std;

class Solution {
public:
    bool isFlipedString(string s1, string s2) {
        if (s1.size() != s2.size())return false;
        string t = s2 + s2;
        return t.find(s1)  != string::npos;
    }
};

int main() {
    Solution s = Solution();
    cout << boolalpha << s.isFlipedString("waterbottle", "erbottlewat");
}
