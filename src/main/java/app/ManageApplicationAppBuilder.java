package app;

import data_access.*;
import interface_adapter.manage_application.*;
import use_case.manage_application.*;
import entity.Application;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ManageApplicationAppBuilder {

    private final DatabaseApplicationGateway gateway;
    private final DBApplicationDataAccessObject dao;
    private final ManageApplicationViewModel viewModel;
    private final ManageApplicationPresenter presenter;
    private final ManageApplicationInteractor interactor;
    private final ManageApplicationController controller;
    private final ManageApplicationView view;

    public ManageApplicationAppBuilder(Connection conn) {
        this.gateway = new DatabaseApplicationGateway(conn);
        this.dao = new DBApplicationDataAccessObject(gateway);

        this.viewModel = new ManageApplicationViewModel();
        this.presenter = new ManageApplicationPresenter(viewModel);
        this.interactor = new ManageApplicationInteractor(dao, presenter);
        this.controller = new ManageApplicationController(interactor);

        this.view = new ManageApplicationView(viewModel);

        // Preload applications map for presenter usage
        Map<String, Application> appMap = dao.getAllApplications().stream()
                .collect(Collectors.toMap(Application::getApplicationId, a -> a));

        // store map in viewModel or in presenter if needed
        // presenter.view_model.setApplicationsMap(appMap); // optional
    }

    public void buildAndShow() {
        // Load applications
        controller.loadApplications();

        // Show cards in view
        List<ApplicationCardViewModel> cards = viewModel.getCards();
        if (cards != null) {
            view.showApplications(cards, presenter);
        }

        // Setup click listeners manually to show details
        Map<String, Application> appMap = dao.getAllApplications().stream()
                .collect(Collectors.toMap(Application::getApplicationId, a -> a));

        for (ApplicationCardViewModel cardVM : cards) {
            entity.Application app = appMap.get(cardVM.application_id);
            if (app != null) {
                view.getDetailsArea().setText(""); // start blank
                // For testing, we can select first card automatically
                presenter.presentDetails(app);
                view.showApplicationDetails(viewModel.getSelected());
            }
        }

        SwingUtilities.invokeLater(() -> view.setVisible(true));
    }
}
