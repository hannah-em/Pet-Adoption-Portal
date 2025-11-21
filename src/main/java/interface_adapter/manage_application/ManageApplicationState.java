package interface_adapter.manage_application;

public class ManageApplicationState {

    private String selectedApplicationId;
    private boolean isLoading = false;
    private String errorMessage;

    public String getSelectedApplicationId() {
        return selectedApplicationId;
    }

    public void setSelectedApplicationId(String selectedApplicationId) {
        this.selectedApplicationId = selectedApplicationId;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
