package use_case.submit_application;

import entity.Application;
import entity.User;

public interface SubmitApplicationDataAccessInterface {
    void save(Application application);

    Application get(String username, String petId);

    User get(String username);

}
