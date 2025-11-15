package use_case.add_pet;

import entity.Pet;

public interface AddPetDataAccessInterface {
    /**
     * Returns true if a pet with this name already exists.
     */
    boolean existsById(String id);

    /**
     * Saves a new pet to the data store.
     */
    void add(Pet pet);
}
