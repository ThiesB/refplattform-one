package jhipster.org.repository;

import jhipster.org.domain.Downloads;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Downloads entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DownloadsRepository extends JpaRepository<Downloads, Long> {

}
