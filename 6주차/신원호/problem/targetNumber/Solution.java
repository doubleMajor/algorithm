package problem.targetNumber;

/**
 * 섬보다 쉬운 문제
 * 섬은 둘러싼 네 방향을 탐색해야 했지만
 * 이 문제는 더하기 , 빼기 두 가지 선택지만 다르게 하여 dfs를 탐색하면 된다.
 *
 */
public class Solution {
    int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);

        return answer;
    }
    
    private void dfs(int[] numbers, int target, int index, int sum) {
        if (index == numbers.length) {
            if (sum == target) {
                answer++;
            }
            return;
        }
        
        dfs(numbers, target, index + 1, sum + numbers[index]);
        dfs(numbers, target, index + 1, sum - numbers[index]);
    }
}