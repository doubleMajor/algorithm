/**
 * <pre>
 * 티켓
 * </pre>
 *
 * @author shin-wonho
 * @see
 * @version 1.0
 * @since 2023/02/16
 *
 * //2073. Time Needed to Buy Tickets
 * //https://leetcode.com/problems/time-needed-to-buy-tickets/
 *
 *
 * There are n people in a line queuing to buy tickets, where the 0th person is at the front of the line and the (n - 1)th person is at the back of the line.
 * You are given a 0-indexed integer array tickets of length n where the number of tickets that the ith person would like to buy is tickets[i].
 * Each person takes exactly 1 second to buy a ticket. A person can only buy 1 ticket at a time and has to go back to the end of the line (which happens instantaneously) in order to buy more tickets. If a person does not have any tickets left to buy, the person will leave the line.
 * Return the time taken for the person at position k (0-indexed) to finish buying tickets.
 *
 * n명의 사람들이 티켓을 사기 위해 줄을 서서 기다리고 있는데, 0번째 사람이 줄의 맨 앞에 있고 (n - 1)번째 사람이 줄의 맨 뒤에 있습니다.
 * i번째 사람이 구매하려는 티켓의 수가 ticket[i]인 길이 n의 0 인덱스 정수 배열 티켓이 제공됩니다.
 * 한 사람이 표를 사는 데 정확히 1초가 걸립니다. 한 번에 한 장의 표만 살 수 있고 더 많은 표를 사려면 줄의 끝(즉시 발생)으로 돌아가야 합니다. 사람이 살 티켓이 남아 있지 않으면 그 사람은 줄을 떠날 것입니다.
 * 위치 k(인덱스 0)에 있는 사람이 티켓 구매를 완료하는 데 걸린 시간을 반환합니다.
 *
 * 길이 n 짜리 티켓들이 주어진다. i번째 사람은 배열에 i번째 만큼의 티켓 수를 사고 싶다. 사는데 1초 걸리고 사면 맨 뒤로 간다. 다 사면 집 간다.
 * 주어진 k번째 사람이 티켓을 사는데 걸리는 시간은 ?
 *
 * 제한사항
 * 1 <= tickets.length <= 105
 * 1 <= tickets[i] <= 105
 * 0 <= k < tickets.length
 *
 * 입출력 예
 * tickets = [2,6,3,4,5], k = 2
 * Output: 12
 *
 * tickets = [1,1,1,1,1], k = 2
 * Output: 5
 *
 * tickets = [1,1,1,1,1], k = 0
 * Output: 1
 */

public class Tickets {

    public static void main(String[] args) {
        Tickets tickets = new Tickets();
        int[] ticket = {2, 6, 3, 4, 5};
        int k = 2;

        System.out.println(tickets.timeRequiredToBuy(ticket, k));
    }

    public int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;

        while(true) {

            for (int i = 0; i < tickets.length; i++) {

                if (tickets[i]-- >= 1) {
                    time++;
                }

                if (tickets[k] == 0) {
                    return time;
                }
            }
        }
    }
}
