//
// Created by Administrator on 2022/9/28.
//
#include <bits/stdc++.h>

using namespace std;

bool cmp(string a, string b) {
    return a.compare(b) > 0;
}
/*
 * C++ vector 用法详解
 * 在标准C++中,vector同样是一个模板,并且其底层实现用的是三个指针,
 * 然后利用这三个指针相互加减,达到存储效果.而vector和string类似,
 * 本质是都是一个顺序表. 头文件为 <vector>
 * */

int main() {

    int n = 5, a = 3;
    // 构造vector
    //---------------------------------------------
    // 参数构造
    vector<int> v1 = vector<int>();     //  无参构造
    vector<int> v2 = vector<int>(n);    //  第一个参数为容器大小
    vector<int> v3 = vector<int>(n, a);    // 第二个参数为值,将前n个元素初始化为a

    // 向量构造
    vector<int> v4 = vector<int>(v3);    // 第二个参数为值,将前n个元素初始化为a
    vector<int> v5 = vector<int>(v3.begin(), v3.begin() + 3);    // 第二个参数为值,将前n个元素初始化为a

    vector<string> v6 = {"hi", "my", "name", "is", "f4n"};  //  默认值创建

    // Vector 元素的访问
    // -------------------------------------------
    /*
     * 1.下标访问
     * - vector[i] 不会越界报错
     * - vector.at(i) 会越界报错
     * 只能对已存在的元素进行赋值或者修改操作，
     * 如果是要加入新元素，务必使用push_back。
     * push_back的作用有两个：
     * 告诉编译器为新元素开辟空间、将新元素存入新空间里。
     * 如果访问不存在的元素错误的，但是编译器不会报错，就像是数组越界。
     * */
    cout << v3[1] << endl;
    cout << v3[10] << endl;
    /*
     * 2.迭代器访问
     * 容器有成员begin和end,
     * - begin 成员复制返回指向第一个元素的迭代器，
     * - 而 end 成员返回指向容器尾元素的下一个位置的迭代器，
     *   也就是说end指示的是一个不存在的元素，所以end返回的是尾后迭代器。
     * 迭代器随机访问
     * - begin+i
     * - iter[i]
     * */
    for (vector<string>::iterator iter = v6.begin(); iter != v6.end(); iter++) {
        cout << *iter << " ";
    }
    // 反向迭代器
    for (vector<string>::reverse_iterator iter = v6.rbegin(); iter != v6.rend(); iter++) {
        cout << *iter << "-";
    }
    cout << endl;

    // 插入元素
    //----------------------------------------------
    v6.push_back("zhang");  // 尾部插入
    v6.pop_back();          // 尾部弹出
    /*迭代器插入*/
    v6.insert(v6.begin() + 3, "fuck");  // 在第i个位置后面插入元素
    v6.insert(v6.begin() + 1, 3, "end");   // 在第i个位置后面插入3个end
    v6.insert(v6.end(), {"test1", "test2"});  // 在第i个位置后面插入{}中的元素
    vector<string> v7{"shit1", "shit2"};
    v6.insert(v6.end(), v7.begin(), v7.end());  // 插入另外一个迭代器的区间

    for (int i = 0; i < v6.size(); ++i) {
        cout << v6[i] << " ";
    }
    cout << endl;

    // 删除元素
    //-----------------------------------------------------------
    v6.pop_back(); // 删除最后一个元素
    v6.erase(v6.begin() + 4);   // 删除第2个位置的元素
    v6.erase(v6.begin() + 1, v6.begin() + 4); // 删除[left,right)范围内的元素
    // erase() 函数在删除元素时，会将删除位置后续的元素陆续前移，并将容器的size减 1。
    // 返回值是删除的最后一个元素的下一个位置的迭代器


    // 常用操作
    // -----------------------------------------------------------
    reverse(v6.begin(), v6.end());  // 元素反转
    sort(v6.begin(), v6.end());     // 元素排序 默认升序
    sort(v6.begin(), v6.end(), cmp);       // 自定义比较器,可以降序
    v6.resize(12);  // 重设容器大小size
    v6.clear();     // 清空容器
    vector<string>::iterator it = find(v6.begin(), v6.end(), "test1");    // 寻找指定元素,并返回下标或者迭代器
    cout << *it << endl;
    vector<string> v8 = vector<string>(9);
    copy(v6.begin() + 3, v6.end(), v8.begin()); // 目的地容器要能容纳所拷贝的元素,不然会报错

}