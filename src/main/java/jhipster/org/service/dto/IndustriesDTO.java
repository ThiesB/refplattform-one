package jhipster.org.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Industries entity.
 */
public class IndustriesDTO implements Serializable {

    private Long id;

    @NotNull
    private String industryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IndustriesDTO industriesDTO = (IndustriesDTO) o;
        if(industriesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), industriesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "IndustriesDTO{" +
            "id=" + getId() +
            ", industryName='" + getIndustryName() + "'" +
            "}";
    }
}
