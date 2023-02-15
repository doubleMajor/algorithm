
class Node:
    def __init__(self, data=None):
        self.prev = None
        self.next = None
        self.value = data

class LinkedListQueue:
    def __init__(self):
        self.front = Node()
        self.rear = Node()
        self.front.next = self.rear
        self.rear.prev = self.front

    def __len__(self):
        size = 0
        node = self.front.next
        while node.next != None:
            size += 1
            node = node.next

        return size

    def isEmpty(self):
        return len(self) == 0

    def enqueue(self, data):
        insert_node = Node(data)
        insert_node.prev = self.rear.prev
        self.rear.prev.next = insert_node
        self.rear.prev = insert_node
        insert_node.next = self.rear

    def dequeue(self):
        if self.isEmpty():
            raise Exception("Queue is EMPTY!!")

        data = self.front.next.value
        self.front.next = self.front.next.next

        return data

    def peek(self):
        return self.front.next.value
    
    def print(self):
        queue = []
        node = self.front.next
        while node.next != None:
            queue.append(node.value)
            node = node.next
        
        print(queue)

if __name__ == "__main__":
    queue = LinkedListQueue()
    
    queue.print()
    print("size: ", len(queue))
    queue.enqueue(1)
    queue.print()
    print("size: ", len(queue))
    queue.enqueue(2)
    queue.print()
    print("size: ", len(queue))
    queue.enqueue(3)
    queue.print()
    print("size: ", len(queue))
    print("dequeue: ", queue.dequeue())
    queue.print()
    print("size: ", len(queue))
    print("dequeue: ", queue.dequeue())
    queue.print()
    print("size: ", len(queue))
    print("dequeue: ", queue.dequeue())
    queue.print()
    print("size: ", len(queue))
    print("dequeue: ", queue.dequeue())
