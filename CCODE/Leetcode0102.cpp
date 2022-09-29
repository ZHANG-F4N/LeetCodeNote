//
// Created by Administrator on 2022/9/27.
//
#include <bits/stdc++.h>

using namespace std;

class Leetcode0102 {
public:
    bool CheckPermutation(string s1, string s2) {
        int map1[26] = {0};
        int map2[26] = {0};
        for (int i = 0; i < s1.length(); ++i) map1[s1[i] - 'a']++;
        for (int i = 0; i < s2.length(); ++i) map2[s2[i] - 'a']++;

        for (int i = 0; i < 26; ++i) {
            if (map1[i] != map2[i])
                return false;
        }
        return true;
    }
};

int main() {
    Leetcode0102 solution = Leetcode0102();
    cout << boolalpha << solution.CheckPermutation("abc", "bac");
//    printf("%s", solution.CheckPermutation("abc", "bac"));
};

