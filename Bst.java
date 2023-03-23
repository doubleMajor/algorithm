package BST;

import TreeTraversal.BFS;
import base.Node;

class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    public void insert(int val) {
        root = insertHelper(root, val);
    }

    private Node insertHelper(Node root, int val) {
        if (root == null) {
            root = new Node(val);
            return root;
        }

        if (val < root.getData()) {
            root.setLeft(insertHelper(root.getLeft(), val));
        } else if (val > root.getData()) {
            root.setRight(insertHelper(root.getRight(), val));
        }

        return root;
    }

    public boolean search(int val) {
        return searchHelper(root, val);
    }

    private boolean searchHelper(Node root, int val) {
        if (root == null) {
            return false;
        }

        if (val == root.getData()) {
            return true;
        } else if (val < root.getData()) {
            return searchHelper(root.getLeft(), val);
        } else {
            return searchHelper(root.getRight(), val);
        }
    }

    // 이진탐색트리 삭제
    public void delete(int val) {
        root = deleteHelper(root, val);
    }

    private Node deleteHelper(Node root, int val) {
        if (root == null) {
            return root;
        }

        if (val < root.getData()) {
            root.setLeft(deleteHelper(root.getLeft(), val));
        } else if (val > root.getData()) {
            root.setRight(deleteHelper(root.getRight(), val));
        } else {
            // 자식이 없는 경우
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            }

            // 자식이 하나인 경우
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            // 자식이 둘인 경우
            root.setData(minValue(root.getRight()));
            root.setRight(deleteHelper(root.getRight(), root.getData()));
        }

        return root;
    }

    private int minValue(Node root) {
        int minv = root.getData();
        while (root.getLeft() != null) {
            minv = root.getLeft().getData();
            root = root.getLeft();
        }
        return minv;
    }
}

