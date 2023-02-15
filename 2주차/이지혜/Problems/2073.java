package Main.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class TimeNeededtoBuyTickets_2073 {

    public static void main(String[] args) {
        int[] tickets = {5, 1, 1, 1};
        int k = 0;

        TimeNeededtoBuyTickets_2073 timeNeededtoBuyTickets = new TimeNeededtoBuyTickets_2073();
        int answer = timeNeededtoBuyTickets.timeRequiredToBuy(tickets, k);

        System.out.println("answer : " + answer);
    }

    public int timeRequiredToBuy(int[] tickets, int k) {
        int totCnt = 0;
        while(tickets[k]>0){
            for(int i=0; i<tickets.length; i++) {
                if(tickets[i]>0){
                    totCnt++;
                    tickets[i]--;
                }
                if(tickets[k]==0){
                    break;
                }
            }
        }
        return totCnt;
    }
}