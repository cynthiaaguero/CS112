public class MaxInLinkedList {
    public static void main (String[] args){
        SNode head = new SNode(-1, null);
        SNode ptr = new SNode(0,null);
        ptr = head;

        for (int i = 0; i <= 4; i++){
            ptr.setNext(new SNode(i, null));
            ptr = ptr.getNext();
        }
        ptr.setNext(null);
        ptr = head;
        System.out.println(max(head));
    }
    public static int max (SNode head){
        if (head == null){
            return 0;
        }
        return Math.max(head.getVal(), max(head.getNext()));
    }
}
