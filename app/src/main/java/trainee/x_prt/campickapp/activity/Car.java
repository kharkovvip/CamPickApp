package trainee.x_prt.campickapp.activity;



public class Car {

    private String make;
    private int year;
    private int iconID;
    private String condition;

    public Car(String make, int year, int iconID, String condition) {
        super();
        this.make = make;
        this.year = year;
        this.iconID = iconID;
        this.condition = condition;

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
