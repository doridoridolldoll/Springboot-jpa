package springboot.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import springboot.jpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.id=:id")
    Member findOne(@Param("id") Long id);

    @Query("select m from Member m where m.name =:name")
    List<Member> findByName(@Param("name") String name);

    @Query("select m from Member m where m.email =:email")
    Member findByEmail(@Param("email") String email);
}
