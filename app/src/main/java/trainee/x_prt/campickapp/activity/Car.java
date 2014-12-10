package trainee.x_prt.campickapp.activity;


public class Car {

    private int adressID;
    private int subjectID;
    private int iconID;
    private int adressField;
    private int subjectField;

    public Car(int adressID, int subjectID, int iconID, int adressField, int subjectField) {
        super();
        this.adressID = adressID;
        this.subjectID = subjectID;
        this.iconID = iconID;
        this.adressField = adressField;
        this.subjectField = subjectField;

    }

    public int getAdressID() {
        return adressID;
    }

    public void setAdressID(int adressID) {
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

    public int getAdressField() {
        return adressField;
    }

    public void setAdressField(int adressField) {
        this.adressField = adressField;
    }

    public int getSubjectField() {
        return subjectField;
    }

    public void setSubjectField(int subjectField) {
        this.subjectField = subjectField;
    }
}
