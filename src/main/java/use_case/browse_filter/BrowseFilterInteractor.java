package use_case.browse_filter;

import java.util.List;

import data_access.PetAPIGatewayInterface;
import entity.Pet;

public class BrowseFilterInteractor implements BrowseFilterInputBoundary {
    private final PetAPIGatewayInterface petGateway;
    private final BrowseFilterOutputBoundary presenter;

    public BrowseFilterInteractor(PetAPIGatewayInterface petGateway, BrowseFilterOutputBoundary presenter) {
        this.petGateway = petGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(BrowseFilterInputData inputData) {
        final List<Pet> pets = petGateway.fetchPets(inputData.getType(), inputData.getGender());
        final BrowseFilterOutputData outputData = new BrowseFilterOutputData(pets);
        presenter.present(outputData);
    }
}
