import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private String fullName;
    private String phoneNumber;
    private String email;

    public Contact(String fullName, String phoneNumber, String email) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact contact)) return false;
        return fullName.equals(contact.fullName) && phoneNumber.equals(contact.phoneNumber) && email.equals(contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, phoneNumber, email);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("----- Person Information -----").append('\n');
        sb.append("First Name: ").append(fullName).append('\n');
        sb.append("Phone Number: ").append(fullName).append('\n');
        sb.append("email: ").append(email).append('\n');
        return sb.toString();
    }
}

