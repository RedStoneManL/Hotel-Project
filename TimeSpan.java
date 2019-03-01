
/**
 * repersents a peroid of time between two dates
 *
 * @author Xingyu Liu
 * @version (a version number or a date)
 */
public class TimeSpan implements Comparable<TimeSpan>
{
    // instance variables - replace the example below with your own
    private Date date1, date2;
    /**
     * Constructor for objects of class TimeSpan
     * 
     * @param date1 the beginnning date of the time span
     * @param date2 the end date of the time span
     * 
     * @throws IllegalArgumentException when the beginning date is later than the end date
     */
    public TimeSpan(Date date1, Date date2)
    {
        if(date1.compareTo(date2) > 0 ){
            throw new IllegalArgumentException("Invalid time span!");
        }
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * to check whether a date is in a time span
     *
     * @param  date the date which to be examined
     * @return  true is contains, false if not
     */
    public boolean contains(Date date)
    {
        return this.date1.compareTo(date) <= 0 && this.date2.compareTo(date) >= 0;
    }

    /**
     * to check whether two ti[me spans overlapped
     *
     * @param  ts the time span which to be examined
     * @return  true is overlaps, false if not
     */
    public boolean isOverlap(TimeSpan ts){
        return !(this.date1.compareTo(ts.date2) > 0 || this.date2.compareTo(ts.date1) < 0);
    }

    /**
     * get the time span of two dates
     * 
     * @param date the date which to be examined
     */
    public int getDays(){
        int year1 = this.date1.getYear();
        int month1 = this.date1.getMonth();
        int day1 = this.date1.getDay();
        int year2 = this.date2.getYear();
        int month2 = this.date2.getMonth();
        int day2 = this.date2.getDay();

        Date temp_date1 = new Date(month1, day1, year1);
        Date temp_date2 = new Date(month2, day2, year2);

        int days = 0;
        days += temp_date2.getDay() - temp_date1.getDay();
        temp_date1.setDay(temp_date2.getDay());
        while(temp_date1.getYear() < temp_date2.getYear() || temp_date1.compareTo(temp_date2) < 0){
            temp_date1.setYear(temp_date1.getYear() + 1);
            if(temp_date1.compareTo(temp_date2) <= 0){
                if(((temp_date1.getYear() - 1) % 4 == 0 && temp_date1.getMonth() < 3) || (temp_date1.getYear() % 4 == 0 && temp_date1.getMonth() >= 3)){
                    days += 366;
                }else{
                    days += 365;
                }
            }else{
                temp_date1.setYear(temp_date1.getYear() - 1);
                break;
            }
        }

        while(temp_date1.compareTo(temp_date2) < 0){
            if(temp_date1.getMonth() + 1 == 13){
                temp_date1.setMonth(1);
                temp_date1.setYear(temp_date1.getYear()+1);
            }else{
                temp_date1.setMonth(temp_date1.getMonth() + 1);
            }

            if(temp_date1.compareTo(temp_date2) <= 0){
                switch(temp_date1.getMonth()-1){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 0:
                    days += 31;
                    break;
                    case 4:
                    case 6:
                    case 9:
                    case 11:
                    days += 30;
                    break;
                    case 2:
                    if(temp_date1.getYear()%4 == 0){
                        days += 29;
                    }else{
                        days += 28;
                    }
                    break;
                }
            }else{
                if(temp_date1.getMonth() == 1){
                    temp_date1.setMonth(12);
                    temp_date1.setYear(temp_date1.getYear() - 1);
                }else{
                    temp_date1.setMonth(temp_date1.getMonth() - 1);
                }
            }
        }
        return days;
    }

    public boolean equals(TimeSpan ts){
        return this.date1.equals(ts.date1) && this.date2.equals(ts.date2);
    }
    
    @Override
    public int compareTo(TimeSpan ts){
        return this.date1.compareTo(ts.date1);
    }

    public String toString(){
        return this.date1.toString() + "-" + this.date2.toString();
    }

    
    
    public static void test(){
        TimeSpan ts1 = new TimeSpan(new Date(3, 20, 2000), new Date(3,20,2001));

        TimeSpan ts2 = new TimeSpan(new Date(3, 20, 1999), new Date(3,20,2001));

        TimeSpan ts3 = new TimeSpan(new Date(3, 21, 2001), new Date(3,20,2002));

        TimeSpan ts4 = new TimeSpan(new Date(4, 21, 2000), new Date(3,20,2001));

        TimeSpan ts5 = new TimeSpan(new Date(2, 21, 2000), new Date(3,20,2001));

        TimeSpan ts6 = new TimeSpan(new Date(12, 19, 2000), new Date(3,20,2001));

        System.out.println(ts1);
        System.out.println(ts1.getDays());//365
        System.out.println(ts2);
        System.out.println(ts2.getDays());//731
        System.out.println(ts3.getDays()); //364
        System.out.println(ts4.getDays()); //333
        System.out.println(ts5.getDays()); //393
        System.out.println(ts6.getDays()); //91
        System.out.println(ts2.isOverlap(ts3));
        System.out.println(ts3.isOverlap(ts2));
        System.out.println(ts1.isOverlap(ts4));
        System.out.println(ts4.isOverlap(ts1));
    }
}