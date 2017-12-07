package jhipster.org.repository;

import jhipster.org.domain.ConsultingDivision;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the ConsultingDivision entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConsultingDivisionRepository extends JpaRepository<ConsultingDivision, Long> {



}
