package 이진트리;

class Node<T> {
    T data;
    Node<T> left;
    Node<T> parent;
    Node<T> right;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {
        this.data = data;
        this.parent = parent;
    }
}
