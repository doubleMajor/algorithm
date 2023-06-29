class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

/**
 * 9. palindrome number
 */
public class Solution {
    public boolean isPalindrome(int x) {
        String str = Integer.toString(x);
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }
}

/**
 * 21. Merge two Sorted List
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode workingNodeList = new ListNode();
        ListNode answer = workingNodeList;

        while (list1 != null && list2 != null) {

            int val1 = list1.val;
            int val2 = list2.val;

            if (val1 <= val2) {
                workingNodeList.next = list1;
                list1 = list1.next;
            } else {
                workingNodeList.next = list2;
                list2 = list2.next;
            }

            workingNodeList = workingNodeList.next;
        }

        if (list1 != null) {
            workingNodeList.next = list1;
        } else if (list2 != null) {
            workingNodeList.next = list2;
        }

        return answer.next;
    }
}