package app;

import interface_adapter.manage_application.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ManageApplicationView extends JFrame {

    private final ManageApplicationViewModel viewModel;
    private final JPanel cardsPanel;
    private final JTextArea detailsArea;

    public ManageApplicationView(ManageApplicationViewModel viewModel) {
        super("Manage Applications Test");
        this.viewModel = viewModel;

        setLayout(new BorderLayout());
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Cards panel
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(cardsPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Details area
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setBorder(BorderFactory.createTitledBorder("Application Details"));
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(detailsArea, BorderLayout.SOUTH);
    }

    public void showApplications(List<ApplicationCardViewModel> cards, ManageApplicationPresenter presenter) {
        cardsPanel.removeAll();

        for (ApplicationCardViewModel cardVM : cards) {
            JPanel card = new JPanel(new FlowLayout(FlowLayout.LEFT));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel label = new JLabel(cardVM.application_id + " | " + cardVM.applicant_name + " | " + cardVM.pet_id);
            card.add(label);

            // Click listener
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    var app = viewModel.getCards().stream()
                            .filter(c -> c.application_id.equals(cardVM.application_id))
                            .findFirst()
                            .orElse(null);

                    if (app != null) {
                        var selectedApp = presenter.view_model.getCards().stream()
                                .filter(c -> c.application_id.equals(cardVM.application_id))
                                .findFirst()
                                .orElse(null);

                        // We need entity Application from map
                        entity.Application entityApp = presenter.view_model.getCards().stream()
                                .filter(c -> c.application_id.equals(cardVM.application_id))
                                .map(c -> presenter.view_model.getCards().get(0)) // workaround
                                .findFirst().orElse(null); // we'll fix below

                        // Instead: use map from ViewModel
                        entity.Application application = presenter.view_model.getState().getSelectedApplicationId() == null ? null :
                                null; // placeholder

                        // Better: presenter.view_model.getSelected() updates state via presenter.presentDetails
                        // But we need the entity Application object
                        // We'll assume you pass it from DAO in AppBuilder
                    }
                }
            });

            cardsPanel.add(card);
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    public void showApplicationDetails(ApplicationDetailViewModel detail) {
        if (detail == null) return;

        String text = String.format("""
                Application ID: %s
                Name: %s %s
                Pet ID: %s
                Email: %s
                Phone: %s
                Occupation: %s
                Age: %s
                Availability: %s
                Reason: %s
                Environment: %s
                Previous Experience: %s
                """,
                detail.applicationId,
                detail.firstName, detail.lastName,
                detail.petId,
                detail.email,
                detail.phone,
                detail.occupation,
                detail.age,
                detail.availability,
                detail.reason,
                detail.environment,
                detail.prevExperience
        );

        detailsArea.setText(text);
    }

    public JTextArea getDetailsArea() { return detailsArea; }
}

