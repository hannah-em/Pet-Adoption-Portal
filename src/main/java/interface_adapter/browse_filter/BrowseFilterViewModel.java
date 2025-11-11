package interface_adapter.browse_filter;

import java.util.ArrayList;
import java.util.List;

public class BrowseFilterViewModel {
    private List<String> petsList = new ArrayList<>();

    public List<String> getPetsList() {
        return petsList;
    }

    public void setPetsList(List<String> petsList) {
        this.petsList = petsList;
        // In a full app, you’d notify listeners here (for now, just print)
        System.out.println("🐾 Found pets: ");
        petsList.forEach(System.out::println);
    }
}
