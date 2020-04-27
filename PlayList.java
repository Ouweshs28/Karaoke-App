/**
 * Custom Circular Queue Array Implementations
 * Keeps track of head,tail and size
 */
public class PlayList {

    private Node head;
    private Node tail;
    private int size;

    /**
     *
     * @return size of the Queue
     */
    public int size() {

        return this.size;

    }

    /**
     *
     * @return gets the first element performance O(1)
     * @throws Exception
     */

    public Song getFirst() throws Exception {

        if (this.size == 0) {

            throw new Exception("LL is Empty.");

        }

        return this.head.data;

    }

    /**
     *
     * @param idx - index of an element
     * @return gets the required index performance O(1)
     * @throws Exception
     */

    public Song getAt(int idx) throws Exception {

        if (this.size == 0) {

            throw new Exception("LL is Empty.");

        }

        if (idx < 0 || idx >= this.size) {

            throw new Exception("Invalid Index.");

        }

        Node temp = this.head;

        for (int i = 1; i <= idx; i++) {

            temp = temp.next;

        }

        return temp.data;

    }

    /**
     *
     * @param item takes a song to be added at the first
     */

    public void addLast(Song item) {

        // create

        Node nn = new Node();

        nn.data = item;

        nn.next = null;

        // attach

        if (this.size > 0)

            this.tail.next = nn;

        // dm update

        if (this.size == 0) {

            this.head = nn;

            this.tail = nn;

            this.size += 1;

        } else {

            this.tail = nn;

            this.size += 1;

        }

    }

    /**
     *
     * @return the first element in the queue
     * @throws Exception
     */

    public Song removeFirst() throws Exception {

        if (this.size == 0) {

            throw new Exception("LL is empty.");

        }

        Node temp = this.head;

        if (this.size == 1) {

            this.head = null;

            this.tail = null;

            this.size = 0;

        } else {

            this.head = this.head.next;

            this.size--;

        }

        return temp.data;

    }

    /**
     * Populates the circular list into an array for iteration
     * @return
     */

    public Song[] convertToArray() {

        int i = 0;

        Song arraySong[] = new Song[size];

        Node temp = this.head;

        while (temp != null) {

            arraySong[i] = temp.data;

            temp = temp.next;
            i++;

        }
        return arraySong;

    }

    /**
     * Node class that takes a Song
     */

    private class Node {

        Song data;

        Node next;

    }


}
