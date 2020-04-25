
import java.util.ArrayDeque;

public class OrderedSequentialSearchST<Key extends Comparable<Key>, Value> {
        Node first;
        int size;

        private class Node {
            Key key;
            Value value;
            Node next;
            public Node(Key key, Value value) {
                this.key = key;
                this.value = value;
                next = null;
            }
        }

        public OrderedSequentialSearchST() {
            first = null;
            size = 0;
        }
        public void put(Key key, Value value) {
            if (value == null) {
                delete(key);
            }
            if (first == null) {
                first = new Node(key, value);
                size++;
                return;
            }
            Node current = first;
            while (current.next != null && current.next.key.compareTo(key) < 0) {
                current = current.next;
            }
            if (current.key.compareTo(key) == 0) {
                current.value = value;
                return;
            }
            Node node = new Node(key, value);
            node.next = current.next;
            current.next = node;
            size++;
        }
        public Value get(Key key) {
            Node current = first;
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }
            return null;
        }
        public void delete(Key key) {
            if (first == null) {
                return;
            }
            if (first.key.compareTo(key) == 0) {
                first = first.next;
                size--;
                return;
            }
            Node current = first;
            while (current.next != null) {
                if (current.key.compareTo(key) == 0) {
                    current.next = current.next.next;
                    size--;
                    return;
                }
                current = current.next;
            }
        }
        public boolean contains(Key key) {
            Node current = first;
            while (current != null && current.key.compareTo(key) <= 0) {
                if (current.key.compareTo(key) == 0)
                    return true;
                current = current.next;
            }
            return false;
        }
        public boolean isEmpty() {
            return size == 0;
        }
        public int size() {
            return size;
        }
        public Key min() {
            if (first == null)
                return null;
            return first.key;
        }
        public Key max() {
            if (first == null)
                return null;
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            return current.key;
        }
        // Largest key less than or equal to key
        public Key floor(Key key) {
            if (first == null || first.key.compareTo(key) > 0)
                return null;
            Node current = first;
            while (current.next != null && current.next.key.compareTo(key) <= 0) {
                current = current.next;
            }
            return current.key;
        }
        // Smallest key greater than or equal to key
        public Key ceiling(Key key) {
            if (first == null)
                return null;
            Node current = first;
            while (current != null && current.key.compareTo(key) < 0) {
                current = current.next;
            }
            if (current == null)
                return null;
            return current.key;
        }
        // Number of keys less than key
        public int rank(Key key) {
            if (first == null)
                return 0;
            int r = 0;
            Node current = first;
            while (current != null && current.key.compareTo(key) < 0) {
                r++;
                current = current.next;
            }
            return r;
        }
        public Key select(int k) {
            if (first == null)
                return null;
            Node current = first;
            while (k > 0) {
                current = current.next;
                if (current == null)
                    return null;
                k--;
            }
            return current.key;
        }
        public void deleteMin() {
            if (first == null)
                return;
            first = first.next;
            size--;
        }
        public void deleteMax() {
            if (first == null)
                return;
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = null;
            size--;
        }
        public int size(Key lo, Key hi) {
            if (first == null)
                return 0;
            Node current = first;
            while (current != null && current.key.compareTo(lo) < 0) {
                current = current.next;
            }
            if (current == null)
                return 0;
            int s = 0;
            while (current != null && current.key.compareTo(hi) <= 0) {
                s++;
                current = current.next;
            }
            return s;
        }
        public Iterable<Key> keys(Key lo, Key hi) {
            ArrayDeque<Key> deque = new ArrayDeque<>();
            if (first == null)
                return deque;
            Node current = first;
            while (current != null && current.key.compareTo(lo) < 0) {
                current = current.next;
            }
            while (current != null && current.key.compareTo(hi) <= 0) {
                deque.add(current.key);
                current = current.next;
            }
            return deque;
        }
        public Iterable<Key> keys() {
            return keys(select(0), select(this.size - 1));
        }

    }