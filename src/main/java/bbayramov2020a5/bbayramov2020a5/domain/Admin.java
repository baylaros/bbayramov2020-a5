package bbayramov2020a5.bbayramov2020a5.domain;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String lastName;
    @NotNull
    private String firstName;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String login;
    @NotNull
    @NotBlank
    private String password;

    public Admin(Long id, @NotNull String lastName, @NotNull String firstName, @NotNull String login, @NotNull String password) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
    }

    public Admin(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
