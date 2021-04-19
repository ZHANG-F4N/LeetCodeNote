# Code-note of LeetCode 1 to 50

[TOC]

## Question 2 链表两数相加

> <font face='宋体'>给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
>
> 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 
> 输出：7 -> 0 -> 8
> 原因：342 + 465 = 807 
>
>  * 解题思路
> 考察链表的操作。  
> 这类问题基本不可以使用先转换成数字再进行加减的方法进行计算，只能通过按结点进行加减，不然会出现int或long无法存储等一类问题。</font>


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
> 示例 1:
> 输入: "abcabcbb"
> 输出: 3 
> 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
>
>  * 解题思路  
>
>    传统遍历方法时间复杂度为O(n*n)，可以通过O(n)的方法来解决。
>
>      每次遍历，需要维护一个当前字符最后一次出现的位置下标。
>      		我们需要记录之前出现过的字符，记录的方式有很多，最常见的是统计字符出现的个数，但是这道题字符出现的位置很重要，所以我们可以使用HashMap来建立字符和其出现位置之间的映射。进一步考虑，由于字符会重复出现，到底是保存所有出现的位置呢，还是只记录一个位置？我们之前手动推导的方法实际上是维护了一个滑动窗口，窗口内的都是没有重复的字符，我们需要尽可能的扩大窗口的大小。
>
>      ​		由于窗口在不停向右滑动，所以我们只关心每个字符最后出现的位置，并建立映射。窗口的右边界就是当前遍历到的字符的位置，为了求出窗口的大小，我们需要一个变量left来指向滑动窗口的左边界，这样，如果当前遍历到的字符从未出现过，那么直接扩大右边界，如果之前出现过，那么就分两种情况，在或不在滑动窗口内，如果不在滑动窗口内，那么就没事，当前字符可以加进来，如果在的话，就需要先在滑动窗口内去掉这个已经出现过的字符了，去掉的方法并不需要将左边界left一位一位向右遍历查找，由于我们的HashMap已经保存了该重复字符最后出现的位置，所以直接移动left指针就可以了。我们维护一个结果res，每次用出现过的窗口大小来更新结果res，就可以得到最终结果了。</font>


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
> * 解题思路
> 动态规划，DP[i][j]表示从i到j是不是回文子串，关键为判断，当s[i] == s[j] 时，如果dp[i+1][j-1]为回文子串时，加上当前的s[i]s[j]便也为回文子串，则dp[i][j]也就为真。当出现 dd 或者 ab 这种情况，需要j-i判断一下。但首先要s[i] == s[j]
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

##  Question 8 字符串转换整数 (atoi)

> <font face='宋体'>请你来实现一个 atoi 函数，使其能将字符串转换成整数。
首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。  
&nbsp;注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
在任何情况下，若函数不能进行有效的转换时，请返回 0 。
提示：
本题中的空白字符只包括空格字符 ' ' 。
假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
示例 1:
输入: "42"
输出: 42
示例 2:
输入: "   -42"
输出: -42
解释: 第一个非空白字符为 '-', 它是一个负号。
     我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
> * 解题思路
> 根据规则字符串处理，注意python的默认int范围，int 类型在python中是动态长度的。因为python3中int类型是长整型，理论支持无限大的数字
</font>

