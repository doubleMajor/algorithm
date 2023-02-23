# 202. Happy Number
```java
package algorithm.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class HappyNumber {
    @Test
    void happyNumber_Test(){
        int n = 1;
//        int n = 1999999999;
        Solution solution = new Solution();
        boolean answer = solution.isHappy(n);
        Assertions.assertTrue(answer);
    }

        class Solution {
            public boolean isHappy(int n) {
                if(n==1) return true;
                int tmp = n;
                Set<Integer> seen = new HashSet<>();

                Deque<Integer> tmpArray = new ArrayDeque<>();

                while (tmp != 1 && !seen.contains(tmp)) {
                    seen.add(tmp);

                    while (tmp > 0) {
                        tmpArray.addLast(tmp % 10);
                        tmp /= 10;
                    }

                    tmp = tmpArray.stream().mapToInt(integer -> (int) Math.pow(integer, 2)).sum();

                    if (tmp == 1)
                        return true;

                    tmpArray.clear();
                }

                return false;
            }
        }
}

```
# 처참! 처참! 처참!
![img.png](img.png)