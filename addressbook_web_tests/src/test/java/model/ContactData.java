package model;

public record ContactData(
        String id,
        String firstName,
        String lastName,
        String address,
        String email1,
        String mobilePhone,
        String photo,
        String homePhone,
        String workPhone,
        String secondaryPhone) {

    public ContactData() {
        this("", "", "", "", "",
                "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address,
                this.email1, this.mobilePhone, this.photo, this.homePhone, this.workPhone, this.secondaryPhone);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address,
                this.email1, this.mobilePhone, this.photo, this.homePhone, this.workPhone, this.secondaryPhone);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address,
                this.email1, this.mobilePhone, this.photo, this.homePhone, this.workPhone, this.secondaryPhone);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address,
                this.email1, this.mobilePhone, this.photo, "", "", "");
    }

    public ContactData withEmail1(String email1) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                email1, this.mobilePhone, this.photo, this.homePhone, this.workPhone, this.secondaryPhone);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email1, mobilePhone, this.photo, this.homePhone, this.workPhone, this.secondaryPhone);
    }

    public ContactData withWorkPhone(String workPhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email1, mobilePhone, this.photo, this.homePhone, workPhone, this.secondaryPhone);
    }

    public ContactData withHomePhone(String homePhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email1, mobilePhone, this.photo, homePhone, this.workPhone, this.secondaryPhone);
    }

    public ContactData withSecondaryPhone(String secondaryPhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email1, mobilePhone, this.photo,this.homePhone, this.workPhone, secondaryPhone);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email1, this.mobilePhone, "", this.homePhone, this.workPhone, this.secondaryPhone);
    }
}
