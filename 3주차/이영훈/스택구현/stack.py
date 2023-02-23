class Stack:
    def __init__(self, size):
        self.size = size
        self.data = [None] * self.size
        self.top = 0

    def __len__(self):
        return self.size
    
    def isEmpty(self):
        return self.top == 0
    
    def isFull(self):
        return self.top == self.size
    
    def push(self, item):
        if self.isFull():
            raise "Stack is Full!!"
        
        self.data[self.top] = item
        self.top += 1

    def pop(self):
        if self.isEmpty():
            raise "Stack is Empty!!"

        self.top -= 1
        data = self.data[self.top]
        self.data[self.top] = None
        return data
    
    def peek(self):
        return self.data[self.top-1]
    
if __name__ == "__main__":
    stack = Stack(5)

    print(stack.data)
    stack.push(1)
    print(stack.data)
    stack.push(2)
    print(stack.data)
    stack.push(3)
    print(stack.data)
    stack.push(4)
    print(stack.data)
    stack.push(5)
    print(stack.data)
    stack.pop()
    print(stack.data)
    stack.pop()
    print(stack.data)
    stack.pop()
    print(stack.data)
    stack.push(5)
    print(stack.data)
    stack.push(5)
    print(stack.data)
    stack.pop()
    print(stack.data)
    stack.pop()
    print(stack.data)
    stack.pop()
    print(stack.data)
    stack.pop()
    print(stack.data)