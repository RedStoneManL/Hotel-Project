import java.util.*;
/**
 * Write a description of class HotelHashSet here.
 *
 * @author (your name)
 * @version (a version number or a date)
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
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void add(E value)
    {
        for(E key : map.keySet()){
            if (key == value || key.equals(value)){
                return;
            }
        }
        map.put(value, DUMMY);
        size++;
    }
    
    public void remove(E value){
        map.remove(value);
        size--;
    }
    
    public int size(){
        return size;
    }
    
    public boolean contains(E value){
        for (E key : map.keySet()){
            if(key == value || key.equals(value)){
                return true;
            }
        }
        return false;
    }
    
    public void clear(){
        map = new HotelHashMap<E,Integer>();
        size = 0;
    }
    
    public ArrayList<E> toList(){
        return map.keySet();
    }
    
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
