package data_access;

import entity.Pet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePetGateway implements PetAPIGatewayInterface {
    private final Connection conn;

    public DatabasePetGateway(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Pet> fetchPets(String type, String gender) {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pets WHERE type LIKE ? AND gender LIKE ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type.isEmpty() ? "%" : type);
            stmt.setString(2, gender.isEmpty() ? "%" : gender);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                pets.add(new Pet(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("breed"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("size"),
                        rs.getString("contact")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
    }

    @Override
    public Pet fetchPetById(String petId) {
        String sql = "SELECT * FROM pets WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, petId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Pet(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("breed"),
                        rs.getString("age"),
                        rs.getString("gender"),
                        rs.getString("size"),
                        rs.getString("contact")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addPet(Pet pet) {
        String sql = "INSERT OR REPLACE INTO pets (id, name, type, breed, age, gender, size, contact) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pet.getId());
            stmt.setString(2, pet.getName());
            stmt.setString(3, pet.getType());
            stmt.setString(4, pet.getBreed());
            stmt.setString(5, pet.getAge());
            stmt.setString(6, pet.getGender());
            stmt.setString(7, pet.getSize());
            stmt.setString(8, pet.getContact());
            stmt.executeUpdate();
            System.out.println("✅ Added pet to DB: " + pet.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePet(String id) {
        String sql = "DELETE FROM pets WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