```python
class Solution:
    def myAtoi(self, str: str) -> int:
        StrTemp = str.lstrip(' ')
        if(StrTemp == ''):
            return 0
        if (StrTemp[0] >'9' or StrTemp[0]<'0') and StrTemp[0] !='-' and StrTemp[0] !='+':
            return 0
        if StrTemp =='+' or StrTemp =='-':
            return 0
        if (StrTemp[0] =='-' or StrTemp[0] =='+') and (StrTemp[1] <'0' or StrTemp[1] >'9'):
            return 0
        ansChar = ''
        flag = False
        FirstIndex = -1
        for index in range(len(StrTemp)):
            if '0'<=StrTemp[index]<='9':
                ansChar += StrTemp[index]
                if flag == False:
                    FirstIndex = index
                flag = True
            if flag == True and(StrTemp[index]<'0' or StrTemp[index] >'9'):
                break 
        if FirstIndex == 0:
            ans = int(ansChar)
        else:
            ans = int(StrTemp[FirstIndex-1]+ansChar)
        if ans > 2**31 - 1:
            return 2**31 - 1
        if ans < -2**31:
            return -2**31
        return ans
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
> * 解题思路
> &nbsp;首先要学习两数之和，给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数。简单的思路是进行两边遍历，确定第一个数，然后看其他的数与确定的数之和。时间复杂度为O(N^2),可以通过hash target-nums[i]: nums, 将时间复杂度降为O(N) 
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

##  Question 16 最接近的三数之和

> <font face='宋体'>给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
示例：
输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
> * 解题思路
&nbsp; 做法和[三数之和](#question-15-三数之和)相同,只需要维护一个全局的best距离即可，注意代码中的优化，可以过滤边缘值

</font>

```python
class Solution:
    def threeSumClosest(self, nums: List[int], target: int) -> int:
        nums.sort()

        N  = len(nums)
        best = nums[0]+nums[1]+nums[2] #假设nums至少三个元素

        #def updateDis(cur):
        #    nonlocal best # nonlocal关键字用来在函数或其他作用域中使用外层(非全局)变量
        #    if abs(cur - target) < abs(best - target) :
        #        best = cur  

        for first in range(N-2):
            third = N-1
            #flag = False #每次second和third移动是否减小又增大，当开始变大时可以停止遍历，代码未实现
            second = first+1
            """
            此段代码可以大大过滤样本，排过序后最小的三个数是前三个，最大的数是后三个，所以可以使用这俩个值与target比较，来过滤第一次循环，固然可以大大提高效率
            max_sum = nums[first] + nums[-2] + nums[-1]
            min_sum = nums[first] + nums[first + 1] + nums[first + 2]
            if max_sum <= target:    # 最大的数
                if abs(max_sum - target) < abs(best - target):
                    best = max_sum
                continue              
            elif min_sum >= target:  # 最小的数
                if abs(min_sum - target) < abs(best - target):
                    best = min_sum      
                break   

            """

            while third > second:
                if second > first + 1 and nums[second] == nums[second - 1]:
                    second += 1
                    continue
                if third < N-1 and nums[third] == nums[third + 1]:
                    third-=1
                    continue
                dis = nums[first]+nums[second]+nums[third]

                if dis == target:
                    return dis
                #updateDis(dis)
                if abs(dis - target) < abs(best - target) :
                    best = dis  
                if dis > target:
                    third -= 1
    
                if dis < target:
                    second += 1    
        return best
```

##  Question 17 电话号码的字母组合

> <font face='宋体'>给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。phoneMap = {
            "2": "abc","3": "def","4": "ghi","5": "jkl",
            "6": "mno","7": "pqrs","8": "tuv","9": "wxyz",
        }
示例:
输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
> * 解题思路
&nbsp; DFS注意深度优先的边界值，具体看代码

</font>

```python
class Solution:
    def letterCombinations(self, digits: str) -> List[str]:
        if not digits:
            return list()
        phoneMap = {
            "2": "abc",
            "3": "def",
            "4": "ghi",
            "5": "jkl",
            "6": "mno",
            "7": "pqrs",
            "8": "tuv",
            "9": "wxyz",
        }
        ansStr=[]
        singleStr = []
        def DFS(index:int):
            if index == len(digits):
                ansStr.append("".join(singleStr))
            else:
                digit = digits[index]
                for char in phoneMap[digit]:#for循环就是分支的过程
                    singleStr.append(char)
                    DFS(index+1)
                    singleStr.pop()
        DFS(0)
        return ansStr


```


##  Question 19 删除链表的倒数第N个节点

> <font face='宋体'>给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
> 示例：
给定一个链表: 1->2->3->4->5, 和 n = 2.
当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：
给定的 n 保证是有效的。
> * 解题思路
> 使用双指针，先让一个指针向后移动N个结点，再用头指针和N指针一起移动，N指针到达最后一个位置时，删除头指针的结点即为所求链表。
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
> * 解题思路
> 这个题注意Python下链表的使用
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

##  Question 24 两两交换链表中的节点

> <font face='宋体'>给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
示例:
给定 1->2->3->4, 你应该返回 2->1->4->3.
> * 解题思路
> 双指针，维护一个第一个指针的前一个指针来连接，改天研究一下递归的方法
</font>

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:

        if head == None or head.next ==None:
            return head
        
        firstnode = head
        secondnode = head.next
        ans = secondnode
        temp = secondnode.next
        firstnode.next = temp
        secondnode.next = firstnode
        pre = firstnode
        
        while pre.next != None and pre.next.next :
            firstnode = pre.next
            secondnode = pre.next.next
            
            temp = secondnode.next
            pre.next = secondnode
            secondnode.next = firstnode
            firstnode.next = temp

            pre = firstnode


        return ans
```

##  Question 28 实现 strStr() KMP

