package use_case.add_pet;

import java.util.List;

import entity.Pet;
import entity.PetFactory;

public class AddPetInteractor implements AddPetInputBoundary {
    private final PetFactory petFactory;
    private final AddPetDataAccessInterface petRepo;
    private final AddPetOutputBoundary presenter;

    public AddPetInteractor(PetFactory petFactory,
                            AddPetDataAccessInterface petRepo,
                            AddPetOutputBoundary presenter) {
        this.petFactory = petFactory;
        this.petRepo = petRepo;
        this.presenter = presenter;
    }

    @Override
    public void execute(AddPetInputData inputData) {

        if (inputData.getName().isEmpty()
                || inputData.getType().isEmpty()
                || inputData.getBreed().isEmpty()
                || inputData.getAge().isEmpty()
                || inputData.getGender().isEmpty()
                || inputData.getSize().isEmpty()
                || inputData.getContact().isEmpty()) {

            presenter.prepareFailView("All fields must be filled out.");
        }

        final String petId = petRepo.generateId(inputData.getType());

        final List<String> attributes = List.of(
                inputData.getName(),
                inputData.getGender(),
                inputData.getAge(),
                inputData.getType(),
                inputData.getSize(),
                inputData.getBreed()
        );

        if (petRepo.existsPet(attributes)) {
            presenter.prepareFailView("A pet already exists.");
        }

        final Pet pet = petFactory.create(
                petId,
                inputData.getName(),
                inputData.getType(),
                inputData.getBreed(),
                inputData.getAge(),
                inputData.getGender(),
                inputData.getSize(),
                inputData.getContact()
        );

        petRepo.add(pet);

        final AddPetOutputData outputData =
                new AddPetOutputData(petId, "Pet added successfully!", true);

        presenter.prepareSuccessView(outputData);
    }
}
