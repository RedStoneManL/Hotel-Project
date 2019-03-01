
/**
 * Write a description of class Date here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Date implements Comparable<Date>
{
    // instance variables - replace the example below with your own
    private int day;
    private int month;
    private int year;

    /**
     * Constructor for objects of class Date
     */
    public Date(int month, int day, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * set the day to the given day
     *
     * @param  day the target day to be changed
     */
    public void setDay(int day)
    {
        this.day = day;
    }

    /**
     * set the month to the given month
     *
     * @param  month the target month to be changed
     */
    public void setMonth(int month)
    {
        this.month = month;
    }

    /**
     * set the year to the given year
     *
     * @param  year the target year to be changed
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * return the day
     *
     * @return the day 
     */
    public int getDay()
    {
        return day;
    }

    /**
     * return the month
     *
     * @return the month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * return the year
     *
     * @return the year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * check the validity of the date
     * 
     * @param date the date which to be examined
     */
    public static boolean dateValidation(Date date){
        int year = date.getYear();
        int month = date.getMonth();
        int day = date.getDay();
        if(month <= 0 || month > 12){
            return false;
        }
        switch(month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
            if(day <= 0 || day > 31){
                return false;
            }
            break;
            case 4:
            case 6:
            case 9:
            case 11:
            if(day <= 0 || day > 30){
                return false;
            }
            break;
            case 2:
            if(year%4 == 0){
                if(day <= 0 || day > 29){
                    return false;
                }
            }else{
                if(day <= 0 || day > 28){
                    return false;
                }
            }
            break;
        }
        return true;
    }

    public boolean equals(Date date){
        return this.year == date.year && this.month == date.month && this.day == date.day;
    }
    
    @Override
    public int compareTo(Date date){
        int year = this.year - date.year;
        int month = this.month - date.month;
        int day = this.day - date.day;
        if(year == 0){
            if(month == 0){
                return day;
            }else{
                return month;
            }
        }else{
            return year;
        }
    }
    
    public String toString(){
        return String.format("%02d", this.month) + "/" + String.format("%02d", this.day) + "/" + this.year;
    }
}

