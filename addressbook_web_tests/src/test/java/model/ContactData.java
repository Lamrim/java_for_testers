package model;

public record ContactData(String firstName, String lastName, String address, String email1, String mobilePhone) {

    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(firstName, this.lastName, this.address, this.email1, this.mobilePhone);
    }

    public ContactData withSomeFields(String firstName, String lastName, String address, String email1, String mobilePhone) {
        return new ContactData(firstName, lastName, address, email1, mobilePhone);
    }

}
