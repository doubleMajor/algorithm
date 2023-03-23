package 이진트리;

public class Traversal {

    public void printPreOrder(Node node) {
        StringBuilder sb = new StringBuilder();

        System.out.println(sb);
    }

    private void preOrder(StringBuilder sb, Node node) {
        if (node == null) return;

        sb.append(node.data);
        preOrder(sb, node.left);
        preOrder(sb, node.right);
    }

    public void printInOrder(Node node) {
        StringBuilder sb = new StringBuilder();

        inOrder(sb, node);

        System.out.println(sb);
    }

    private void inOrder(StringBuilder sb, Node node) {
        if (node == null) return;

        inOrder(sb, node.left);
        sb.append(node.data);
        inOrder(sb, node.right);
    }

    public void printPostOrder(Node node) {
        StringBuilder sb = new StringBuilder();

        postOrder(sb, node);

        System.out.println(sb);
    }

    private void postOrder(StringBuilder sb, Node node) {
        if (node == null) return;;

        postOrder(sb, node.left);
        postOrder(sb, node.right);
        sb.append(node.data);
    }
}
