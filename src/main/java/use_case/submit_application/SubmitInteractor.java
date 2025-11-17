package use_case.submit_application;

import entity.Application;
import entity.Visitor;

public class SubmitInteractor implements SubmitInputBoundary {
    private final SubmitApplicationDataAccessInterface applicationDataAccessObject;
    private final SubmitOutputBoundary submitPresenter;
    private final Application application;

    public SubmitInteractor(SubmitApplicationDataAccessInterface applicationDataAccessInterface,
                            SubmitOutputBoundary submitOutputBoundary,
                            Application application) {
        this.applicationDataAccessObject = applicationDataAccessInterface;
        this.submitPresenter = submitOutputBoundary;
        this.application = application;
    }

    @Override
    public void execute(SubmitInputData submitInputData) {
        if ("".equals(submitInputData.getUsername())||
                "".equals(submitInputData.getFirstname())||
                "".equals(submitInputData.getLastname())||
                "".equals(submitInputData.getAge())||
                "".equals(submitInputData.getOccupation())||
                "".equals(submitInputData.getAddress())||
                "".equals(submitInputData.getHomeEvi())||
                "".equals(submitInputData.getTel())||
                "".equals(submitInputData.getEmail())||
                "".equals(submitInputData.getReason())||
                "".equals(submitInputData.getPrevExp())||
                "".equals(submitInputData.getAvailability())) {
            submitPresenter.prepareFailView("Some required fields are missing");
        }
        final Visitor visitor = (Visitor)DAO.get(submitInputData.getUsername());
        final Application application = new Application(visitor, submitInputData.getPetId(),
                submitInputData.getReason(), submitInputData.getHomeEvi(),
                submitInputData.getAvailability(), submitInputData.getPrevExp());
        applicationDataAccessObject.save(application);
        }

    @Override
    public void switchToBrowserFilterView() {
        submitPresenter.switchToBrowserFilterView();
    }
}
