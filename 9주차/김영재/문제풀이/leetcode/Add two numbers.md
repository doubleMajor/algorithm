## 2. Add Two Numbers

### 간단 설명
> - 두 개의 리스트 노드의 값을 더하여 새로운 리스트 노드를 만드는 문제
> - 해당 문제는 과거에 푼 적이 있으나, 과거에 알고 있던 지식과 현재가 다르므로 새롭게 풀어 보았고, 두 로직 모두 공개하겠습니다.

### 리스트 노드
```java
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
```

### 첫 번째 정답

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        List<Integer> list1 = new ArrayList<>();
        while(l1 != null) {
            list1.add(l1.val);
            l1 = l1.next;
        }
        List<Integer> list2 = new ArrayList<>();
        while(l2 != null) {
            list2.add(l2.val);
            l2 = l2.next;
        }
        int len = list1.size() > list2.size() ? list1.size() : list2.size();
        List<Integer> list = new ArrayList<>();
        int sum = 0;
        for(int i=0; i<len; i++) {
            int a = list1.size() > i? Integer.parseInt(String.valueOf(list1.get(i))) : 0;
            int b = list2.size() > i? Integer.parseInt(String.valueOf(list2.get(i))) : 0;
            int abSum = a+b+sum;
            if(abSum >= 10) {
                sum = 1;
                abSum = abSum%10;
            } else {
                sum = 0;
            }
            list.add(abSum);
            if(sum == 1 && (len-1) == i) list.add(1);
        }

        ListNode newList = null;
        for(int i=1, listLen=list.size(); i<=listLen; i++) {
            if(newList == null) {
                newList = new ListNode(list.get(listLen-i));
            } else {
                newList = new ListNode(list.get(listLen-i), newList);
            }
        }
        return newList;
    }
}
```


![첫 번째 답](First_Answer.png)


```java

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
       ListNode result = new ListNode();
       ListNode cur = result;
       int digits = 0;
       while(l1 != null || l2 != null) {
           int sum = 0;
           if(l1 != null) {
               sum += l1.val;
               l1 = l1.next;
           }
           if(l2 != null) {
               sum += l2.val;
               l2 = l2.next;
           }
           if(digits >= 1) {
               sum += digits;
               digits = 0;
           }
           if(sum >= 10) {
               digits = sum/10;
               sum %= 10;
           }
           cur.next = new ListNode(sum);
           cur = cur.next;
       }
       if(digits >= 1) {
           cur.next = new ListNode(digits);
       }

       return result.next;
    }
}

```


![두 번째 답](Second_Answer.png)