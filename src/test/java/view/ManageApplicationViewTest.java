package view;

import entity.Application;
import interface_adapter.manage_application.ManageApplicationController;
import interface_adapter.manage_application.ManageApplicationViewModel;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManageApplicationViewTest {

    @Test
    void constructor_callsControllerExecute() {
        class FakeController extends ManageApplicationController {
            boolean called = false;
            FakeController() { super(() -> {}); }
            @Override public void execute() { called = true; }
        }

        FakeController controller = new FakeController();
        ManageApplicationViewModel vm = new ManageApplicationViewModel();

        new ManageApplicationView(controller, vm);

        assertTrue(controller.called);
    }

    @Test
    void propertyChange_updatesCards() {
        ManageApplicationController controller = new ManageApplicationController(() -> {});
        ManageApplicationViewModel vm = new ManageApplicationViewModel();
        ManageApplicationView view = new ManageApplicationView(controller, vm);

        Application a = new Application(
                "id1", "John", "Doe", "j@mail.com", "123",
                "25", "Student", "pet1",
                "Reason", "Env", "Avail", "Exp"
        );

        PropertyChangeEvent evt =
                new PropertyChangeEvent(vm, "applications", null, List.of(a));

        view.propertyChange(evt);

        // verify components were added
        JPanel container = (JPanel) ((JScrollPane) view.getComponent(0)).getViewport().getView();
        assertEquals(2, container.getComponentCount()); // card + spacer
    }

    @Test
    void createApplicationCard_displaysCorrectInfo() {
        ManageApplicationView view =
                new ManageApplicationView(new ManageApplicationController(() -> {}),
                        new ManageApplicationViewModel());

        Application a = new Application(
                "id1", "John", "Doe", "j@mail.com", "123",
                "25", "Student", "pet1",
                "Reason", "Env", "Avail", "Exp"
        );

        JPanel card = view.createApplicationCard(a);

        assertTrue(card.getComponent(0) instanceof JLabel);
        JLabel first = (JLabel) card.getComponent(0);
        assertEquals("Application ID: id1", first.getText());
    }

    @Test
    void getViewName_returnsCorrectString() {
        ManageApplicationView view =
                new ManageApplicationView(new ManageApplicationController(() -> {}),
                        new ManageApplicationViewModel());
        assertEquals("Manage Application", view.getViewName());
    }
}
