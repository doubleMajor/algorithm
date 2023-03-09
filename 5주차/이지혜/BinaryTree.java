import javax.sound.midi.Soundbank;

public class BinaryTree {
    Node rootNode = null;


    public static void main(String[] args) {
        int count = 7; //정점의 개수
        Node[] nodeList = new Node[count+1]; // index의 시작이 0부터라서

        for(int i=1; i <= count; i++){
            Node binaryTree = new Node(i);
            nodeList[i] = binaryTree;
        }

        for(int i=1; i <= count;i++) {
            if( i*2 <= count) {
                nodeList[i].left = nodeList[i *2];
                nodeList[i].right = nodeList[(i*2)+1];
            }
        }

        preOrder(nodeList[1]);
    }
    static void preOrder(Node node){
        if(node != null) {
            System.out.println(node.value + "  ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    static void inOrder(Node node){
        if(node != null){
            inOrder(node.left);
            System.out.println(node.value + "  ");
            inOrder(node.right);
        }
    }

    static void postOder(Node node){
        if(node != null){
            postOder(node.left);
            postOder(node.right);
            System.out.println(node.value+"  ");
       }
    }


}
