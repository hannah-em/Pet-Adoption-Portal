package interface_adapter.add_pet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AddPetViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private String message = "";

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void addPropertyChangeListener(PropertyChangeListener l) {
        support.addPropertyChangeListener(l);
    }

    public void firePropertyChanged() {
        support.firePropertyChange("addPetStatus", null, message);
    }
}
