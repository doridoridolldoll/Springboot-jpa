package springboot.jpa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.jpa.Entity.Member;

@Repository
@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.id=:id ")
    Member findOne(@Param("id") Long id);

}
