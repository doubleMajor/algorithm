# 분수의 덧셈
##### https://school.programmers.co.kr/learn/courses/30/lessons/120808

## 문제 설명
>Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
<br>You may assume that each input would have exactly one solution, and you may not use the same element twice.
<br>You can return the answer in any order.
<br><br>Example 1:
<br>Input: nums = [2,7,11,15], target = 9
<br>Output: [0,1]
<br>Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
<br><br>Example 2:
<br>Input: nums = [3,2,4], target = 6
<br>Output: [1,2]
<br><br>Example 3:
<br>Input: nums = [3,3], target = 6
<br>Output: [0,1]
<br><br>Constraints:
<br>2 <= nums.length <= 104
<br>-109 <= nums[i] <= 109
<br>-109 <= target <= 109
<br><br>Only one valid answer exists.
---
### 최종 풀이 - TRY 1
![img.png](1week_leetcode_result.png)
~~~java
class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {
            numer1 * denom2 + numer2 * denom1, 
            denom1 * denom2
        };
        
        int divi = calcDivisor(answer[0], answer[1]);
        
        answer[0] /= divi;
        answer[1] /= divi;
        
        return answer;
    }
    
    private int calcDivisor(int a, int b) {
        int maxDivisors = 1;
        
        for (int i = a; i > 1; i--) {
            if (a % i == 0 && b % i == 0 && maxDivisors % i > 0) {
                maxDivisors *= i;
            }        
        }
        
        return maxDivisors;
    }
}
~~~
---
### TRY 1
- 성공
~~~java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < nums.length ; i++) {
            int n = nums[i];
            int gap = target - n;
            Integer gapIdx = map.get(gap);

            if (gapIdx != null) {
                return new int[]{gapIdx, i};
            } else {
                map.put(n, i);
            }
        }

        return null;
    }
}
~~~