package use_case.browse_filter;

import data_access.PetAPIGatewayInterface;
import entity.Pet;
import java.util.List;

public class BrowseFilterInteractor implements BrowseFilterInputBoundary {
    private final PetAPIGatewayInterface petApiGateway;
    private final BrowseFilterOutputBoundary presenter;

    public BrowseFilterInteractor(PetAPIGatewayInterface petApiGateway, BrowseFilterOutputBoundary presenter) {
        this.petApiGateway = petApiGateway;
        this.presenter = presenter;
    }

    @Override
    public void execute(BrowseFilterInputData inputData) {
        // Fetch pets using filters
        List<Pet> pets = petApiGateway.fetchPets(
                inputData.getSpecies(),
                inputData.getGender()
        );

        BrowseFilterOutputData outputData = new BrowseFilterOutputData(pets);
        presenter.presentPets(outputData);
    }
}
