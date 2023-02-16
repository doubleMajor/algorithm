package algorithm.leetcod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

/**
 * n 명의 사람이 티켓을 사려고 줄을 설 때 index 0 번의 사람이 제일 앞에 서있는 사람이고 (n-1)명의 사람이 그 뒤로 줄을 서있는 상황이다
 * 0-indexed 숫자배열 tickets 가 주어지고 i번째 사람은 tickets[i] 값 만큼 티켓을 구매한다고 한다
 * 각 사람은 티켓을 구매할 때 한번에 한장씩만 살 수 있고 한장 구매에 1초가 걸린다고 한다.
 * 티켓을 한장 구매하고도 더 구매해야하는 사람이면 줄의 맨뒤로 돌아가는 상황이고 티켓을 다 구매한 사람은 줄을 더이상 서지 않고 떠난다고 한다
 * 이러한 티켓팅 상황에서 index k의 사람이 티켓을 전부 구매하는데 걸리는 시간을 구하는 문제이다
 */


public class TimeNeededtoBuyTickets {

    @Test
    public void timeNeedToBuyTicket_Test() {
        /*
         * tickets = [2,3,2], k = 2
         * Output: 6
         * */
        int[] tickets = new int[]{2, 3, 2};
        int[] tickets2 = new int[]{5,1,1,1};
        int k = 2;
        int k2 = 0;

        Solution solution = new Solution();
        int timeRequiredToBuy = solution.timeRequiredToBuy(tickets, k);
        int timeRequiredToBuy2 = solution.timeRequiredToBuy(tickets2, k2);
        int timeRequiredToBuy3 = solution.timeRequiredToBuy2(tickets2, k2);

        Assertions.assertEquals(6, timeRequiredToBuy);
        Assertions.assertEquals(8, timeRequiredToBuy2);
        Assertions.assertEquals(8, timeRequiredToBuy3);

    }
}


class Solution {


    public int timeRequiredToBuy2(int[] t, int k) { // 1
        return IntStream.range(0, t.length).map(i -> Math.min(t[i], i > k ? t[k] - 1 : t[k])).sum();
    }
    protected int timeRequiredToBuy(int[] tickets, int k) {
        int timeRequiredToBuy = 0;
        int index = 0;
        while (tickets[k] != 0) {
            if (index > tickets.length - 1) {
                index = 0;
            }

            if (tickets[index] != 0) {
                tickets[index]--;
                timeRequiredToBuy++;
                if (tickets[k] == 0 && index == k) {
                    break;
                }
            }
                index ++;
        }
        return timeRequiredToBuy;
    }
}