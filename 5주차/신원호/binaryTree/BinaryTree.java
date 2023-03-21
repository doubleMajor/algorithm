import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree(1);

        binaryTree.insert(2);
        binaryTree.insert(3);
        binaryTree.insert(4);
        binaryTree.insert(5);
        binaryTree.insert(6);
        binaryTree.insert(7);
        binaryTree.insert(8);
        binaryTree.insert(9);
        binaryTree.insert(10);
        binaryTree.insert(11);
        binaryTree.insert(12);
        binaryTree.insert(13);
        binaryTree.insert(14);
        binaryTree.insert(15);



        binaryTree.bfs(binaryTree);
        binaryTree.dfs(binaryTree);
    }

    private BinaryTree left;
    private BinaryTree right;
    private int value;

    public BinaryTree(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    // 이진트리 삽입
    public void insert(int value) {
        if (value < this.value) {
            if (this.left == null) {
                this.left = new BinaryTree(value);
            } else {
                this.left.insert(value);
            }
        } else {
            if (this.right == null) {
                this.right = new BinaryTree(value);
            } else {
                this.right.insert(value);
            }
        }
    }

    // 너비 우선 탐색
    public void bfs(BinaryTree root) {
        Queue<BinaryTree> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            BinaryTree poll = queue.poll();
            System.out.println(poll.getValue());

            if (poll.getLeft() != null) {
                queue.add(poll.getLeft());
            }

            if (poll.getRight() != null) {
                queue.add(poll.getRight());
            }
        }
    }

    // 깊이 우선 탐색
    public void dfs(BinaryTree root) {
        if (root == null) {
            return;
        }

        System.out.println(root.getValue());

        dfs(root.getLeft());
        dfs(root.getRight());
    }
}