
class Queue:
    def __init__(self, size):
        self.size = size + 1
        self.data = [None] * self.size
        self.front = 0
        self.rear = 0

    def __len__(self):
        return self.size - 1

    def isEmpty(self):
        return self.front == self.rear

    def isFull(self):
        return ((self.rear+1)%self.size) == self.front

    def enqueue(self, data):
        if self.isFull():
            raise Exception("Queue is FULL!!")
        
        self.data[self.rear] = data
        self.rear = (self.rear + 1) % self.size
        return True

    def dequeue(self):
        if self.isEmpty():
            raise Exception("Queue is EMPTY!!")

        data = self.data[self.front]
        self.data[self.front] = None
        self.front = (self.front + 1) % self.size
        return data

    def peek(self):
        return self.data[self.front]

if __name__ == "__main__":
    queue = Queue(3)
    
    print(queue.data)
    queue.enqueue(1)
    print(queue.data)
    print(queue.peek())
    queue.enqueue(2)
    print(queue.data)
    print(queue.peek())
    queue.enqueue(3)
    print(queue.data)
    print(queue.peek())
    queue.dequeue()
    print(queue.data)
    print(queue.peek())
    queue.dequeue()
    print(queue.data)
    print(queue.peek())
    queue.enqueue(4)
    print(queue.data)
    print(queue.peek())
    queue.dequeue()
    print(queue.data)
    print(queue.peek())
    queue.enqueue(5)
    print(queue.data)
    print(queue.peek())
    queue.enqueue(6)
    print(queue.data)
    print(queue.peek())
