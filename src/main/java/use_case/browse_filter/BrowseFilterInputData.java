package use_case.browse_filter;

public class BrowseFilterInputData {
    private final String species;
    private final String gender;

    public BrowseFilterInputData(String species, String gender) {
        this.species = species;
        this.gender = gender;
    }

    public String getSpecies() { return species; }
    public String getGender() { return gender; }
}
