```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int val) {
        this.root = insert(this.root, val);
    }

    private TreeNode insert(TreeNode node, int val) {
        if (node == null) {
            node = new TreeNode(val);
        } else {
            if (val <= node.val) {
                node.left = insert(node.left, val);
            } else {
                node.right = insert(node.right, val);
            }
        }
        return node;
    }

    public boolean search(int val) {
        return search(this.root, val);
    }

    private boolean search(TreeNode node, int val) {
        if (node == null) {
            return false;
        }
        if (node.val == val) {
            return true;
        }
        if (val < node.val) {
            return search(node.left, val);
        } else {
            return search(node.right, val);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.insert(1);
        tree.insert(9);

        System.out.println(tree.search(5)); // true
        System.out.println(tree.search(8)); // false
    }
}
```

```java
public class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int data) {
        Node newNode = new Node(data);

        if (root == null) {
            root = newNode;
        } else {
            insertNode(root, newNode);
        }
    }

    private void insertNode(Node currentNode, Node newNode) {
        if (currentNode.left == null) {
            currentNode.left = newNode;
        } else if (currentNode.right == null) {
            currentNode.right = newNode;
        } else {
            insertNode(currentNode.left, newNode);
        }
    }
}
```