/**
 * 역순으로 주어진 두 개의 링크드 리스트를 더한 값
 *
 * 2 -> 4 -> 3 (342) +
 * 5 -> 6 -> 4 (465) =
 * 7 -> 0 -> 8 (807)
 *
 * 접근 1.
 * 두 리스트를 각각 순회하면서 한 자리씩 뽑아서 더한다.
 * 더 하다가 1의 자리숫자니까 10이 넘어가면 다음 자리에 더할 숫자를 표시하고 나머지들만 표현한다.
 * 마지막에 10이 넘어가면 노드에 추가해준다.
 *
 */
public class TwoList {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode answer = new ListNode(0);
        ListNode list1 = l1;
        ListNode list2 = l2;

        ListNode current = answer;

        int nextDigit = 0;

        while (list1 != null || list2 != null) {
            int x = (list1 != null) ? list1.val : 0;
            int y = (list2 != null) ? list2.val : 0;

            int sum = nextDigit + x + y;

            nextDigit = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;

            if (list1 != null) {
                list1 = list1.next;
            }

            if (list2 != null) {
                list2 = list2.next;
            }
        }

        if (nextDigit > 0) {
            current.next = new ListNode(nextDigit);
        }

        return answer.next;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
