
package entity;
import java.util.ArrayList;
import java.util.List;

public class Application {

    // Applicant Information
    private Visitor user;

    // Which Pet to adopt - !!!! Might change to ID !!!!!;
    private Pet pet; // !! Might change to pet id !!!! //

    //
    private String reason_to_adopt;
    private String home_environment; // describe, apartment, small, big, etc.
    // give hint on how to fill out each field
    private int availability;
    // indicate by 0-10, 10 means someone is going to be home all the time with the pet
    // or by hours per week or smth;
    private String previous_experience;
    private List<String> personal_information;

    // helper method, to gather all info from the user
    public List<String> getPersonalInfo() {
        // get all personal info from visitor
        String name = user.getName();
        String email = user.getEmail();
        String phone_number = user.getPhoneNumber();
        String age = user.getAge();
        String occupation = user.getOccupation();

        List<String> personal_information = new ArrayList<>();
        personal_information.add(name);
        personal_information.add(email);
        personal_information.add(phone_number);
        personal_information.add(age);
        personal_information.add(occupation);

        return personal_information;
    }

    /**
     * Creates a new application
     * @throws IllegalArgumentException if the password or name are empty
     */
    //
    public Application(Visitor user, Pet pet, String reason_to_adopt, String home_environment, int availability, String previous_experience) {
        List<String> personal_information = getPersonalInfo();
        // loop through the list, see if any is empty, if yes throw an error
        for (String info : personal_information) {
            if ("".equals(info)) {
                throw new IllegalArgumentException("This information cannot be empty." +
                        "Please fill out your personal information in setting.");
            }
        }
        // set all attributes
        this.pet = pet;
        this.personal_information = personal_information;
        this.reason_to_adopt = reason_to_adopt;
        this.home_environment = home_environment;
        this.availability = availability;
        this.previous_experience = previous_experience;

    }


    // Getter methods
    public List<String> getPersonalInformation() {
        return personal_information;
    }
    public String getReason() {
        return reason_to_adopt;
    }
    public String getHomeEnvironment() {return home_environment;}
    public String getPreviousExperience() {return previous_experience;}
    public Pet getPet() {return pet;}
    public int getAvailability() {return availability;}
    public Visitor getVisitor() {return user;}

}
