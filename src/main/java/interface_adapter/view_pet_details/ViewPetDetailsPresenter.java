package interface_adapter.view_pet_details;

import entity.Pet;
import use_case.view_pet_details.ViewPetDetailsOutputBoundary;
import use_case.view_pet_details.ViewPetDetailsOutputData;

public class ViewPetDetailsPresenter implements ViewPetDetailsOutputBoundary {

    private final ViewPetDetailsViewModel viewModel;

    public ViewPetDetailsPresenter(ViewPetDetailsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(ViewPetDetailsOutputData outputData) {
        Pet pet = outputData.getPet();
        ViewPetDetailsState state = new ViewPetDetailsState();

        if (pet == null) {
            state.setErrorMessage("Pet details not found.");
            return;
        } else {
            state.setPetId(pet.getId());
            state.setPetName(pet.getName());
            state.setPetType(pet.getType());
            state.setPetBreed(pet.getBreed());
            state.setPetAge(pet.getAge());
            state.setPetGender(pet.getGender());
            state.setPetSize(pet.getSize());
            state.setPetContact(pet.getContact());
        }
        viewModel.setState(state);
    }
}
