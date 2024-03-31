package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;
    public String firstname;
    public String lastname;
    public String address;
    public String email;
    public String email2;
    public String email3;
    @Column(name = "mobile")
    public String mobilePhone;

    public String middlename = "";
    public String nickname = "";
    public String company = "";
    public String title = "";
    @Column(name = "home")
    public String homePhone;
    @Column(name = "work")
    public String workPhone;
    public String phone2 = "";
    public String fax = "";
    public String homepage = "";


    public ContactRecord() {
    }

    public ContactRecord(
            int id,
            String firstname,
            String lastname,
            String address,
            String email,
            String email2,
            String email3,
            String mobilePhone,
            String homePhone,
            String workPhone) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.mobilePhone = mobilePhone;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
    }
}
