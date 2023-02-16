package Main.programmers;

import java.util.*;


public class Print {
    public static void main(String[] args) {
        Print print = new Print();
        int[] priorites = {1,1,9,1,1,1};
        int location = 1;
        int result = print.solution(priorites,location);
        System.out.println(result);
    }

    public int solution(int[] priorities, int location) {
        int cnt = 0;
        PriorityQueue<Integer> priorityQueueHighest = new PriorityQueue<>(Collections.reverseOrder());
        Queue<jQueue> printer = new LinkedList<>();

        for(int i=0; i<priorities.length;i++){
            priorityQueueHighest.offer(priorities[i]);
            printer.offer(new jQueue(i,priorities[i]));
        }

        while (!priorityQueueHighest.isEmpty()&&!printer.isEmpty()){
            jQueue jQueue = printer.poll();
            if(jQueue.priority == priorityQueueHighest.peek()){
                priorityQueueHighest.poll();
                cnt++;
                if(jQueue.index ==location) break;
            } else {
                printer.poll();
                printer.offer(jQueue);
            }
        }
        return cnt;
    }

    public class jQueue{
        int index;
        int priority;

        public jQueue(int index, int priority){
            this.index = index;
            this.priority = priority;
        }
    }


}
