import java.util.NoSuchElementException;
import java.util.ArrayList;

/**
 * Write a description of class Member here.
 *
 * @author Xingyu Liu
 * @version 02/26/2019
 */
public class Member
{
    // instance variables - replace the example below with your own
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
    
    public Member(HotelHashSet<Guest> g, int x){
        members = g;
        memberNumber = x;
    }
    
    public int getMemberNumber(){
        return memberNumber;
    }

    public void setMemberNumber(int number){
        memberNumber = number;
    }
    
    public void add(Guest g)
    {
        members.add(g);
    }
    
    public int addMember(Guest g){
        g.setMemberNumber(++memberNumber);
        members.add(g);
        return memberNumber;
    }
    
    public Guest findMember(String lastname, String phoneNum){
        for(Guest g : members.toList()){
            if(g.getLastName().equals(lastname) && g.getPhoneNum().equals(phoneNum)) return g;
        }
        throw new NoSuchElementException("There is no current member satisfy the criteria");
    }
    
    public ArrayList<Guest> getMembers(){
        return members.toList();
    }
    
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
