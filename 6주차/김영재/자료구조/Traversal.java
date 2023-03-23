
import java.util.*;
import java.util.stream.Collectors;

//순회 알고리즘
class Traversal {
    record Node(int val, Node left, Node right) {
        Node(int val) {
            this(val, null, null);
        }
        Node(int val, Node left) {
            this(val, left, null);
        }

        static Node exampleCreate() {
            Node left = new Node(5, new Node(3), new Node(15, new Node(10), new Node(17)));
            Node right = new Node(50, new Node(30, null, new Node(40)));
            return new Node(20, left, right);
        }
    }
    public void preTraversal(Node node, List<String> list) {
        //전위 순회
        list.add(String.valueOf(node.val));
        if(node.left() != null) preTraversal(node.left(), list);
        if(node.right() != null) preTraversal(node.right(), list);

        //  [ 20, 5, 3, 15, 10, 17, 50, 30, 40 ]

    }
    public void inorderTraversal(Node node, List<String> list) {
        //중위 순회
        if(node.left() != null) inorderTraversal(node.left(), list);
        list.add(String.valueOf(node.val));
        if(node.right() != null) inorderTraversal(node.right(), list);

        //  [ 3, 5, 10, 15, 17, 20, 30, 40, 50 ]
    }
    public void postTraversal(Node node, List<String> list) {
        //후위 순회
        if(node.left() != null) postTraversal(node.left(), list);
        if(node.right() != null) postTraversal(node.right(), list);
        list.add(String.valueOf(node.val));
        //  [ 3, 10, 17, 15, 5, 40, 30, 50, 20 ]
    }


    public static void main(String[] args) {
        Node node = Node.exampleCreate();
        Traversal traversal = new Traversal();
        List<String> preTraversal = new ArrayList<>();
        List<String> inorderTraversal = new ArrayList<>();
        List<String> postTraversal = new ArrayList<>();

        traversal.preTraversal(node, preTraversal);
        traversal.inorderTraversal(node, inorderTraversal);
        traversal.postTraversal(node, postTraversal);

        System.out.println("preTraversal : " + preTraversal.stream().map(String::valueOf).collect(Collectors.joining(", ", " [ ", " ] ")));
        System.out.println("inorderTraversal : " + inorderTraversal.stream().map(String::valueOf).collect(Collectors.joining(", ", " [ ", " ] ")));
        System.out.println("postTraversal : " + postTraversal.stream().map(String::valueOf).collect(Collectors.joining(", ", " [ ", " ] ")));
    }

}