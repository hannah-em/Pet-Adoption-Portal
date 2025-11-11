package entity;

public class Visitor extends User{

    private String legalName;
    private String address;
    private String homeEvi;
    private String occupation;
    private String contactInfo;
    private String adoptionReason;
    private String availability;
    private String prevExp;
    private String age;

    /**
     * Creates a new user with the given non-empty name and non-empty password.
     *
     * @param name     the username
     * @param password the password
     * @throws IllegalArgumentException if the password or name are empty
     */
    public Visitor(String name, String password) {
        super(name, password);
        this.legalName = "";
        this.address = "";
        this.homeEvi = "";
        this.occupation = "";
        this.contactInfo = "";
        this.adoptionReason = "";
        this.availability = "";
        this.prevExp = "";
        this.age = "";
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHomeEvi() {
        return homeEvi;
    }

    public void setHomeEvi(String homeEvi) {
        this.homeEvi = homeEvi;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getAdoptionReason() {
        return adoptionReason;
    }

    public void setAdoptionReason(String adoptionReason) {
        this.adoptionReason = adoptionReason;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getPrevExp() {
        return prevExp;
    }

    public void setPrevExp(String prevExp) {
        this.prevExp = prevExp;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
