package use_case.submit_application;

import entity.Application;

public interface SubmitApplicationDataAccessInterface {
    void save(Application application);

    Application get(String username, String petId);

}
