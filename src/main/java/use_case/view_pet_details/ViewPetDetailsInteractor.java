package use_case.view_pet_details;

import data_access.PetAPIGatewayInterface;
import entity.Pet;

public class ViewPetDetailsInteractor implements ViewPetDetailsInputBoundary {
    private final PetAPIGatewayInterface petGateway;
    private final ViewPetDetailsOutputBoundary presenter;

    public ViewPetDetailsInteractor(PetAPIGatewayInterface petGateway,
                                    ViewPetDetailsOutputBoundary presenter) {
        this.petGateway = petGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(ViewPetDetailsInputData inputData) {
        final Pet pet = petGateway.fetchPetById(inputData.getPetId());

        final ViewPetDetailsOutputData outputData = new ViewPetDetailsOutputData(pet);
        presenter.present(outputData);
    }

    @Override
    public void switchToApplicationView() {
        presenter.switchToApplicationView();
    }
}
