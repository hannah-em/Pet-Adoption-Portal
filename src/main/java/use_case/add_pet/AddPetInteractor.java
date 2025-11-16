package use_case.add_pet;

import entity.Pet;
import entity.PetFactory;

public class AddPetInteractor implements AddPetInputBoundary {
    private final PetFactory petFactory;
    private final AddPetDataAccessInterface gallery;

    public AddPetInteractor(PetFactory petFactory, AddPetDataAccessInterface gallery) {
        this.petFactory = petFactory;
        this.gallery = gallery;
    }

    @Override
    public void execute(AddPetInputData addPetInputData) {
        String petID = addPetInputData.getId();

        // Check if pet already exists
        if (gallery.existsById(petID)) {
            System.out.println("Pet already exists in gallery.");
            return;
        }

        // Create new Pet entity using factory
        String petName = addPetInputData.getId();
        String petType = addPetInputData.getType();
        String breed = addPetInputData.getBreed();
        String age = addPetInputData.getAge();
        String gender = addPetInputData.getGender();
        String size = addPetInputData.getSize();
        String contact = addPetInputData.getContact();


        Pet newPet = petFactory.create(petID,petName, petType, breed, age, gender, size, contact);

        // Save to data access layer
        gallery.add(newPet);

        System.out.println("Pet added successfully!");
    }
}

