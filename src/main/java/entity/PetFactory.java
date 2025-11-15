package entity;
//Factory for creating Pet Objects
public class PetFactory {
    public Pet create(String name, String type, String breed, int age, String gender,
                                String size, String contact, String description) {
        return new Pet(name, type,breed,age,gender,size,contact, description);
        }
}