> <font face='宋体'>给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
示例 1:
输入: haystack = "hello", needle = "ll"
输出: 2
> * 解题思路
> 使用KMP算法，KMP算法的关键是构建Next数组，为什么要用next数组呢？是为了在一次匹配失败后可以不用在从头匹配字符串，只需要从子串的某个位置重新匹配，而这个位置就是要又Next数组来提供。核心就是：找到了最长相等的前缀和后缀，匹配失败的位置是后缀子串的后面，那么我们找到与其相同的前缀的后面从新匹配就可以了。
</font>

```python

```

## Question 31 下一个排列

> <font face='宋体'>实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
必须原地修改，只允许使用额外常数空间。
以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2  
3,2,1 → 1,2,3  
1,1,5 → 1,5,1 
> * 解题思路  
> 两趟扫描，先从后往前找第一个减小的数记为a[i],再从a[i]开始往后扫描，找最后一个大于a[i] (注意如果有重复的数字的话，一定是严格大于),的数，记为a[j],将a[i]与a[j]交换，再将a[i]后面的序列调整为最小的序列。如果a[i]后面的序列都大于a[i],则选择最后一个(因为第一次扫描时，确保了a[i]后面的序列都是降序，最后一位即为最小的大于a[i]的数)
</font>

```python
class Solution:
    def nextPermutation(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        #代码逻辑已经为最优解，但代码还可继续优化
        changeIndex1 = -1
        for index in range(len(nums)-1,-1,-1):
            if index != 0 and nums[index-1]>=nums[index]:
                continue
            elif index != 0:
                changeIndex1 = index - 1
                break
            else:
                nums.reverse()
                return
        
        for index in range(changeIndex1+1,len(nums)):
            if  nums[changeIndex1] >= nums[index] :
                #print(changeIndex1,'1in',index-1)
                temp = nums[changeIndex1]
                nums[changeIndex1] = nums[index-1]
                nums[index-1] = temp
                #print(nums)
                nums[changeIndex1+1:] = sorted(nums[changeIndex1+1:])
                #nums[changeIndex1+1:len(nums)].sort() --这个语法是错的，会返回None,需要使用上面这一句
                return
            elif  index == len(nums)-1:
             """当changeIndex1位置后的数都比nums[changeIndex1]大 时，
            这个后缀肯定是降序的，将最后一个字符进行调换"""
                # print(changeIndex1,'2in',index)
                temp = nums[changeIndex1]
                nums[changeIndex1] = nums[index]
                nums[index] = temp
                #print(nums)
                nums[changeIndex1+1:] = sorted(nums[changeIndex1+1:])
                return
```
## Question 33 搜索旋转排序数组

