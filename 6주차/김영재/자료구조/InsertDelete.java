
import java.util.*;

class InsertDelete {

    public static class Node {

        int val;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
        }
        Node(int val, Node left) {
            this.val = val;
            this.left = left;
        }
        Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        static Node exampleCreate() {
            Node left = new Node(5, new Node(3), new Node(15, new Node(10), new Node(17)));
            Node right = new Node(50, new Node(30, null, new Node(40)));
            return new Node(20, left, right);
        }
    }

    public void inorder(Node node, List<Integer> list) {
        //중위 순회
        if(node.left != null) inorder(node.left, list);
        list.add(node.val);
        if(node.right != null) inorder(node.right, list);

        //  [ 3, 5, 10, 15, 17, 20, 30, 40, 50 ]
    }

    public int insert(Node node, int newVal) {
        while(true) {
            if(node.val > newVal) {
                if(node.left == null) {
                    node.left = new Node(newVal);
                    return newVal;
                }
                node = node.left;
            }
            if(node.val < newVal) {
                if(node.right == null) {
                    node.right = new Node(newVal);
                    return newVal;
                }
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        Node node = Node.exampleCreate();
        List<Integer> list1 = new ArrayList<>();
        InsertDelete id = new InsertDelete();
        id.inorder(node, list1);
        System.out.println(list1);

        id.insert(node, 1);
        id.insert(node, 6);
        id.insert(node, 11);
        id.insert(node, 23);

        List<Integer> list2 = new ArrayList<>();
        id.inorder(node, list2);
        System.out.println(list2);
    }
}
