/**
 * Problem :
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * Constraints:
 *  2 <= nums.length <= 10^4
 *  -10^9 <= nums[i] <= 10^9
 *  -10^9 <= target <= 10^9
 *  Only one valid answer exists.
 *
 *  길이는 2이상 10^4이하
 *  배열의 값은 -10^9이상 10^9이하
 *  target은 -10^9이상 10^9이하
 *  답은 하나만 존재
 */

/**
 * 해설 :
 * 접근 1 : 주어진 배열을 두 번 순회하면서 각 수의 합이 target이 되는 수를 찾는다.
 *
 *
 */
public class Twosum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;

        Twosum twosum = new Twosum();
        int[] answer = twosum.twoSum(nums, target);

        System.out.println("answer : " + answer[0] + ", " + answer[1]);
    }

    /**
     * 1. 두 수의 합이 target이 되는 Array를 return 하라
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] answerArr = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length && i != j; j++) {

                if (nums[i] + nums[j] == target) {
                    answerArr[0] = i;asdf
                    answerArr[1] = j;

                    return answerArr;
                }
            }
        }

        return answerArr;
    }
}
