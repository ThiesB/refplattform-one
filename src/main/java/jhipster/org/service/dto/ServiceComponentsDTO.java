package jhipster.org.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the ServiceComponents entity.
 */
public class ServiceComponentsDTO implements Serializable {

    private Long id;

    @NotNull
    private String serviceName;

    private Long consultingdivisionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getConsultingdivisionId() {
        return consultingdivisionId;
    }

    public void setConsultingdivisionId(Long consultingDivisionId) {
        this.consultingdivisionId = consultingDivisionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ServiceComponentsDTO serviceComponentsDTO = (ServiceComponentsDTO) o;
        if(serviceComponentsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), serviceComponentsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ServiceComponentsDTO{" +
            "id=" + getId() +
            ", serviceName='" + getServiceName() + "'" +
            "}";
    }
}
