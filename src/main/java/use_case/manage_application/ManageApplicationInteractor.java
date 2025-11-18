package use_case.manage_application;

import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;

public class ManageApplicationInteractor implements ManageApplicationInputBoundary {
    private final userDataAccessObject;
    private final ManageApplicationOutputBoundary managePresenter;

    public ManageApplicationInteractor(ManageApplicationDataAccessInterface userDataAccessInterface,
                           ManageApplicationOutputBoundary manageApplicationOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.managePresenter = manageApplicationOutputBoundary;
    }


    // grab information from database

    // do http request, like for login logout

    @Override
    public void execute() {


//        final String username = loginInputData.getUsername();
//        final String password = loginInputData.getPassword();
//        if (!userDataAccessObject.existsByName(username)) {
//            loginPresenter.prepareFailView(username + ": Account does not exist.");
//        }
//        else {
//            final String pwd = userDataAccessObject.get(username).getPassword();
//            if (!password.equals(pwd)) {
//                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
//            }
//            else {
//
//                final User user = userDataAccessObject.get(loginInputData.getUsername());
//
//                userDataAccessObject.setCurrentUsername(username);
//
//                final LoginOutputData loginOutputData = new LoginOutputData(user.getName());

        /// you are trying to do your logic here, and then define output data,
        /// pass it back into the prepare success view method in outputboundary at last
//                loginPresenter.prepareSuccessView(loginOutputData);
//            }
//        }
    }

}
