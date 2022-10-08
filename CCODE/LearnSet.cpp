//
// Created by Administrator on 2022/9/28.
//

#include <bits/stdc++.h>

using namespace std;

/*
 *
 * set 容器存储的各个键值对，要求键 key 和值 value 必须相等。
 *
 * C++ STL 标准库为 set 容器配置的迭代器类型为双向迭代器。
 * 这意味着，假设 p 为此类型的迭代器，则其只能进行 ++p、p++、--p、p--、*p 操作，
 * 并且 2 个双向迭代器之间做比较，也只能使用 == 或者 != 运算符。
 *
 * 使用 set 容器存储的各个元素的值必须各不相同。更重要的是，从语法上讲 set 容器并没有强制对存储元素的类型做 const 修饰，
 * 即 set 容器中存储的元素的值是可以修改的。但是，C++ 标准为了防止用户修改容器中元素的值，对所有可能会实现此操作的行为做了限制，
 * 使得在正常情况下，用户是无法做到修改 set 容器中元素的值的。
 * 切勿尝试直接修改 set 容器中已存储元素的值，这很有可能破坏 set 容器中元素的有序性，
 * 最正确的修改 set 容器中元素值的做法是：先删除该元素，然后再添加一个修改后的元素。
 * */

int main() {

    // 构造
    // -----------------------------
    set<int> set1;  // 无参构造 该容器采用默认的std::less<T>规则，会对存储的元素做 升序 排序。
    // 注意，由于 set 容器支持随时向内部添加新的元素，因此创建空 set 容器的方法是经常使用的。
    set<int> set2({1, 4, 2, 5, 6, 8, 15, 65});  // 初始化
    set<int> set3(set2);    // 拷贝（复制）构造函数
    set<int> set4(++set2.begin(), set2.end());          // 迭代器部分构造
    set<int, greater<int>> set5({5, 6, 8, 15, 65});    // 自定义比较器


    // 元素访问 set访问主要以查找是否包含为主
    // -------------------------------
    set<int>::iterator it = set5.find(2);
    cout << *it << "---";
    //返回一个指向当前 set 容器中第一个大于等于 val 的元素的双向迭代器。
    set<int>::iterator it2 = set5.lower_bound(5);
    while (it2 != --set5.begin()) {
        cout << *it2-- << "-";
    }

    // 插入元素
    // ---------------------------------
    // insert 插入 返回 pair<set<T>::iterator, bool> 如果插入成功,Bool 为 true 反之为 false,迭代器指向插入的元素
    pair<set<int>::iterator, bool> res = set5.insert(65);
    // 指定将新元素插入到 set 容器中的具体位置，但没用,因为底层红黑树要重排
    set5.insert(++set5.begin(), 65);
    // 支持向当前 set 容器中插入其它 set 容器指定区域内的所有元素，只要这 2 个 set 容器存储的元素类型相同即可。
    set5.insert(set2.begin(), ++set2.begin());
    // 实现一次向 set 容器中添加多个元素
    set1.insert({45641, 1, 654, 654, 613, 6451});

    // 向指定 set 容器中添加新元素的，只有 3 个成员方法，分别为 insert()、emplace() 和 emplace_hint()。
    // emplace() 和 emplace_hint() 是 C++ 11 标准加入到 set 类模板中的，
    // 相比具有同样功能的 insert() 方法，完成同样的任务，emplace() 和 emplace_hint() 的效率会更高。
    set1.emplace(456);
    set1.emplace_hint(++set1.begin(), 456); // emplace_hint() 可以指定插入位置 但没用


    // 删除数据
    // ----------------------------------
    // erase()和clear()方法
    set1.erase(1);  // 删除元素 1
    set1.erase(set1.begin());  // 删除位于 set1.begin() 的元素
    // 删除位于 [left, right) 之间的元素
    set1.erase(set1.begin(), --set1.end());
    set1.clear();      // 删除所有元素


    cout << "hello";
}