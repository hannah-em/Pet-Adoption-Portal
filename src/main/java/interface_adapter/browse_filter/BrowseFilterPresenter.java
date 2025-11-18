package interface_adapter.browse_filter;

import entity.Pet;
import use_case.browse_filter.BrowseFilterOutputBoundary;
import use_case.browse_filter.BrowseFilterOutputData;

import java.util.List;
import java.util.stream.Collectors;

public class BrowseFilterPresenter implements BrowseFilterOutputBoundary {

    private final BrowseFilterViewModel viewModel;

    public BrowseFilterPresenter(BrowseFilterViewModel vm) {
        this.viewModel = vm;
    }

    @Override
    public void present(BrowseFilterOutputData outputData) {
        List<Pet> pets = outputData.getPets();

        List<String> petStrings = pets.stream()
                .map(p -> p.getName() + " | " + p.getType() + " | " + p.getBreed() + " | " + p.getGender())
                .collect(Collectors.toList());

        viewModel.setPets(petStrings);
    }
}
