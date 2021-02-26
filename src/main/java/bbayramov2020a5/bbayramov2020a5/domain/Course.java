package bbayramov2020a5.bbayramov2020a5.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="COURSE")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long CRN;

    @NotBlank
    @Size(min=10,max=150)
    private String courseDesc;

    @NotBlank
    @Size(min=5,max=25)
    private String courseName;

    @ManyToMany(mappedBy = "enrolledCourses")
    private List<Student> enrolledStudents = new ArrayList<Student>();

    public Long getCRN() {
        return CRN;
    }

    public void setCRN(Long CRN) {
        this.CRN = CRN;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<Student> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public void enrollStudent(Student std) {
        enrolledStudents.add(std);
    }
}

