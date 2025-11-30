package app;

import javax.swing.JFrame;

public class Main {
    /**
     *  Main for Pet Adoption Portal.
     *  @param args main
     */
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addSubmitApplicationView()
                .addViewPetDetails()
                .addViewPetDetailsUseCase()
                .addBrowseFilterView()
                .addManageApplicationView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .addSubmitApplicationUseCase()
                .addBrowseFilterUseCase()
                .addAddPetView()
                .addDeletePetView()
                .addAddPetUseCase()
                .addDeletePetUseCase()
                .addHomeView()
                .addLogoutUseCase()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}

