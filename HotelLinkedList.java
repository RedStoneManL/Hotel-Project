import java.util.Collection;
import java.util.Iterator;
import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;
/**
 * HotelLiskedList constructs a linked array of listnodes which one point to the next listnode
 * this linked list uses a dummy node as the first node which stores a null as value
 * the list itself implements the iterator.
 *
 * @author Xingyu Liu
 * @version 3/4/2019
 */
public class HotelLinkedList<E> implements Iterable<E>
{
    // instance variables 
    private ListNode<E> next;
    private int size;
    
    /**
     * Constructor for objects of class HotelLinkedList
     */
    public HotelLinkedList()
    {
        next = new ListNode<E>();
    }
    
    /**
     * Constructor for HotelLinkedList with the initial data E
     * 
     * @param data the initial data of the linked list
     */
    public HotelLinkedList(E data)
    {
        next = new ListNode<E>();
        next.next = new ListNode<E>(data);
    }
    
    /**
     * Constructor for HotelLinkedList with initial data in a collection of E
     * 
     * @param e the collection of the data
     */
    public HotelLinkedList(Collection<? extends E> e){
        this();
        addAll(e);
    }
    
    /**
     * add a collection of data to the list
     * 
     * @param e the collection of data
     */
    public void addAll(Collection<? extends E> e){
        for(E value : e){
            this.add(value);
        }
    }
    
    /**
     * add the ListNode to the list
     * 
     * @param node the node to be added
     */
    public void add(ListNode<E> node){
        ListNode<E> entry = next;
        while(entry.next != null){
            entry = entry.next;
        }
        entry.next = node;
        size++;
    }

    /**
     * add the value to the list
     * 
     * @param data the data to be added
     */
    public void add(E data){
        ListNode<E> entry = next;
        while(entry.next != null){
            entry = entry.next;
        }
        entry.next = new ListNode<E>(data, null);
        size++;
    }

    /**
     * add the value to specific index
     * 
     * @param index the index where te data will be inserted
     * @param data the data to be added
     */
    public void add(int index, E data){
        if(size <= index || index < 0) throw new IndexOutOfBoundsException();

        ListNode<E> entry = next;
        ListNode<E> entry2 = next.next;
        for(int i = 0; i < index; i++){
            entry = entry2;
            entry2 = entry2.next;
        }
        entry.next = new ListNode<E>(data,entry2);
        size++;
    }

    /**
     * get the value at the given index
     * 
     * @param index the given index to get the value
     * @return the value at the given index
     */
    public E get(int index){
        if(size <= index) throw new IndexOutOfBoundsException();

        ListNode<E> entry = next.next;
        for(int i = 0; i < index; i++){
            entry = entry.next;
        }
        return entry.value;
    }

    /**
     * remove the value at given index
     * 
     * @param index the given index to remove the value
     * @return the value removed
     */
    public E remove(int index){
        if(size <= index || index < 0) throw new IndexOutOfBoundsException();

        ListNode<E> entry = next;
        ListNode<E> entry2 = next.next;
        for(int i = 0; i < index; i++){
            entry = entry2;
            entry2 = entry2.next;
        }
        entry.next = entry2.next;
        size--;
        return entry2.value;
    }

    /**
     * clear all data in the list
     */
    public void clear(){
        size = 0;
        next.next = null;
    }
    
    /**
     * return the size of the list
     * 
     * @return the size of the list
     */
    public int size(){
        return size;
    }

    /**
     * create the iterator for the linked list
     * 
     * @return the iterator of the linked list
     */
    public Iterator<E> iterator(){ 
        Iterator<E> iterator = new HotelListIterator<E>(this);
        return iterator;
    }

    /**
     * return the string representation of the list object
     * 
     * @return the string representation of the list object
     */
    public String toString(){
        String str = "{";
        for(E value: this){
            str += value.toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }
    
    /**
     * copy the linked list object to a new object
     * 
     * @param list the linkedlist object to be copied
     * @return the new copy of the list
     */
    public HotelLinkedList<E> copyOf(HotelLinkedList list){
        HotelLinkedList<E> newList = new HotelLinkedList();
        ListNode<E> node = list.next.next;
        while(node != null){
            newList.add(new ListNode(node.value));
        }
        return newList;
    }

    /**
     * Class HotelListIterator defines the behaviour of the iterator of the HotelLInkedList
     */
    private class HotelListIterator<E> implements Iterator<E>{

        private ListNode<E> next;
        private E data;

        /**
         * Constructor of the HotelListIterator
         */
        public HotelListIterator(HotelLinkedList list) 
        { 
            if(list.next.next != null){
                next = list.next;
                data = (E)list.next.value;
            }else{
                throw new NoSuchElementException();
            }
        } 

        /**
         * check if there is next element
         */
        public boolean hasNext(){
            return !(next.next == null);
        }

        /**
         * get the next value
         */
        public E next(){
            if(hasNext()){
                next = next.next;
                E value = next.value;
                return value;
            }else{
                throw new NoSuchElementException();
            }

        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * Class ListNode constructs basic node which forms the Linked List
     */
    private class ListNode<E>
    {
        // instance variables - replace the example below with your own
        public E value;
        public ListNode<E> next;

        /**
         * Constructor for ListNode object
         */
        public ListNode(){
            value = null;
            next = null;
        }
        
        /**
         * Constructor for ListNode object with given value
         */
        public ListNode(E value){
            this.value = value;
            next = null;
        }
        
        /**
         * Constructor for ListNode object with given value and specific next pointer
         */
        public ListNode(E value, ListNode<E> next)
        {
            this.value = value;
            this.next = next;
        }
    }
    
}