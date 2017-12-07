package jhipster.org.repository;

import jhipster.org.domain.Industries;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Industries entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IndustriesRepository extends JpaRepository<Industries, Long> {

}
