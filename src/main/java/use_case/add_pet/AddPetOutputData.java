package use_case.add_pet;

public class AddPetOutputData {

    private final String id;
    private final String name;
    private final boolean success;

    public AddPetOutputData(String id, String name, boolean success) {
        this.id = id;
        this.name = name;
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isSuccess() {
        return success;
    }
}

