    public class SNode {
        private int val;
        private SNode next;    // a link to the next student int the linked list
    
        public SNode ( int v, SNode n ) {
            val = v;
            next = n;
        }
        
        public int getVal () { return val; }
        public void setval (int v) { val = v; }
    
        public SNode getNext () { return next; }
        public void setNext (SNode n) { next = n; }
    }
    
