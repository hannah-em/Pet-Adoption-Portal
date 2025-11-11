package interface_adapter.browse_filter;

import entity.Pet;
import use_case.browse_filter.BrowseFilterOutputBoundary;
import use_case.browse_filter.BrowseFilterOutputData;

import java.util.List;
import java.util.stream.Collectors;

public class BrowseFilterPresenter implements BrowseFilterOutputBoundary {
    private final BrowseFilterViewModel viewModel;

    public BrowseFilterPresenter(BrowseFilterViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentPets(BrowseFilterOutputData outputData) {
        List<String> petSummaries = outputData.getPets().stream()
                .map(p -> p.getName() + " | " + p.getPrimaryBreed() + " | " + p.getAge() + " | " + p.getGender())
                .collect(Collectors.toList());

        viewModel.setPetsList(petSummaries);
    }
}
