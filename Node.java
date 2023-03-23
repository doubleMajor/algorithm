package base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}
