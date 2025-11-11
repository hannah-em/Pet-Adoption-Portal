package data_access;

import entity.Pet;
import okhttp3.*;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class PetfinderAPIGateway implements PetAPIGatewayInterface {

    private final OkHttpClient client = new OkHttpClient();
    private final String apiToken; // You already successfully retrieved this in your test

    public PetfinderAPIGateway(String apiToken) {
        this.apiToken = apiToken;
    }

    @Override
    public List<Pet> fetchPets(String species, String gender) {
        List<Pet> pets = new ArrayList<>();

        // Build request URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.petfinder.com/v2/animals").newBuilder();
        if (!species.isBlank()) urlBuilder.addQueryParameter("type", species);
        if (!gender.isBlank()) urlBuilder.addQueryParameter("gender", gender);
        urlBuilder.addQueryParameter("limit", "50");

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .addHeader("Authorization", "Bearer " + apiToken)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String jsonString = response.body().string();
            JSONObject root = new JSONObject(jsonString);

            System.out.println("🔍 Raw JSON response:\n" + jsonString);

            JSONArray animalsArray = root.getJSONArray("animals");

            for (int i = 0; i < animalsArray.length(); i++) {
                JSONObject a = animalsArray.getJSONObject(i);

                String id = String.valueOf(a.getInt("id"));
                String name = a.optString("name", "Unknown");
                String speciesRes = a.optString("species", "Unknown");

                JSONObject breeds = a.getJSONObject("breeds");
                String primaryBreed = breeds.optString("primary", "Unknown");
                String secondaryBreed = breeds.optString("secondary", "");
                boolean mixed = breeds.optBoolean("mixed", false);

                String petAge = a.optString("age", "Unknown");
                String petGender = a.optString("gender", "Unknown");
                String petSize = a.optString("size", "Unknown");
                String coat = a.optString("coat", "Unknown");

                // Photos
                List<String> allPhotos = new ArrayList<>();
                String primaryMediumPhoto = null;

                if (a.has("photos")) {
                    JSONArray photos = a.getJSONArray("photos");
                    for (int p = 0; p < photos.length(); p++) {
                        JSONObject photo = photos.getJSONObject(p);
                        if (photo.has("medium"))
                            allPhotos.add(photo.getString("medium"));
                    }
                }

                if (a.has("primary_photo_cropped") && !a.isNull("primary_photo_cropped")) {
                    JSONObject ppc = a.getJSONObject("primary_photo_cropped");
                    primaryMediumPhoto = ppc.optString("medium", null);
                }

                // Contact
                JSONObject contactObj = a.optJSONObject("contact");
                Pet.Contact contact = null;

                if (contactObj != null) {
                    String email = contactObj.optString("email", "");
                    String phone = contactObj.optString("phone", "");

                    JSONObject addressObj = contactObj.optJSONObject("address");
                    Pet.Address address = null;
                    if (addressObj != null) {
                        address = new Pet.Address(
                                addressObj.optString("address1", ""),
                                addressObj.optString("city", ""),
                                addressObj.optString("state", ""),
                                addressObj.optString("postcode", ""),
                                addressObj.optString("country", "")
                        );
                    }
                    contact = new Pet.Contact(email, phone, address);
                }

                pets.add(new Pet(
                        id,
                        name,
                        speciesRes,
                        primaryBreed,
                        secondaryBreed,
                        mixed,
                        petAge,
                        petGender,
                        petSize,
                        coat,
                        new ArrayList<>(), // tags can be added later
                        allPhotos,
                        primaryMediumPhoto,
                        contact
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pets;
    }

    @Override
    public Pet fetchPetById(String petId) {
        // we will implement this after Browse+Filter use case is done
        return null;
    }
}
