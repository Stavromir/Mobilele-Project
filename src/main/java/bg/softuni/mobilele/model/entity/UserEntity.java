package bg.softuni.mobilele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean active;

    public UserEntity() {
        super();
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }


    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Boolean getActive() {
        return active;
    }

    public UserEntity setActive(Boolean active) {
        this.active = active;
        return this;
    }
}
