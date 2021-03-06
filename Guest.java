import java.util.ArrayList;
import java.lang.IllegalArgumentException;

/**
 * Guest class models a customer of a hotel. The guest is a person with a name and various 
 * personal information. Guests might have discounts applied based on membership to the 
 * hotel or status as a veteran or government employee so we have fields for those data.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/19/2019
 */
public class Guest implements Comparable<Guest>
{
    /* INSTANCE VARIABLES */
    private String firstName;
    private String lastName;
    private String phoneNumber;    
    private boolean isMilitary;
    private boolean isGovernment;
    private int membershipNumber;

    /**
     * Guest Constructor 2/2: The full Guest constructor that takes into account all 
     * parameters about a Guest.
     *
     * @param first (String) guest's first name
     * @param last (String) guest's last name
     * @param phoneNum (String) guest's phone number
     * @param partySize (int) how many people in the group, including reserving guest.
     * @param nights (int) how many nights the guest+party will stay at hotel
     * @param isMil (boolean) true if the guest qualifies for military discount
     * @param isGov (boolean) true if the guest qualifies for government discount
     * @param memberNumber the membership number of the guest
     */
    public Guest( String first, String last, String phoneNum,                    
    boolean isMil, boolean isGov, int memberNumber )
    {
        setFirstName( first );
        setLastName( last );
        setPhoneNum( phoneNum );        
        setMil( isMil );
        setGovt( isGov );
        membershipNumber = memberNumber;
    }

    /**
     * Guest Constructor 2/2: The full Guest constructor that takes into account all 
     * parameters about a Guest.
     *
     * @param first (String) guest's first name
     * @param last (String) guest's last name
     * @param phoneNum (String) guest's phone number
     * @param partySize (int) how many people in the group, including reserving guest.
     * @param nights (int) how many nights the guest+party will stay at hotel
     * @param isMil (boolean) true if the guest qualifies for military discount
     * @param isGov (boolean) true if the guest qualifies for government discount
     */
    public Guest( String first, String last, String phoneNum,                    
    boolean isMil, boolean isGov)
    {
        setFirstName( first );
        setLastName( last );
        setPhoneNum( phoneNum );        
        setMil( isMil );
        setGovt( isGov );
        membershipNumber = -1;
    }

    /* ACCESSOR METHODS */

    /**
     * Method getFirstName returns the guest's first name
     *
     * @return (String) guest's first name
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Method getLastName returns the guest's last name
     *
     * @return (String) guest's last name
     */
    public String getLastName() 
    {
        return lastName;
    }

    /**
     * Method getPhoneNum returns the guest's phone number
     *
     * @return (String) guest's phone number
     */
    public String getPhoneNum() 
    {
        return phoneNumber;
    }

    /**
     * Method getFullName returns guest's first and last name combined in one string
     *
     * @return (String) guest's first and last name combined in one string
     */
    public String getFullName() 
    {
        return firstName + " " + lastName;
    }

    public int getMemberNumber(){
        return membershipNumber;
    }

    /**
     * Method isMilitary returns true if the guest qualifies for military discount
     *
     * @return true if the guest qualifies for military discount
     */
    public boolean isMilitary() 
    {
        return isMilitary;
    }

    /**
     * Method isGovernment returns true if the guest qualifies for government discount
     *
     * @return true if the guest qualifies for government discount
     */
    public boolean isGovernment() 
    {
        return isGovernment;
    }

    /**
     * Method isMember returns true if the guest qualifies for membership discount
     *
     * @return true if the guest qualifies for membership discount
     */
    public boolean isMember() 
    {
        return !(membershipNumber == -1);
    }

    /* MUTATOR METHODS */

    /**
     * Method setFirstName sets the guest's first name. An empty string is an invalid name.
     *
     * @param first (String) representing guest's first name
     * @throw IllegalArgumentException if an empty string is passed
     */
    public void setFirstName(String first) 
    {
        if (first.isEmpty())
        {
            throw new IllegalArgumentException("First Name can't be an Empty String");
        }

        firstName = first;
    }

    /**
     * Method setLastName sets the guest's last name. An empty string is an invalid name.
     *
     * @param last (String) representing guest's last name
     * @throw IllegalArgumentException if an empty string is passed
     */
    public void setLastName(String last) 
    {
        if (last.isEmpty())
        {
            throw new IllegalArgumentException("Last Name can't be an Empty String");
        }

        lastName = last;
    }

    /**
     * Method setPhoneNum sets the guest's phone number, if valid. a valid phone number is
     * 10 digits, like 2061234567.
     *
     * @param phoneNum (String) representing guest's phone number
     * @throw IllegalArgumentException if the phone number does not match a 10digit string.
     */
    public void setPhoneNum(String phoneNum) 
    {
        for (char c : phoneNum.toCharArray())
        {
            if (!Character.isDigit(c))
            {
                throw new IllegalArgumentException("String is not numeric");
            }
        }

        if (phoneNum.length() != 10)
        {
            throw new IllegalArgumentException("Phone number entered has too many/ too few numbers");
        }

        phoneNumber = phoneNum;
    }

    /**
     * Method setMil sets the military discount status of the guest.
     *
     * @param m (boolean) representing military discount status of guest.
     */
    public void setMil(boolean m) 
    {
        isMilitary = m;
    }

    /**
     * Method setGovt sets the government discount status of the guest.
     *
     * @param g (boolean) representing government discount status of guest.
     */
    public void setGovt(boolean g) 
    {
        isGovernment = g;
    }

    // /**
     // * Method setMembership sets the membership discount status of the guest.
     // *
     // * @param m (boolean) representing membership discount status of guest.
     // */
    // public void setMembership(boolean m) 
    // {
        // isMember = m;
    // }

    /**
     * set the membership number of the guest
     *
     * @param x the membership number of the guest
     */
    public void setMemberNumber(int x){
        membershipNumber = x;
    }

    /* OTHER METHODS */

    @Override
    public int compareTo(Guest g){
        return this.getFullName().compareTo(g.getFullName());
    }
    
    
    /**
     * Method toString overrides Class Object's toString() method. it returns information 
     * about this Guest, including their name, phone number, party size, nights stayed, 
     * and discount status(es).
     *
     * @return (String) with information about the guest.
     */
    public String toString() 
    {
        String str;
        if(membershipNumber == -1){
            str = "Not a member";
        }else{
            str = String.format("%09d", membershipNumber);
        }
        return this.getFullName() + " , Phone: " + phoneNumber + "\n" +            
        "Military: " + isMilitary + ", " + 
        "Government: " + isGovernment + ", " +
        "MemberNumber: " + str;        
    }

    /**
     *  Methods used in GUI
     */

    @Override
    public int hashCode(){
        return this.getFullName().hashCode() + this.getPhoneNum().hashCode();
    }
    
    public boolean equals(Guest guest){
        boolean equals = false;
        if(this.getFullName().equals(guest.getFullName()) && this.getPhoneNum().equals(guest.getPhoneNum()))
            equals = true;
        return equals;
    }

    public String getRoomReserved(Hotel h){
        return h.findReservation(this).getRoom().getRoomNumber();
    }

    public boolean isCheckedIn(Hotel h){
        Status status = h.findReservation(this).getStatus();
        if(status == Status.IN)
            return true;
        return false;
    }

}
