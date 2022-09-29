//
// Created by Administrator on 2022/9/28.
//
#include <bits/stdc++.h>

using namespace std;

int main() {
    /*
     * C++ vector 用法详解
     * 在标准C++中,vector同样是一个模板,并且其底层实现用的是三个指针,
     * 然后利用这三个指针相互加减,达到存储效果.而vector和string类似,
     * 本质是都是一个顺序表. 头文件为 <vector>
     * */
    int n = 5, a = 3;
    // 参数构造
    vector<int> v1 = vector<int>();     //  无参构造
    vector<int> v2 = vector<int>(n);    //  第一个参数为容器大小
    vector<int> v3 = vector<int>(n, a);    // 第二个参数为值,将前n个元素初始化为a

    // 向量构造
    vector<int> v4 = vector<int>(v3);    // 第二个参数为值,将前n个元素初始化为a
    vector<int> v5 = vector<int>(v3.begin(), v3.begin() + 3);    // 第二个参数为值,将前n个元素初始化为a

    cout << &v3 << " " << &v4;


}