class Solution {
    public int trap(int[] height) {
        int arraySize = height.length;
        int maxIndex = 0;
        int water = 0;
        int leftMax = 0;
        int rightMax = 0;

        for (int i = 1; i < arraySize; i++) {
            if (height[i] > height[maxIndex]) {
                maxIndex = i;
            }
        }

        for (int i = 0; i < maxIndex; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            } else {
                water += leftMax - height[i];
            }
        }

        for (int i = arraySize - 1; i > maxIndex; i--) {
            if (height[i] > rightMax) {
                rightMax = height[i];
            } else {
                water += rightMax - height[i];
            }
        }

        return water;
    }
}