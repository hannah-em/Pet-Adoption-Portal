package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        AppBuilder appBuilder = new AppBuilder();
        JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addViewPetDetails()
                .addViewPetDetailsUseCase()
                .addBrowseFilterView()
                .addSubmitApplicationView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .addSubmitApplicationUseCase()
                .addBrowseFilterUseCase()
                .addHomeView()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}

