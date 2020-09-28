<p id='top'>

# CodeNote of LeetCode 1 to 50
---
##目录
>[链表两数相加](#question-2-链表两数相加)
[无重复字符的最长子串](#question-3-无重复字符的最长子串)
[最长回文子串](#question-5-最长回文子串)
[三数之和](#question-15-三数之和)
[删除链表的倒数第N个节点](#question-19-删除链表的倒数第n个节点)
[合并两个有序链表](#question-21-合并两个有序链表)
---

## Question 2 链表两数相加

> <font face='宋体'>给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
> 
> 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 
输出：7 -> 0 -> 8
原因：342 + 465 = 807 
>> * 解题思路
>>考察链表的操作。  
这类问题基本不可以使用先转换成数字再进行加减的方法进行计算，只能通过按结点进行加减，不然会出现int或long无法存储等一类问题。</font>


```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def addTwoNumbers(self, l1: ListNode, l2: ListNode) -> ListNode:
        ans  = ListNode(0)
        isUp = 0
        temp  = ans
        #相加
        while l1 != None and l2 != None:
            temp.val = int(str(isUp +l1.val + l2.val)[-1:])
            temp.next = ListNode(0)
            if l1.val + l2.val + isUp >= 10:
                isUp = 1
            else :
                isUp = 0
            l1 = l1.next
            l2 = l2.next
            temp = temp.next

        #链长短不一处理
        while  l1 != None:
            temp.val = int(str(isUp +l1.val)[-1:])
            if l1.val  + isUp >= 10:
                isUp = 1
            else :
                isUp = 0
            temp.next = ListNode(0)
            l1 = l1.next
            temp = temp.next

        while l2 != None:
            temp.val = int(str(isUp +l2.val)[-1:])
            if l2.val  + isUp >= 10:
                isUp = 1
            else :
                isUp = 0
            temp.next = ListNode(0)
            l2 = l2.next
            temp = temp.next
        
        if isUp == 1:
            temp.val = 1
            temp.next = ListNode(0)

        #去掉链最后的结点 可以优化
        testBit = ans
        while testBit.next.next != None:
            testBit = testBit.next
        testBit.next = None
        
        return ans
```

##  Question 3 无重复字符的最长子串

> <font face='宋体'>给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
示例 1:
输入: "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
>> * 解题思路
>>传统便利方法时间复杂度为O(n*n)，可以通过O(n)的方法来解决。
每次遍历，需要维护一个当前字符最后一次出现的位置下标。
我们需要记录之前出现过的字符，记录的方式有很多，最常见的是统计字符出现的个数，但是这道题字符出现的位置很重要，所以我们可以使用HashMap来建立字符和其出现位置之间的映射。进一步考虑，由于字符会重复出现，到底是保存所有出现的位置呢，还是只记录一个位置？我们之前手动推导的方法实际上是维护了一个滑动窗口，窗口内的都是没有重复的字符，我们需要尽可能的扩大窗口的大小。由于窗口在不停向右滑动，所以我们只关心每个字符最后出现的位置，并建立映射。窗口的右边界就是当前遍历到的字符的位置，为了求出窗口的大小，我们需要一个变量left来指向滑动窗口的左边界，这样，如果当前遍历到的字符从未出现过，那么直接扩大右边界，如果之前出现过，那么就分两种情况，在或不在滑动窗口内，如果不在滑动窗口内，那么就没事，当前字符可以加进来，如果在的话，就需要先在滑动窗口内去掉这个已经出现过的字符了，去掉的方法并不需要将左边界left一位一位向右遍历查找，由于我们的HashMap已经保存了该重复字符最后出现的位置，所以直接移动left指针就可以了。我们维护一个结果res，每次用出现过的窗口大小来更新结果res，就可以得到最终结果了。</font>


```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        strLen = len(s)
        left = 0
        chardict = {}
        ans = 0
        for index in range(len(s)):
            chardict[s[index]] = 0 #初始化
        for index in range(len(s)):
            if chardict[s[index]] == 0 or chardict[s[index]] < left:  #这一句为核心
                ans = max(ans,index-left+1)
            else :
                left = chardict[s[index]] 
            chardict[s[index]] = index + 1 #字符最后一次出现的位置从1开始算，所以+1

        return ans
```


##  Question 5 最长回文子串

> <font face='宋体'>给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
300.最长上升子序列 && 376.摆动序列 && 5.最长回文子串 && 516.最长回文子序列 && 最长公共子序列/串
>> * 解题思路
>> 动态规划，DP[i][j]表示从i到j是不是回文子串，关键为判断，当s[i] == s[j] 时，如果dp[i+1][j-1]为回文子串时，加上当前的s[i]s[j]便也为回文子串，则dp[i][j]也就为真。当出现 dd 或者 ab 这种情况，需要j-i判断一下。但首先要s[i] == s[j]
</font>


```python
class Solution:
    def longestPalindrome(self, s: str) -> str:
        StrDP = [[0 for i in range(0,len(s))] for j in range(0,len(s)) ]
        for i in range(0,len(s)):
            StrDP[i][i] = 1
        left = 0
        right = 0
        for i in range(len(s)-2,-1,-1):
            for j in range(i + 1,len(s)):
                if (s[i] == s[j]) and (j-i == 1 or StrDP[i+1][j-1]):
                    StrDP[i][j] = 1
                    if(j-i>right -left):
                        left = i 
                        right = j

        return s[left:right+1]
```

##  Question 15 三数之和

> <font face='宋体'>给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
注意：答案中不可以包含重复的三元组
示例：
给定数组 nums = [-1, 0, 1, 2, -1, -4]，
满足要求的三元组集合为：
[
  [-1, 0, 1],
  [-1, -1, 2]
]
>> * 解题思路
>> &nbsp;首先要学习两数之和，给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数。简单的思路是进行两边遍历，确定第一个数，然后看其他的数与确定的数之和。时间复杂度为O(N^2),可以通过hash target-nums[i]: nums, 将时间复杂度降为O(N) 
&nbsp; 三数之和可以理解为规定一个数之后的两数之和，但是问题在于处理掉相同的序列，采用的方法是排序，排序后按序进行筛选，就可以避免重复元素，而当出现相同的值时，必定是相邻的，所以可以遍历时判断有相邻相同的数进行continue，再次过滤相同序列。最后使用双指针(既就是两个下标second和third，一个从前，一个从后进行遍历，因为数组是有序的) 时间复杂度为O(N^2)

</font>

```python
class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        N = len(nums)
        nums.sort()
        ans = []
        #print(nums)
        for first in range(N):
            #避免重复元素 
            if first > 0 and nums[first] == nums[first-1]:
                continue
            
            third = N - 1
            tempans = 0 - nums[first]
            for second in range(first+1,N-1):
                
                #print(first,second,third,tempans)
                #避免重复元素 
                if second > first + 1 and nums[second] == nums[second-1]: #
                    continue

                
                while nums[second] + nums[third] > tempans and third > second:
                    third -= 1
                #需要在进行判断前先判断下标是否越界
                if third - second == 0 :
                    break

                #print('a',first,second,third,tempans)
                if nums[second] + nums[third] == tempans:
                    ans.append( [nums[first] , nums[second], nums[third]])
                    continue

        return ans
```

##  Question 19 删除链表的倒数第N个节点

> <font face='宋体'>给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
> 示例：
给定一个链表: 1->2->3->4->5, 和 n = 2.
当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：
给定的 n 保证是有效的。
>> * 解题思路
>> 使用双指针，先让一个指针向后移动N个结点，再用头指针和N指针一起移动，N指针到达最后一个位置时，删除头指针的结点即为所求链表。
</font>


```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def removeNthFromEnd(self, head: ListNode, n: int) -> ListNode:
        ans = head
        headTemp = head

        if(head.next == None):
            return None

        for index in range(n):
            print(index)
            headTemp = headTemp.next

        if(headTemp == None):
            return ans.next

        while headTemp.next != None:
            head = head.next
            headTemp = headTemp.next

        if(head.next == None):
            return ans
        head.next = head.next.next

        return ans
```

##  Question 21 合并两个有序链表

> <font face='宋体'>将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
> 示例：
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
>> * 解题思路
>> 这个题注意Python下链表的使用
</font>


```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        if l1 == None and l2 == None:
            return None
        if l1 == None:
            return l2
        if l2 == None:
            return l1
        #对于Python中的链表操作要注意
        head = ListNode()
        ans = head
        #虽然是简单题，对于不新建结点的方法要注意
        while l1 != None and l2!= None:
            if l1.val < l2.val:
                head.next = l1
                l1 = l1.next
            else:
                head.next = l2
                l2 = l2.next
            head = head.next
        
        if l1 != None:
            head.next = l1
        if l2 != None:
            head.next = l2
        
        return ans.next
```



[返回顶部](#top)