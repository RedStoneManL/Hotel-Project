import java.util.*;
import java.lang.IndexOutOfBoundsException;
/**
 * Write a description of class HotelLinkedList here.
 *
 * @author Xingyu Liu
 * @version (a version number or a date)
 */
public class HotelLinkedList<E> implements Iterable<E>
{
    // instance variables - replace the example below with your own
    private ListNode<E> next;
    private int size;
    /**
     * Constructor for objects of class HotelLinkedList
     */
    public HotelLinkedList()
    {
        next = new ListNode<E>(null,null);
    }

    public void add(E data){
        ListNode<E> entry = next;
        while(entry.next != null){
            entry = entry.next;
        }
        entry.next = new ListNode<E>(data, null);
        size++;
    }

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
    
    public void add(HotelLinkedList list){
        if(list == null || list.size == 0){
            return;
        }
        ListNode<E> node = new ListNode<E>();
        
    }

    public E get(int index){
        if(size <= index) throw new IndexOutOfBoundsException();

        ListNode<E> entry = next.next;
        for(int i = 0; i < index; i++){
            entry = entry.next;
        }
        return entry.value;
    }

    public E remove(int index){
        if(size <= index) throw new IndexOutOfBoundsException();

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

    public int size(){
        return size;
    }

    public Iterator<E> iterator(){ 
        Iterator<E> iterator = new HotelListIterator<E>(this);
        return iterator;
    }

    public String toString(){
        String str = "{";
        for(E value: this){
            str += value.toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }

    class HotelListIterator<E> implements Iterator<E>{

        private ListNode<E> next;
        private E data;

        public HotelListIterator(HotelLinkedList list) 
        { 
            if(list.next.next != null){
                next = list.next;
                data = (E)list.next.value;
            }else{
                throw new NoSuchElementException();
            }
        } 

        public boolean hasNext(){
            return !(next.next == null);
        }

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

    class ListNode<E>
    {
        // instance variables - replace the example below with your own
        public E value;
        public ListNode<E> next;

        public ListNode(){
            value = null;
            next = null;
        }
        
        public ListNode(E value, ListNode<E> next)
        {
            this.value = value;
            this.next = next;
        }
    }
    
}