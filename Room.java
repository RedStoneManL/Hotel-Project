
/**
 * Abstract class Room provides the required fields and methods for 
 * a type of Hotel Room. Rooms on higher floors have a higher cost per night.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 01/21/2019
 */
public abstract class Room
{
    /* CLASS CONSTANTS */
    private static final double BASE_RATE = 150.00;

    /* INSTANCE VARIABLES */
    private String roomNum;
    private int floor;
    private int capacity;
    private String bedType;
    private HotelTree<TimeSpan> reservationTimes;

    /**
     * Constructor for Room object
     * 
     * @param roomNum (String) the room number of the room
     * @param floor (int) the floor where room located
     * @param capacity (int) the human capacity of the room
     * @param bedType (String) the bedtype of the room
     */
    public Room( String roomNum, int floor, int capacity, String bedType )
    {
        /* Because we are assuming that the textfile with room information is correct, we are not going to require validators here. */
        this.roomNum = roomNum;
        this.floor = floor;
        this.capacity = capacity;
        this.bedType = bedType;
        reservationTimes = new HotelTree<TimeSpan>();
    }

    /* ACCESSOR METHODS */

    /**
     * This abstract method must be present in all child classes.
     * It should return the price of the room per night, accounting for 
     * which floor the room is on and any special charges based on
     * Room - Regular, Large, or Suite.
     * Note that in the child classes, getRate() will need to call 
     * super.getBaseRate()!
     * 
     * @return (double) representing nightly price of Room
     */
    public abstract double getRate();

    /**
     * Returns the Room number as a String.
     * 
     * @return (String) the room number
     */
    public String getRoomNumber()
    {
        return this.roomNum;
    }

    /**
     * Returns the Room's floor as an int.
     * 
     * @return (int) the room's floor number
     */
    public int getFloor()
    {
        return this.floor;
    }

    /**
     * Returns the Room's capacity as an int.
     * 
     * @return (int) the room's human capacity
     */
    public int getCapacity()
    {
        return this.capacity;
    }

    /**
     * Returns the Room's bedType as a String.
     * 
     * @return (String) the room's bedType
     */
    public String getBedType()
    {
        return this.bedType;
    }

    /**
     * Returns the base rate with NO changes based on floor (that is done in child classes).
     * 
     * @return (double) the base price of the room per night in Hotel Burger
     */
    protected double getBaseRate()
    {
        return BASE_RATE;
    }
    
    /**
     * Return the tree of reservation times
     * 
     * @return the tree stores the reservation times
     */
    public HotelTree<TimeSpan> getTimes(){
        return reservationTimes;
    }

    public void setTimes(HotelTree<TimeSpan> times){
        reservationTimes = times;
    }    
    
    /**
     * Returns the Room's RoomType as a String.
     * 
     * @return (String) the room's RoomType
     */

    abstract String getRoomType();
    
    /**
     * Return ture if the time span is overlapping with any of the current reserved time,
     * false if not
     * 
     * @param ts the time span to be examined
     */
    public boolean isOverlap(TimeSpan ts){
        for(TimeSpan span : reservationTimes.inorder()){
            if(span.equals(ts)) return true;
        }
        return false;
    }
    
    /**
     * Add new time span to the collections of current time span
     * 
     * @param ts the time span to be added to the collection
     * @throws IllegalArguentException when the time span overlaps with the current time span
     */
    public void addTimeSpan(TimeSpan ts){
        if(isOverlap(ts)) throw new IllegalArgumentException("Overlapped reservation time");
        reservationTimes.add(ts);
    }
    
    /**
     * remove the given time span from the current time spans
     * 
     * @param ts the time span to be removed from the collection
     */
    public void removeTimeSpan(TimeSpan ts){
        reservationTimes.remove(ts);
    }

    /**
     * Returns true if the Room in the given time span is available.
     * 
     * @param ts the time span to check
     * @return true if the room is Available for guests.
     */
    public boolean isAvailable(TimeSpan ts)
    {
        return !(isOverlap(ts));
    }

    /* OTHER METHODS */

    /**
     * Method equals overrides the Object class's equals method; matches by room number.
     * It returns true if the other Room object has the same room number as this room.
     * It returns false if the other Object is not the same room, or is not a room-object.
     *
     * @param other (Object) to be compared
     * @return true if other Room has the same room number as this room.
     */
    @Override
    public boolean equals( Object other )
    {
        boolean equals = false;

        if (other != null && other instanceof Room)
        {
            equals = this.roomNum == ((Room) other).roomNum;
        }

        return equals;
    }

    /**
     * Overrides the Object Class's toString() method. 
     * For example: 'Room: 405, Floor: 4, BedType: DOUBLE, Capacity: 4
     * 
     * @return (String) representing information about this Room
     */
    @Override
    public String toString()
    {
        return "Room " + this.getRoomNumber() +
        ", Floor " + this.getFloor() +
        ", BedType " + this.getBedType() + 
        ", Capacity " + this.getCapacity();
    }
}
