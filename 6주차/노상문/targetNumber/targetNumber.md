```java
class Solution {
    public int solution(int[] numbers, int target) {
        int[] sum = new int[1];  // 현재까지 더한 숫자의 합을 저장하는 배열
        dfs(numbers, 0, sum, target);
        return sum[0];
    }

    private void dfs(int[] numbers, int index, int[] sum, int target) {
        if (index == numbers.length) {
            if (sum[0] == target) {
                sum[0]++;
            }
        } else {
            dfs(numbers, index+1, sum, target);  // 현재 인덱스의 숫자를 더하지 않는 경우
            sum[0] += numbers[index];  // 현재 인덱스의 숫자를 더하는 경우
            dfs(numbers, index+1, sum, target);
            sum[0] -= numbers[index];
        }
    }
}
```

-- 왜 틀리지...........ㅇ룬이ㅏ헐ㅇㄴ호;ㄴ랑ㅁ호ㅓ나오라ㅓㅗ