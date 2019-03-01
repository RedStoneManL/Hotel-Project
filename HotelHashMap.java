import java.util.*;
/**
 * Write a description of class HotelHashMap here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HotelHashMap<K,V> {

    private class HashEntry<K, V>{
        protected int hash;
        protected K key;
        protected V value;
        protected HashEntry<K, V> next;

        HashEntry(int hash, K key, V value, HashEntry<K, V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private HashEntry<K,V>[] table;
    private int capacity;
    private int size;
    private int threshold;

    public HotelHashMap(int capacity){
        if(capacity < 0){
            throw new IllegalArgumentException();
        }else{
            table = new HashEntry[capacity];
            this.capacity = capacity;
            size = 0;
            threshold = (int)(capacity*LOAD_FACTOR);
        }
    }

    /**
     * Constructor for objects of class HotelHashMap
     */
    public HotelHashMap()
    {
        this(INITIAL_CAPACITY);
    }
    
    public int size(){
        return size;
    }

    public V put(K key, V value){
        if(key == null) throw new IllegalArgumentException();

        if(size == threshold) this.resize();

        int hash = this.hash(key);
        HashEntry<K,V> newEntry = new HashEntry(hash, key, value, null);
        HashEntry<K,V> entry = table[hash];

        if(table[hash] == null){
            table[hash] = newEntry;
            size++;
            return null;
        }
        while(entry.next != null){
            if(entry.key == key || entry.key.equals(key)){
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            entry = entry.next;
        }
        entry.next = newEntry;
        size++;
        return null;
    }

    public int hash(K key){
        double temp = (key.hashCode()*Math.PI - 2.5) / Math.E;
        temp = temp - (int)temp;
        return (int)temp*capacity;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void resize()
    {
        HashEntry<K,V>[] oldTable = table;
        int oldCapacity = capacity;
        int oldThreshold = threshold;
        int newCapacity = 0;
        int newThreshold = 0;
        if(capacity > 0){
            newCapacity = oldCapacity*2;
            newThreshold = oldThreshold*2;
        }
        HashEntry[] newTable = new HashEntry[newCapacity];
        table = newTable;
        for(int i = 0; i < oldTable.length; i++){
            HashEntry<K,V> entry = oldTable[i];
            while(entry != null ){
                this.put(entry.key, entry.value);
                entry = entry.next;
            }
        }
    }

    public V get(K key){
        if(key == null) throw new IllegalArgumentException();
        int hash = this.hash(key);
        HashEntry<K,V> entry = table[hash];
        while(entry != null){
            if(entry.key == key || entry.key.equals(key)){
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public V remove(K key){
        int hash = this.hash(key);
        HashEntry<K,V> entry = table[hash];
        HashEntry<K,V> entry2 = table[hash].next;
        if(entry.key == key || entry.key.equals(key)){
            table[hash] = entry2;
            return entry.value;
        }
        while(entry2 != null){
            if(entry2.key == key || entry2.key.equals(key)){
                entry.next = entry2.next;
                size--;
                return entry2.value;
            }
            entry = entry2;
            entry2 = entry2.next;
        }

        return null;
    }

    public void clear() {
        HashEntry<K,V>[] tab;

        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }

    public ArrayList<K> keySet(){
        ArrayList<K> key = new ArrayList<K>();
        for(int i = 0; i < table.length; i++){
            HashEntry<K,V> entry = table[i];
            while(entry != null ){
                key.add(entry.key);
                entry = entry.next;
            }
        }
        return key;
    }

    public ArrayList<V> valueSet(){
        ArrayList<V> value = new ArrayList<V>();
        for(int i = 0; i < table.length; i++){
            HashEntry<K,V> entry = table[i];
            while(entry != null ){
                value.add(entry.value);
                entry = entry.next;
            }
        }
        return value;
    }

    public class EntrySet<K, V>{
        protected K key;
        protected V value;

        EntrySet(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey(){
            return key;
        }

        public V getValue(){
            return value;
        }
    }

    public ArrayList<EntrySet<K,V>> entrySet(){
        ArrayList<EntrySet<K,V>> entrySet = new ArrayList<>();
        for(int i = 0; i < table.length; i++){
            HashEntry<K,V> entry = table[i];
            while(entry != null ){
                entrySet.add(new EntrySet<K, V>(entry.key,entry.value));
                entry = entry.next;
            }
        }
        return entrySet;
    }
    
    public String toString(){
        String str = "{";
        for(EntrySet<K,V> entry : this.entrySet()){
            str += entry.getKey().toString() + " : " + entry.getValue().toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }
}