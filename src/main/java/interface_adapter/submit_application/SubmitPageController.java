package interface_adapter.submit_application;

import interface_adapter.ViewManagerModel;

public class SubmitPageController {

    private final ViewManagerModel viewManagerModel;
    private final SubmitViewModel submitViewModel;

    public SubmitPageController(ViewManagerModel viewManagerModel,
                                      SubmitViewModel submitViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.submitViewModel = submitViewModel;
    }

    public void execute() {
        viewManagerModel.setState(submitViewModel.getViewName());
        viewManagerModel.firePropertyChange();
    }
}
