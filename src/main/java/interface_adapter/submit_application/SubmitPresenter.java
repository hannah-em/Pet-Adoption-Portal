package interface_adapter.submit_application;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.submit_application.SubmitOutputBoundary;

public class SubmitPresenter implements SubmitOutputBoundary {
    private final SubmitViewModel submitViewModel;
    private final BrowseFilterViewModel browseFilterViewModel;
    private final ViewManagerModel viewManagerModel;

    public SubmitPresenter(ViewManagerModel viewManagerModel,
                           SubmitViewModel submitViewModel,
                           BrowseFilterViewModel browseFilterViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.submitViewModel = submitViewModel;
        this.browseFilterViewModel = browseFilterViewModel;
    }

    @Override
    public void prepareSuccessView(String successMessage) {
        //show "submit successfully" to user
        final SubmitState submitState = submitViewModel.getState();
        submitState.setSuccess(successMessage);
        submitViewModel.firePropertyChange();
        //On success, switch back to BrowserFilter view
        viewManagerModel.setState(BrowseFilterViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SubmitState submitState = submitViewModel.getState();
        submitState.setError(errorMessage);
        submitViewModel.firePropertyChange();
    }

    @Override
    public void switchToBrowserFilterView() {
        //for the cancel button
        viewManagerModel.setState(BrowseFilterViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
