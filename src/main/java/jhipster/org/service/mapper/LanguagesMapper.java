package jhipster.org.service.mapper;

import jhipster.org.domain.*;
import jhipster.org.service.dto.LanguagesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Languages and its DTO LanguagesDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LanguagesMapper extends EntityMapper<LanguagesDTO, Languages> {

    

    

    default Languages fromId(Long id) {
        if (id == null) {
            return null;
        }
        Languages languages = new Languages();
        languages.setId(id);
        return languages;
    }
}
