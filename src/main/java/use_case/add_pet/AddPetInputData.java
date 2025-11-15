package use_case.add_pet;

public class AddPetInputData {
    private String name;
    private String type;
    private String breed;
    private int age;
    private String gender;
    private String size;
    private String contact;
    private  String description;

    public AddPetInputData(String name, String type, String breed, int age, String gender, String size,
                           String contact, String description) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
        this.gender = gender;
        this.size = size;
        this.contact = contact;
        this.description = description;
    }

    public String getName() {return name;}
    public String getType() {return type;}
    public String getBreed() {return breed;}
    public int getAge() {return age;}
    public String getGender() {return gender;}
    public String getSize() {return size;}
    public String getContact() {return contact;}
    public String getDescription() {return description;}
}
