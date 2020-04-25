// Java program for linked-list implementation of queue

// A linked list (LL) node to store a queue entry
class QNode {
    Song value;
    QNode next;

    // constructor to create a new linked list node
    public QNode(Song value)
    {
        this.value = value;
        this.next = null;

    }
}

// A class to represent a queue
// The queue, front stores the front node of LL and rear stores the
// last node of LL
public class LinkedListQueue{
    QNode front, rear;

    public LinkedListQueue()
    {
        this.front = this.rear = null;
    }

    // Method to add an value to the queue.
    void enqueue(Song value)
    {

        // Create a new LL node
        QNode temp = new QNode(value);

        // If queue is empty, then new node is front and rear both
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }

        // Add the new node at the end of queue and change rear
        this.rear.next = temp;
        this.rear = temp;
    }

    // Method to remove an value from queue.
    void dequeue()
    {
        // If queue is empty, return NULL.
        if (this.front == null)
            return;

        // Store previous front and move front one node ahead
        QNode temp = this.front;
        this.front = this.front.next;

        // If front becomes NULL, then change rear also as NULL
        if (this.front == null)
            this.rear = null;
    }
}
