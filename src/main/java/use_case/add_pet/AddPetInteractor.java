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
        String petName = addPetInputData.getName();

        // Check if pet already exists
        if (gallery.existsByName(petName)) {
            System.out.println("Pet already exists in gallery.");
            return;
        }

        // Create new Pet entity using factory
        String petType = addPetInputData.getType();
        String breed = addPetInputData.getBreed();
        int age = addPetInputData.getAge();
        String gender = addPetInputData.getGender();
        String size = addPetInputData.getSize();
        String contact = addPetInputData.getContact();
        String description = addPetInputData.getDescription();

        Pet newPet = petFactory.create(petName, petType, breed, age, gender, size, contact, description);

        // Save to data access layer
        gallery.add(newPet);

        System.out.println("Pet added successfully!");
    }
}

