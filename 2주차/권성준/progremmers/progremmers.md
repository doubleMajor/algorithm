
//테스트 케이스를 통해 우선순위가 가장 높은 것 부터 실행이 되도록 저장
public int solution(int[] priorities, int location) {
        int temp = priorities[0];
        int tempI = location;

        for(int i = 0; i < priorities.length; i++) {
        if(temp < priorities[i]) {
        temp = priorities[i];
        tempI = tempI + priorities.length-i+1;
        }
        if(tempI > priorities.length) {
        tempI = tempI-priorities.length;
        }
        }

        return tempI;
        }


//우선순위큐 이용
//하지만 배열의 순번이 아닌 값을 이용해 찾으려고 해 똑같은 값이 존재할경우 다름
// 우선순위 큐 참고 사이트 : https://velog.io/@gillog/Java-Priority-Queue%EC%9A%B0%EC%84%A0-%EC%88%9C%EC%9C%84-%ED%81%90
        import java.util.PriorityQueue;
        import java.util.Collections;
public int solution(int[] priorities, int location) {
        //큰수가 우선인 우선순위
        PriorityQueue<Integer> priorityQueueHighest = new PriorityQueue<>(Collections.reverseOrder());

        int result = 0;
        for(int n : priorities){
        priorityQueueHighest.add(n);
        }
        Integer loca = priorities[location];

        while(!priorityQueueHighest.isEmpty()){
        if((int)priorityQueueHighest.peek() == loca){
        break;
        } else {
        priorityQueueHighest.poll();
        result++;
        }
        }

        result = result + 1;

        return result;
        }

        import java.util.PriorityQueue;
        import java.util.Collections;
class Solution {
    public int solution(int[] priorities, int location) {
        //큰수가 우선인 우선순위 큐
        PriorityQueue<Integer> priorityQueueHighest = new PriorityQueue<>(Collections.reverseOrder());

        int result = 0;
        for(int n : priorities){
            priorityQueueHighest.add(n);
        }

        while(!priorityQueueHighest.isEmpty()){
            for(int i = 0; i < priorities.length; i++) {
                if((int)priorityQueueHighest.peek() == priorities[i]){
                    priorityQueueHighest.poll();
                    result++;
                    if(i == location) {
                        return result;
                    }
                }

            }
        }

        return result;
    }
}








릿코드

class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int result = 0;
        for(int i = 0; i < tickets.length; i++){
            if(tickets[i] <= tickets[k]){
                result = result + tickets[i];
            }
            else {
                result = result + tickets[k];
            }
            if(i > k && tickets[i] >= tickets[k]) {
                result--;
            }
        }
        return result;
    }
}
