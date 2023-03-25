package TreeTraversal;

import base.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 너비 우선 탐색
 * 루트를 큐에 넣고 시작
 * 큐에서 노드를 꺼내서 출력하고, 왼쪽 자식과 오른쪽 자식을 큐에 넣는다.
 * 큐가 빌때까지 반복
 */
public class BFS {

    public static void bfs(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();

            /**
             * 큐에서 자신을 꺼내서 출력하고, 왼쪽, 오른쪽을 큐에 넣는다.
             * 왼쪽 자식도 자신을 출력하고, 왼쪽, 오른쪽을 큐에 넣는다.
             *
             */

            for (int i = 0; i < size; i++) {
                Node tempNode = queue.poll();
                System.out.print(tempNode.getData() + " ");

                if (tempNode.getLeft() != null) {
                    queue.offer(tempNode.getLeft());
                }

                if (tempNode.getRight() != null) {
                    queue.offer(tempNode.getRight());
                }
            }
        }
    }
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        Node node7 = new Node(8);

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node3.setLeft(node7);

        bfs(root);
    }
}
