package use_case.add_pet;

import entity.Pet;

public interface AddPetDataAccessInterface {
    /**
     * Returns true if a pet with this name already exists.
     */
    boolean existsByName(String name);

    /**
     * Saves a new pet to the data store.
     */
    void add(Pet pet);
}
