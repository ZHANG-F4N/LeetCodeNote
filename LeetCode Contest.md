# LeetCode Contest

[TOC]



## 20210911-2-心算挑战

「力扣挑战赛」心算项目的挑战比赛中，要求选手从 `N` 张卡牌中选出 `cnt` 张卡牌，若这 `cnt` 张卡牌数字总和为偶数，则选手成绩「有效」且得分为 `cnt` 张卡牌数字总和。
给定数组 `cards` 和 `cnt`，其中 `cards[i]` 表示第 `i` 张卡牌上的数字。 请帮参赛选手计算最大的有效得分。若不存在获取有效得分的卡牌方案，则返回 0。

**示例 1：**

> 输入：`cards = [1,2,8,9], cnt = 3`
>
> 输出：`18`
>
> 解释：选择数字为 1、8、9 的这三张卡牌，此时可获得最大的有效得分 1+8+9=18。

**示例 2：**

> 输入：`cards = [3,3,1], cnt = 1`
>
> 输出：`0`
>
> 解释：不存在获取有效得分的卡牌方案。

**提示：**

- `1 <= cnt <= cards.length <= 10^5`
- `1 <= cards[i] <= 1000`

```java
class Solution {
    public int maxmiumScore(int[] cards, int cnt) {
        Arrays.sort(cards);
        int ans = 0;
        int oddIndex = Integer.MAX_VALUE;
        int evenIndex = Integer.MAX_VALUE;
        for (int i = cards.length - 1; i >= cards.length - cnt; i--) {
            if (cards[i] % 2 == 0) {
                evenIndex = i;
            } else {
                oddIndex = i;
            }
            ans += cards[i];
        }
        if (ans % 2 == 0) {
            return ans;
        }
        int maxOdd = Integer.MAX_VALUE;
        int maxEven = Integer.MAX_VALUE;
        for (int i = 0; i < cards.length - cnt; i++) {
            if (cards[i] % 2 == 0) {
                maxEven = i;
            } else {
                maxOdd = i;
            }
        }
        
        int temp1 = 0;
        int temp2 = 0;
        if (maxEven != Integer.MAX_VALUE && oddIndex != Integer.MAX_VALUE) {
            temp1 = ans - cards[oddIndex] + cards[maxEven];
        }
        if (maxOdd != Integer.MAX_VALUE && evenIndex != Integer.MAX_VALUE) {
            temp2 = ans - cards[evenIndex] + cards[maxOdd];
        }
        ans = Math.max(temp1, temp2);
        ans = Math.max(0,ans);
       
        return ans;
    }
}
```

