public class Student {

    //student details
    private String firstName;
    private String lastName;
    private String address;
    private int gender;
    private String stream;
    private String contact;
    private long rollNum;
    public final int GENDER_FEMALE = 1;
    public final int GENDER_MALE = 2;
    public final int GENDER_OTHER = 3;

    //subject details
    private double subJava;
    private double subEnglish;
    private double subMaths;
    private double subMacro;

    public Student(){

    }


    public Student(String firstName, String lastName, String address, int gender, String stream, String contact, long rollNum, double subJava, double subEnglish, double subMacro, double subMaths){
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.stream = stream;
        this.contact = contact;
        this.rollNum = rollNum;
        this.subJava = subJava;
        this.subEnglish = subEnglish;
        this.subMacro = subMacro;
        this.subMaths = subMaths;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public double getSubJava() {
        return subJava;
    }

    public void setSubJava(double subJava) {
        this.subJava = subJava;
    }

    public double getSubEnglish() {
        return subEnglish;
    }

    public void setSubEnglish(double subEnglish) {
        this.subEnglish = subEnglish;
    }

    public double getSubMaths() {
        return subMaths;
    }

    public void setSubMaths(double subMaths) {
        this.subMaths = subMaths;
    }

    public double getSubMacro() {
        return subMacro;
    }

    public void setSubMacro(double subMacro) {
        this.subMacro = subMacro;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public long getRollNum() {
        return rollNum;
    }

    public void setRollNum(long rollNum) {
        this.rollNum = rollNum;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
