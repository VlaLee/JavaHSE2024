public class User {

    private final int id;
    private final String name;
    private final String company;
    private final String username;
    private final String email;
    private final String address;
    private final String zip;
    private final String state;
    private final String country;
    private final String phone;
    private final String photo;

    public User(int id, String name, String company, String username,
                String email, String address, String zip, String state,
                String country, String phone, String photo) {

        this.id = id;
        this.name = name;
        this.company = company;
        this.username = username;
        this.email = email;
        this.address = address;
        this.zip = zip;
        this.state = state;
        this.country = country;
        this.phone = phone;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return String.format("""
                id: %d
                name: %s
                company: %s
                username: %s
                email: %s
                address: %s
                zip: %s
                state: %s
                country: %s
                phone: %s
                photo: %s""", id, name, company, username, email,
                              address, zip, state, country, phone, photo);
    }
}
