import java.util.ArrayDeque;

public class HashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;        // number of key-value pairs
    private int m;        // number of chains
    private Node[] st;    // array of linked-list symbol tables

    // a helper linked list data type
    private static class Node {
        private final Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next)  {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }

    /**
     * Initializes an empty symbol table.
     */
    public HashST() {
        this(INIT_CAPACITY);
    } 

    /**
     * Initializes an empty symbol table with {@code m} chains.
     * @param m the initial number of chains
     */
    public HashST(int m) {
        this.m = m;
        st = new Node[m];
    } 

    // resize the hash table to have the given number of chains,
    // rehashing all of the keys
    @SuppressWarnings("unchecked")
    private void resize(int chains) {
        HashST<Key, Value> temp = new HashST<Key, Value>(chains);
        for (int i = 0; i < m; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                temp.put((Key) x.key, (Value) x.val);
            }
        }

        this.m  = temp.m;
        this.n  = temp.n;
        this.st = temp.st;
    }

    // hash value between 0 and m-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % m;
    } 


     /** Returns the value associated with the specified key in this symbol table.
     *
     * @param  key the key
     * @return the value associated with {@code key} in the symbol table;
     *         {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    @SuppressWarnings("unchecked")
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next) {
            if (key.equals(x.key)) return (Value) x.val;
        }
        return null;
    }


    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old 
     * value with the new value if the symbol table already contains the specified key.
     * Removes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param  key the key
     * @param  val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        // double table size if average length of list >= 10
        if (n >= 10*m) resize(2*m);
        int i = hash(key);
        n++;
        st[i] = new Node(key, val, st[i]);
    }
    /**
     * Returns true if this symbol table contains the specified key.
     *
     * @param  key the key
     * @return {@code true} if this symbol table contains {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns all keys in the symbol table.
     *
     * @return all keys in the symbol table, as in iterable
     */
    @SuppressWarnings("unchecked")
    public Iterable<Key> keys() {
        ArrayDeque<Key> queue = new ArrayDeque<Key>();
        for (int i = 0; i < m; i++) {
            for (Node x = st[i]; x != null; x = x.next) {
                queue.add((Key) x.key);
            }
        }
        return queue;
    }
}
