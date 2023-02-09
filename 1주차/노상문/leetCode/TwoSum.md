```java


package algorithm.leetcod.twoSum;
/**
* 문제 :
* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
*
* You may assume that each input would have exactly one solution, and you may not use the same element twice.
*
* You can return the answer in any order.
*
* 조건 :
*
* 2 <= nums.length <= 10^4
*   nums의 길이는 2 이상  10000 이하
* -109 <= nums[i] <= 109
* 인덱스당 값은 -109이상  109이하
* -109 <= target <= 109
* target 값은 -109 이상 109 이하
*
* Can you come up with an algorithm that                is less than O(n2) time complexity?
*  => O(n^2) 시간 복잡도 미만으로 알고리즘을 만들 수 있니?
*
* 시간 복잡도 ( Time Complexity )
* "입력값의 변화에 따라 연산을 실행할 때, 연산 횟수에 비해 시간이
* */
  class Solution {
  public static void main(String[] args) {
  Solution solution = new Solution();
  int [] answer = solution.twoSum(new int[] {1,2,7,8},6);

       System.out.println("["+answer[0]+" , "+answer[1]+"]");

  }

  public int[] twoSum(int[] nums, int target) {
  int[] answerArr = new int[2];

       for (int i = 0; i < nums.length; i++) {
           for (int j = 0; j < nums.length && i != j; j++) {

               if (nums[i] + nums[j] == target) {
                   answerArr[0] = i;
                   answerArr[1] = j;

                   return answerArr;
               }
           }
       }

       return answerArr;
  }
  }
```