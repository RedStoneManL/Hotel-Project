import java.util.ArrayList;
import java.util.Collection;
/**
 * HotelHashSet is a hash set which use a HotelHashMap as data storage.
 * It uses key to store value and use a dummy integer as value of the map.
 * The HotelHashSet does not allow duplicate values, which means 
 * if add a duplicate value, the set wob't change.
 * Since it is a hashset, the order of hashset is unpredictable.
 *
 * @author Xingyu Liu
 * @version 3/4/2019
 */
public class HotelHashSet<E>
{
    private final int DUMMY = 299792458;
    private HotelHashMap<E, Integer> map;
    private int size;

    /**
     * Constructor for objects of class HotelHashSet
     */
    public HotelHashSet()
    {
        size = 0;
        map = new HotelHashMap<E,Integer>();
    }

    /**
     * Constructor for HotelHashSet with a collection of values
     * 
     * @param e the collection of the value
     */
    public HotelHashSet(Collection<? extends E> e){
        this();
        addAll(e);
    }

    /**
     * add new value to the set
     * 
     * @param value the value to be added 
     */
    public void add(E value)
    {
        map.put(value, DUMMY);
        size++;
    }

    /**
     *  add new collection of value to the set
     *  
     *  @param e the collection of value to be added
     */        
    public void addAll(Collection<? extends E> e){
        for(E value : e){
            add(value);
        }
    }

    /**
     * remove the given value from the set
     * 
     * @param value the value to be removed
     */
    public void remove(E value){
        if(map.remove(value) != null)size--;
    }

    /**
     * return the size of the set
     * 
     * @return the size of the set
     */
    public int size(){
        return size;
    }

    /**
     * to check if the given value is inside the set
     * 
     * @param value the given value to be checked
     * @return true if contains false if not
     */
    public boolean contains(E value){
        for (E key : map.keySet()){
            if(key == value || key.equals(value)){
                return true;
            }
        }
        return false;
    }

    /**
     * clear all data in the set
     */
    public void clear(){
        map = new HotelHashMap<E,Integer>();
        size = 0;
    }

    /**
     * return all data in the set in an ArrayList
     * 
     * @return the arraylist contains all data in the set
     */
    public ArrayList<E> toList(){
        return map.keySet();
    }

    /**
     * return the string representation of the set
     * 
     * @return the string representation of the set
     */
    public String toString(){
        String str = "{";
        for(E key : map.keySet()){
            str += key.toString() + ", ";
        }
        if(str.length() != 1)str = str.substring(0, str.length()-2);
        str += "}";
        return str;
    }
}
