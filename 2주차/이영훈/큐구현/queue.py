
class Queue:
    def __init__(self, size):
        self.size = size
        self.data = [None] * self.size
        self.rear = 0

    def __len__(self):
        return self.size

    def isEmpty(self):
        return self.rear == 0

    def isFull(self):
        return self.rear == self.size

    def enqueue(self, data):
        if self.isFull():
            raise Exception("Queue is FULL!!")
        
        self.data[self.rear] = data
        self.rear += 1
        return True

    def dequeue(self):
        if self.isEmpty():
            raise Exception("Queue is EMPTY!!")

        data = self.data[0]

        for i in range(self.rear-1):
            self.data[i] = self.data[i+1]
        self.data[self.rear-1] = None

        self.rear -= 1
        return data

    def peek(self):
        return self.data[0]

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
