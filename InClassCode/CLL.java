public class CLL<T> {
    
    // inner node class
    private class Node<T> {
        T data;
        Node<T> next;
    }

    private Node<T> last; // points to the last node in the LL
    private int size;

    public CLL () {
        last = null;
        size = 0;
    }
    
    public boolean isEmpty () {
        return size == 0;
        //return last == null;
    }

    public int size() {
        return size;
    }

    public void addToLast (T item) {

        Node<T> oldLast = last;
        
        if ( last != null ) {
            last = new Node<T>();
            last.data = item;
            last.next = oldLast.next; // points to front
            oldLast.next = last;
        } else {
            // list is empty
            last = new Node<T>();
            last.data = item;
            last.next = last;
        }

        size += 1;
    }

    public void addToFront (T item) {

        Node<T> n = new Node<T>();
        n.data = item;

        if ( last == null ) {
            // list is empty
            n.next = n; // node points to itself
            last = n;
        } else {
            n.next = last.next; // new node points to the front of the list
            last.next = n; // last points to the new front
        }

        size += 1;
    }

    public void print () {

        if ( isEmpty() ) {
            System.out.println("CLL is empty");
        } else {

            Node<T> ptr = last.next; // points to first node
            do {
                System.out.print(ptr.data + " -> ");
                ptr = ptr.next; // go to the next node
            } while ( ptr != last.next );
            System.out.println(" back to front");
        }
    }

    public static void main (String[] args) {

        CLL<String> grocery = new CLL<String>();
        grocery.addToFront("lettuce");
        grocery.addToFront("bananas");
        grocery.addToLast("potatos");
        grocery.addToFront("nuggets");
        grocery.addToFront("lemon");
        grocery.print();

        CLL<Integer> numbers = new CLL<Integer>();
        numbers.addToFront(4);
        numbers.addToFront(3);
        numbers.addToFront(2);
        numbers.addToFront(1);
        numbers.print();
    }
}
