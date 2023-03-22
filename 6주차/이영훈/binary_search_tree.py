from binary_tree import Node, BinaryTree

class BinarySearchTree(BinaryTree):
    def __init__(self, val=None):
        self.root = val

    def insert(self, val):
        if self.root == None:
            self.root = Node(val)
            return

        current_node = self.root
        while True:
            if val > current_node.val:
                if current_node.right == None:
                    current_node.right = Node(val)
                    return
                else:
                    current_node = current_node.right

            else:
                if current_node.left == None:
                    current_node.left = Node(val)
                    return
                else:
                    current_node = current_node.left

    def search(self, val):
        current_node = self.root
        while current_node:
            if current_node.val == val:
                return True
            if val > current_node.val:
                current_node = current_node.right
            else:
                current_node = current_node.left
        
        return False   
    
    # 참고 : https://oneshottenkill.tistory.com/435
    def delete(self, val):
        target = self.root
        parent = self.root
        while target: # 삭제할 노드와 부모노드를 찾음
            if target.val == val:
                break
            if val > target.val:
                parent = target
                target = target.right
            else:
                parent = target
                target = target.left

        if target:
            # 삭제할 노드의 자식 노드가 없을 때
            if not target.left and not target.right:
                if val > parent.val:
                    parent.right = None
                else:
                    parent.left = None

            ## 자식 노드가 하나만 있을 땐 그 노드를 위로 끌어올린다
            # 삭제할 노드의 왼쪽 자식 노드만 있을 때
            if target.left and not target.right:
                if val > parent.val:
                    parent.right = target.left
                else:
                    parent.left = target.left
            
            # 삭제할 노드의 오른쪽 자식 노드만 있을 때
            if not target.left and target.right:
                if val > parent.val:
                    parent.right = target.right
                else:
                    parent.left = target.right

            # 삭제할 노드의 자식 노드가 둘 모두 있을 때
            if target.left and target.right:
                #삭제할 노드 오른쪽에서 가장 작은 노드를 찾는다
                change_node = target.right
                change_node_parent = target.right
                while change_node.left != None:
                    change_node_parent = change_node
                    change_node = change_node.left

                change_node.left = target.left
                change_node_parent.left = change_node.right
                change_node.right = target.right
                parent.right = change_node
                
        else:
            print('Delete value not exist')
        



if __name__ == "__main__":
    tree = BinarySearchTree()

    tree.insert(2)
    tree.insert(1)
    tree.insert(3)
    tree.insert(1)
    tree.insert(2)
    tree.insert(3)
    tree.insert(4)

    '''
    트리구조
              2
            ／ ＼
            1    3
          ／ ＼ ／ ＼
          1   2 3  4
    '''

    tree.levelorder(tree.root)
    print()
    '''
    출력결과
    2 1 3 1 2 3 4
    '''

    tree.delete(3)
    tree.levelorder(tree.root)
    print()
    


