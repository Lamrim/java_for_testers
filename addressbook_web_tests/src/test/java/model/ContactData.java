package model;

import org.openqa.selenium.WebElement;

public record ContactData(String id, String firstName, String lastName, String address, String email1, String mobilePhone) {

    public ContactData() {
        this("", "", "", "", "", "");
    }

    public ContactData withSomeFields(String firstName, String lastName, String address, String email1, String mobilePhone) {
        return new ContactData("", firstName, lastName, address, email1, mobilePhone);
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.email1, this.mobilePhone);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.email1, this.mobilePhone);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.email1, this.mobilePhone);
    }
}
