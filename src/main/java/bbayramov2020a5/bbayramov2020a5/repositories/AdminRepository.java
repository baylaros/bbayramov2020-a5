package bbayramov2020a5.bbayramov2020a5.repositories;


import bbayramov2020a5.bbayramov2020a5.domain.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends CrudRepository<Admin,Long> {
    @Query("SELECT c FROM Admin c where c.login = :login and c.password=:pass")
    Admin findByCredentials(@Param("login") String login, @Param("pass") String pass);

}