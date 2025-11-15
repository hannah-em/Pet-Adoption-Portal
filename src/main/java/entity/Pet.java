package entity;

public class Pet {
    private String name;
    private String type;
    private String breed;
    private int age;
    private String gender;
    private String size;
    private String contact;
    private  String description;

    public Pet(String name, String type, String breed, int age, String gender, String size, String contact, String description) {
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getType() {return type;}
    public void setType(String type) {this.type = type;}

    public String getBreed() {return breed;}
    public void setBreed(String breed) {this.breed = breed;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public String getGender() {return gender;}
    public void setGender(String gender) {this.gender = gender;}

    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public String getContact() {return contact;}
    public void setContact(String contact) {this.contact = contact;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

}
