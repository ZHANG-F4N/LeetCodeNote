//
// Created by Administrator on 2022/10/7.
//
#include <bits/stdc++.h>

using namespace std;

/*
 * C++ 容器大致分为 2 类，即序列式容器和关联式容器。
 * 其中，序列式容器 包括 array、vector、list、deque 和 forward_list
 * 关联式容器 包括 map、set、multimap、multiset
 * STL 标准库在实现 关联式容器 时，底层选用了 「红黑树」这种数据结构来组织和存储各个键值对。
 * C++ 11 还新增了 4 种哈希容器，即 unordered_map、unordered_multimap 以及 unordered_set、unordered_multiset。
 * 严格来说，它们也属于关联式容器，但由于 哈希容器 底层采用的是哈希表，而不是红黑树。
 *
 * 除此之外，序列式容器中存储的元素默认都是未经过排序的，而使用关联式容器存储的元素，默认会根据各元素的键值的大小做升序排序。
 * 哈希容器 不会进行排序,通过哈希表实现
 * */

/*
 * 关联式容器存储的是“键值对”形式的数据，考虑到“键值对”并不是普通类型数据，
 * C++ STL 标准库提供了 pair 类模板，其专门用来将 2 个普通元素 first 和 second 建成一个新元素<first, second>
 * pair 类模板定义在<utility>头文件中，所以在使用该类模板之前，需引入此头文件。
 * pair 对象重载了 <、<=、>、>=、==、!= 这 6 的运算符，
 * 其运算规则是：对于进行比较的 2 个 pair 对象，先比较 pair.first 元素的大小，如果相等则继续比较 pair.second 元素的大小。
 * */
int main() {

    // map 容器定义在 <map> 头文件中，并位于 std 命名空间中。

    // 初始化map
    // ----------------------------------------
    map<string, int> map1; // 默认构造
    map<string, string> map2{
            {"name", "zhang"},
            {"age",  "18"},
            {"gender","female"}
    };   // 带初始化的构造
    map<string, int> map3(map1); // 调用拷贝构造函数,利用先前已创建好的 map 容器，再创建一个新的 map 容器。
    // 取已建 map 容器中指定区域内的键值对，创建并初始化新的 map 容器。
    map<string, string> map4(++map2.begin(), map2.end());

    // 访问map
    // ---------------------------------------
    cout << map2["name"];   // 通过重载运算符 [] 进行访问
    // 只有当 map 容器中确实存有包含该指定键的键值对，借助重载的 [ ] 运算符才能成功获取该键对应的值；
    // 反之，若当前 map 容器中没有包含该指定键的键值对，则此时使用 [ ] 运算符将不再是访问容器中的元素，而变成了向该 map 容器中增添一个键值对。
    // 如果 map 容器中包含该指定键的键值对，使用 [ ] 运算符 作用就变成了修改该键对应的值。

    cout << map2.at("name");    // 使用at取map容器中指定键对应的值,如果在当前容器中查找失败，该方法不会向容器中添加新的键值对，而是直接抛出 out_of_range 异常。

    // 还可以借助 find() 成员方法间接实现此目的。
    // 和以上 2 种方式不同的是，该方法返回的是一个迭代器，即如果查找成功，该迭代器指向查找到的键值对；
    // 反之，则指向 map 容器最后一个键值对之后的位置（和 end() 成功方法返回的迭代器一样）。
    map<string, string>::iterator it = map2.find("age");
    cout << it->first << ":" << it->second;


    for (const auto &item: map4) {
        cout << item.first << " " << item.second << endl;
    }


}