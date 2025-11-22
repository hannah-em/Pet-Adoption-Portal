package use_case.delete_pet;

public class DeletePetOutputData {
    private String message;
    private boolean success;

    public DeletePetOutputData(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {return message;}
    public boolean isSuccess() {return success;}
}
