public class PlayList {

        private class Node {

            Song data;

            Node next;

        }

        private Node head;

        private Node tail;

        private int size;

        public int size() {

            return this.size;

        }

        public Song getFirst() throws Exception {

            if (this.size == 0) {

                throw new Exception("LL is Empty.");

            }

            return this.head.data;

        }

        public Song getLast() throws Exception {

            if (this.size == 0) {

                throw new Exception("LL is Empty.");

            }

            return this.tail.data;

        }

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

        private Node getNodeAt(int idx) throws Exception {

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

            return temp;

        }

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

        public void addFirst(Song item) {

            // create

            Node nn = new Node();

            nn.data = item;

            nn.next = null;

            // attach

            nn.next = this.head;

            // dm update

            if (this.size == 0) {

                this.head = nn;

                this.tail = nn;

                this.size++;

            } else {

                this.head = nn;

                this.size++;

            }

        }

        public void addAt(Song item, int idx) throws Exception {

            if (idx < 0 || idx > this.size) {

                throw new Exception("Invalid Index.");

            }

            if (idx == 0) {

                addFirst(item);

            } else if (idx == this.size) {

                addLast(item);

            } else {

                // create

                Node nn = new Node();

                nn.data = item;

                nn.next = null;

                // attach

                Node nm1 = getNodeAt(idx - 1);

                Node np1 = nm1.next;

                nm1.next = nn;

                nn.next = np1;

                // dm

                this.size++;

            }

        }

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

        public Song removeLast() throws Exception {

            if (this.size == 0) {

                throw new Exception("LL is empty.");

            }

            Node temp = this.tail;

            if (this.size == 1) {

                this.head = null;

                this.tail = null;

                this.size = 0;

            } else {

                Node sm2 = getNodeAt(this.size - 2);

                sm2.next = null;

                this.tail = sm2;

                this.size--;

            }

            return temp.data;

        }

        public Song removeAt(int idx) throws Exception {

            if (this.size == 0) {

                throw new Exception("LL is empty.");

            }

            if (idx < 0 || idx >= this.size) {

                throw new Exception("Invalid Index.");

            }

            if (idx == 0) {

                return removeFirst();

            } else if (idx == this.size - 1) {

                return removeLast();

            } else {

                Node nm1 = getNodeAt(idx - 1);

                Node n = nm1.next;

                Node np1 = n.next;

                nm1.next = np1;

                this.size--;

                return n.data;

            }

        }

        public Song[] convertToArray(){

            int i=0;

            Song arraySong[]=new  Song[size];

            Node temp = this.head;

            while (temp != null) {

                arraySong[i]=temp.data;

                temp = temp.next;
                i++;

            }
            return arraySong;

        }

    }