> <font face='宋体'>假设按照升序排序的数组在预先未知的某个点上进行了旋转。
( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回-1 。
你可以假设数组中不存在重复的元素。
你的算法时间复杂度必须是 O(log n) 级别。 
示例 1:
输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
> * 解题思路  
> 二分查找，Log(N),如果有序数组从一个位置旋转过，在二分时，肯定有一部分是有序的，先判断那一部分有序，如果tar在有序部分，继续二分即可，如果在无序的部分，做同样的处理。关键是确定其在那一部分，左边有序时：`nums[left]<=nums[mid]`右边有序时：`nums[mid] <= nums[right]`
判断tar在那一部分时，需要两边都限制才能确定。`nums[left]<= target< nums[mid]`
</font>

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        if nums==[]:
            return -1

        left = 0
        right = len(nums) - 1
        mid = right // 2
        while left <= right:
            if nums[mid] == target:
                return mid
            #要加=号，为了保证mid与left或者right重合时也能进入left和right的变化过程
            if nums[left]<=nums[mid]:
                if nums[left]<= target< nums[mid]: #左右范围都要限制，才能确保target的位置
                    right = mid - 1
                else:
                    left = mid + 1
            if nums[mid] <= nums[right]:
                if nums[mid]<target <=nums[right]:
                    left = mid + 1
                else:
                    right = mid - 1
            mid = (right + left) // 2
        return -1 
```

## Question 34 在排序数组中查找元素的第一个和最后一个位置

> <font face='宋体'>&nbsp;给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。  
你的算法时间复杂度必须是 O(log n) 级别。
如果数组中不存在目标值，返回 [-1, -1]。
示例 1:
输入: nums = [5,7,7,8,8,10], target = 8  
输出: [3,4]
> * 解题思路  
> 二分查找，Log(N),没有难度，主要就是在第一次确定到`nums[mid] == target` 时，开始使用双指针向两边扩散，寻找边界，注意寻找边界时的细节。
</font>

```python
class Solution:
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if nums ==[]:
            return [-1,-1]
        mid = -1
        left = 0
        right = len(nums) - 1
        lindex = -1
        rindex = -1
        while left <= right:
            mid = (right + left) // 2
            if nums[mid] == target:
                lindex = rindex = mid
                #print(mid)
                while  lindex >= 0 and nums[lindex] == target :
                    lindex -= 1
                while  rindex <= len(nums) -1 and nums[rindex] == target:
                    rindex += 1
                if (lindex < 0 or nums[lindex] != target):
                    lindex+=1
                if (rindex > len(nums) -1 or nums[rindex] != target):
                    rindex -= 1
                return [lindex,rindex]
            if target < nums[mid]:
                right = mid -1
            if target > nums[mid]:
                left = mid + 1
        return [lindex,rindex]
```

## Question 35 搜索插入位置

> <font face='宋体'>给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。你可以假设数组中无重复元素。  
示例 1:
输入: [1,3,5,6], 5
输出: 2
示例 2:
输入: [1,3,5,6], 2
输出: 1
> * 解题思路  
> 二分查找，Log(N),注意元素不存在时的输出位置，mid向下取整，所以最后会偏向left，最后输出left位置
</font>

```python
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        if nums == []:
            return 0
        if target > nums[len(nums)-1]:
            return len(nums)
        if target < nums[0]:
            return 0
        mid = (len(nums)-1) // 2
        left = 0
        right = len(nums) - 1
        #注意为什么匹配不到元素时返回left
        while left <= right:
            print(left,right, mid)
            if target == nums[mid]:
                return mid
            if target < nums[mid]:
                right = mid - 1
            if target > nums[mid]:
                left = mid + 1
            mid = (right + left) // 2
        return left #最后的插入位置，向下取整的，所以最后left为mid+1，mid总是最后等于left
```


##  Question 48 旋转图像

> <font face='宋体'>给定一个 n × n 的二维矩阵表示一个图像。
将图像顺时针旋转 90 度。
说明： 
你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。  
示例 1:
给定 matrix = 
[  
  [1,2,3],  
  [4,5,6],  
  [7,8,9]
],  
原地旋转输入矩阵，使其变为:
[  
  [7,4,1],  
  [8,5,2],  
  [9,6,3]
]
> * 解题思路
> 将顺时针旋转90转化为先转置再左右对调，同样，对于其他的旋转也可以转换为基本的转置，上下左右对调等，需要思考。
</font>

```python
class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        """
        Do not return anything, modify matrix in-place instead.
        """
        #思考其他的旋转方法，分步为基本的转置或者左右上下对调
        if matrix == []:
            return
        N = len(matrix[0])
        if N == 1:
            return
        for i in range(N-1):
            for j in range(i+1,N):
                temp = matrix[i][j]
                matrix[i][j] = matrix[j][i]
                matrix[j][i] = temp 
        for i in range(N):
            matrix[i].reverse()
```
##  Question 41 缺失的第一个正数

> <font face='宋体'> 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。  
> 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。  
> 示例 1:
> 输入: [1,2,0]
> 输出: 3
> 示例 2:
> 输入: [3,4,-1,1]
> 输出: 2
>
>  * 解题思路
>  由于时间复杂度O(n)所以不能排序，所以考虑哈希，但是为常数空间，所以只能在原来的数组上进行hash，即将下标为a[i]的值的数变为负数，扫描一遍之后，第一个不为负数的数a[j]对应的下标j就是所求的值。(代码实现时需要预处理和将值 - 1来对应下标)
>  </font>

```python
class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        N = len(nums)
        if nums==[]:
            return 1
        for index in range(N):
            if nums[index] <= 0 or nums[index] > N:
                nums[index] = N+1

        for index in range(N):
            #print(index)
            """
            加abs是因为在将数变变相反数时，前面的N+1标志可能在之前被取反了
            nums[abs(nums[index])-1]是为了防止有相同的数，在取相反数之前先判断
            位置上是否已经取反，如果取过反就不再重复取反，否则可能又变正
            """
            if abs(nums[index]) != N+1 and nums[abs(nums[index])-1]> 0:
                #print(index,nums[index]-1,nums[nums[index]-1])
                nums[abs(nums[index])-1] = -nums[abs(nums[index])-1]
        print(nums)
        for index in range(N):
            if nums[index] > 0 :#存在正数时，正数的下标+1即为答案
                return index+1
            elif index == N-1:#当全负时，即数组由1到N组成
                return N+1

```
[返回顶部](#top)