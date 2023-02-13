/* Problem leetcode2073
There are n people in a line queuing to buy tickets, where the 0th person is
at the front of the line and the (n - 1)th person is at the back of the line.
You are given a 0-indexed integer array tickets of length n where the number of tickets
that the ith person would like to buy is tickets[i].
Each person takes exactly 1 second to buy a ticket.
A person can only buy 1 ticket at a time and has to go back to the end of the line
(which happens instantaneously) in order to buy more tickets.
If a person does not have any tickets left to buy, the person will leave the line.
Return the time taken for the person at position k (0-indexed) to finish buying tickets.
*/
public class TimeNeededtoBuyTickets_2073(){
    public static void main(String[] args) {
        int[] tickets = {5, 1, 1, 1};
        int k = 0;

        TimeNeededtoBuyTickets_2073 timeNeededtoBuyTickets = new TimeNeededtoBuyTickets_2073();
        int answer = timeNeededtoBuyTickets.buyTickets(tickets, k);

        System.out.println("answer : " + answer[0] + ", " + answer[1]);
    }

    public int buyTickets(int[] tickets, int k){
        int ticketCnt = 0;
        int kcnt = tickets[k];

        Queue<Integer> ticketQueue = new LinkedList<>();
        for (int i=0; i<tickets.length; i++){
            ticketQueue.add(tickets[i]);
        }

        return result;
    }

}