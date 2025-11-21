package view;

import interface_adapter.add_pet.AddPetController;
import interface_adapter.add_pet.AddPetPageController;
import interface_adapter.browse_filter.BrowseFilterController;
import interface_adapter.browse_filter.BrowseFilterPageController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.manage_application.ManageApplicationController;
import interface_adapter.manage_application.ManageApplicationsPageController;
import interface_adapter.submit_application.SubmitController;
import interface_adapter.submit_application.SubmitPageController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements PropertyChangeListener {

    private final LoggedInViewModel loggedInViewModel;

    private JButton browseButton = new JButton("Browse Pets");
    private JButton submitApplicationButton = new JButton("Submit Application");
    private JButton changePetButton = new JButton("Add/Remove Pet");
    private JButton viewApplicationsButton = new JButton("View Applications");

    public HomeView(LoggedInViewModel loggedInViewModel,
                    BrowseFilterPageController browsePageController,
                    SubmitPageController submitPageController,
                    AddPetPageController addPetPageController,
                    ManageApplicationsPageController manageAppsController) {

        this.loggedInViewModel = loggedInViewModel;
        loggedInViewModel.addPropertyChangeListener(this);

        setLayout(new GridLayout(5, 1, 10, 10));

        add(browseButton);
        add(submitApplicationButton);
        add(changePetButton);
        add(viewApplicationsButton);

        // Link buttons to controllers
        browseButton.addActionListener(e -> browsePageController.execute());
        submitApplicationButton.addActionListener(e -> submitPageController.execute());
        changePetButton.addActionListener(e -> addPetPageController.execute());
        viewApplicationsButton.addActionListener(e -> manageAppsController.execute());

        propertyChange(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = loggedInViewModel.getState();

        if ("administrator".equals(state.getRole())) {
            browseButton.setVisible(true);
            submitApplicationButton.setVisible(false);

            changePetButton.setVisible(true);
            viewApplicationsButton.setVisible(true);
        } else {
            browseButton.setVisible(true);
            submitApplicationButton.setVisible(true);

            changePetButton.setVisible(false);
            viewApplicationsButton.setVisible(false);
        }
    }
}
