package use_case.submit_application;

public interface SubmitOutputBoundary {

    void prepareFailView(String errorMessage);

    void switchToBrowserFilterView();

}
