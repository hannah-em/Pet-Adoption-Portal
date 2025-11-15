package view;

import interface_adapter.submit_application.SubmitViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SubmitView extends JPanel implements ActionListener, PropertyChangeListener {
    final String viewName = "Fill the Blanks below to Create an Application";

    private final SubmitViewModel submitViewModel;
    private final JTextField legalNameInputField = new JTextField(15);
    private final JTextField occupationInputField = new JTextField(15);
    private final JTextField addressInputField = new JTextField(100);
    private final JTextField homeEviInputField = new JTextField(200);


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
