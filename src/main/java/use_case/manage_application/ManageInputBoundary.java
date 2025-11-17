package use_case.manage_application;

import use_case.login.LoginInputData;
import use_case.signup.SignupInputData;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface ManageInputBoundary {

    /**
     * Executes the login use case.
     * @param manageInputData the input data
     */
    void execute(ManageInputData manageInputData);

    // when clicked on an application:
//    /**
//     * Executes the switch to accept/reject view use case.
//     */
//    void switchToLoginView();
}
