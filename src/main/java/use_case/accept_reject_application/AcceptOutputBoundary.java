package use_case.accept_reject_application;

import use_case.login.LoginInputData;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface AcceptOutputBoundary {

    /**
     * Executes the login use case.
     * @param loginInputData the input data
     */
    void execute(LoginInputData loginInputData);
}
