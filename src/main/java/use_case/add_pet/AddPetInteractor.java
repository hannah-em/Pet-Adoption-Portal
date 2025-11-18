package use_case.add_pet;

import entity.Pet;
import entity.PetFactory;

public class AddPetInteractor implements AddPetInputBoundary {
    private final PetFactory petFactory;
    private final AddPetDataAccessInterface gallery;
    private final AddPetOutputBoundary presenter;

    public AddPetInteractor(PetFactory petFactory,
                            AddPetDataAccessInterface gallery,
                            AddPetOutputBoundary presenter) {
        this.petFactory = petFactory;
        this.gallery = gallery;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddPetInputData addPetInputData) {
        String petID = addPetInputData.getId();

        if (gallery.existsById(petID)) {
            presenter.prepareFailView("Pet already exists.");
            return;
        }

        // Create pet
        Pet newPet = petFactory.create(
                addPetInputData.getId(),
                addPetInputData.getName(),
                addPetInputData.getType(),
                addPetInputData.getBreed(),
                addPetInputData.getAge(),
                addPetInputData.getGender(),
                addPetInputData.getSize(),
                addPetInputData.getContact()
        );

        gallery.add(newPet);

        presenter.prepareSuccessView(new AddPetOutputData(petID, addPetInputData.getName(), true));
    }
}



