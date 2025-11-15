package use_case.add_pet;
import entity.Pet;

import java.util.List;

//Output Data for the AddPet usecase
public class AddPetOutputData {
    private final List<Pet> gallery;//change data type to gallery soon

    public  AddPetOutputData(List<Pet> gallery){
        this.gallery = gallery;
    }

    public List<Pet> getGallery() {return gallery;}
}
