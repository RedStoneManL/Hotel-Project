
/**
 * Write a description of class test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class test
{
    // instance variables - replace the example below with your own
    private int x;

    /**
     * Constructor for objects of class test
     */
    public test()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void test()
    {
        HotelHashMap<Integer, String> map = new HotelHashMap();
        
        HotelTree<String> tree = new HotelTree();
        
        HotelLinkedList<String> list = new HotelLinkedList();
        
        HotelHashSet<String> set = new HotelHashSet();
        
        map.put(1, "mike"); map.put(20, "dale"); map.put(31, "joe"); map.put(1221, "yamamoto"); map.put(23, "wing");
        
        tree.add("niko"); tree.add("guardian"); tree.add("olofmeister"); tree.add("rain"); tree.add("arden"); tree.add("niko");
        
        list.add("xyp9x"); list.add("dev1ce"); list.add(1,"gla1ve"); list.add("magisk"); list.add("dupreeh");
        
        set.add("Astralis"); set.add("Faze"); set.add("Faze"); set.add("NIP"); set.add("fnatic"); set.add("NAVI");
        
        System.out.println("map size: " + map.size());
        System.out.println("tree size: " + tree.size());
        System.out.println("list size: " + list.size());
        System.out.println("set size: " + set.size());
        System.out.println();
        System.out.println("Map: " + map);
        System.out.println("Tree: " + tree);
        System.out.println("List: " + list);
        System.out.println("Set: " + set);
    }
}
