package bbayramov2020a5.bbayramov2020a5.repositories;

import bbayramov2020a5.bbayramov2020a5.domain.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student,Long> {
    @Query("SELECT c FROM Student c where c.login = :login and c.password=:pass")
    Student findByCredentials(@Param("login") String login, @Param("pass") String pass);
}