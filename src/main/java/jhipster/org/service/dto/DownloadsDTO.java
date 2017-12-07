package jhipster.org.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Downloads entity.
 */
public class DownloadsDTO implements Serializable {

    private Long id;

    @NotNull
    private String titel;

    private String bereich;

    @NotNull
    private String anlageURL;

    private Long customerReferencesId;

    private Long doctypeId;

    private Long languageId;

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

    public String getBereich() {
        return bereich;
    }

    public void setBereich(String bereich) {
        this.bereich = bereich;
    }

    public String getAnlageURL() {
        return anlageURL;
    }

    public void setAnlageURL(String anlageURL) {
        this.anlageURL = anlageURL;
    }

    public Long getCustomerReferencesId() {
        return customerReferencesId;
    }

    public void setCustomerReferencesId(Long customerReferencesId) {
        this.customerReferencesId = customerReferencesId;
    }

    public Long getDoctypeId() {
        return doctypeId;
    }

    public void setDoctypeId(Long documentTypesId) {
        this.doctypeId = documentTypesId;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languagesId) {
        this.languageId = languagesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DownloadsDTO downloadsDTO = (DownloadsDTO) o;
        if(downloadsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), downloadsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DownloadsDTO{" +
            "id=" + getId() +
            ", titel='" + getTitel() + "'" +
            ", bereich='" + getBereich() + "'" +
            ", anlageURL='" + getAnlageURL() + "'" +
            "}";
    }
}
