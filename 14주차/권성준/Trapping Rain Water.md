https://leetcode.com/problems/trapping-rain-water/description/

```java
class Solution {
    public int trap(int[] height) {
        int answer = 0; 
        int left = 0; 
        int right = height.length - 1;
        int leftMax = 0;  // 왼쪽 현재까지 최대 높이
        int rightMax = 0; // 오른쪽 현재까지 최대 높이
        while(left < right){
            leftMax = (height[left] < leftMax) ? leftMax : height[left];
            rightMax = (height[right] < rightMax) ? rightMax : height[right]; 
            
            if (leftMax < rightMax) {
                int diff = leftMax - height[left];
                answer += diff;
                left++;
            } else {
                int diff = rightMax - height[right];
                answer += diff;
                right--;
            }
        }
        return answer;
    }
}
```

![img.png](img.png)