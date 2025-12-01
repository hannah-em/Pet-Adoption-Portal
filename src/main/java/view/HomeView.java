package view;

import interface_adapter.add_pet.AddPetController;
import interface_adapter.add_pet.AddPetPageController;
import interface_adapter.delete_pet.DeletePetPageController;
import interface_adapter.delete_pet.DeletePetController;
import interface_adapter.browse_filter.BrowseFilterController;
import interface_adapter.browse_filter.BrowseFilterPageController;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.manage_application.ManageApplicationController;
import interface_adapter.manage_application.ManageApplicationsPageController;
import interface_adapter.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeView extends JPanel implements PropertyChangeListener {

    private final LoggedInViewModel loggedInViewModel;

    private JButton browseButton = new JButton("Browse Pets");
    private JButton addPetButton = new JButton("Add Pet");
    private JButton deletePetButton = new JButton("Delete Pet");
    private JButton viewApplicationsButton = new JButton("View Applications");
    private JButton logoutButton = new JButton("Logout");

    public HomeView(LoggedInViewModel loggedInViewModel,
                    BrowseFilterPageController browsePageController,
                    AddPetPageController addPetPageController,
                    DeletePetPageController deletePetPageController,
                    ManageApplicationsPageController manageAppsController,
                    LogoutController logoutController) {

        this.loggedInViewModel = loggedInViewModel;
        loggedInViewModel.addPropertyChangeListener(this);

        setLayout(new GridLayout(5, 1, 10, 10));

        add(browseButton);
        add(addPetButton);
        add(deletePetButton);
        add(viewApplicationsButton);
        add(logoutButton);

        // Link buttons to controllers
        browseButton.addActionListener(e -> browsePageController.execute());
        addPetButton.addActionListener(e -> addPetPageController.execute());
        deletePetButton.addActionListener(e -> deletePetPageController.execute());
        viewApplicationsButton.addActionListener(e -> manageAppsController.execute());
        logoutButton.addActionListener(e -> logoutController.execute());

        propertyChange(null);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = loggedInViewModel.getState();

        if ("administrator".equals(state.getRole())) {
            browseButton.setVisible(true);

            addPetButton.setVisible(true);
            deletePetButton.setVisible(true);
            viewApplicationsButton.setVisible(true);
            logoutButton.setVisible(true);
        } else {
            browseButton.setVisible(true);

            addPetButton.setVisible(false);
            deletePetButton.setVisible(false);
            viewApplicationsButton.setVisible(false);
            logoutButton.setVisible(true);
        }
    }
}

