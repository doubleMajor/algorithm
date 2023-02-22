########################
# 20230223 만들다 말았음
########################

class Node:
    def __init__(self, data=None):
        self.next = None
        self.value = data

class LinkedListStack:
    def __init__(self):
        self.top = None

    def __len__(self):
        size = 0
        node = self.top
        if node == None:
            return 0
        size += 1
        while node.next != None:
            size += 1
            node = node.next

        return size
    
    def isEmpty(self):
        return len(self) == 0
    
    def push(self, item):
        insert_node = Node(item)
        insert_node.next = self.top
        self.top = insert_node

    def pop(self):
        if self.isEmpty():
            raise "Stack is Empty!!"

        data = self.top.value
        self.top = self.top.next

        return data
    
    def peek(self):
        if self.isEmpty():
            raise "Stack is Empty!!"

        return self.top.value
    
    def print(self):
        stack = []
        node = self.top
        if self.isEmpty():
            print(stack)
            return

        while node.next != None:
            stack.append(node.value)
            node = node.next
        
        print(stack)
    
if __name__ == "__main__":
    stack = LinkedListStack()

    print(stack.print())
    stack.push(1)
    print(stack.print())
    stack.push(2)
    print(stack.print())
    stack.push(3)
    print(stack.print())
    stack.push(4)
    print(stack.print())
    stack.push(5)
    print(stack.print())
    stack.pop()
    print(stack.print())
    stack.pop()
    print(stack.print())
    stack.pop()
    print(stack.print())
    stack.push(5)
    print(stack.print())
    stack.push(5)
    print(stack.print())
    stack.pop()
    print(stack.print())
    stack.pop()
    print(stack.print())
    stack.pop()
    print(stack.print())
    stack.pop()
    print(stack.print())