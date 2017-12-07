package jhipster.org.service.mapper;

import jhipster.org.domain.*;
import jhipster.org.service.dto.ServiceComponentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ServiceComponents and its DTO ServiceComponentsDTO.
 */
@Mapper(componentModel = "spring", uses = {ConsultingDivisionMapper.class})
public interface ServiceComponentsMapper extends EntityMapper<ServiceComponentsDTO, ServiceComponents> {

    @Mapping(source = "consultingdivision.id", target = "consultingdivisionId")
    ServiceComponentsDTO toDto(ServiceComponents serviceComponents); 

    @Mapping(source = "consultingdivisionId", target = "consultingdivision")
    ServiceComponents toEntity(ServiceComponentsDTO serviceComponentsDTO);

    default ServiceComponents fromId(Long id) {
        if (id == null) {
            return null;
        }
        ServiceComponents serviceComponents = new ServiceComponents();
        serviceComponents.setId(id);
        return serviceComponents;
    }
}
