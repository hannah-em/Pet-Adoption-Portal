package interface_adapter.manage_application;

import use_case.manage_application.ManageApplicationOutputBoundary;
import use_case.manage_application.ManageApplicationOutputData;
import entity.Application;

import java.util.List;
import java.util.stream.Collectors;

public class ManageApplicationPresenter implements ManageApplicationOutputBoundary {

    private final ManageApplicationViewModel view_model;

    public ManageApplicationPresenter(ManageApplicationViewModel view_model) {
        this.view_model = view_model;
    }

    @Override
    public void present(ManageApplicationOutputData outputData) {

        // Convert entity list → card list
        List<ApplicationCardViewModel> cards =
                outputData.getApplications().stream()
                        .map(application -> new ApplicationCardViewModel(
                                application.getApplicationId(),
                                application.getFirstName(), application.getLastName(),
                                application.getPetId()
                        ))
                        .collect(Collectors.toList());

        view_model.setCards(cards);
    }

    // Called when user selects a card
    public void presentDetails(Application application) {

        ApplicationDetailViewModel detail = new ApplicationDetailViewModel(
                application.getApplicationId(),
                application.getFirstName(),
                application.getLastName(),
                application.getPetId(),
                application.getEmail(),
                application.getPhoneNumber(),
                application.getOccupation(),
                application.getAge(),
                application.getAvailability(),
                application.getReason(),
                application.getHomeEnvironment(),
                application.getPreviousExperience()
        );

        view_model.setSelected(detail);

        view_model.getState().setSelectedApplicationId(application.getApplicationId());
    }
}
