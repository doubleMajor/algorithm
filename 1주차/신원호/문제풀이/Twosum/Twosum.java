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
 * 접근 1 :
 * 주어진 배열을 두 번 순회하면서 각 수의 합이 target이 되는 수를 찾는다.
 *
 * 알게된 점 :
 * 1. 시간 복잡도
 * 배열의 길이가 2이상 10^4이하이므로, 배열을 두 번 순회하는 것은 시간 복잡도가 O(n^2)이다.
 * 시간복잡도나 공간복잡도에 대한 제한이 없다면, 배열을 두 번 순회하는 방법이 가장 간단하다.
 * 배열을 두 번 순회하는 방법은 시간 복잡도가 O(n^2)이므로, 더 적은 시간 복잡도를 가지는 방법을 찾아야 한다.
 *
 * 2. 두 배열을 루프로 비교하는 경우
 * 두 배열을 비교할 때 인덱스로 나올 수 있는 조합은 루프일 때 다음과 같다.
 * (0,1), (0,2), (0,3), (0,4), (0,5)
 * (1,0), (1,2), (1,3), (1,4), (1,5)
 * (2,0), (2,1), (2,3), (2,4), (2,5)
 * (3,0), (3,1), (3,2), (3,4), (3,5)
 * (4,0), (4,1), (4,2), (4,3), (4,5)
 * (5,0), (5,1), (5,2), (5,3), (5,4)
 *
 * 2-1.
 * 제약조건에 따르면, 같은 인덱스에 있는 수는 한 번만 사용해야 한다.
 * 따라서,두 배열을 비교할 때는 루프를 돌 때마다 두 인덱스가 같은지 확인하고, 같은 경우는 무시하면 된다.
 * (하지만 제약조건이 없다면 두 경우 모두 비교해야 한다.)
 *
 * 2-2.
 * 동일한 배열을 비교할때 (0,1)을 비교했으므로 (1,0)은 비교하지 않아도 된다.
 * (0,1),(0,2),(0,3),(0,4),(0,5) 를 비교했으므로
 * (1,0),(2,0),(3,0),(4,0),(5,0) 는 비교하지 않아도 된다.
 * 그러므로 j = i + 1 부터 시작하면 된다.
 *
 * 공식 해설 :
 * https://leetcode.com/problems/two-sum/solutions/127810/two-sum/?orderBy=most_votes
 */
public class Twosum {
    public static void main(String[] args) {
        int[] nums = {3, 2, 4};
        int target = 6;

        Twosum twosum = new Twosum();
        int[] answer = twosum.twoSum1(nums, target);

        System.out.println("answer : " + answer[0] + ", " + answer[1]);
    }

    /**
     * 1. 두 수의 합이 target이 되는 Array를 return 하라
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {
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
