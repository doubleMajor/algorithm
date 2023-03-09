class Node:
    def __init__(self, val=None):
        self.val = val
        self.left = None
        self.right = None

class BinaryTree:
    def __init__(self):
        self.root = None

    def preorder(self, node):
        if node != None:
            print(node.val, end=' ')
            if node.left:
                self.preorder(node.left)
            if node.right:
                self.preorder(node.right)

    def inorder(self, node):
        if node != None:
            if node.left:
                self.inorder(node.left)
            print(node.val, end=' ')
            if node.right:
                self.inorder(node.right)

    def postorder(self, node):
        if node != None:
            if node.left:
                self.postorder(node.left)
            if node.right:
                self.postorder(node.right)
            print(node.val, end=' ')

    # def levelorder(self, node):

if __name__ == "__main__":
    tree = BinaryTree()
    n1 = Node(10)
    n2 = Node(20)
    n3 = Node(30)
    n4 = Node(40)
    n5 = Node(50)
    n6 = Node(60)
    n7 = Node(70)
    n8 = Node(80)

    tree.root = n1
    n1.left = n2
    n1.right = n3
    n2.left = n4
    n2.right = n5
    n3.left = n6
    n3.right = n7
    n4.left = n8

    tree.preorder(tree.root)
    print()

    tree.inorder(tree.root)
    print()

    tree.postorder(tree.root)