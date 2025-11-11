package interface_adapter.browse_filter;

import use_case.browse_filter.*;

public class BrowseFilterController {
    private final BrowseFilterInputBoundary interactor;

    public BrowseFilterController(BrowseFilterInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void onSearch(String species, String gender) {
        BrowseFilterInputData inputData = new BrowseFilterInputData(species, gender);
        interactor.execute(inputData);
    }
}
