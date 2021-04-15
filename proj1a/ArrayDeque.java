public class ArrayDeque<T> {

    private int size;
    private T[] items;
    private int nextFirst = 7;
    private int nextLast  = 0;
    public ArrayDeque(){
         items =(T []) new Object [8];
         size = 0;
    }
    public void addFirst(T x){
        //nextFirst 到0，下一个值是item[size-1]
        if(nextFirst == -1){
            nextFirst = items.length - 1;
        }
        //格子满了，外加一圈(多加几个免得每次都加)
        if(nextFirst == nextLast) {
            this.resize();
        }
        //否则，nextFirst左移一格

            items[nextFirst] = x;
            nextFirst -= 1;
            size += 1;
    }
    public void resize(){

        T[] resizedArr = (T[]) new Object[items.length * 2];
        System.arraycopy(items, 0, resizedArr, 0, nextFirst + 1);
        if(nextFirst + 1 != items.length) {
            System.arraycopy(items, nextFirst + 1, resizedArr, nextFirst + items.length + 1,
                    items.length - nextFirst - 1);
        }
        int extraLength = items.length;
        items = resizedArr;
        nextFirst = nextFirst + extraLength;
    }
    public void addLast(T x){
        //nextFirst 到items.length-1，下一个值是0
        if(nextLast == items.length){
            nextLast = 0;
        }
        //格子满了，外加一圈
        if(nextFirst == nextLast) {
            this.resize();
        }
        //否则，nextLast右移一格
        items[nextLast] = x;
        nextLast += 1;
        size += 1;

    }
    public boolean isEmpty(){
        return (size==0);
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if(size == 0){
            System.out.println("empty");
        }
        else{
            for(int i = 0; i < items.length; i++){
                System.out.println(items[i]);
            }
        }
    }
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        if(nextFirst + 1 != items.length){
            T result = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            size -= 1;
            return result;
        }
        else{
            T result = items[0];
            items[0] = null;
            size -= 1;
            return result;
        }
    }
    public T removeLast(){
        if(size == 0){
            return null;
        }
        if(nextLast - 1 != -1){
            T result = items[nextLast - 1];
            items[nextLast - 1] = null;
            size -= 1;
            return result;
        }
        else{
            T result = items[items.length - 1];
            items[items.length - 1] = null;
            size -= 1;
            return result;
        }
    }
    public T get(int index){
        if(size == 0){
            return null;
        }
        return items[index];
    }

//    public static void main(String[] args) {
//        ArrayDeque <Integer>test1 = new ArrayDeque();
//        test1.addFirst(10);
//        test1.addFirst(20);
//        test1.addFirst(30);
//        test1.addLast(5);
////        test1.removeFirst();
////        test1.removeLast();
//        test1.addFirst(40);
//        test1.addFirst(50);
//        test1.addFirst(60);
//        test1.addFirst(70);
////        test1.addFirst(80);
//
//        System.out.println(test1.get(6));
//        test1.printDeque();
//    }
}