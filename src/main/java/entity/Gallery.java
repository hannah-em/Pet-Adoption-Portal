package entity;

import java.util.Collections;
import java.util.List;

public class Gallery {
    private final List<Pet> pets; // immutable list reference (treat as read-only)

    public Gallery(List<Pet> pets) {
        this.pets = pets == null ? Collections.emptyList() : Collections.unmodifiableList(pets);
    }

    public List<Pet> getPets() {
        return pets;
    }

    public int size() {
        return pets.size();
    }

    public boolean isEmpty() {
        return pets.isEmpty();
    }
}
