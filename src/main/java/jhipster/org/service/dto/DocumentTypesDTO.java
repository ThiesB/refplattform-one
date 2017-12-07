package jhipster.org.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DocumentTypes entity.
 */
public class DocumentTypesDTO implements Serializable {

    private Long id;

    @NotNull
    private String titel;

    @NotNull
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentTypesDTO documentTypesDTO = (DocumentTypesDTO) o;
        if(documentTypesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentTypesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentTypesDTO{" +
            "id=" + getId() +
            ", titel='" + getTitel() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
