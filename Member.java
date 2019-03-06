import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * Class Member manage all members in the hotel
 *
 * @author Xingyu Liu
 * @version 02/26/2019
 */
public class Member
{
    private HotelHashSet<Guest> members;
    int memberNumber;
    /**
     * Constructor for objects of class Member
     */
    public Member()
    {
        members = new HotelHashSet<Guest>();
        memberNumber = 0;
    }
    
    /**
     * Constructor for objects of class Member with given set of guests and memberNumber
     */
    public Member(HotelHashSet<Guest> g, int x){
        members = g;
        memberNumber = x;
    }
    
    /**
     * get the currrent memberNumber
     * 
     * @return the current memberNumber
     */
    public int getMemberNumber(){
        return memberNumber;
    }

    /**
     * set the current memberNumber
     * 
     * @param number the desired memberNumber
     */
    public void setMemberNumber(int number){
        memberNumber = number;
    }
    
    /**
     * add new guest to the member
     * 
     * @param g the new guest to be added
     */
    public void add(Guest g)
    {
        members.add(g);
    }
    
    /**
     * add a new guest to the member and assign the guest a member number
     * 
     * @param g the new guest to be added
     * @return the assigned member number
     */
    public int addMember(Guest g){
        g.setMemberNumber(++memberNumber);
        members.add(g);
        return memberNumber;
    }
    
    /**
     * find the guest in members with give lastname and phone number
     * 
     * @param lastname the lastname of the guest
     * @param phoneNum the phone number of the guest
     * 
     * @return the guest satisfies the criteria
     */
    public Guest findMember(String lastname, String phoneNum){
        for(Guest g : members.toList()){
            if(g.getLastName().equals(lastname) && g.getPhoneNum().equals(phoneNum)) return g;
        }
        throw new NoSuchElementException("There is no current member satisfy the criteria");
    }
    
    /**
     * return all guest in a arraylist
     * 
     * @return the arrayList contains all guest objects
     */
    public ArrayList<Guest> getMembers(){
        return members.toList();
    }
    
    /**
     * 
     */
    public Guest findMember(String memberNumber){
        if(memberNumber.length() != 9) throw new IllegalArgumentException("Not a valid length for membership number"); 
        try{
            Integer.parseInt(memberNumber);
        }catch(Exception e){
            throw new IllegalArgumentException("Not a valid length for membership number"); 
        }
        
        for(Guest g : members.toList()){
            if(String.format("%09d", g.getMemberNumber()).equals(memberNumber)) return g;
        }
        throw new NoSuchElementException("There is no current member satisfy the criteria");
    }
    
    public String toString(){
        String str = "";
        for(Guest g : members.toList()){
            str += g.toString() + "\n";
        }
        return str;
    }
}
