package mantis.model;

public record UserData(String username, String email, String password) {

    public UserData() {
        this("", "", "");
    }

    public UserData withUsername(String username) {
        return new UserData(username, this.email, this.password);
    }

    public UserData withEmail(String email) {
        return new UserData(this.username, email, this.password);
    }

    public UserData withPassword(String password) {
        return new UserData(this.username, this.email, password);
    }

}
