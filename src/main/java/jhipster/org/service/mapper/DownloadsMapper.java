package jhipster.org.service.mapper;

import jhipster.org.domain.*;
import jhipster.org.service.dto.DownloadsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Downloads and its DTO DownloadsDTO.
 */
@Mapper(componentModel = "spring", uses = {CustomerReferencesMapper.class, DocumentTypesMapper.class, LanguagesMapper.class})
public interface DownloadsMapper extends EntityMapper<DownloadsDTO, Downloads> {

    @Mapping(source = "customerReferences.id", target = "customerReferencesId")
    @Mapping(source = "doctype.id", target = "doctypeId")
    @Mapping(source = "language.id", target = "languageId")
    DownloadsDTO toDto(Downloads downloads); 

    @Mapping(source = "customerReferencesId", target = "customerReferences")
    @Mapping(source = "doctypeId", target = "doctype")
    @Mapping(source = "languageId", target = "language")
    Downloads toEntity(DownloadsDTO downloadsDTO);

    default Downloads fromId(Long id) {
        if (id == null) {
            return null;
        }
        Downloads downloads = new Downloads();
        downloads.setId(id);
        return downloads;
    }
}
