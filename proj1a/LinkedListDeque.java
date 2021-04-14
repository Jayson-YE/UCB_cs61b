public class LinkedListDeque<T> {

    private class LinkedNode {
        private T item;
        private LinkedNode prev;
        private LinkedNode next;

        public LinkedNode(T i, LinkedNode p, LinkedNode n) {
            item = i;
            prev = p;
            next = n;
        }
        private T getRecursive(int index){
            if(index == 0){
                return this.item;
            }
            return next.getRecursive(index -1);
        }
    }


    private LinkedNode sentinel;
    private LinkedNode last;
    private int size;

    //Creates an empty linked list deque.

    public LinkedListDeque(){
        sentinel = new LinkedNode(null,null,null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        last = sentinel;
        size = 0;
    }
//    public LinkedListDeque(T x){
//        sentinel = new LinkedNode(null,null,null);
//        sentinel.next = new LinkedNode(x,sentinel.next,sentinel.next);
//        sentinel.prev = sentinel.next;
//        last = sentinel.next;
//        size = 1;
//    }





    //Adds an item of type T to the front of the deque.
    public void addFirst(T item){
            sentinel.next = new LinkedNode(item,sentinel,sentinel.next);
            sentinel.next.next.prev = sentinel.next;
            //The first addFirst is the right one of addFirst, later ones will be put on the left
            if(size == 0){
                last = sentinel.next;
            }
            size += 1;
    }

    //Adds an item of type T to the back of the deque
    public void addLast(T item){
            last.next = new LinkedNode(item,last,sentinel);
            //every time move last to left one space
            last = last.next;
            sentinel.prev = last;
            size += 1;

    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty(){
        return (size == 0);
    }
    //Returns the number of items in the deque.
    public int size(){
        return size;
    }
    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        LinkedNode a = sentinel;
        while (a.next != sentinel) {
            System.out.println(a.next.item + " ");
            a = a.next;
        }
    }
    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst(){
        if(isEmpty()){
            System.out.println("空的");
            return null;
        }
        T result = sentinel.next.item;
        sentinel.next.item = null;
        sentinel.next = sentinel.next.next;
        size -= 1;

        // track last pointer
        if(size == 0) {
            last = sentinel;
            sentinel.prev = sentinel;
            return result;
        }

        sentinel.next.prev = sentinel;
        return result;
    }
    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast(){
        if(isEmpty()){
            System.out.println("空的");
            return null;
        }
        T result2 = last.item;
        last.prev.next = sentinel;
        sentinel.prev = last.prev;
        last.prev = last;
        size -= 1;
        return result2;
    }
    //Gets the item at the given index, where 0 is the front, 1 is the next item,
    // and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index){
        if(size <= index || index < 0){
            return null;
        }
        else {
            LinkedNode b = sentinel.next;
            for (int i = 0; i < index; i++) {
                b = b.next;
            }
            return b.item;
        }
    }
    //Same as get, but uses recursion.
    public T getRecursive(int index){
        if(size < index || index < 0){
            return null;
        }
        return sentinel.next.getRecursive(index);
    }
//    public static void main(String[] args) {
//        LinkedListDeque<Integer> test1 = new LinkedListDeque();
//        test1.addFirst(1);
//        test1.addLast(2);
////        test1.removeFirst();
////        test1.removeLast();
//        test1.printDeque();
//        System.out.println(test1.size());
//        System.out.println(test1.isEmpty());
//        System.out.println(test1.get(0));
//        System.out.println(test1.get(2));
//        System.out.println(test1.getRecursive(0));
//    }
}