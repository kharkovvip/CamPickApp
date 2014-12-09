package trainee.x_prt.campickapp.activity;


public class Car {

    private int adressID;
    private int subjectID;
    private int iconID;
    private int adressField;
    private int subjectField;

    public Car(int adressID, int subjectID, int iconID, int adressField, int subjectField) {
        super();
        this.adressID = this.adressID;
        this.subjectID = this.subjectID;
        this.iconID = iconID;
        this.adressField = this.adressField;
        this.adressField = this.subjectField;

    }

    public String getAdressID() {
        return adressID;
    }

    public void setAdressID(String adressID) {
        this.adressID = adressID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public String getAdressField() {
        return adressField;
    }

    public void setAdressField(String adressField) {
        this.adressField = adressField;
    }
}
