package app;

import data_access.*;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_pet.AddPetPageController;
import interface_adapter.add_pet.AddPetViewModel;
import interface_adapter.browse_filter.BrowseFilterController;
import interface_adapter.browse_filter.BrowseFilterPageController;
import interface_adapter.browse_filter.BrowseFilterPresenter;
import interface_adapter.browse_filter.BrowseFilterViewModel;
import interface_adapter.home.HomeViewModel;
import interface_adapter.logged_in.ChangePasswordController;
import interface_adapter.logged_in.ChangePasswordPresenter;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import view.*;
import interface_adapter.manage_application.*;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.submit_application.SubmitController;
import interface_adapter.submit_application.SubmitPageController;
import interface_adapter.submit_application.SubmitPresenter;
import interface_adapter.submit_application.SubmitViewModel;
import interface_adapter.view_pet_details.ViewPetDetailsController;
import interface_adapter.view_pet_details.ViewPetDetailsPresenter;
import interface_adapter.view_pet_details.ViewPetDetailsViewModel;
import use_case.manage_application.*;
import use_case.browse_filter.BrowseFilterInputBoundary;
import use_case.browse_filter.BrowseFilterInteractor;
import use_case.browse_filter.BrowseFilterOutputBoundary;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
//import use_case.login.LoginInputBoundary; it wasn't working, did login not have input boundary?
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.submit_application.SubmitInputBoundary;
import use_case.submit_application.SubmitInteractor;
import use_case.submit_application.SubmitOutputBoundary;
import use_case.view_pet_details.ViewPetDetailsInputBoundary;
import use_case.view_pet_details.ViewPetDetailsInteractor;
import use_case.view_pet_details.ViewPetDetailsOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final UserFactory userFactory = new UserFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    // set which data access implementation to use, can be any
    // of the classes from the data_access package

    // DAO version using local file storage
    final FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject("users.csv", userFactory);

    // DAO version using a shared external database
    //final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);


    // Application DAO using a database
    final Connection connection;
    final DatabaseApplicationGateway  databaseApplicationGateway;
    final DBApplicationDataAccessObject applicationDataAccessObject;

    private BrowseFilterView browseFilterView;
    private PetAPIGatewayInterface petGateway;
    private BrowseFilterPresenter browseFilterPresenter;
    private ViewPetDetailsViewModel viewPetDetailsViewModel;
    private ViewPetDetailsPresenter viewPetDetailsPresenter;
    private ViewPetDetailsView viewPetDetailsView;
    private ViewPetDetailsController viewPetDetailsController;
    private BrowseFilterViewModel browseFilterViewModel;
    private SubmitView submitView;
    private SubmitViewModel submitViewModel;
    private ManageApplicationView manageApplicationView;
    private ManageApplicationViewModel manageApplicationViewModel;
    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private AddPetViewModel addPetViewModel;
    private HomeViewModel homeViewModel;
    private HomeView homeView;


    public AppBuilder() {
        try {
            this.connection = DatabaseConnection.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.databaseApplicationGateway = new DatabaseApplicationGateway(connection);
        this.applicationDataAccessObject = new DBApplicationDataAccessObject(databaseApplicationGateway);
        this.petGateway = new DatabasePetGateway(connection);

        cardPanel.setLayout(cardLayout);
    }

    public AppBuilder addViewPetDetails() {
        viewPetDetailsViewModel = new ViewPetDetailsViewModel();
        viewPetDetailsView = new ViewPetDetailsView(viewPetDetailsViewModel);
        return this;
    }

    public AppBuilder addViewPetDetailsUseCase() {
        final ViewPetDetailsOutputBoundary viewPetDetailsOutputBoundary = new ViewPetDetailsPresenter(viewPetDetailsViewModel);
        final ViewPetDetailsInputBoundary viewPetDetailsInputBoundary = new ViewPetDetailsInteractor(petGateway, viewPetDetailsOutputBoundary);

        this.viewPetDetailsController = new ViewPetDetailsController(viewPetDetailsInputBoundary);
        return this;
    }

    public AppBuilder addBrowseFilterView() {
        browseFilterViewModel = new BrowseFilterViewModel();
        browseFilterView =
                new BrowseFilterView(browseFilterViewModel, viewPetDetailsViewModel);
        cardPanel.add(browseFilterView, browseFilterView.getViewName());
        return this;
    }

    public AppBuilder addSubmitApplicationView() {
        submitViewModel = new SubmitViewModel();
        submitView = new SubmitView(submitViewModel);
        cardPanel.add(submitView, submitView.getViewName());
        return this;
    }

    public AppBuilder addManageApplicationView() {
        manageApplicationViewModel = new ManageApplicationViewModel();
        manageApplicationView = new ManageApplicationView(manageApplicationViewModel);
        cardPanel.add(manageApplicationView, manageApplicationView.getViewName());
        return this;
    }

    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    public AppBuilder addHomeView() {
        homeViewModel = new HomeViewModel();
//        loggedInViewModel = new LoggedInViewModel(); // needed for role detection

        // Create page-opening controllers (no input required)
        BrowseFilterPageController browseFilterPageController =
                new BrowseFilterPageController(viewManagerModel, browseFilterViewModel);
        SubmitPageController submitPageController =
                new SubmitPageController(viewManagerModel, submitViewModel);
        AddPetPageController addPetPageController = new AddPetPageController(viewManagerModel, addPetViewModel);
        ManageApplicationsPageController manageApplicationsPageController =
                new ManageApplicationsPageController(viewManagerModel, manageApplicationViewModel);

        // Create the actual HomeView
        homeView = new HomeView(
                loggedInViewModel,
                browseFilterPageController,
                submitPageController,
                addPetPageController,
                manageApplicationsPageController
        );

        cardPanel.add(homeView, "home");
        return this;
    }

    //for use case
    public AppBuilder addSubmitApplicationUseCase() {
        final SubmitOutputBoundary submitOutputBoundary =
                new SubmitPresenter(viewManagerModel, submitViewModel, browseFilterViewModel);
        final SubmitInputBoundary submitInputBoundary =
                new SubmitInteractor(userDataAccessObject, applicationDataAccessObject, submitOutputBoundary);

        SubmitController submitController = new SubmitController(submitInputBoundary);
        submitView.setSubmitController(submitController);
        return this;
    }


    public AppBuilder addManageApplicationUseCase() {
        final ManageApplicationOutputBoundary manageApplicationOutputBoundary =
                new ManageApplicationPresenter(manageApplicationViewModel);
        final ManageApplicationInputBoundary manageApplicationInputBoundary =
                new ManageApplicationInteractor(applicationDataAccessObject, manageApplicationOutputBoundary);

        ManageApplicationController manageApplicationController = new ManageApplicationController(manageApplicationInputBoundary);
        manageApplicationView.setApplicationController(manageApplicationController);
        return this;
    }


    public AppBuilder addBrowseFilterUseCase() {
        final BrowseFilterOutputBoundary browseFilterOutputBoundary = new BrowseFilterPresenter(browseFilterViewModel);
        final BrowseFilterInputBoundary browseFilterInputBoundary =
                new BrowseFilterInteractor(petGateway, browseFilterOutputBoundary);

        BrowseFilterController browseFilterController = new BrowseFilterController(browseFilterInputBoundary);
        browseFilterView.setBrowseFilterController(browseFilterController);
        browseFilterView.setDetailsController(viewPetDetailsController);
        return this;
    }

    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel, signupViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary = new ChangePasswordPresenter(viewManagerModel,
                loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        ChangePasswordController changePasswordController = new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }



    public JFrame build() {
        final JFrame application = new JFrame("User Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);
        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChange();

        return application;
    }


}

