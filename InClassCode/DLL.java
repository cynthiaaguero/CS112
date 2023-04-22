public class DLL<T> {
    
    private class Node<T> {

        T data;
        Node<T> previous; // refers to the previous node
        Node<T> next;     // refers to the next node
    }

    int size;
    Node<T> front; // points to the front of the LL

    public boolean isEmpty () {
        return size == 0;
        //return front == null;
    }

    public void addToFront (T item) {

        Node<T> newNode = new Node<T>();
        newNode.data = item;
        newNode.next = null;
        newNode.previous = null;

        if ( front != null ) {
            // list is NOT empty
            newNode.next = front;
            front.previous = newNode;
        }

        front = newNode;
        size += 1;
    }

    public boolean delete (T target) {

        // FIX THE BUG ON THIS METHOD
        
       Node<T> ptr = front;
       while ( ptr != null && !ptr.data.equals(target) ) {
            ptr = ptr.next;
       }

       if ( ptr == null ) {
        // target not found
        return false;
       }
       ptr.previous.next = ptr.next;
       ptr.next.previous = ptr.previous;
       size -= 1;
       return true;
    }

    public void print () {
        Node<T> ptr = front;
        while ( ptr != null ) {
            System.out.print(ptr.data + " -> ");
            ptr = ptr.next;
        }
        System.out.println("\\");
    }

    public static void main (String[] args) {
        DLL<String> songs = new DLL<String>();
        songs.addToFront("Hips don't lie");
        songs.addToFront("Gucci gang");
        songs.addToFront("Rockstar");
        songs.addToFront("American idiot");
        songs.print();
        songs.delete("American idiot");
        songs.print();
    }
}
