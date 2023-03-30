//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 접근 1. 리스트에 담아서 가운데를 찾고, 가운데 기준으로 왼쪽, 오른쪽을 각각 root의 왼쪽, 오른쪽으로 BST를 만든다.
// * -> 서브 트리의 깊이가 안 맞는 불상사가 일어남..
// *
// * 접근 2. avl 트리를 사용해서 위랑 똑같이
// * -> 개같은거 돌릴때 높이 따윈 없다고 에러남..
// *
// * 접근 3. 계속 반으로 갈라서 왼쪽, 오른쪽을 각각 root의 왼쪽, 오른쪽으로 BST를 만든다.
// * -> 길이가 좌,우가 동일하므로 균형 트리가 생긴다고 생각했고, 이미 정렬되어 있으므로
// *   BST 트리가 생긴다.
// *
// * 접근 4. 3번에 성능 최적화
// */
//
//class Solution {
//    class Solution {
//        public static void main(String[] args) {
//            ListNode head = new ListNode(0);
//            ListNode node1 = new ListNode(1);
//            ListNode node2 = new ListNode(2);
//            ListNode node3 = new ListNode(3);
//            ListNode node4 = new ListNode(4);
//            ListNode node5 = new ListNode(5);
//
//            head.next = node1;
//            node1.next = node2;
//            node2.next = node3;
//            node3.next = node4;
//            node4.next = node5;
//
//            Solution solution = new Solution();
//            TreeNode treeNode = solution.sortedListToBST(head);
//            int val = treeNode.val;
//        }
//
//        public TreeNode sortedListToBST(ListNode head) {
//            if (head == null) {
//                return null;
//            }
//
//            List<Integer> headList = new ArrayList<>();
//
//            headList.add(head.val);
//            ListNode next = head.next;
//
//            while(next != null) {
//                headList.add(next.val);
//                next = next.next;
//            }
//
//            // 가운데 인덱스
//            int middleIndex = headList.size() / 2;
//
//            TreeNode root = new TreeNode(headList.get(middleIndex));
//            headList.remove(middleIndex);
//
//            for (int i = 0; i < headList.size(); i++) {
//                root = insertBst(root, headList.get(i));
//            }
//
//            return root;
//        }
//
//        public TreeNode insertBst(TreeNode node, int val) {
//            if (node == null) {
//                return new TreeNode(val);
//            }
//
//            if (node.val < val) {
//                node.right = insertBst(node.right, val);
//            } else if (node.val > val) {
//                node.left = insertBst(node.left, val);
//            }
//
//            return node;
//        }
//    }
//========================================================================================================================
//import java.util.ArrayList;
//import java.util.List;
//
//    class Solution {
//        public static void main(String[] args) {
//            ListNode head = new ListNode(0);
//            ListNode node1 = new ListNode(1);
//            ListNode node2 = new ListNode(2);
//            ListNode node3 = new ListNode(3);
//            ListNode node4 = new ListNode(4);
//            ListNode node5 = new ListNode(5);
//
//            head.next = node1;
//            node1.next = node2;
//            node2.next = node3;
//            node3.next = node4;
//            node4.next = node5;
//
//            Solution solution = new Solution();
//            TreeNode treeNode = solution.sortedListToBST(head);
//            int val = treeNode.val;
//        }
//
//        public TreeNode sortedListToBST(ListNode head) {
//            if (head == null) {
//                return null;
//            }
//
//            List<Integer> headList = new ArrayList<>();
//
//            headList.add(head.val);
//            ListNode next = head.next;
//
//            while (next != null) {
//                headList.add(next.val);
//                next = next.next;
//            }
//
//            // 가운데 인덱스
//            int middleIndex = headList.size() / 2;
//
//            TreeNode root = new TreeNode(headList.get(middleIndex));
//            root.height++;
//
//            headList.remove(middleIndex);
//
//            for (int i = 0; i < headList.size(); i++) {
//                root = insertBst(root, headList.get(i));
//            }
//
//            return root;
//        }
//
//        public TreeNode insertBst(TreeNode node, int val) {
//            if (node == null) {
//                return new TreeNode(val);
//            }
//
//            if (node.val < val) {
//                node.right = insertBst(node.right, val);
//            } else if (node.val > val) {
//                node.left = insertBst(node.left, val);
//            }
//
//            node.height = 1 + Math.max(height(node.left), height(node.right));
//
//            int balance = getBalance(node);
//
//            if (balance > 1 && val < node.left.val)
//                return rightRotate(node);
//
//            if (balance < -1 && val > node.right.val)
//                return leftRotate(node);
//
//            if (balance > 1 && val > node.left.val) {
//                node.left = leftRotate(node.left);
//                return rightRotate(node);
//            }
//
//            if (balance < -1 && val < node.right.val) {
//                node.right = rightRotate(node.right);
//                return leftRotate(node);
//            }
//
//            return node;
//        }
//
//        int height(TreeNode N) {
//            if (N == null)
//                return 0;
//            return N.height;
//        }
//
//        TreeNode rightRotate(TreeNode y) {
//            TreeNode x = y.left;
//            TreeNode T2 = x.right;
//
//            x.right = y;
//            y.left = T2;
//
//            y.height = Math.max(height(y.left), height(y.right)) + 1;
//            x.height = Math.max(height(x.left), height(x.right)) + 1;
//
//            return x;
//        }
//
//        TreeNode leftRotate(TreeNode x) {
//            TreeNode y = x.right;
//            TreeNode T2 = y.left;
//
//            y.left = x;
//            x.right = T2;
//
//            x.height = Math.max(height(x.left), height(x.right)) + 1;
//            y.height = Math.max(height(y.left), height(y.right)) + 1;
//
//            return y;
//        }
//
//        int getBalance(TreeNode N) {
//            if (N == null)
//                return 0;
//            return height(N.left) - height(N.right);
//        }
//    }
//}
//========================================================================================================================
//
//        import java.util.ArrayList;
//        import java.util.List;
//
///**
// * Given the head of a singly linked list where elements are sorted in ascending order
// * convert it to a height-balanced binary search tree.
// *
// * -10,-3,0,5,9
// */
//class Solution {
//    public static void main(String[] args) {
//        ListNode head = new ListNode(0);
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//
//        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//
//        Solution solution = new Solution();
//        TreeNode treeNode = solution.sortedListToBST(head);
//        int val = treeNode.val;
//    }
//
//    public TreeNode sortedListToBST(ListNode head) {
//        if (head == null) {
//            return null;
//        }
//
//        if (head.next == null) {
//            return new TreeNode(head.val);
//        }
//
//        List<Integer> list = new ArrayList<>();
//
//        while (head != null) {
//            list.add(head.val);
//            head = head.next;
//        }
//
//        return insertBst(list, 0, list.size() - 1);
//    }
//
//    /**
//     * BST에 노드를 삽입한다.
//     * @param list
//     * @param startIndex
//     * @param endIndex
//     * @return
//     */
//    public TreeNode insertBst(List list, int startIndex, int endIndex) {
//        if (startIndex > endIndex) {
//            return null;
//        }
//
//        int mid = (startIndex + endIndex) / 2;
//        TreeNode node = new TreeNode((int) list.get(mid));
//
//        node.left = insertBst(list, startIndex, mid - 1);
//        node.right = insertBst(list, mid + 1, endIndex);
//
//        return node;
//    }
//}