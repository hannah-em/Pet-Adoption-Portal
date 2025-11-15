package data_access;

import entity.Pet;
import use_case.add_pet.AddPetDataAccessInterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryPetDataAccessObject implements AddPetDataAccessInterface {
    private final Map<String, Pet> pets = new HashMap<>();


    // Optional: expose for debugging/testing
    public Collection<Pet> getAllPets() {
        return pets.values();
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public void add(Pet pet) {

    }
}

