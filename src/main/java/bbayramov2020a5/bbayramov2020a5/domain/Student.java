package bbayramov2020a5.bbayramov2020a5.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="STUDENT")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Size(min=3,max=15)
    private String firstName;

    @NotBlank
    @Size(min=5,max=15)
    private String lastName;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String login;
    @NotNull
    @NotBlank
    private String password;
    @ManyToMany
    private List<Course> enrolledCourses = new ArrayList<Course>();


    public Student(Long id, @NotBlank @Size(min = 3, max = 15) String firstName, @NotBlank @Size(min = 5, max = 15) String lastName, @NotNull @NotBlank String login, @NotNull @NotBlank String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }
    public Student(){

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void enrollToCourse(Course course) {
        enrolledCourses.add(course);
    }
}
