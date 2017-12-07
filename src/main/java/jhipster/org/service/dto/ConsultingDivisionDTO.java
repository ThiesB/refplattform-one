package jhipster.org.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ConsultingDivision entity.
 */
public class ConsultingDivisionDTO implements Serializable {

    private Long id;

    @NotNull
    private String divisionName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ConsultingDivisionDTO consultingDivisionDTO = (ConsultingDivisionDTO) o;
        if(consultingDivisionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), consultingDivisionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ConsultingDivisionDTO{" +
            "id=" + getId() +
            ", divisionName='" + getDivisionName() + "'" +
            "}";
    }
}
