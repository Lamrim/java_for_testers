package model;

public record ContactData(
        String id,
        String firstName,
        String lastName,
        String address,
        String email,
        String email2, String email3, String mobilePhone,
        String photo,
        String homePhone,
        String workPhone
) {

    public ContactData() {
        this("", "", "", "", "",
                "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address,
                this.email, this.email2, this.email3, this.mobilePhone, this.photo, this.homePhone, this.workPhone);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address,
                this.email, this.email2, this.email3, this.mobilePhone, this.photo, this.homePhone, this.workPhone);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address,
                this.email, this.email2, this.email3, this.mobilePhone, this.photo, this.homePhone, this.workPhone);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address,
                this.email, this.email2, this.email3, this.mobilePhone, this.photo, "", "");
    }

    public ContactData withEmail1(String email1) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                email1, this.email2, this.email3, this.mobilePhone, this.photo, this.homePhone, this.workPhone);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email, email2, this.email3, this.mobilePhone, this.photo, this.homePhone, this.workPhone);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email, this.email2, email3, this.mobilePhone, this.photo, this.homePhone, this.workPhone);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email, this.email2, this.email3, mobilePhone, this.photo, this.homePhone, this.workPhone);
    }

    public ContactData withWorkPhone(String workPhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email, this.email2, this.email3, mobilePhone, this.photo, this.homePhone, workPhone);
    }

    public ContactData withHomePhone(String homePhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email, this.email2, this.email3, mobilePhone, this.photo, homePhone, this.workPhone);
    }

//    public ContactData withSecondaryPhone(String secondaryPhone) {
//        return new ContactData(this.id, this.firstName, this.lastName, this.address,
//                this.email, this.email2, this.email3, mobilePhone, this.photo, this.homePhone, this.workPhone, secondaryPhone);
//    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address,
                this.email, this.email2, this.email3, this.mobilePhone, "", this.homePhone, this.workPhone);
    }
}
