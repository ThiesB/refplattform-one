package jhipster.org.service.mapper;

import jhipster.org.domain.*;
import jhipster.org.service.dto.ConsultingDivisionDTO;

import org.mapstruct.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Mapper for the entity ConsultingDivision and its DTO ConsultingDivisionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ConsultingDivisionMapper extends EntityMapper<ConsultingDivisionDTO, ConsultingDivision> {





    default ConsultingDivision fromId(Long id) {
        if (id == null) {
            return null;
        }
        ConsultingDivision consultingDivision = new ConsultingDivision();
        consultingDivision.setId(id);
        return consultingDivision;
    }
}
