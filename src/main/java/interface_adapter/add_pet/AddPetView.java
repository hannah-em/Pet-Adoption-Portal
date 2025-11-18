package interface_adapter.add_pet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AddPetView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Add Pet";

    private final JTextField nameInputField = new JTextField(15);
    private final JTextField typeInputField = new JTextField(15);
    private final JTextField breedInputField = new JTextField(15);
    private final JTextField ageInputField = new JTextField(15);
    private final JTextField genderInputField = new JTextField(15);
    private final JTextField sizeInputField = new JTextField(15);
    private final JTextField contactInputField = new JTextField(15);
    private final JTextArea petIDInputArea = new JTextArea(3, 15);

    private final JButton submitButton = new JButton("Add Pet");

    // The controller for this view
    private AddPetController addPetController;

    public AddPetView() {
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 5, 5));

        formPanel.add(new JLabel("Pet Name:"));
        formPanel.add(nameInputField);

        formPanel.add(new JLabel("Type:"));
        formPanel.add(typeInputField);

        formPanel.add(new JLabel("Breed:"));
        formPanel.add(breedInputField);

        formPanel.add(new JLabel("Age:"));
        formPanel.add(ageInputField);

        formPanel.add(new JLabel("Gender:"));
        formPanel.add(genderInputField);

        formPanel.add(new JLabel("Size:"));
        formPanel.add(sizeInputField);

        formPanel.add(new JLabel("Contact:"));
        formPanel.add(contactInputField);

        formPanel.add(new JLabel("Pet ID :"));
        formPanel.add(new JScrollPane(petIDInputArea));

        submitButton.addActionListener(this);

        add(formPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }

    public String getViewName() {
        return viewName;
    }

    public void setAddPetController(AddPetController controller) {
        this.addPetController = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (addPetController != null) {
            addPetController.execute(
                    petIDInputArea.getText(),
                    nameInputField.getText(),
                    typeInputField.getText(),
                    breedInputField.getText(),
                    ageInputField.getText(),
                    genderInputField.getText(),
                    sizeInputField.getText(),
                    contactInputField.getText()
            );
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // This is where the presenter can update UI elements.
        // Example: showing success or error messages
        if (evt.getPropertyName().equals("addPetStatus")) {
            JOptionPane.showMessageDialog(this, evt.getNewValue().toString());
        }
    }
}
