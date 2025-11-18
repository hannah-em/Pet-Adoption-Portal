package interface_adapter.browse_filter;

import entity.Pet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class BrowseFilterViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private List<String> pets;

    public void setPets(List<String> pets) {
        this.pets = pets;
        support.firePropertyChange("pets", null, pets);
    }

    public List<String> getPets() {
        return pets;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
