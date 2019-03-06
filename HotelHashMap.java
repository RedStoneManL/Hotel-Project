import java.util.ArrayList;
/**
 * HashMap class. uses key, value structure to store values, and uses hash code to let
 * the data evenly distribute. and use capacity and load factor to keep track of the depth of the values.
 * if the size > capacity * load factor, the hash map will call the resize method and double the hashtable
 * to make sure the quick access of the data.
 * Since it is a hashmap, the order of hashset is unpredictable.
 *
 * @author Xingyu Liu
 * @version 3/5/2019
 */
public class HotelHashMap<K,V> {
    /**
     * HashEntry class consrtructs the object to store the key, value set of data
     */
    private class HashEntry<K, V>{
        protected int hash;
        protected K key;
        protected V value;
        protected HashEntry<K, V> next;
        /**
         * default constructor of the Hash Entry object
         */
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

    /**
     * Constructor for HotelHashMap with custom capacity
     * 
     * @param int the desired capacity
     */
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

    /**
     * return the size of the map
     * 
     * @return the size of the map
     */
    public int size(){
        return size;
    }

    /**
     * Add a key-value pair to the map. If the key is duplicated, replace the old corresponding value
     * and return it
     * 
     * @param key the key of the pair
     * @param value the value of the pair
     * 
     * @return the old value, null if the key is not duplicated.
     */
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

        if(entry.key == key || entry.key.equals(key)){
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }else{
            entry.next = newEntry;
        }
        size++;
        return null;
    }

    /**
     * get the hash code of the key
     * 
     * @param the key object to obtain hash code
     * @return the hash code of the key
     */
    public int hash(K key){
        double temp = (key.hashCode()*Math.PI - 2.5) / Math.E;
        temp = temp - (int)temp;
        return (int)temp*capacity;
    }

    /**
     * resize the old hashtable to twice its size.
     */
    public void resize()
    {
        HashEntry<K,V>[] oldTable = table;
        int oldCapacity = capacity;
        int oldThreshold = threshold;
        int newCapacity = 0;
        int newThreshold = 0;
        size = 0;
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

    /**
     * check if two map objects are equal
     * 
     * @param map the map object to be compared
     * @return true if equals, false if not
     */
    public boolean equals(HotelHashMap map){
        if(this.size != map.size) return false;
        ArrayList<EntrySet<K,V>> entry1 = this.entrySet();
        ArrayList<EntrySet<K,V>> entry2 = this.entrySet();
        for(int i = 0; i < this.size; i++){
            if(entry1.get(1).equals(entry2.get(2))) return false;
        }
        return true;
    }

    /**
     * get the value of the corresponding key
     * 
     * @param key the key to search the value
     * @return the value corresponed to the key
     */
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

    /**
     * remove the key-value set of the given key
     * 
     * @param key the key value of the key-value set
     * @return the value of the key-value set
     */
    public V remove(K key){
        int hash = this.hash(key);
        HashEntry<K,V> entry = table[hash];
        HashEntry<K,V> entry2 = table[hash].next;
        if(entry.key == key || entry.key.equals(key)){
            table[hash] = entry2;
            size--;
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

    /**
     * clear all data in the map
     */
    public void clear() {
        HashEntry<K,V>[] tab;
        if ((tab = table) != null && size > 0) {
            size = 0;
            for (int i = 0; i < tab.length; ++i)
                tab[i] = null;
        }
    }

    /**
     * return the arraylist contains all key
     * 
     * @return the arraylist contains all key
     */
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

    /**
     * return the arraylist contains all values
     * 
     * @return the arraylist contains all values
     */
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

    /**
     * class EntrySet constructs a object stores only key and value
     */
    public class EntrySet<K, V>{
        protected K key;
        protected V value;

        /**
         * default constructor for Class EntrySet
         * 
         * @param key the value of the key
         * @param value the value of the value
         */
        EntrySet(K key, V value){
            this.key = key;
            this.value = value;
        }

        /**
         * return the value of the key
         * 
         * @return the value of the key
         */
        public K getKey(){
            return key;
        }

        /**
         * return the value of the value
         * 
         * @return the value of the value
         */
        public V getValue(){
            return value;
        }
    }

    /**
     * return the arraylist of entryset contains all key-value pairs in the map
     * 
     * @return the arraylist of entryset contains all key-value pairs in the map
     */
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

    /**
     * return the string expression of the object
     * 
     * @return the string expression of the object
     */
    public String toString(){
        String str = "{";
        for(EntrySet<K,V> entry : this.entrySet()){
            str += entry.getKey().toString() + " : " + entry.getValue().toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }

    public static void test(){
        HotelHashMap<String, Integer> map = new HotelHashMap();
        map.put("Steve", 10);
        map.put("gla1ve", 12);
        map.put("magisk", 100);
        map.put("dev1ce", 989);
        System.out.println(map.size);
        System.out.println(map);
        map.resize();
        System.out.println(map.size);
        System.out.println(map);
        map.remove("Steve");
        System.out.println(map.size);
        System.out.println(map);
        System.out.println(map.put("dev1ce", 962));
        System.out.println(map.size);
        System.out.println(map);
    }
}