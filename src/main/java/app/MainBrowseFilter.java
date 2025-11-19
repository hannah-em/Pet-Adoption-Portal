package app;

import data_access.*;
import interface_adapter.browse_filter.*;
import use_case.browse_filter.*;
import view.BrowseFilterView;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Pet;

public class MainBrowseFilter {
    public static void main(String[] args) throws Exception {

        // 1. Connect to database
        Connection conn = DatabaseConnection.connect();
        DatabasePetGateway dbGateway = new DatabasePetGateway(conn);

        // 2. Populate DB if memory map is empty
        if (dbGateway.getPetCount() == 0) {
            System.out.println("🐾 DB empty — importing pets...");

            String[] speciesList = {"dog", "cat", "rabbit", "bird", "horse"};
            int totalImported = 0;

            for (String species : speciesList) {

                String token = PetfinderAuthService.getAccessToken();
                PetfinderAPIGateway apiGateway = new PetfinderAPIGateway(token);

                System.out.println("🐾 Fetching: " + species);

                var pets = apiGateway.fetchPets(species, "");

                for (var pet : pets) {
                    dbGateway.addPet(pet);      // adds to DB and updates map
                }

                totalImported += pets.size();
                System.out.println("   → " + pets.size() + " added.");
            }

            System.out.println("🐾 Imported " + totalImported + " total pets!");
        }

        // 3. Show results
        System.out.println("📦 Map size: " + dbGateway.getPetCount());
        dbGateway.getPetMap().values().forEach(p -> System.out.println(p.getType()));

        // 4. Run GUI
        BrowseFilterViewModel vm = new BrowseFilterViewModel();
        BrowseFilterPresenter presenter = new BrowseFilterPresenter(vm);
        BrowseFilterInteractor interactor = new BrowseFilterInteractor(dbGateway, presenter);
        BrowseFilterController controller = new BrowseFilterController(interactor);

        new BrowseFilterView(controller, vm);
    }
}