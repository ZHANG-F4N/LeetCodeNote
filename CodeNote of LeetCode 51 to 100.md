# Code-note of LeetCode 51 to 100

[TOC]

## Question 53 最大子序和

给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

```java
输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
输出：6
解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
```

---

解题思路：

动态规划的是首先对数组进行遍历，当前最大连续子序列和为 sum，结果为 ans

- 如果 sum > 0，则说明 sum 对结果有增益效果，则 sum 保留并加上当前遍历数字
- 如果 sum <= 0，则说明 sum 对结果无增益效果，需要舍弃，则 sum 直接更新为当前遍历数字
- 每次比较 sum 和 ans的大小，将最大值置为ans，遍历结束返回结果
- 时间复杂度：O(n)

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int MAXArray = nums[0];
        for (int i = 1;i<nums.length;i++){
            if(nums[i-1] > 0){
                nums[i]=nums[i]+nums[i-1];
            }
            if (MAXArray < nums[i]){
                MAXArray = nums[i];
            }
        }
        return MAXArray;
    }
}
```





## Question 56 合并区间

以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。

```java
输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
输出：[[1,6],[8,10],[15,18]]
解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```

---

解题思路：

首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：

- 如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；

- 否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值。

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        
        ArrayList<int[]> List = new ArrayList();
        List.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= ( List.get(List.size() - 1))[1]) {
                ( List.get(List.size() - 1))[1] = Math.max(( List.get(List.size() - 1))[1], intervals[i][1]);
            } else {
                List.add(intervals[i]);
            }
        }
        return List.toArray(new int[List.size()][]);
    }
}
```



## Question 58 最后一个单词的长度



给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。

单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。

```s
输入：s = "Hello World"
输出：5
```

---

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int ans = 0;
        String str = s.trim();
        for(int i = str.length()-1;i >= 0;i--){
            if ( str.charAt(i) == ' '){
                break;
            }
            ans++;
        }
        System.out.println(ans);
        return ans;
    }
}
```



## Question 66 加一

给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。

最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。

你可以假设除了整数 0 之外，这个整数不会以零开头。

```java
输入：digits = [1,2,3]
输出：[1,2,4]
解释：输入数组表示数字 123。
```

---

```java
for (int i = digits.length - 1; i >= 0; i--) {
    digits[i]++;
    digits[i] %= 10;

    if(digits[i]!=0){
        return digits;
    }
}
int[] ints = new int[digits.length+1];
ints[0] = 1;
return ints;
```

