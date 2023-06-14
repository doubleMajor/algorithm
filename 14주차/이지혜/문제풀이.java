import static java.lang.Math.max;

public class 문제풀이 {

    public static void main(String[] args) {
        문제풀이 test = new 문제풀이();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height2 = {4,2,0,3,2,5};

        System.out.println(test.trap(height));
        System.out.println(test.trap(height2));
    }

    public int trap(int[] height) {
        if(height.length <= 1) {
            return 0;
        }
        int answer = 0;
        int leftTarget = 0;
        int rightTarget = height.length-1;
        int maxLeft = height[0];
        int maxRight = height[rightTarget];

        while(leftTarget < rightTarget){
            if(maxLeft < maxRight){
                leftTarget++;
                maxLeft = max(maxLeft,height[leftTarget]);
                answer += maxLeft - height[leftTarget];
            } else {
                rightTarget--;
                maxRight = max(maxRight,height[rightTarget]);
                answer += maxRight - height[rightTarget];
            }
        }


        return answer;
    }
}
