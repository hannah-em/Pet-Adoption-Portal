package entity;

import java.util.List;
import java.util.Objects;

public class Pet {
    private final String id;
    private final String name;
    private final String species;
    private final String primaryBreed;
    private final String secondaryBreed;
    private final boolean mixed;
    private final String age;
    private final String gender;
    private final String size;
    private final String coat;
    private final List<String> tags;
    private final List<String> photoUrls;       // ordered list of photo URLs (small/medium/large/full as you choose)
    private final String primaryPhotoMediumUrl; // convenience field (what you showed in Main.java)
    private final Contact contact;              // nested contact info

    public Pet(String id,
               String name,
               String species,
               String primaryBreed,
               String secondaryBreed,
               boolean mixed,
               String age,
               String gender,
               String size,
               String coat,
               List<String> tags,
               List<String> photoUrls,
               String primaryPhotoMediumUrl,
               Contact contact) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.primaryBreed = primaryBreed;
        this.secondaryBreed = secondaryBreed;
        this.mixed = mixed;
        this.age = age;
        this.gender = gender;
        this.size = size;
        this.coat = coat;
        this.tags = tags;
        this.photoUrls = photoUrls;
        this.primaryPhotoMediumUrl = primaryPhotoMediumUrl;
        this.contact = contact;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public String getPrimaryBreed() { return primaryBreed; }
    public String getSecondaryBreed() { return secondaryBreed; }
    public boolean isMixed() { return mixed; }
    public String getAge() { return age; }
    public String getGender() { return gender; }
    public String getSize() { return size; }
    public String getCoat() { return coat; }
    public List<String> getTags() { return tags; }
    public List<String> getPhotoUrls() { return photoUrls; }
    public String getPrimaryPhotoMediumUrl() { return primaryPhotoMediumUrl; }
    public Contact getContact() { return contact; }

    @Override
    public String toString() {
        return "Pet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", primaryBreed='" + primaryBreed + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", primaryPhotoMediumUrl='" + primaryPhotoMediumUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Contact nested static class
    public static class Contact {
        private final String email;
        private final String phone;
        private final Address address;

        public Contact(String email, String phone, Address address) {
            this.email = email;
            this.phone = phone;
            this.address = address;
        }

        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public Address getAddress() { return address; }

        @Override
        public String toString() {
            return "Contact{" + "email='" + email + '\'' + ", phone='" + phone + '\'' + ", address=" + address + '}';
        }
    }

    // Address nested static class
    public static class Address {
        private final String address1;
        private final String city;
        private final String state;
        private final String postcode;
        private final String country;

        public Address(String address1, String city, String state, String postcode, String country) {
            this.address1 = address1;
            this.city = city;
            this.state = state;
            this.postcode = postcode;
            this.country = country;
        }

        public String getAddress1() { return address1; }
        public String getCity() { return city; }
        public String getState() { return state; }
        public String getPostcode() { return postcode; }
        public String getCountry() { return country; }

        @Override
        public String toString() {
            return "Address{" +
                    "address1='" + address1 + '\'' +
                    ", city='" + city + '\'' +
                    ", state='" + state + '\'' +
                    ", postcode='" + postcode + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }
}
