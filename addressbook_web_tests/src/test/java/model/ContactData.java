package model;

public record ContactData(String firstName ) {

    public ContactData() {
        this("");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(firstName);
    }

}
