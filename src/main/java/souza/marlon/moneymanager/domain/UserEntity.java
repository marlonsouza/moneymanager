package souza.marlon.moneymanager.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotEmpty
    @Column(unique = true)
    private String documentIdentity;

    @NotEmpty
    private String name;

    private String address;

    private String phone;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @Column(name = "type")
    private UserType userType;

    public UserEntity() {
    }

    public UserEntity(UUID id, String documentIdentity, String name, String address, String phone, String email, String password, UserType userType) {
        this.id = id;
        this.documentIdentity = documentIdentity;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(documentIdentity, that.documentIdentity) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && userType == that.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, documentIdentity, name, address, phone, email, password, userType);
    }

    public UUID getId() {
        return id;
    }

    public @NotEmpty String getDocumentIdentity() {
        return documentIdentity;
    }

    public @NotEmpty String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public @NotEmpty String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDocumentIdentity(@NotEmpty String documentIdentity) {
        this.documentIdentity = documentIdentity;
    }

    public void setName(@NotEmpty String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public void setPassword(@NotEmpty String password) {
        this.password = password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
